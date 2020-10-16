package ua.karazin.kravchenko.lab2;

import java.util.*;

public class Huffman {
    private Map<Character, Integer> map = new HashMap<>();
    HuffmanNode root = new HuffmanNode();

    static class HuffmanNode implements Comparable<HuffmanNode> {
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;

        public int compareTo(HuffmanNode o) {
            return data - o.data;
        }
    }

    public Huffman(String text) {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (map.containsKey(ch)) {
                int count = map.get(ch) + 1;
                map.put(ch, count);
            } else {
                map.put(ch, 1);
            }
        }
        Character[] chars = new Character[map.size()];
        chars = map.keySet().toArray(chars);
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
    }

    @Override
    public String toString() {
        return "Huffman{" +
                "map=" + map +
                '}';
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman("Hellooooo");
        System.out.println(huffman);
    }
}
