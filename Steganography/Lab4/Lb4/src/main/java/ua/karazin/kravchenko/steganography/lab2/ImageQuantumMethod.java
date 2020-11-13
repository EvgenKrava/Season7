package ua.karazin.kravchenko.steganography.lab2;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ImageQuantumMethod implements SteganographyAlgorithm {

    private Map<Integer, Integer> d = new HashMap<>();

    public ImageQuantumMethod() {
        for (int i = -255; i < 256; i++) {
            d.put(i, Math.abs(i % 2));
        }
    }

    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        String mb = toBinaryString(message);
        if (mb.length() > (bufferedImage.getWidth() * bufferedImage.getHeight() / 2)) {
            throw new ThroughputException();
        }
        int k = 0;
        for (int j = 0; j < bufferedImage.getHeight(); j++) {
            for (int i = 1; i < bufferedImage.getWidth() - 1; i += 2) {
                Color currentColor = new Color(bufferedImage.getRGB(i, j));
                Color previousColor = new Color(bufferedImage.getRGB(i - 1, j));
                int red0 = previousColor.getRed();
                int red1 = currentColor.getRed();
                while (d.get(red1 - red0) != charToInt(mb.charAt(k))) {
                    if (red0 + 1 < 255) {
                        red0++;
                        continue;
                    } else {
                        red0--;
                    }
                    if (red1 + 1 < 255) {
                        red1++;
                    } else {
                        red1--;
                    }
                }
                bufferedImage.setRGB(i - 1, j, new Color(red0, previousColor.getGreen(), previousColor.getBlue()).getRGB());
                bufferedImage.setRGB(i, j, new Color(red1, currentColor.getGreen(), currentColor.getBlue()).getRGB());
                if (++k == mb.length()) return bufferedImage;
            }
        }
        return bufferedImage;
    }

    @Override
    public String decode(BufferedImage bufferedImage) {
        StringBuilder mb = new StringBuilder();
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 1; j < bufferedImage.getWidth() - 1; j += 2) {
                Color currentColor = new Color(bufferedImage.getRGB(j, i));
                Color previousColor = new Color(bufferedImage.getRGB(j - 1, i));
                int b = d.get(currentColor.getRed() - previousColor.getRed());
                mb.append(b);
            }
        }
        byte[] result = new byte[(bufferedImage.getWidth() / 2 * bufferedImage.getHeight()) / 8];
        for (int i = 0, k = 0; i + 8 < mb.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(mb.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "Quantum method";
    }

    private int charToInt(char charAt) {
        return charAt == '0' ? 0 : 1;
    }
}
