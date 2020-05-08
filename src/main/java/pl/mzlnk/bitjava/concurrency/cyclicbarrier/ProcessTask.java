package pl.mzlnk.bitjava.concurrency.cyclicbarrier;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class ProcessTask implements Runnable {

    private CyclicBarrier barrier;
    private int taskId;

    @Override
    @SneakyThrows(InterruptedException.class)
    public void run() {
        System.out.printf("Starting task [%d]...\n", this.taskId);
        Thread.sleep(ThreadLocalRandom.current().nextLong(3000) + 1000);
        System.out.printf("Task [%d] finished!\n", this.taskId);

        try {
            this.barrier.await();
        } catch (BrokenBarrierException e) {
            System.out.println("Barrier broken!");
        }
    }

}
