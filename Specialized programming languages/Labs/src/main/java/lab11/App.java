package lab11;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Task 1");
        //task1();
        System.out.println("Task 2");
        new Thread(App::task2).start();
        System.out.println("Task 3");
        //new Thread(App::task3).start();
        System.out.println("Task 4");
        new Thread(App::task3).start();
        new Thread(App::task3).start();
        new Thread(App::task3).start();
        System.out.println("Task 5");
    }

    private static void task3() {
        new Thread(new Task3("localhost", 9999)).start();
    }

    private static void task2() {
        new Task2(9999).start();
    }

    private static void task1() throws IOException {
        new Task1(9999).start();
    }
}
