package uz.pdp.homework1;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Integer> futures = new Vector<>();


        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            int s = i;

            Future<Integer> future = executorService.submit(() -> {
                int sum = 0;

                for (int j = s * 100000; j < (s + 1) * 100000; j++) sum += j;
                return sum;
            });

            while (!future.isDone()) {
                futures.add(future.get());
                break;
            }
        }

        Integer sum = 0;
        for (Integer future : futures) {
            sum += future;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));
        System.out.println("Result: " + sum);
    }
}
