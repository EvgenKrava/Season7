package lab10;

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
        System.out.println("Task 6");
        task6();
    }

    private static void task6() {
        Market market = new Market();
        for (int i = 0; i < 20; i++) {
            market.addProduct(new Product("qwerty" + i, i * 5 + 1, i + 3));
        }
        for (int i = 0; i < 5; i++) {
            market.addProduct(new Product("qwerty" + i, i * 5 + 1, i + 3));
        }
        System.out.println(market);
    }

    private static void task5() {
        Map<String, Product> map1 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product("qwerty" + i, 10 * i, i + 1);
            map1.put(product.getArticle(), product);
        }
        Map<String, Product> map2 = new HashMap<>();
        for (int i = 15; i > 5; i--) {
            Product product = new Product("qwerty" + i, 10 * i, i);
            map2.put(product.getArticle(), product);
        }
        Map<String, List<Product>> map = new HashMap<>();
        for (Map.Entry<String, Product> entry : map1.entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                List<Product> arr = new ArrayList<>();
                arr.add(entry.getValue());
                map.put(entry.getKey(), arr);
            } else {
                map.get(entry.getKey()).add(entry.getValue());
            }
        }
        for (Map.Entry<String, Product> entry : map2.entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                List<Product> arr = new ArrayList<>();
                arr.add(entry.getValue());
                map.put(entry.getKey(), arr);
            } else {
                map.get(entry.getKey()).add(entry.getValue());
            }
        }
        System.out.println(map);
        for (List<Product> l : map.values()) {
            l.sort((Comparator.comparingInt(Product::getRealize)));
        }
        System.out.println(map);
    }

    private static void task4() {
        Map<String, Product> map1 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product("qwerty" + i, 10 * i, i);
            map1.put(product.getArticle(), product);
        }
        Map<String, Product> map2 = new HashMap<>();
        for (int i = 5; i < 15; i++) {
            Product product = new Product("qwerty" + i, 10 * i, i);
            map2.put(product.getArticle(), product);
        }
        Map<String, List<Product>> map = new HashMap<>();
        for (Map.Entry<String, Product> entry : map1.entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                List<Product> arr = new ArrayList<>();
                arr.add(entry.getValue());
                map.put(entry.getKey(), arr);
            } else {
                map.get(entry.getKey()).add(entry.getValue());
            }
        }
        for (Map.Entry<String, Product> entry : map2.entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                List<Product> arr = new ArrayList<>();
                arr.add(entry.getValue());
                map.put(entry.getKey(), arr);
            } else {
                map.get(entry.getKey()).add(entry.getValue());
            }
        }
        System.out.println(map);
    }

    private static void task3() {
        Map<String, Product> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product("qwerty" + i, 10 * i, i);
            map.put(product.getArticle(), product);
        }
        System.out.println(map.get("qwerty5"));
    }

    private static void task2() {
        PriorityQueue<Product> products = new PriorityQueue<>();
        products.add(new Product("123qwe", 123, 3));
        products.add(new Product("12qw12", 231, 1));
        products.add(new Product("123qwe1", 23, 3));
        products.add(new Product("123qwe3", 342, 4));
        products.add(new Product("123qwe2", 100, 2));
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void task1() {
        ArrayDeque<Product> products = new ArrayDeque<>();
        products.add(new Product("123qwe", 123, 3));
        products.addFirst(new Product("12qw12", 231, 1));
        products.addLast(new Product("123qwe1", 23, 3));
        products.addLast(new Product("123qwe3", 342, 4));
        products.addFirst(new Product("123qwe2", 100, 2));
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
