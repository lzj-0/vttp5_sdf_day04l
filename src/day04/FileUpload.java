package day04;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileUpload {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String host = "localhost";
        int port = 5000;
        String filename = "abc.txt";

        if (args.length > 0) {
            host = args[0].split(":")[0];
            port = Integer.parseInt(args[0].split(":")[1]);
            filename = args[1];
        }
        
        Socket socket = new Socket(host, port);

        System.out.println(">>> connected to server");

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        File f = new File(filename);

        if (!(f.exists() && f.isFile())) {
            System.err.printf("%s is not a file\n", filename);
            System.exit(-1);
        }

        dos.writeUTF(filename);
        System.out.println("file name sent to server");
        dos.writeLong(f.length());
        System.out.println("file size sent to server");
        
        InputStream fis = new FileInputStream(filename);
        for (int bytes = 0; bytes < f.length(); bytes++) {
            dos.write(fis.read());
        }
        System.out.println("file contents sent to server");
        fis.close();

        dos.flush();
        os.flush();
        dos.close();
        os.close();
        socket.close();

    }
}
