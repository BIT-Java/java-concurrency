package pl.mzlnk.bitjava.concurrency.blockingqueue;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {

        final int QUEUE_SIZE = 10;
        final int THREADS_SIZE = 100;

        BlockingQueue<File> queue = new ArrayBlockingQueue<>(QUEUE_SIZE);
        File searchDirectory = new File("data/search-dir");
        String keyword = "risus";

        System.out.println(searchDirectory.getAbsolutePath());

        DirectoryInspectorTask directoryInspectorTask = new DirectoryInspectorTask(queue, searchDirectory);
        new Thread(directoryInspectorTask).start();

        for(int i = 0; i < THREADS_SIZE; i++) {
            new Thread(new FileInspectorTask(queue, keyword)).start();
        }
    }

}
