package pl.mzlnk.bitjava.concurrency.countdownlatch;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class ProcessTask implements Runnable {

    private CountDownLatch downLatch;
    private int taskId;

    @Override
    @SneakyThrows(InterruptedException.class)
    public void run() {
        this.downLatch.await();
        System.out.printf("Processing task [%d]...\n", this.taskId);
    }

}
