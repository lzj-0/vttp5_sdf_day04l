package day04;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServerSocketMain {
    public static void main(String[] args) throws IOException {
        
        // default port number
        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);

        }
        System.out.printf(">> Listening on port %d\n", port);

        String name = Thread.currentThread().getName();

        // create a threadpool
        ExecutorService thrPool = Executors.newFixedThreadPool(3);


        // create the server
        ServerSocket server = new ServerSocket(port);

        int connections = 0;

        while (true) {
            // Wait for incoming connection, block (means will not go to the next statement until there is a client connection)
            System.out.printf("[%s] %d Waiting for connection\n", name, connections);
            connections++;
            
            // wait for incoming connection, block
            Socket conn = server.accept(); // returns a socket

            System.out.printf("[%s] Got a client connection\n", name);

            ConnectionHandler handler = new ConnectionHandler(conn);

            thrPool.submit(handler);

            System.out.printf("[%s] Submitted connection handler to thread pool\n", name);
        }
        

    }
}
