package ua.karazin.kravchenko.steganography.lab3;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

public class ImageSpectrumExpansion implements SteganographyAlgorithm {
    private int k = 4;
    private int g = 1;
    private int[][] hm;

    private int[][] sum(int[] mpb, int k) {
        int[][] sum = new int[hm.length][hm.length];
        for (int i = 0; i < hm.length; i++) {
            int[] a = new int[hm.length];
            for (int j = 0; j < k; j++) {
                int[] arr = new int[hm.length];
                System.arraycopy(hm[j + 1], 0, arr, 0, hm.length);
                for (int l = 0; l < arr.length; l++) {
                    arr[l] *= (((i * k + j) < mpb.length) ? mpb[i * k + j] * g : 0);
                    a[l] += arr[l];
                }
            }
            System.arraycopy(a, 0, sum[i], 0, a.length);
        }
        return sum;
    }

    public static int[][] generateHadamardMatrix(int n) {
        int size = (int) Math.pow(2, n);
        int[][] hadamard = new int[size][size];
        hadamard[0][0] = 1;
        for (int k = 1; k < size; k += k) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    hadamard[i + k][j] = hadamard[i][j];
                    hadamard[i][j + k] = hadamard[i][j];
                    hadamard[i + k][j + k] = -hadamard[i][j];
                }
            }
        }
        return hadamard;
    }

    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        String mb = toBinaryString(message);
        int[] mpb = mbToMbp(mb);
        //System.out.println(mb);
        int n = (int) log2(Math.min(bufferedImage.getHeight(), bufferedImage.getWidth()));
        hm = generateHadamardMatrix(n);
        int[][] sum = sum(mpb, k);
        //System.out.println(Arrays.deepToString(sum));
        for (int i = 0; i < hm.length; i++) {
            for (int j = 0; j < hm.length; j++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                int newRed = color.getRed() + sum[i][j];
                if (newRed > 255) newRed = 255;
                if (newRed < 0) newRed = 0;
                bufferedImage.setRGB(i, j, new Color(newRed, color.getGreen(), color.getBlue()).getRGB());
            }
        }
        return bufferedImage;
    }

    @Override
    public String decode(BufferedImage bufferedImage) {
        int n = (int) log2(Math.min(bufferedImage.getHeight(), bufferedImage.getWidth()));
        hm = generateHadamardMatrix(n);
        StringBuilder mb = new StringBuilder();
        int[][] arrayString = new int[hm.length][hm.length];
        int[] a = new int[hm.length];
        for (int i = 0; i < hm.length; i++) {
            for (int j = 0; j < hm.length; j++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                a[j] = color.getRed();
            }
            System.arraycopy(a, 0, arrayString[i], 0, hm.length);
        }
        for (int i = 0; i < hm.length; i++) {
            for (int j = 0; j < k; j++) {
                mb.append(multString(arrayString[i], hm[j + 1]) > 0 ? '1' : '0');
            }
        }
        byte[] result = new byte[hm.length * k / 8];
        for (int i = 0, k = 0; i + 8 < mb.length(); i += 8) {
            byte b = (byte) (int) Integer.valueOf(mb.substring(i, i + 8), 2);
            result[k++] = b;
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    private int multString(int[] a, int[] b) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            res += a[i] * b[i];
        }
        return res;
    }

    @Override
    public String toString() {
        return "Spectrum Expansion";
    }

    private int[] mbToMbp(String mb) {
        int[] mbp = new int[mb.length()];
        for (int i = 0; i < mbp.length; i++) {
            mbp[i] = mb.charAt(i) == '0' ? -1 : 1;
        }
        return mbp;
    }

    public static int log2(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }
}
