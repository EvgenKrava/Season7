package lab6;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class App {
    public static void main(String[] args) {
        System.out.println("Task 1");
        task1("file1.txt");
        System.out.println("Task 2");
        //task2(); //work only in console
        System.out.println("Task 3");
        task3();
        System.out.println("Task 4");
        task4();
        System.out.println("Task 5");
        //task5(); //work, but I don't wont input data every time
        System.out.println("Task 6");
        //task6("archive.zip");
    }

    private static void task6(String zipName) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipName))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (Objects.nonNull(zipEntry)) {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(zipEntry.getName()));
                int a;
                while ((a = zipInputStream.read()) != -1) {
                    fileOutputStream.write(a);
                }
                fileOutputStream.close();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task5() {
        boolean write = true;
        System.out.println("Input archive name");
        String name = new Scanner(System.in).nextLine();
        try (FileOutputStream fos = new FileOutputStream(name + ".zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fos)) {
            while (write) {
                File currentFile;
                System.out.println("Input file name:");
                currentFile = new File(new Scanner(System.in).nextLine());
                zipOutputStream.putNextEntry(new ZipEntry(currentFile.getName()));
                StringBuilder stringBuilder = new StringBuilder();
                System.out.println("Input file content:");
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.matches("!q.*")) {
                        break;
                    }
                    if (line.matches("!Q.*")) {
                        write = false;
                        break;
                    }
                    stringBuilder.append(line).append(System.lineSeparator());
                }
                zipOutputStream.write(stringBuilder.toString().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task4() {
        byte[] bytes = new byte[64];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (Math.random() * 256 - 128);
        }
        System.out.println(Arrays.toString(bytes));
        try (FileOutputStream fileOutputStream = new FileOutputStream("lab6_task4.txt");) {
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("lab6_task4.txt", "rw")) {
            for (int i = 0; i < bytes.length; i += 2) {
                randomAccessFile.seek(i);
                randomAccessFile.writeByte(-bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("lab6_task4.txt");) {
            System.out.println(Arrays.toString(fis.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task3() {
        class Person implements Serializable {
            private String name;
            private Integer age;

            public Person(String name, Integer age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                        .add("name='" + name + "'")
                        .add("age=" + age)
                        .toString();
            }
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("lab6_task3.bin"))) {
            objectOutputStream.writeInt(123);
            objectOutputStream.writeBoolean(true);
            objectOutputStream.writeUTF("Люблю Java");
            objectOutputStream.writeObject(new Person("Anna", 20));
            objectOutputStream.writeObject(new Person("Vita", 20));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("lab6_task3.bin"))) {
            System.out.println(objectInputStream.readInt());
            System.out.println(objectInputStream.readBoolean());
            System.out.println(objectInputStream.readUTF());
            System.out.println(objectInputStream.readObject());
            System.out.println(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void task2() {
        Console console = System.console();
        console.printf("Input login: ");
        String login = console.readLine();
        console.printf("Input password: ");
        char[] password = console.readPassword();
        if (hash(password) == hash("password".toCharArray()) && "login".equals(login)) {
            System.out.println("Access permit");
        } else {
            System.out.println("Access deny");
        }

    }

    private static int hash(char[] chars) {
        int result = 0;
        for (int i : chars) {
            result += i + result * 5 + 7;
        }
        return result;
    }

    private static void task1(String filename) {
        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder
                        .append(scanner.nextLine())
                        .append(" ");
            }
            String result = String.join(" ", stringBuilder.toString().split("\\W")).replaceAll(" +", " ");
            System.out.println(result);
        } catch (FileNotFoundException ignored) {
        }
    }
}
