package day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class ConnectionHandler implements Runnable {

    private final Socket clientConn;

    public ConnectionHandler(Socket conn) {
        this.clientConn = conn;
    }

    @Override
    public void run() {
        String name= Thread.currentThread().getName();

            // Get the input stream
            InputStream is;
            try {
                is = clientConn.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(reader);

                // get output stream
                OutputStream os = clientConn.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(writer);

                String msg = br.readLine();
                System.out.printf("[%s]: >>> MSG from client: %s\n", name, msg);

                msg = "timestamp=%s, msg=%s \n".formatted((new Date()).toString(), msg.toUpperCase());

                bw.write(msg);
                bw.flush();

                clientConn.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
