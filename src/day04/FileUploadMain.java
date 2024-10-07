package day04;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadMain {

    public static void main(String[] args) throws IOException {
        int port = 5000;
        String dir = "files";

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
            dir = args[1];
        }

        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        System.out.printf(">> Listening on port %d\n", port);

        ServerSocket server = new ServerSocket(port);

        Socket conn = server.accept();

        InputStream is = conn.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        String filename = dis.readUTF();
        long filesize = dis.readLong();

        File f = new File(dir + "/" + filename);

        if (!f.exists()) {
            boolean res = f.createNewFile();

            if (res) {
                FileOutputStream os = new FileOutputStream(f);

                for (int i = 0; i < filesize; i++) {
                    os.write(dis.read());
                }

                os.flush();
                os.close();
                System.out.println("file successfully saved");
            }
        }

        dis.close();
        is.close();
        conn.close();
        server.close();

    }
}
