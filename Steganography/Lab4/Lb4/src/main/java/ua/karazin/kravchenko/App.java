package ua.karazin.kravchenko;

import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.lab3.MultiBasicSteganographiCoding;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, ThroughputException {
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\Yevhenii\\Desktop\\Kravchenko\\Lb3\\src\\main\\resources\\landscape.bmp"));
        MultiBasicSteganographiCoding multiBasicSteganographiCoding = new MultiBasicSteganographiCoding();
        bufferedImage = multiBasicSteganographiCoding.encode(bufferedImage, "hellohellohello");
        System.out.println(multiBasicSteganographiCoding.decode(bufferedImage));
    }
}
