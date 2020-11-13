package lab5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Task 1");
        task1();
        System.out.println("Task 2");
        task2("Task 2");
        System.out.println("Task 3");
        task3("file1.txt", "file2.txt");
        System.out.println("Task 4");
        task4();
        System.out.println("Task 5");
        task5();
        System.out.println("Task 6");
        task6();
    }

    private static void task6() {
        System.out.println("1: Exception 1" + System.lineSeparator() + "2: Exception 2");
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> throw new MyException1();
                case 2 -> throw new MyException2();
            }
        } catch (MyException1 | MyException2 myException1) {
            System.out.println(Arrays.toString(myException1.getStackTrace()));
            try {
                throw new MyException3();
            } catch (MyException3 myException3) {
                System.out.println(Arrays.toString(myException3.getStackTrace()));
            }
        }
    }

    public static void task1() {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(scanner.nextLine());
            System.out.println("String Builder capacity: " + stringBuilder.capacity());
        }
    }

    public static void task2(String str) {
        for (char c : str.toCharArray()) {
            System.out.println(c);
            System.out.println("Number: " + (!Character.isLetter(c) && !Character.isSpaceChar(c)));
            System.out.println("Symbol: " + Character.isLetter(c));
            System.out.println("Upper case: " + Character.isUpperCase(c));
            System.out.println("Is space: " + Character.isSpaceChar(c));
            System.out.println();
        }
    }

    private static void task3(String fileName1, String fileName2) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName1);
             FileOutputStream fileOutputStream = new FileOutputStream(fileName2)) {
            int a;
            while ((a = fileInputStream.read()) != -1) {
                fileOutputStream.write(a);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void task4() {
        String filename;
        while (true) {
            System.out.println("Input file name: ");
            filename = new Scanner(System.in).nextLine();
            if ("".equals(filename)) {
                System.out.println("Work with program ended");
                break;
            }
            try (FileInputStream fis = new FileInputStream(filename)) {
                int a;
                while ((a = fis.read()) != -1) {
                    System.out.printf("%X ", a);
                }
                System.out.println();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void task5() {
        try (MyCloseable myCloseable = new MyCloseable()) {
            System.out.println("Work");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
