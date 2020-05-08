package pl.mzlnk.bitjava.concurrency.future;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {
        File searchDirectory = new File("data/search-dir");
        String keyword = "risus";

        FutureTask<Integer> task = new FutureTask<>(new FileInspectorTask(searchDirectory, keyword));
        new Thread(task).start();

        try {
            System.out.println("Result: " + task.get());
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
