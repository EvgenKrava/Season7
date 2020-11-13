package ua.karazin.kravchenko.steganography;


import ua.karazin.kravchenko.exceptions.ThroughputException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

// Least significant bit
public class ImageLSB implements SteganographyAlgorithm {

    public static final int LSB = 7;

    public BufferedImage encode(BufferedImage bufferedImage, String text) throws ThroughputException {
        String mb = toBinaryString(text);
        if (mb.length() > bufferedImage.getWidth() * bufferedImage.getHeight()) {
            throw new ThroughputException();
        }
        for (int k = 0; k < mb.length(); k++) {
            int i = k / bufferedImage.getHeight();
            int j = k - i * bufferedImage.getHeight();
            Color color = new Color(bufferedImage.getRGB(i, j)); // get color from image
            char[] changeRedColor = toBinaryString(color.getRed()).toCharArray(); // changeRedColor - binary char array
            changeRedColor[LSB] = mb.charAt(k); // lsb index 7
            bufferedImage.setRGB(i, j, new Color(Integer.parseInt(new String(changeRedColor), 2), color.getGreen(), color.getBlue()).getRGB());
        }
        return bufferedImage;
    }

    public String decode(BufferedImage bufferedImage) {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                binaryString.append(toBinaryString(new Color(bufferedImage.getRGB(i, j)).getRed()).charAt(LSB));
            }
        }
        byte[] result = new byte[bufferedImage.getWidth() * bufferedImage.getWidth() / 8];
        for (int i = 0, k = 0; i + 8 < binaryString.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(binaryString.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "Least significant bit";
    }
}
