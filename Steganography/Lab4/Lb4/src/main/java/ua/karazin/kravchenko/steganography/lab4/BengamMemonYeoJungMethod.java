package ua.karazin.kravchenko.steganography.lab4;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BengamMemonYeoJungMethod implements SteganographyAlgorithm {
    private int Pr = 10;
    private int N = 8;

    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        int height = (bufferedImage.getHeight() / N) * N;
        int width = (bufferedImage.getWidth() / N) * N;
        double[][] Y = new double[height][width];
        double[][] Cb = new double[height][width];
        double[][] Cr = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                Y[i][j] = (int) (0 + (0.299 * color.getRed()) + (0.587 * color.getGreen()) + (0.114 * color.getBlue()));
                Cb[i][j] = (int) (128 - (0.168736 * color.getRed()) - (0.331264 * color.getGreen()) + (0.50000 * color.getBlue()));
                Cr[i][j] = (int) (128 + (0.50000 * color.getRed()) - (0.418688 * color.getGreen()) - (0.081312 * color.getBlue()));
            }
        }
        ArrayList<double[][]> dctBlocks = new ArrayList<>();
        DCT dct = new DCT(N);
        for (int i = 0; i < height; i += N) {
            for (int j = 0; j < width; j += N) {
                double[][] tmp = new double[N][N];
                for (int k = i, x = 0; k < i + N; k++, x++) {
                    for (int l = j, y = 0; l < j + N; l++, y++) {
                        tmp[x][y] = Y[k][l];
                    }
                }
                dctBlocks.add(dct.forwardDCT(tmp).clone());
            }
        }
        String mb = toBinaryString(message);
        int ind2 = N / 3;
        int ind1 = ind2 + ind2;
        int ind3 = (ind1 + ind2) / 2;
        for (int i = 0; i < Math.min(dctBlocks.size(), mb.length()); i++) {
            if ((mb.charAt(i) == '1')) {
                if (dctBlocks.get(i)[ind3][ind3] > 0) {
                    dctBlocks.get(i)[ind3][ind3] = Math.max(Math.abs(dctBlocks.get(i)[ind2][ind1]), Math.abs(dctBlocks.get(i)[ind1][ind2])) + Pr;
                } else {
                    dctBlocks.get(i)[ind3][ind3] = -Math.max(Math.abs(dctBlocks.get(i)[ind2][ind1]), Math.abs(dctBlocks.get(i)[ind1][ind2])) - Pr;
                }
            } else {
                if (dctBlocks.get(i)[ind3][ind3] > 0) {
                    dctBlocks.get(i)[ind3][ind3] = Math.min(Math.abs(dctBlocks.get(i)[ind2][ind1]), Math.abs(dctBlocks.get(i)[ind1][ind2])) - Pr;
                } else {
                    dctBlocks.get(i)[ind3][ind3] = -Math.min(Math.abs(dctBlocks.get(i)[ind2][ind1]), Math.abs(dctBlocks.get(i)[ind1][ind2])) + Pr;
                }
            }
        }

        for (int i = 0, index = 0; i < height; i += N) {
            for (int j = 0; j < width; j += N, index++) {
                double[][] tmp = dct.inverseDCT(dctBlocks.get(index));
                for (int k = i, x = 0; k < i + N; k++, x++) {
                    for (int l = j, y = 0; l < j + N; l++, y++) {
                        Y[k][l] = tmp[x][y];
                    }
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r = (int) (Y[i][j] + 1.40200 * (Cr[i][j] - 0x80));
                int g = (int) (Y[i][j] - 0.34414 * (Cb[i][j] - 0x80) - 0.71414 * (Cr[i][j] - 0x80));
                int b = (int) (Y[i][j] + 1.77200 * (Cb[i][j] - 0x80));
                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));
                bufferedImage.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        return bufferedImage;
    }

    @Override
    public String decode(BufferedImage bufferedImage) {
        StringBuilder mb = new StringBuilder();
        int height = (bufferedImage.getHeight() / N) * N;
        int width = (bufferedImage.getWidth() / N) * N;
        double[][] Y = new double[height][width];
        double[][] Cb = new double[height][width];
        double[][] Cr = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                Y[i][j] = (int) (0 + (0.299 * color.getRed()) + (0.587 * color.getGreen()) + (0.114 * color.getBlue()));
                Cb[i][j] = (int) (128 - (0.168736 * color.getRed()) - (0.331264 * color.getGreen()) + (0.50000 * color.getBlue()));
                Cr[i][j] = (int) (128 + (0.50000 * color.getRed()) - (0.418688 * color.getGreen()) - (0.081312 * color.getBlue()));

            }
        }
        ArrayList<double[][]> dctBlocks = new ArrayList<>();
        DCT dct = new DCT(N);
        for (int i = 0; i < height; i += N) {
            for (int j = 0; j < width; j += N) {
                double[][] tmp = new double[N][N];
                for (int k = i, x = 0; k < i + N; k++, x++) {
                    for (int l = j, y = 0; l < j + N; l++, y++) {
                        tmp[x][y] = Y[k][l];
                    }
                }
                dctBlocks.add(dct.forwardDCT(tmp.clone()));
            }
        }
        int ind2 = N / 3;
        int ind1 = ind2 + ind2;
        int ind3 = (ind1 + ind2) / 2;
        for (double[][] doubles : dctBlocks) {
            if (Math.abs(doubles[ind3][ind3]) > Math.max(Math.abs(doubles[ind2][ind1]), Math.abs(doubles[ind1][ind2]))) {
                mb.append('1');
            } else {
                mb.append('0');
            }
        }
        byte[] result = new byte[mb.length() / 8];
        for (int i = 0, k = 0; i + 8 < mb.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(mb.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "Bengam-Memon-Yeo-Jung Method";
    }
}