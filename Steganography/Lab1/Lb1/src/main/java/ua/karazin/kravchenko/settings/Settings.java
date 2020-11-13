package ua.karazin.kravchenko.settings;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;

public class Settings {
    private int LSB;
    private int intervalLength;
    private int[][] permutationMatrix;

    public void load() {
        File settings = null;
        try {
            settings = new File(Settings.class.getResource("/settings.txt").toURI());
        } catch (URISyntaxException ignored) {
        }
        if (Objects.nonNull(settings) && settings.exists()) {
            try (Scanner scanner = new Scanner(settings)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.matches("LSB=(\\d+)")){
                        setLSB(Integer.parseInt(line.substring(4)));
                        System.out.println(LSB);
                    }
                }
            } catch (FileNotFoundException e) {
                setDefaultSettings();
            }
        } else {
            setDefaultSettings();
        }
    }

    public void save() {

    }

    public int getLSB() {
        return LSB;
    }

    public void setLSB(int LSB) {
        this.LSB = LSB;
    }

    public int getIntervalLength() {
        return intervalLength;
    }

    public void setIntervalLength(int intervalLength) {
        this.intervalLength = intervalLength;
    }

    public int[][] getPermutationMatrix() {
        return permutationMatrix;
    }

    public void setPermutationMatrix(int[][] permutationMatrix) {
        this.permutationMatrix = permutationMatrix;
    }

    private void setDefaultSettings() {
        LSB = 7;
        intervalLength = 10;
        permutationMatrix = new int[][]{
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1}
        };
    }
}
