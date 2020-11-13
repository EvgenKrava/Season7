package ua.karazin.kravchenko.GUI;


import ua.karazin.kravchenko.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsFrame extends JFrame {

    private Settings settings;
    private final JButton save = new JButton("Save");
    private final JButton saveForever = new JButton("Save forever");
    private final JButton cancel = new JButton("Cancel");
    JSpinner lsb = new JSpinner(new SpinnerListModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
    JTextField interval = new JTextField(5);
    JTextField permutationMatrix = new JTextField(5);
    private final JPanel content = new JPanel();
    private final JPanel footer = new JPanel();


    public SettingsFrame(Settings settings) {
        super("Settings");
        this.settings = settings;
        init();
    }


    void init() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(400, 400));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setLayout(new BorderLayout());
        footer.setLayout(new FlowLayout(FlowLayout.TRAILING));
        footer.add(save);
        footer.add(saveForever);
        footer.add(cancel);
        initContent();
        save.addActionListener(this::save);
        cancel.addActionListener(e -> this.dispose());
        this.add(content, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
    }

    private void save(ActionEvent actionEvent) {
        settings.setLSB(8 - Integer.parseInt((String) lsb.getValue()));
    }

    private void initContent() {
        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.EAST, lsb, 5, SpringLayout.WEST, new JLabel("LSB:"));
        content.setLayout(springLayout);
        /*content.add(new JLabel("Interval Length"));
        content.add(interval);
        content.add(new JLabel("Permutation matrix size"));*/
    }

    public void start() {
        this.setVisible(true);
    }


}
