package lab9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Task 1");
        task1();
        System.out.println("Task 2");
        task2();
        System.out.println("Task 3");
        task3();
        System.out.println("Task 4");
        task4();
        System.out.println("Task 5");
        task5();
    }

    private static void task5() {
        List<Student> students = new LinkedList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("lab9_task5.txt"))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] params = s.split(" ");
                students.add(new Student(params[0], params[1], params[2], Integer.parseInt(params[3])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Список дисциплин:");
        Set<Student> courses = new TreeSet<>(Comparator.comparing(Student::getCourse));
        courses.addAll(students);
        courses.stream().map(Student::getCourse).forEach(System.out::println);
        System.out.println("Список групп:");
        Set<Student> groups = new TreeSet<>(Comparator.comparing(Student::getGroup));
        groups.addAll(students);
        groups.stream().map(Student::getGroup).forEach(System.out::println);
    }

    private static void task4() {
        ArrayList<String> arrayList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("lab9_task3.txt"))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                arrayList.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        arrayList.sort(String::compareTo);
        System.out.println(arrayList);
        Collections.shuffle(arrayList);
        System.out.println(arrayList);
    }

    private static void task3() {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream("lab9_task3.txt"))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                arrayList.add(s);
                set.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String s : set) {
            int size = arrayList.size();
            arrayList.removeIf(s1 -> s1.equals(s));
            System.out.println(s + " " + (size - arrayList.size()));
        }
    }

    private static void task2() {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream("lab9_task2_1.txt"));) {
            while (scanner.hasNextLine()) {
                set1.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Scanner scanner = new Scanner(new FileInputStream("lab9_task2_2.txt"));) {
            while (scanner.hasNextLine()) {
                set2.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Set<String> set3 = new HashSet<>();
        System.out.println("Объединение:");
        set3.addAll(set1);
        set3.addAll(set2);
        set3.forEach(System.out::println);
        System.out.println("Пересечение");
        set3.clear();
        set3.addAll(set1);
        set3.stream().filter(set2::contains).forEach(System.out::println);
        System.out.println("Разность");
        set3.clear();
        set3.addAll(set1);
        set3.addAll(set2);
        set3.stream().filter(s -> !(set1.contains(s) && set2.contains(s))).forEach(System.out::println);
    }

    private static void task1() {
        Set<String> set = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream("lab9_task1.txt"));) {
            while (scanner.hasNextLine()) {
                set.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("toString()");
        System.out.println(set);
        System.out.println("for-each");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("iterator");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
