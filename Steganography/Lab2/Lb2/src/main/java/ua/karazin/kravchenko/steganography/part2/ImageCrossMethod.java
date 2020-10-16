package ua.karazin.kravchenko.steganography.part2;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

public class ImageCrossMethod implements SteganographyAlgorithm {

    private double u = 0.15;
    private int o = 3;

    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        String mb = toBinaryString(message);
        if (mb.length() > bufferedImage.getWidth() - 2 * o || mb.length() > bufferedImage.getHeight() - 2 * o) {
            throw new ThroughputException();
        }
        for (int i = o, k = 0; k < mb.length(); i++, k++) {
            Color color = new Color(bufferedImage.getRGB(i, i));
            double a = 0.29890 * color.getRed() + 0.58662 * color.getGreen() + 0.11448 * color.getBlue();
            int newBlue = (int) (color.getBlue() + (2 * chatToInt(mb.charAt(k)) - 1) * a * u);
            Color newColor = new Color(color.getRed(), color.getGreen(), Math.min(newBlue, 255));
            bufferedImage.setRGB(i, i, newColor.getRGB());
        }
        return bufferedImage;
    }


    @Override
    public String decode(BufferedImage bufferedImage) {
        StringBuilder mb = new StringBuilder();

        for (int i = o; i < bufferedImage.getHeight() - o && i < bufferedImage.getHeight() - o; i++) {
            Color color = new Color(bufferedImage.getRGB(i, i));
            double tmp = 0d;
            for (int j = i - o; j <= i + o; j++) {
                if (i != j) {
                    tmp += new Color(bufferedImage.getRGB(i, j)).getBlue() + new Color(bufferedImage.getRGB(j, i)).getBlue();
                }
            }
            tmp /= 4 * o;
            double b = color.getBlue() - tmp;
            mb.append(b > 0 ? '1' : '0');
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
        return "Cross method";
    }

    private int chatToInt(char ch) {
        return ch == '1' ? 1 : 0;
    }
}
