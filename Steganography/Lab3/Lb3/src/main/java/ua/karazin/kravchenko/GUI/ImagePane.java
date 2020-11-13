package ua.karazin.kravchenko.GUI;


import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImagePane extends JComponent {
    private String format;
    private BufferedImage bufferedImage;
    private SteganographyAlgorithm steganographyAlgorithm;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Objects.isNull(bufferedImage)) {
            g.drawRect(0, 0, getWidth(), getHeight());
            g.drawString("This is aria for you image container", getWidth() / 2 - 80, getHeight() / 2 - 10);
        }
        g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
    }

    public String decode() {
        return this.steganographyAlgorithm.decode(bufferedImage);
    }

    public void encode(String text) throws ThroughputException {
        bufferedImage = this.steganographyAlgorithm.encode(bufferedImage, text);
        repaint();
    }

    public void readImage() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("BMP", "bmp"));
        jFileChooser.setAcceptAllFileFilterUsed(false);
        int returnVal = jFileChooser.showOpenDialog(getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.bufferedImage = ImageIO.read(jFileChooser.getSelectedFile());
                format = jFileChooser.getSelectedFile().getName().substring(jFileChooser.getSelectedFile().getName().indexOf(".") + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        repaint();
    }

    public void saveImage() {
        if (Objects.nonNull(bufferedImage)) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image file", format));
            jFileChooser.setAcceptAllFileFilterUsed(false);
            int returnVal = jFileChooser.showSaveDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    System.out.println(format);
                    System.out.println(ImageIO.write(bufferedImage, format, jFileChooser.getSelectedFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public SteganographyAlgorithm getSteganographyAlgorithm() {
        return steganographyAlgorithm;
    }

    public void setSteganographyAlgorithm(SteganographyAlgorithm steganographyAlgorithm) {
        this.steganographyAlgorithm = steganographyAlgorithm;
    }
}
