package pl.mzlnk.bitjava.concurrency.blockingqueue;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class FileInspectorTask implements Runnable {

    private BlockingQueue<File> queue;
    private String keyword;

    @Override
    public void run() {
        try {
            while(true) {
                File file = queue.take();
                if(file == DirectoryInspectorTask.SEARCH_END) {
                    queue.put(file);
                    return;
                }

                inspect(file);
            }
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void inspect(File file) throws IOException {
        try(Scanner sc = new Scanner(file)) {
            int lineNo = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains(this.keyword)) {
                    System.out.printf("[%d] %s - line %d\n", Thread.currentThread().getId(), file.getPath(), lineNo);
                    lineNo++;
                }
            }
        }
    }

}
