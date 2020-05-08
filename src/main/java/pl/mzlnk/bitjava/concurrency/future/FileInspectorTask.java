package pl.mzlnk.bitjava.concurrency.future;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@AllArgsConstructor
public class FileInspectorTask implements Callable<Integer> {

    private File directory;
    private String keyword;

    @Override
    public Integer call() {
        File[] files = this.directory.listFiles();
        if(files == null) {
            return 0;
        }

        int result = 0;

        List<Future<Integer>> subResults = new ArrayList<>();

        for(File file : files) {
            if(file.isDirectory()) {
                FutureTask<Integer> subTask = new FutureTask<>(new FileInspectorTask(file, this.keyword));
                subResults.add(subTask);

                new Thread(subTask).start();
            } else {
                result += inspect(file);
            }
        }

        for(var subResult : subResults) {
            try {
                result += subResult.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private int inspect(File file) {
        int result = 0;

        try (Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains(this.keyword)) {
                    result++;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
