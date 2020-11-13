package ua.karazin.kravchenko.steganography.lab3;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MultiBasicSteganographiCoding implements SteganographyAlgorithm {

    int NUM = 666;
    private int g = 40;
    private int[][] arrayFunction;

    public MultiBasicSteganographiCoding() {
        arrayFunction = arrayFunction();
    }

    private int[][] arrayFunction() {
        Random random = new Random(System.nanoTime());
        int[][] res = new int[1024][NUM];
        try (FileWriter fileWriter = new FileWriter("arrayFunction.txt")) {
            for (int i = 0; i < 1024; i++) {
                int[] a = new int[NUM];
                for (int j = 0; j < NUM; j++) {
                    int b = Math.abs(random.nextInt() % 2);
                    a[j] = b == 0 ? -1 : 1;
                    fileWriter.write(b + "");
                }
                fileWriter.write(System.lineSeparator());
                res[i] = a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        String mb = toBinaryString(message);
        int[] mb2 = mbToMb(mb);
        int[] mb3 = new int[bufferedImage.getHeight() * 10];
        int[] md = new int[bufferedImage.getHeight()];
        System.arraycopy(mb2, 0, mb3, 0, mb2.length);
        for (int i = 0; i < mb3.length / 10; i++) {
            int a = 0;
            for (int j = 0; j < 10; j++) {
                a += mb3[10 * i + j] * (int) Math.pow(2, j);
            }
            md[i] = a;
        }
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < NUM; j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                int newBlue = color.getBlue() + g * arrayFunction[md[i]][j];
                if (newBlue > 255) newBlue = 255;
                if (newBlue < 0) newBlue = 0;
                bufferedImage.setRGB(j, i, new Color(color.getRed(), color.getGreen(), newBlue).getRGB());
            }
        }
        return bufferedImage;
    }

    @Override
    public String decode(BufferedImage bufferedImage) {
        arrayFunction = readArrayFunction();
        int[][] arrayString = new int[bufferedImage.getHeight()][NUM];
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < NUM; j++) {
                arrayString[i][j] = new Color(bufferedImage.getRGB(j, i)).getBlue();
            }
        }
        int[] md1 = new int[bufferedImage.getHeight()];
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            int a = 0;
            for (int j = 0; j < 1024; j++) {
                if (multString(arrayString[i], arrayFunction[j]) > a) {
                    a = multString(arrayString[i], arrayFunction[j]);
                    md1[i] = j;
                }
            }
        }
        int[] mb = new int[md1.length * 10];
        for (int i = 0; i < md1.length; i++) {
            int x = md1[i];
            for (int j = 0; j < 10; j++) {
                mb[i * 10 + j] = x % 2;
                x /= 2;
            }
        }
        StringBuilder mbs = new StringBuilder();
        for (int j : mb) {
            mbs.append(j);
        }
        byte[] result = new byte[(bufferedImage.getWidth() / 2 * bufferedImage.getHeight()) / 8];
        for (int i = 0, k = 0; i + 8 < mbs.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(mbs.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    private int[] mbToMb(String mb) {
        int[] res = new int[mb.length()];
        for (int i = 0; i < mb.length(); i++) {
            res[i] = mb.charAt(i) == '1' ? 1 : 0;
        }
        return res;
    }

    private int[] mbToMbp(String mb) {
        int[] arr = new int[mb.length()];
        for (int i = 0; i < mb.length(); i++) {
            arr[i] = mb.charAt(i) == '0' ? -1 : 1;
        }
        return arr;
    }

    private int[][] readArrayFunction() {
        List<String> strings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("arrayFunction.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                strings.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[][] res = new int[strings.size()][strings.get(0).length()];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = strings.get(i).charAt(j) == '0' ? -1 : 1;
            }
        }
        return res;
    }

    private int multString(int[] a, int[] b) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            res += a[i] * b[i];
        }
        return res;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int[][] getArrayFunction() {
        return arrayFunction;
    }

    public void setArrayFunction(int[][] arrayFunction) {
        this.arrayFunction = arrayFunction;
    }

    @Override
    public String toString() {
        return "Multi Basic Steganographi Coding";
    }
}
