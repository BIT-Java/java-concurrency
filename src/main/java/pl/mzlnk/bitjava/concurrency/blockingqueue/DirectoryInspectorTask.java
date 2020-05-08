package pl.mzlnk.bitjava.concurrency.blockingqueue;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class DirectoryInspectorTask implements Runnable {

    public static final File SEARCH_END = new File("");

    private BlockingQueue<File> queue;
    private File searchDirectory;

    @Override
    public void run() {
        try {
            inspect(this.searchDirectory);
            queue.put(SEARCH_END);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void inspect(File directory) throws InterruptedException {
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                inspect(file);
            } else {
                this.queue.put(file);
            }
        }
    }

}
