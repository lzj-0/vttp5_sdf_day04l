package day04;

import java.security.SecureRandom;

public class ClientThread implements Runnable {

    private String message;

    public ClientThread(String msg) {
        this.message = msg;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        // THread's body
        int sleepTime = (new SecureRandom()).nextInt(5) + 3;
        try {
            Thread.sleep(sleepTime + 1000);
        } catch (InterruptedException e) { }
        System.out.printf("THREAD[%s]: %d message: %s\n", threadName, sleepTime, this.message);
    }

}
