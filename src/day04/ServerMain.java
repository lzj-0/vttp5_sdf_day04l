package day04;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        
        // default port number
        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);

        }
        System.out.printf(">> Listening on port %d\n", port);

        // create the server
        ServerSocket server = new ServerSocket(port);

        while (true) {
            // Wait for incoming connection, block (means will not go to the next statement until there is a client connection)
            System.out.println("Waiting for connection");
            Socket conn = server.accept(); // returns a socket

            System.out.println("Got a client connection");

            // Get the input stream
            InputStream is = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            // get output stream
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // blocking read
            String msg = br.readLine();

            System.out.printf(">>> MSG from client: %s\n", msg);

            msg = msg.toUpperCase() + "\n";

            bw.write(msg);
            bw.flush();

            conn.close();
            server.close();
        }
        

    }
}
