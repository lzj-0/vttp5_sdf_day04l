package day04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServerMain {
    public static void main(String[] args) {
        
        //Create a fixed thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(2); // 2 workers

        for (int i= 0; i < 10; i++) { // 10 tasks to do
            // create a work for the thread to perform
            System.out.println(">>> MAIN: Creating thread" + i);
            ClientThread t = new ClientThread("No " + i);
            // pass the work to the pool
            thrPool.submit(t); // t.run() is not correct, it will only run on the main thread
        }

        System.out.println("\n\n\n===================\nMAIN: Main thread completed");

    }
}
