package ua.karazin.kravchenko.GUI;


import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.settings.Settings;
import ua.karazin.kravchenko.steganography.ImageLSB;
import ua.karazin.kravchenko.steganography.ImagePRI;
import ua.karazin.kravchenko.steganography.ImagePRP;
import ua.karazin.kravchenko.steganography.SteganographyAlgorithm;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SteganographyFrame extends JFrame {
    public static final String ERROR_MESSAGE = "Data is larger than container. Try to load large container, or cut data!";

    private Settings settings = new Settings();
    private JPanel content = new JPanel();
    private final ImagePane imagePane = new ImagePane();
    private final JButton encode = new JButton("Encode data");
    private final JButton decode = new JButton("Decode data");
    private final JTextArea text = new JTextArea(7, 47);


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SteganographyFrame()::start);
    }

    private void configure() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        content.setLayout(new BorderLayout());
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createAlgorithm());
        jMenuBar.add(createImageMenu());
        jMenuBar.add(createTextMenu());
        this.setJMenuBar(jMenuBar);
        JPanel parametersPanel = new JPanel();
        content.add(parametersPanel, BorderLayout.SOUTH);
        content.add(imagePane, BorderLayout.CENTER);
        content.add(new JScrollPane(text), BorderLayout.NORTH);
        parametersPanel.add(encode);
        parametersPanel.add(decode);
        encode.addActionListener(e -> {
            try {
                imagePane.encode(text.getText());
            } catch (ThroughputException throughputException) {
                JOptionPane.showMessageDialog(this, ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(this, "Choose container!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        decode.addActionListener(this::decodeData);
        this.setContentPane(content);
    }

    private JMenu createTextMenu() {
        JMenu jMenu = new JMenu("Text");
        JMenuItem saveTextItem = new JMenuItem("Save text");
        JMenuItem loadTextItem = new JMenuItem("Load text");
        JMenuItem clearAria = new JMenuItem("Clear text aria");
        jMenu.add(saveTextItem);
        jMenu.add(loadTextItem);
        jMenu.addSeparator();
        jMenu.add(clearAria);
        clearAria.addActionListener(e -> this.text.setText(""));
        saveTextItem.addActionListener(this::saveTextToFile);
        loadTextItem.addActionListener(this::loadTextFromFile);
        return jMenu;
    }

    private JMenu createImageMenu() {
        JMenu jMenu = new JMenu("Image");
        JMenuItem saveImageItem = new JMenuItem("Save image");
        JMenuItem loadImageItem = new JMenuItem("Load image");
        jMenu.add(saveImageItem);
        jMenu.add(loadImageItem);
        loadImageItem.addActionListener(e -> imagePane.readImage());
        saveImageItem.addActionListener(e -> imagePane.saveImage());
        return jMenu;
    }

    private void decodeData(ActionEvent actionEvent) {
        try {
            text.setText(imagePane.decode());
        } catch (NullPointerException exception) {
            JOptionPane.showMessageDialog(this, "Choose container!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void start() {
        configure();
        this.setVisible(true);
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem settings = new JMenuItem("Settings");
        file.add(settings);
        file.addSeparator();
        file.add(exit);
        settings.addActionListener(e -> {
            new SettingsFrame(this.settings).start();
        });
        exit.addActionListener(e -> this.dispose());
        return file;
    }

    private void loadTextFromFile(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text", "txt"));
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setDialogTitle("Load text from file");
        int returnVal = jFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try (Scanner scanner = new Scanner(jFileChooser.getSelectedFile())) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
                }
                text.setText(stringBuilder.toString());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveTextToFile(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        int returnVal = jFileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try (FileWriter fileWriter = new FileWriter(jFileChooser.getSelectedFile(), StandardCharsets.UTF_8)) {
                fileWriter.write(text.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JMenu createAlgorithm() {
        JMenu algorithm = new JMenu("Algorithm");
        JRadioButtonMenuItem lsb = new JRadioButtonMenuItem("Least significant bit");
        JRadioButtonMenuItem pri = new JRadioButtonMenuItem("Pseudo random interval");
        JRadioButtonMenuItem prp = new JRadioButtonMenuItem("Pseudo random permutation");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(lsb);
        buttonGroup.add(pri);
        buttonGroup.add(prp);
        lsb.addActionListener(e -> setAlgo(new ImageLSB()));
        pri.addActionListener(e -> setAlgo(new ImagePRI(10)));
        prp.addActionListener(e -> setAlgo(new ImagePRP()));
        algorithm.add(lsb);
        algorithm.add(pri);
        algorithm.add(prp);
        lsb.doClick();
        return algorithm;
    }

    private void setAlgo(SteganographyAlgorithm steganographyAlgorithm) {
        imagePane.setSteganographyAlgorithm(steganographyAlgorithm);
        setTitle(steganographyAlgorithm.toString());
    }
}
