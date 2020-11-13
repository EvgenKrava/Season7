package ua.karazin.kravchenko.steganography.lab4;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import java.awt.image.BufferedImage;

public class JFridrichMethod implements SteganographyAlgorithm {
    @Override
    public BufferedImage encode(BufferedImage bufferedImage, String message) throws ThroughputException {
        
        return null;
    }

    @Override
    public String decode(BufferedImage bufferedImage) {
        return null;
    }

    @Override
    public String toString() {
        return "Jessica Fridrich method";
    }
}
