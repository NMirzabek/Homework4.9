package uz.pdp.homework3;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("All")
public class Main {
    static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {
        String[] str = new String[]{"book", "pen", "ruler", "note", "laptop", "window"};

        Random random = new Random();

        File file = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework3\\a.txt");

        if (file.exists()) file.delete();
        file.createNewFile();


        for (int i = 0; i < 1000; i++) {

            try (
                    FileOutputStream fos = new FileOutputStream(file, true);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos))
            ) {
                bw.write(str[random.nextInt(str.length)]);
                bw.newLine();
            } catch (Exception e) {
                throw new RuntimeException("Error!");
            }
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1 - Remove duplicates of books
                    2 - Remove duplicates of pens
                    """);
            switch (scanner.nextInt()) {
                case 1 -> books();
                case 2 -> pens();
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void books() {
        service.execute(() -> {
            String book = "book";

            File file = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework3\\a.txt");
            File file1 = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework3\\b.txt");
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
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

                String line;
                while ((line = br.readLine()) != null) {
                    line = line.replaceAll("(?i)\\b" + book + "\\b", "");
                    writer.write(line);
                    writer.newLine();
                }
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            file.delete();

            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (FileInputStream fis = new FileInputStream(file1);
                 FileOutputStream fos = new FileOutputStream(file)) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

                while ((reader.readLine()) != null) {
                    writer.write(reader.readLine());
                    writer.newLine();
                }
                writer.flush();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void pens() {

        service.execute(() -> {
            String pen = "pen";

            File file = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework3\\a.txt");
            File file1 = new File("C:\\PDP\\Man\\src\\uz\\pdp\\homework3\\b.txt");
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
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

                String line;
                while ((line = br.readLine()) != null) {
                    line = line.replaceAll("(?i)\\b" + pen + "\\b", "");
                    writer.write(line);
                    writer.newLine();
                }
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            file.delete();

            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (FileInputStream fis = new FileInputStream(file1);
                 FileOutputStream fos = new FileOutputStream(file)) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

                while ((reader.readLine()) != null) {
                    writer.write(reader.readLine());
                    writer.newLine();
                }
                writer.flush();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
