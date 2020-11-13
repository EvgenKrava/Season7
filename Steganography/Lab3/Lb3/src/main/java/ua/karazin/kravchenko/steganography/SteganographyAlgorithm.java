package ua.karazin.kravchenko.steganography;


import ua.karazin.kravchenko.exceptions.ThroughputException;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

public interface SteganographyAlgorithm {

    default String toBinaryString(int decimal) {
        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.append(decimal % 2);
            decimal = decimal / 2;
        }
        while (binary.length() != 8) {
            binary.append("0");
        }
        return binary.reverse().toString();
    }

    default String toBinaryString(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }

    BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException;

    String decode(BufferedImage bufferedImage);
}
