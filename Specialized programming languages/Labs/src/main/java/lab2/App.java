package lab2;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    int field;
    private static final double SM_IN_INCH = 2.54d;

    public static void main(String... args) {
        System.out.println("Task 1");
        int i;
        double d;
        float f;
        boolean bool;
        char c;
        short s;
        byte b;
        long l;
        int i1 = 12112456;
        double d1 = 12345678d;
        float f1 = 1234567832472643f;
        boolean bool1 = false;
        char c1 = ' ';
        short s1 = 121;
        byte b1 = -10;
        long l1 = 12445;
        int i2 = 1 + 1;
        double d2 = 1. + 1.;
        float f2 = 1 + 1;
        boolean bool2 = !false;
        char c2 = '1' + '1';
        short s2 = 1 + 1;
        byte b2 = 1 + 1;
        long l2 = 1 + 1;
        System.out.println(i1);
        System.out.printf("%X\n", i1);
        System.out.println(d1);
        System.out.println(f1);
        System.out.println(bool1);
        System.out.println(c1);
        System.out.println(s1);
        System.out.printf("%X\n", s1);
        System.out.println(b1);
        System.out.printf("%X\n", b1);
        System.out.println(l1);
        System.out.printf("%X\n", l1);
        System.out.println("Task 2");
        System.out.println((short) i1);
        System.out.println((float) d1);
        System.out.println((int) f1);
        System.out.println("Task 3");
        int notField;
        notField = 12;
        App app = new App();
        System.out.println(notField + " " + app.field);
        System.out.println("Task 4");
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int k : arr) {
            System.out.print(k);
        }
        System.out.println();
        Arrays.stream(arr).forEach(System.out::print);
        System.out.println();
        String[] sArr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        System.out.println(Arrays.toString(sArr));
        Arrays.stream(sArr).forEach(System.out::print);
        System.out.println();
        System.out.println("Task 5");
        System.out.println("Input ENUM number: ");
        System.out.println(switch (new Scanner(System.in).nextInt()) {
            case 0 -> MyEnum.values()[0];
            case 1 -> MyEnum.values()[1];
            case 2 -> MyEnum.values()[2];
            case 3 -> MyEnum.values()[3];
            default -> "Incorrect input";
        });
        System.out.println("Task 6");
        System.out.println("Input value in centimeters:");
        System.out.println("Typed value in inch: " + new Scanner(System.in).nextDouble() / SM_IN_INCH);
        System.out.println("Task 7");
        int a = 0xAB;
        System.out.println(a);
        System.out.println(-a >> 1);
        System.out.println(-a >>> 1);
        System.out.println("Task 8");
        System.out.println("Input number from 0 to 10");
        a = new Scanner(System.in).nextInt();
        System.out.println(a >= 0 && a <= 10 ? "Nice" : "Incorrect input");
        System.out.println("Task 9");
        i = 1;
        int res = 1;
        while (res * i < 10000) {
            System.out.print(i + " ");
            res *= (i += 2);
        }
        System.out.println("Task 10");
        System.out.println("Input simple count: ");
        int count = new Scanner(System.in).nextInt();
        int[] arr1 = new int[count];
        for (int j = 0; j < arr1.length; j++) {
            arr1[j] = j + 1;
        }
        for (int j = 2; j < Math.sqrt(count) + 1; j++) {
            for (int k = 0; k < arr1.length; k++) {
                if (arr1[k] % j == 0 && arr1[k] != j) {
                    arr1[k] = 0;
                }
            }
        }
        Arrays.stream(arr1).filter(value -> value != 0).forEach(value -> System.out.print(value + " "));
    }

    enum MyEnum {
        ELEMENT1,
        ELEMENT2,
        ELEMENT3,
        ELEMENT4,
    }
}