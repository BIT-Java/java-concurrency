package pl.mzlnk.bitjava.concurrency.countdownlatch;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class InitTask implements Runnable {

    private CountDownLatch downLatch;
    private int taskId;

    @Override
    @SneakyThrows(InterruptedException.class)
    public void run() {
        System.out.printf("Init task [%d] starting...\n", this.taskId);
        Thread.sleep(ThreadLocalRandom.current().nextLong(3000) + 1000);
        System.out.printf("Init task [%d] completed!\n", this.taskId);
        this.downLatch.countDown();
    }

}
