package uz.pdp.homework4;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static ExecutorService service = Executors.newFixedThreadPool(3);
    static long count = 0;

    public static void main(String[] args) throws InterruptedException {

        service.execute(() -> {
            long sum = 0;
            for (int i = 0; i < 100000000; i++) {
                sum += i;
            }

            try (
                    FileWriter writer = new FileWriter("C:\\PDP\\Man\\src\\uz\\pdp\\homework4\\a1.txt")
            ) {
                writer.write(String.valueOf(sum));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        service.execute(() -> {
            long sum = 0;
            for (int i = 100000000; i < 200000000; i++) {
                sum += i;
            }

            try (
                    FileWriter writer = new FileWriter("C:\\PDP\\Man\\src\\uz\\pdp\\homework4\\a2.txt")
            ) {
                writer.write(String.valueOf(sum));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        service.execute(() -> {
            long sum = 0;
            for (int i = 200000000; i < 300000000; i++) {
                sum += i;
            }

            try (
                    FileWriter writer = new FileWriter("C:\\PDP\\Man\\src\\uz\\pdp\\homework4\\a3.txt")
            ) {
                writer.write(String.valueOf(sum));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        service.awaitTermination(1400, TimeUnit.MILLISECONDS);

        try (
                BufferedReader reader = new BufferedReader(new FileReader("C:\\PDP\\Man\\src\\uz\\pdp\\homework4\\a1.txt"));
                BufferedReader reader1 = new BufferedReader(new FileReader("C:\\PDP\\Man\\src\\uz\\pdp\\homework4\\a2.txt"));
                BufferedReader reader2 = new BufferedReader(new FileReader("C:\\PDP\\Man\\src\\uz\\pdp\\homework4\\a3.txt"));
        ) {

            String line1 = reader.readLine();
            String line2 = reader1.readLine();
            String line3 = reader2.readLine();

            long sum = Long.parseLong(line1) + Long.parseLong(line2) + Long.parseLong(line3);
            count = sum;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(count);
    }
}
