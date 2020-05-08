package pl.mzlnk.bitjava.concurrency.semaphore;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessTask implements Runnable {

    private Semaphore semaphore;
    private int taskId;

    private final Object lock = new Object();

    public ProcessTask(Semaphore semaphore, int taskId) {
        this.semaphore = semaphore;
        this.taskId = taskId;
    }

    @Override
    @SneakyThrows(InterruptedException.class)
    public void run() {
        synchronized (lock) {
            System.out.printf("Task [%d] tries to acquire permit...\n", this.taskId);
            this.semaphore.acquire();
        }

        System.out.printf("Task [%d] acquired permit! Available permits: %d\n", this.taskId, this.semaphore.availablePermits());

        Thread.sleep(ThreadLocalRandom.current().nextLong(2000) + 1000);

        this.semaphore.release();
        System.out.printf("Task [%d] finished - release permit. Available permits: %d\n", this.taskId, this.semaphore.availablePermits());
    }

}
