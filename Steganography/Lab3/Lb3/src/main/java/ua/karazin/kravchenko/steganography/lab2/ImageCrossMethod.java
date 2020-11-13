package ua.karazin.kravchenko.steganography.lab2;

import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

public class ImageCrossMethod implements SteganographyAlgorithm {

    private double energyBuiltInBit = 0.2;
    private int sizeOfPredictionArea = 5;


    public ImageCrossMethod() {

    }

    public ImageCrossMethod(double energyBuiltInBit, int sizeOfPredictionArea) {
        this.energyBuiltInBit = energyBuiltInBit;
        this.sizeOfPredictionArea = sizeOfPredictionArea;
    }


    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message)  {
        String mb = toBinaryString(message);
        int k = 0;
        for (int j = sizeOfPredictionArea; j < bufferedImage.getHeight() - sizeOfPredictionArea; j++) {
            for (int i = j; i < bufferedImage.getWidth() - sizeOfPredictionArea; i += sizeOfPredictionArea) {
                if (k + 1 == mb.length()) {
                    return bufferedImage;
                }
                Color color = new Color(bufferedImage.getRGB(i, j));
                double a = 0.29890 * color.getRed() + 0.58662 * color.getGreen() + 0.11448 * color.getBlue();
                int newBlue = (int) (color.getBlue() + (2 * chatToInt(mb.charAt(k++)) - 1) * a * energyBuiltInBit);
                Color newColor = new Color(color.getRed(), color.getGreen(), newBlue < 0 ? 0 : Math.min(newBlue, 255));
                bufferedImage.setRGB(i, j, newColor.getRGB());
                //bufferedImage.setRGB(i, j, 0);
            }
        }
        return bufferedImage;
    }


    @Override
    public String decode(BufferedImage bufferedImage) {
        StringBuilder mb = new StringBuilder();
        for (int j = sizeOfPredictionArea; j < bufferedImage.getHeight() - sizeOfPredictionArea; j++) {
            for (int i = j; i < bufferedImage.getWidth() - sizeOfPredictionArea; i += sizeOfPredictionArea) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                double tmp = 0d;
                for (int k = i - sizeOfPredictionArea; k <= i + sizeOfPredictionArea; k++) {
                    if (i != k) {
                        tmp += new Color(bufferedImage.getRGB(k, j)).getBlue();
                    }
                }
                for (int k = j - sizeOfPredictionArea; k <= j + sizeOfPredictionArea; k++) {
                    if (j != k) {
                        tmp += new Color(bufferedImage.getRGB(i, k)).getBlue();
                    }

                }
                tmp /= 4 * sizeOfPredictionArea;
                double b = color.getBlue() - tmp;
                mb.append(b > 0 ? '1' : '0');
            }
        }


        byte[] result = new byte[bufferedImage.getWidth() / sizeOfPredictionArea * (bufferedImage.getHeight() - 2 * sizeOfPredictionArea) / 8];
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

    public double getEnergyBuiltInBit() {
        return energyBuiltInBit;
    }

    public void setEnergyBuiltInBit(double energyBuiltInBit) {
        this.energyBuiltInBit = energyBuiltInBit;
    }

    public int getSizeOfPredictionArea() {
        return sizeOfPredictionArea;
    }

    public void setSizeOfPredictionArea(int sizeOfPredictionArea) {
        this.sizeOfPredictionArea = sizeOfPredictionArea;
    }
}
