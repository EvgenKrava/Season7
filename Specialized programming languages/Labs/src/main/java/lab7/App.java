package lab7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        System.out.println("Task 1");
        task1();
        System.out.println("Task 2");
        //task2();
        System.out.println("Task 3");
        task3("D:\\Season7\\Specialized programming languages\\Labs\\src\\main\\java", "lab1");
        System.out.println("Task 4");
        task4("file1.txt", "tmp\\dir\\");
    }

    private static void task4(String src, String dest) {
        try {
            File srcFile = new File(src);
            File destFile = new File(dest);
            if (dest.matches(".*\\w$")) {
                File f = new File(destFile.getParent());
                if (!f.exists()) {
                    f.mkdirs();
                }
                Files.copy(Paths.get(src), Paths.get(dest));
            } else {
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                Files.copy(Paths.get(src), Paths.get(dest + srcFile.getName()));
            }
            System.out.println("Operation success!");
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task3(String dir, String mask) {
        File file = new File(dir);
        File[] files = file.listFiles();
        Arrays.stream(Objects.requireNonNull(files)).filter(file1 -> file1.getName().matches(mask + ".*")).forEach(System.out::println);
    }

    private static void task2() {
        try {
            File file = File.createTempFile("text", ".temp", new File("."));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int a;
            while ((a = System.in.read()) != 10) {
                fileOutputStream.write(a);
            }
            fileOutputStream.close();
            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(new String(fileInputStream.readAllBytes()));
        } catch (IOException ignored) {
        }
    }

    private static void task1() {
        String userHome = System.getProperty("user.home");
        System.out.println(userHome);
        //listOfDirectories(userHome);
        listOfDirectories(".");
    }

    public static void listOfDirectories(String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isDirectory()) {
                    System.out.println(file);
                    listOfDirectories(file.getAbsolutePath());
                }
            }
        }
    }
}
