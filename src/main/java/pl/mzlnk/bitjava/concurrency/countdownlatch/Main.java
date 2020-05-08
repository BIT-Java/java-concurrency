package pl.mzlnk.bitjava.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(5);

        for(int i = 0; i < 5; i++) {
            new Thread(new InitTask(downLatch, i)).start();
        }

        for(int i = 0; i < 5; i++) {
            new Thread(new ProcessTask(downLatch, i)).start();
        }

    }


}
