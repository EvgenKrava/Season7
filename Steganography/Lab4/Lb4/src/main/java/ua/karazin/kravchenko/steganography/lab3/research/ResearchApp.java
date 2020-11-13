package ua.karazin.kravchenko.steganography.lab3.research;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ua.karazin.kravchenko.exceptions.ThroughputException;
import ua.karazin.kravchenko.steganography.lab3.ImageSpectrumExpansion;
import ua.karazin.kravchenko.steganography.lab3.MultiBasicSteganographiCoding;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ResearchApp extends JFrame {
    private BufferedImage TEST_IMAGE;
    private BufferedImage bufferedImage;

    {
        try {
            bufferedImage = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MultiBasicSteganographiCoding multiBasicSteganographiCoding = new MultiBasicSteganographiCoding();
    private ImageSpectrumExpansion spectrum = new ImageSpectrumExpansion();
    private String TEST_TEXT = "Жив собі в однім лісі Лис Микита, хитрий-прехитрий. Кілька разів гонили його стрільці, травили його псами, заставляли на нього заліза або підкидали йому затроєного м’яса, нічим не могли йому доїхати кінця. Лис Микита кпив собі з них, оминав усякі небезпеки, ще й інших своїх товаришів остерігав. А вже як вибрався на лови — чи то до курника, чи до комори, то не було смілішого, вигадливішого та зручнішого злодія над нього. Дійшло до того, що він не разу білий день вибирався на полювання і ніколи не вертав з порожніми руками.\n" +
            "Се незвичайне щастя і та його хитрість зробили його страшенно гордим. Йому здавалося, що нема нічого неможливого для нього.\n" +
            "— Що ви собі думаєте! — величався він перед своїми товаришами. — Досі я ходив по селах, а завтра в білий день піду до міста і просто з торговиці Курку вкраду.\n" +
            "— Ет, іди, не говори дурниць! — уговкували його товариші.\n" +
            "— Що, дурниць! Ану, побачите! — решетився Лис.\n" +
            "— Побачимо або й не побачимо. Там пси купами по вулицях ходять, то вже хіба б ти перекинувся в Блоху, щоб тебе не побачили і не роздерли.\n" +
            "— От же побачите, і в Блоху не перекинуся, і не розідруть мене! — товк своє Лис і поклав собі міцно зараз завтра, в сам торговий день, побігти до міста і з торговиці вхопити Курку.\n";

    public ResearchApp() {
        try {
            initUI();
        } catch (ThroughputException | IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        setVisible(true);
    }

    private void initUI() throws ThroughputException, IOException {
        XYDataset dataset1 = createDataset1();
        XYDataset dataset2 = createDataset2();
        XYDataset dataset3 = createDataset3();
        XYDataset dataset4 = createDataset4();
        XYDataset dataset5 = createDataset5();
        XYDataset dataset6 = createDataset6();
        ChartPanel chartPanel1 = new ChartPanel(createChart1(dataset1));
        ChartPanel chartPanel2 = new ChartPanel(createChart2(dataset2));
        ChartPanel chartPanel3 = new ChartPanel(createChart3(dataset3));
        ChartPanel chartPanel4 = new ChartPanel(createChart4(dataset4));
        ChartPanel chartPanel5 = new ChartPanel(createChart5(dataset5));
        ChartPanel chartPanel6 = new ChartPanel(createChart6(dataset6));
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(chartPanel1);
        pane.add(chartPanel2);
        pane.add(chartPanel3);
        pane.add(chartPanel4);
        pane.add(chartPanel5);
        pane.add(chartPanel6);
        JScrollPane jScrollPane = new JScrollPane(pane);
        add(jScrollPane);
        pack();
        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset1() throws ThroughputException, IOException {
        var series = new XYSeries("Posh_k(i,1)");
        series.add(0, 0);
        for (int i = 0; i < 8; i++) {
            spectrum.setK((int) Math.pow(2, i));
            String mb = spectrum.toBinaryString(TEST_TEXT);
            TEST_IMAGE = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
            bufferedImage = spectrum.encode(TEST_IMAGE, TEST_TEXT);
            String mb1 = spectrum.toBinaryString(spectrum.decode(bufferedImage));
            int len = Math.min(mb.length(), mb1.length());
            double a = 0;
            for (int j = 0; j < len; j++) {
                if (mb.charAt(j) != mb1.charAt(j)) {
                    a++;
                }
            }
            double posh = a / len;
            series.add(Math.pow(2, i), posh);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset2() throws ThroughputException, IOException {
        var series = new XYSeries("W_k(i,1)");
        series.add(0, 0);
        bufferedImage = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
        for (int i = 0; i < 9; i++) {
            spectrum.setK((int) Math.pow(2, i));
            TEST_IMAGE = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
            TEST_IMAGE = spectrum.encode(TEST_IMAGE, TEST_TEXT);
            double w = 0;
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                for (int k = 0; k < bufferedImage.getWidth(); k++) {
                    w += Math.abs(new Color(bufferedImage.getRGB(k, j)).getRed() - new Color(TEST_IMAGE.getRGB(k, j)).getRed());
                }
            }
            w = (w * 100.) / ((double) bufferedImage.getWidth() * (double) bufferedImage.getHeight() * 256.);
            series.add(Math.pow(2, i), w);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset3() throws IOException, ThroughputException {
        var series = new XYSeries("Posh_g(i,1)");
        for (int i = 1; i < 5; i++) {
            spectrum.setG(i);
            String mb = spectrum.toBinaryString(TEST_TEXT);
            TEST_IMAGE = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
            bufferedImage = spectrum.encode(TEST_IMAGE, TEST_TEXT);
            String mb1 = spectrum.toBinaryString(spectrum.decode(bufferedImage));
            int len = Math.min(mb.length(), mb1.length());
            double a = 0;
            for (int j = 0; j < len; j++) {
                if (mb.charAt(j) != mb1.charAt(j)) {
                    a++;
                }
            }
            double posh = a / len;
            series.add(i, posh);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset4() throws IOException, ThroughputException {
        var series = new XYSeries("W_g(i,1)");
        bufferedImage = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
        for (int i = 1; i < 5; i++) {
            spectrum.setK(i);
            TEST_IMAGE = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
            TEST_IMAGE = spectrum.encode(TEST_IMAGE, TEST_TEXT);
            double w = 0;
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                for (int k = 0; k < bufferedImage.getWidth(); k++) {
                    w += Math.abs(new Color(bufferedImage.getRGB(k, j)).getRed() - new Color(TEST_IMAGE.getRGB(k, j)).getRed());
                }
            }
            w = (w * 100.) / ((double) bufferedImage.getWidth() * (double) bufferedImage.getHeight() * 256.);
            series.add(i, w);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset5() throws IOException, ThroughputException {
        var series = new XYSeries("Posh_g(i,1)");
        multiBasicSteganographiCoding.setG(1);
        for (int i = 5; i < 45; i += 5) {
            String mb = multiBasicSteganographiCoding.toBinaryString(TEST_TEXT);
            TEST_IMAGE = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
            bufferedImage = multiBasicSteganographiCoding.encode(TEST_IMAGE, TEST_TEXT);
            String mb1 = multiBasicSteganographiCoding.toBinaryString(multiBasicSteganographiCoding.decode(bufferedImage));
            double len = Math.min(mb.length(), mb1.length());
            double a = 0;
            for (int j = 0; j < len; j++) {
                if (mb.charAt(j) != mb1.charAt(j)) {
                    a++;
                }
            }
            double posh = a / len;
            series.add(i, posh);
            multiBasicSteganographiCoding.setG(i);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset6() throws IOException, ThroughputException {
        var series = new XYSeries("W_g(i,1)");
        bufferedImage = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
        multiBasicSteganographiCoding.setG(1);
        for (int i = 5; i < 45; i += 5) {
            TEST_IMAGE = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
            TEST_IMAGE = multiBasicSteganographiCoding.encode(TEST_IMAGE, TEST_TEXT);
            double w = 0;
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                for (int k = 0; k < bufferedImage.getWidth(); k++) {
                    w += Math.abs(new Color(bufferedImage.getRGB(k, j)).getBlue() - new Color(TEST_IMAGE.getRGB(k, j)).getBlue());
                }
            }
            w = (w * 100.) / ((double) bufferedImage.getWidth() * (double) bufferedImage.getHeight() * 256.);
            series.add(i, w);
            multiBasicSteganographiCoding.setG(i);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private JFreeChart createChart1(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("", new Font(Font.MONOSPACED, Font.BOLD, 18))
        );

        return chart;
    }

    private JFreeChart createChart2(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("", new Font(Font.MONOSPACED, Font.BOLD, 18))
        );

        return chart;
    }

    private JFreeChart createChart3(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("", new Font(Font.MONOSPACED, Font.BOLD, 18))
        );

        return chart;
    }

    private JFreeChart createChart4(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("", new Font(Font.MONOSPACED, Font.BOLD, 18))
        );

        return chart;
    }

    private JFreeChart createChart5(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("", new Font(Font.MONOSPACED, Font.BOLD, 18))
        );

        return chart;
    }

    private JFreeChart createChart6(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("", new Font(Font.MONOSPACED, Font.BOLD, 18))
        );

        return chart;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new ResearchApp()::start);
    }
}
