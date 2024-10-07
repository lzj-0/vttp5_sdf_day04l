package day04;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        // create a socket to connect to the server
        Socket socket = new Socket("localhost", 5000);
        
        System.out.println(">>> connected to server");

        Console cons = System.console();

        // Read a message
        String msg = cons.readLine(">>> ");
        msg = msg + "\n";

        // output stream
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // input stream
        InputStream is = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        // Write the message to the server
        bw.write(msg + "\n");
        bw.flush(); // foce it into the network

        // read the result back in
        msg = br.readLine();

        System.out.printf(">>> FROM SERVER: %s\n", msg);

        // close the connection
        socket.close();

    }
}
