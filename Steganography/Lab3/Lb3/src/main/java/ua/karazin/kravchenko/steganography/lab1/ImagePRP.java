package ua.karazin.kravchenko.steganography.lab1;


import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

// Pseudo random permutation
public class ImagePRP implements SteganographyAlgorithm {
    private static final int LSB = 7;
    public static final int[][] P = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1}
    };

    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        String mb = toBinaryString(message);
        if (messageIsLargestContainer(bufferedImage, mb)) {
            throw new ThroughputException();
        }
        mb = encode(mb);
        for (int k = 0; k < mb.length(); k++) {
            int j = k / bufferedImage.getWidth();
            int i = k - j * bufferedImage.getWidth();
            Color color = new Color(bufferedImage.getRGB(i, j));
            int red = color.getRed();
            char[] bRed = toBinaryString(red).toCharArray();
            bRed[LSB] = mb.charAt(k);
            bufferedImage.setRGB(i, j, new Color(Integer.parseInt(new String(bRed), 2), color.getGreen(), color.getBlue()).getRGB());
        }
        return bufferedImage;
    }

    public String decode(BufferedImage bufferedImage) {
        StringBuilder binaryString = new StringBuilder();
        for (int j = 0; j < bufferedImage.getHeight(); j++) {
            for (int i = 0; i < bufferedImage.getWidth(); i++) {
                binaryString.append(toBinaryString(new Color(bufferedImage.getRGB(i, j)).getRed()).charAt(LSB));
            }
        }
        binaryString = decode(binaryString);
        byte[] result = new byte[bufferedImage.getHeight() * bufferedImage.getWidth() / 8];
        for (int i = 0, k = 0; i + 8 < binaryString.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(binaryString.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    private StringBuilder decode(StringBuilder binaryString) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i + P.length < binaryString.length(); i += P.length) {
            res.append(multiply(binaryString.substring(i, i + P.length), P, true));

        }
        return res;
    }


    private boolean messageIsLargestContainer(BufferedImage bufferedImage, String mb) {
        return mb.length() > bufferedImage.getWidth() * bufferedImage.getHeight();
    }

    private int charToInt(char charAt) {
        return charAt == '0' ? 0 : 1;
    }

    private String multiply(String m, int[][] P, boolean reverse) {
        if (m.length() != P.length || m.length() != P[0].length) {
            throw new ArithmeticException();
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            int sm = 0;
            for (int j = 0; j < m.length(); j++) {
                int tmp = reverse ? charToInt(m.charAt(i)) * P[i][j] : charToInt(m.charAt(i)) * P[j][i];
                sm += tmp % 2;
            }
            res.append(sm);
        }
        return res.toString();
    }

    private String encode(String mb) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i + P.length < mb.length(); i += P.length) {
            result.append(multiply(mb.substring(i, i + P.length), P, false));
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "Pseudo random permutation";
    }
}
