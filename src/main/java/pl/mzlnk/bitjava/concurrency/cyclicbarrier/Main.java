package pl.mzlnk.bitjava.concurrency.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("All tasks finished. Collecting data..."));

        for(int i = 0; i < 5; i++) {
            new Thread(new ProcessTask(barrier, i)).start();
        }

    }

}
