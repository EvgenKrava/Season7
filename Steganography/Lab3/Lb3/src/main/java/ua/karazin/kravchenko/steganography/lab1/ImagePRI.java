package ua.karazin.kravchenko.steganography.lab1;

import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

// Pseudo random interval
public class ImagePRI implements SteganographyAlgorithm {
    private static final int LSB = 7;
    private int interval;
    private int[] intervalValues;

    public ImagePRI(int interval) {
        this.interval = interval;
        intervalValues = fillK(interval);
    }

    public BufferedImage encode(BufferedImage bufferedImage, String message) {
        int n = interval / 2;
        String mb = toBinaryString(message);
        int index = 0;
        for (int i = 0; i < bufferedImage.getHeight() / interval; i++) {
            for (int j = 0; j < bufferedImage.getWidth() / n; j++) {
                for (int l = 0; l < n && index < mb.length(); l++) {
                    int a = n * j + l;
                    int b = interval * i + intervalValues[l];
                    Color color = new Color(bufferedImage.getRGB(a, b));
                    char[] bred = toBinaryString(color.getRed()).toCharArray();
                    bred[LSB] = mb.charAt(index++);
                    bufferedImage.setRGB(a, b, new Color(Integer.parseInt(new String(bred), 2), color.getGreen(), color.getBlue()).getRGB());
                }
            }
        }
        return bufferedImage;
    }

    public String decode(BufferedImage bufferedImage) {
        int n = interval / 2;
        StringBuilder mb = new StringBuilder();
        for (int i = 0; i < bufferedImage.getHeight() / interval; i++) {
            for (int j = 0; j < bufferedImage.getWidth() / n; j++) {
                for (int l = 0; l < interval / 2; l++) {
                    int a = interval / 2 * j + l;
                    int b = interval * i + intervalValues[l];
                    Color color = new Color(bufferedImage.getRGB(a, b));
                    char ch = toBinaryString(color.getRed()).toCharArray()[LSB];
                    mb.append(ch);
                }
            }
        }
        byte[] result = new byte[bufferedImage.getHeight() * bufferedImage.getWidth() / 8];
        for (int i = 0, k = 0; i + 8 < mb.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(mb.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    private int[] fillK(int k) {
        int n = interval / 2;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i % k;
        }
        return arr;
    }

    @Override
    public String toString() {
        return "Pseudo random interval";
    }
}
