package ua.karazin.kravchenko.steganography.part2;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

//READY
public class ImageBlockMethod implements SteganographyAlgorithm {
    private static final int LSB = 0;

    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        String mb = toBinaryString(message);
        if (mb.length() > bufferedImage.getWidth()) {
            throw new ThroughputException();
        }
        for (int i = 0; i < bufferedImage.getWidth() && i < mb.length(); i++) {
            int b = 0;
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                b += new Color(bufferedImage.getRGB(i, j)).getRed();
            }
            b = b % 2;
            Color color = new Color(bufferedImage.getRGB(i, 0));
            if (b != charToInt(mb.charAt(i))) {
                bufferedImage.setRGB(i, 0, new Color(color.getRed() + 1, color.getGreen(), color.getBlue()).getRGB());
            }
        }
        return bufferedImage;
    }

    @Override
    public String decode(BufferedImage bufferedImage) {
        StringBuilder mb = new StringBuilder();
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            int b = 0;
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                b += new Color(bufferedImage.getRGB(i, j)).getRed();
            }
            b = b % 2;
            mb.append(b);
        }
        byte[] result = new byte[bufferedImage.getWidth() / 8];
        for (int i = 0, k = 0; i + 8 < mb.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(mb.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "Block method";
    }

    private int charToInt(char charAt) {
        return charAt == '0' ? 0 : 1;
    }
}
