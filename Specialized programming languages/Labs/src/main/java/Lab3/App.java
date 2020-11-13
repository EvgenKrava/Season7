package Lab3;

import java.util.Arrays;

public class App {
    static void sort(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        System.out.println("Task 1");
        sort(100);
        System.out.println("Task 2");
        int[][] arr = new int[10][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[(int) (Math.random() * 10 + 1)];
        }
        System.out.println("Array:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (int) (Math.random() * 200 - 100);
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        for (int[] ints : arr) {
            Arrays.sort(ints);
        }
        System.out.println("Sorted array:");
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("Negative array");
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (anInt > 0) {
                    continue;
                }
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("Task 3");
        System.out.println(new NewClass().publicField);
    }
}
