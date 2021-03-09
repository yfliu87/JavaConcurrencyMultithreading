package callable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss MM/dd/YYYY");

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> responses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<String> response = executorService.submit(new Task(i));
            responses.add(response);

            System.out.println("Task " + i + " submitted at: "
                                   + simpleDateFormat.format(new Date(System.currentTimeMillis())));
        }

        for (Future<String> response : responses) {
            System.out.println("checking response: " + response.get()
                                   + " at: " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
        }

        executorService.shutdown();
    }
}
