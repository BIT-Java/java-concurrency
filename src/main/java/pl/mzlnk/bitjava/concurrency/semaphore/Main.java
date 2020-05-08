package pl.mzlnk.bitjava.concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for(int i = 0; i < 10; i++) {
            new Thread(new ProcessTask(semaphore, i)).start();
        }

    }

}
