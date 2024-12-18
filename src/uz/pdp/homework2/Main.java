package uz.pdp.homework2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.ZoneId;

import static uz.pdp.homework2.Input.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    1 - Login
                    2 - Register
                    """);

            switch (num("Choose: ")) {
                case 1 -> Login();
                case 2 -> Register();
            }
        }
    }

    private static void Login() {
        String number;
        String password;

        while (true) {
            while (true) {
                number = str("Enter your number: ");
                password = str("Enter your password: ");
                if (number.matches("\\\\+998\\\\d{9}") && password.matches("[a-zA-Z0-9\\\\D]+")) {
                    break;
                } else {
                    System.out.println("Invalid number or password!");
                }
            }

            try (
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/uz/pdp/homework2/user.txt"));
            ) {
                Object o = ois.readObject();
                User user = (User) o;

                if (user.getPhone().equals(number) && user.getPassword().equals(password)) {
                    user.menu();
                    break;
                } else {
                    System.out.println("Invalid number or password!");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void Register() {
        String number;
        String password;
        while (true) {
            number = str("Enter your number: ");
            password = str("Enter your password: ");
            if (number.matches("\\+998\\d{9}") && password.matches("[a-zA-Z0-9\\D]+")) {
                break;
            } else {
                System.out.println("Invalid number or password!");
            }
        }

        ZoneId zoneId = chooseZoneId();

        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\PDP\\Man\\src\\uz\\pdp\\homework2\\user.txt"))
        ) {
            oos.writeObject(new User(number, password, zoneId));
            System.out.println("Registered!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static ZoneId chooseZoneId() {
        System.out.println("""
                1 - Moscow
                2 - Tokyo
                3 - New-York
                4 - Sydney
                """);
        switch (Input.num("Choose: ")) {
            case 1 -> {
                return ZoneId.of("Europe/Moscow");
            }
            case 2 -> {
                return ZoneId.of("Asia/Tokyo");
            }
            case 3 -> {
                return ZoneId.of("America/New_York");
            }
            case 4 -> {
                return ZoneId.of("Australia/Sydney");
            }
        }
        return ZoneId.systemDefault();
    }
}
