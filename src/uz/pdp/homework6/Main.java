package uz.pdp.homework6;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("All")
public class Main {
    static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        String[] arr = new String[]{"book", "pen", "ruler", "note", "laptop", "window"};

        File file = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework6\\a.txt");

        if (file.exists()) file.delete();

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int index = 0;
        for (int i = 0; i < 10_000; i++) {
            try (
                    FileOutputStream os = new FileOutputStream(file, true);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            ) {
                if (index == 6) {
                    index = 0;
                }
                bw.write(arr[index++]);
                bw.newLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1 - Count book
                    2 - Count pen
                    """);

            switch (scanner.nextInt()) {
                case 1 -> book();
                case 2 -> pen();
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void book() {
        service.execute(() -> {
            int count = 0;
            File file = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework6\\a.txt");
            File file1 = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework6\\b.txt");

            if (file1.exists()) file1.delete();

            try {
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            try (
                    FileInputStream fis = new FileInputStream(file);
                    FileOutputStream fos = new FileOutputStream(file1)
            ) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("book")) {
                        count++;
                    }
                }
                writer.write(String.valueOf(count));
                System.out.println("Counter of books: " + count);
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void pen() {
        service.execute(() -> {
            int count = 0;
            File file = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework6\\a.txt");
            File file1 = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework6\\b.txt");

            if (file1.exists()) file1.delete();

            try {
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            try (
                    FileInputStream fis = new FileInputStream(file);
                    FileOutputStream fos = new FileOutputStream(file1)
            ) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("pen")) {
                        count++;
                    }
                }
                writer.write(String.valueOf(count));
                System.out.println("Counter of pens: " + count);
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
