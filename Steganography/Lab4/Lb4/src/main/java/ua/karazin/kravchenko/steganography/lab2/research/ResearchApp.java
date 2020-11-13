package ua.karazin.kravchenko.steganography.lab2.research;

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
import ua.karazin.kravchenko.steganography.lab2.ImageCrossMethod;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResearchApp extends JFrame {

    List<Double> ws = new ArrayList<>();
    List<Double> vs = new ArrayList<>();
    private BufferedImage TEST_IMAGE;
    private BufferedImage bufferedImage;

    {
        try {
            bufferedImage = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
            TEST_IMAGE = ImageIO.read(new FileInputStream("src/main/resources/landscape.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String TEST_TEXT = "Жив собі в однім лісі Лис Микита, хитрий-прехитрий. Кілька разів гонили його стрільці, травили його псами, заставляли на нього заліза або підкидали йому затроєного м’яса, нічим не могли йому доїхати кінця. Лис Микита кпив собі з них, оминав усякі небезпеки, ще й інших своїх товаришів остерігав. А вже як вибрався на лови — чи то до курника, чи до комори, то не було смілішого, вигадливішого та зручнішого злодія над нього. Дійшло до того, що він не разу білий день вибирався на полювання і ніколи не вертав з порожніми руками.\n" +
            "\n" +
            "Се незвичайне щастя і та його хитрість зробили його страшенно гордим. Йому здавалося, що нема нічого неможливого для нього.\n" +
            "\n" +
            "— Що ви собі думаєте! — величався він перед своїми товаришами. — Досі я ходив по селах, а завтра в білий день піду до міста і просто з торговиці Курку вкраду.\n" +
            "\n" +
            "— Ет, іди, не говори дурниць! — уговкували його товариші.\n" +
            "\n" +
            "— Що, дурниць! Ану, побачите! — решетився Лис.\n" +
            "\n" +
            "— Побачимо або й не побачимо. Там пси купами по вулицях ходять, то вже хіба б ти перекинувся в Блоху, щоб тебе не побачили і не роздерли.\n" +
            "\n" +
            "— От же побачите, і в Блоху не перекинуся, і не розідруть мене! — товк своє Лис і поклав собі міцно зараз завтра, в сам торговий день, побігти до міста і з торговиці вхопити Курку.\n" +
            "\n" +
            "Але сим разом бідний Микита таки перечислився. Поміж коноплі та кукурудзи він заліз безпечно аж до передмістя; огородами, перескакуючи плоти та ховаючися між яриною, дістався аж до середмістя. Але тут біда! Треба було хоч на коротку хвильку вискочити на вулицю, збігати на торговицю і вернути назад. А на вулиці і на торговиці крик, шум, гармидер, вози скриплять, колеса туркочуть, коні гримлять копитами, свині квичуть, жиди шваркочуть, селяни гойкають — одним словом, клекіт такий, якого наш Микита і в сні не бачив, і в гарячці не чував.\n" +
            "\n" +
            "Але що діяти! Наважився, то треба кінчити те, що зачав. Посидівши пару годин у бур’яні коло плоту, що притикав до вулиці, він освоївся трохи з тим гамором. Позбувшися першого страху, а надто роздивившися потроху, куди і як найліпше бігти, щоб осягнути свою ціль, Лис Микита набрав відваги, розбігся і одним духом скочив через пліт на вулицю. Вулицею йшло і їхало людей багато, стояла курява. Лиса мало хто й запримітив, і нікому до нього не було діла. «От Пес так Пес», — думали собі люди. А Микита тому й рад. Знітився, скулився та ровом як не чкурне просто на торговицю, де довгим рядом сиділи жінки, держачи на решетах, у кошах і кобелях на продаж яйця, масло, свіжі гриби, полотно, сім’я, курей, качок і інші такі гарні речі.\n" +
            "\n" +
            "Але не встиг він добігти до торговиці, коли йому настрічу біжить Пес, з іншого боку надбігає другий, там видить третього. Псів уже наш Микита не здурить. Зараз занюхали, хто він, загарчали та й як не кинуться до нього! Господи, яке страхіття! Наш Микита скрутився, мов муха в окропі: що тут робити? куди дітися? Недовго думаючи, він шмигнув у найближчі отворені сіни, а з сіней на подвір’я. Скулився тут і роздивляється, куди б то сховатися, а сам надслухує, чи не біжать пси. Ого! Чути їх! Уже близько!\n" +
            "\n" +
            "Бачить Лис, що на подвір’ї в куті стоїть якась діжа. От він, недовго думаючи, скік у діжу та й сховався.\n" +
            "\n" +
            "Щастя мав, бо ледве він щез у діжі, коли надбігли пси цілою купою, дзявкаючи, гарчачи, нюхаючи.\n" +
            "\n" +
            "— Тут він був! Тут він був! Шукайте його! — кричали передні.\n" +
            "\n" +
            "Ціла юрба кинулася по тісненькім подвір’ї, по всіх закутках, порпають, нюхають, дряпають — Лиса ані сліду нема. Кілька разів підходили й до діжі, але негарний запах, який ішов від неї, відгонював їх. Вкінці, не знайшовши нічого, вони побігли далі. Лис Микита був урятований.\n" +
            "\n" +
            "Урятований, але як!\n" +
            "\n" +
            "У діжі, що так несподівано стала йому в пригоді, було більше як до половини синьої, густо на олії розведеної фарби. Бачите, в тім домі жив маляр, що малював покої, паркани та садові лавки.\n" +
            "\n" +
            "Власне завтра мав малювати якийсь великий шмат паркану і відразу розробив собі цілу діжу фарби та й поставив її в куті на подвір’ї, щоб мав на завтра готову. Вскочивши в сю розчину, Лис Микита в першій хвилі занурився в неї з головою і мало не задушився. Але потім, діставши задніми ногами дна бочки, став собі так, що все його тіло було затоплене в фарбі, а тільки морда, також синьо помальована, трошечки вистирчала з неї. Отак він вичекав, поки минула страшна небезпека. Серце у бідолахи билося сильно, голод крутив кишки, запах олії майже душив його, але що було діяти! Слава богу, що живий! Та й то ще хто знає, що буде. Ану ж надійде господар бочки і застане його тут?\n" +
            "\n" +
            "Але й на се не було ради. Майже вмираючи зо страху, бідний Лис Микита мусив сидіти в фарбі тихо аж до вечора, знаючи добре, що якби тепер, у такім строї, появився на вулиці, то вже не пси, але люди кинуться за ним і не пустять його живого. Аж коли смерклося, Лис Микита прожогом вискочив із своєї незвичайної купелі, перебіг вулицю і, не спостережений ніким, ускочив до садка, а відси бур’янами, через плоти, через капусти та кукурудзи чкурнув до лісу. Довго ще тяглися за ним сині сліди, поки фарба не обтерлася трохи або не висхла. Вже добре стемнілося, коли Микита добіг до лісу, і то не в тім боці, де була його хата, а геть у противнім. Був голодний, змучений, ледве живий. Додому треба було ще бігти зо дві милі, але на се у нього не ставало вже сили. Тож, покріпившися трохи кількома яйцями, що знайшов у гнізді Перепелиці, він ускочив у першу-ліпшу порожню нору, розгорнув листя, зарився у ньому з головою і заснув справді, як по купелі.\n" +
            "\n" +
            "Чи пізно, чи рано встав він на другий день, сього вже в книгах не записано, — досить, що, вставши з твердого сну, позіхнувши смачно і сплюнувши тричі в той бік, де вчора була йому така немила пригода, він обережненько, лисячим звичаєм, виліз із нори. Глип-глип! Нюх-нюх! Усюди тихо, спокійно, чисто. Заграло серце в лисячій груді. «Саме добра пора на полювання»! — подумав. Але в тій хвилі зирнув на себе — господи! Аж скрикнув неборачисько. А се що таке? З переляку він кинувся тікати, але ба, сам від себе не втечеш! Зупинився і знов придивляється: та невже се я сам? Невже се моя шерсть, мій хвіст, мої ноги? Ні, не пізнає, не пізнає, та й годі! Якийсь дивний і страшний звір, синій-синій, з препоганим запахом, покритий не то лускою, не то якимись колючими гудзами, не то їжовими колючками, а хвіст у нього — не хвіст, а щось таке величезне а важке, мов довбня або здоровий ступернак, і також колюче.\n" +
            "\n" +
            "Став мій Лис, оглядає те чудовище, що зробилося з нього, обнюхує, пробує обтріпатися — не йде. Пробує обкачатися в траві — не йде! Пробує дряпати з себе ту луску пазурами — болить, але не пускає! Пробує лизати — не йде! Надбіг до калюжі, скочив у воду, щоб обмитися з фарби, — де тобі! Фарба олійна, через ніч у теплі засохла добре, не пускає. Роби що хочеш, небоже Микито!\n" +
            "\n" +
            "В тій хвилі де не взявся Вовчик-братик. Він ще вчора був добрий знайомий нашого Микити, але тепер, побачивши нечуваного синього звіра, всього в колючках та гудзах і з таким здоровенним, мов із міді вилитим, хвостом, він аж завив з переляку, а отямившися, як не пішов удирати — ледве хлипає! Подибав у лісі Вовчицю, далі Ведмедя, Кабана, Оленя — всі його питають, що з ним, чого так утікає, а він тільки хлипає, баньки витріщив та, знай, тільки лепоче:\n" +
            "\n" +
            "— Он там! Он там! Ой, та й страшне ж! Ой! та й люте ж!\n" +
            "\n" +
            "— Та що, що таке? — допитують його свояки.\n" +
            "\n" +
            "— Не знаю! Не знаю! Ой, та й страшенне ж!\n" +
            "\n" +
            "Що за диво! Зібралося довкола чимало звіра, заспокоюють його, дали води напитися. Малпа Фрузя вистригла йому три жміньки волосся з-між очей і пустила на вітер, щоб так і його переполох розвіявся, але де тобі, все дарма. Бачачи, що з Вовком непорадна година, звірі присудили йти їм усім у той бік, де показував Вовк, і подивитися, що там таке страшне. Підійшли до того місця, де все ще крутився Лис Микита, зирнули собі ж та й кинулися врозтіч. Де ж пак! Такого звіра ні видано ні чувано, відколи світ світом і ліс лісом. А хто там знає, яка у нього сила, які в нього зуби, які кігті і яка його воля?\n" +
            "\n" +
            "Хоч і як тяжко турбувався Лис Микита своєю новою подобою, а все-таки він добре бачив, яке вражіння зробила та його подоба зразу на Вовка, а отеє тепер і на інших звірів.\n" +
            "\n" +
            "«Гей,— подумав собі хитрий Лис,— та се не кепсько, що вони мене так бояться! Так можна добре виграти. Стійте лишень, я вам покажу себе»!\n" +
            "\n" +
            "І, піднявши вгору хвіст, надувшися гордо, він пішов у глиб лісу, де знав, що є місце сходин для всеї лісової людності. Тим часом гомін про нового нечуваного і страшного звіра розійшовся геть по лісі. Всі звірі, що жили в тім лісі, хотіли хоч здалека придивитися новому гостеві, але ніхто не смів приступити ближче. А Лис Микита мов і не бачить сього, йде собі поважно, мов у глибокій задумі, а прийшовши насеред звірячого майдану, сів на тім пеньку, де звичайно любив сідати Ведмідь. Сів і жде. Не минуло й півгодини, як довкола майдану нагромадилося звірів і птахів видимо-невидимо. Всі цікаві знати, що се за поява, і всі бояться її, ніхто не сміє приступити до неї. Стоять здалека, тремтять і тільки чекають хвилі, щоб дати драпака.\n" +
            "\n" +
            "Тоді Лис перший заговорив до них ласкаво:";
    private ImageCrossMethod imageCrossMethod = new ImageCrossMethod();

    public ResearchApp() {
        initUI();
    }

    public void start() {
        setVisible(true);
    }

    private void initUI() {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        XYDataset dataset1 = createDataset1();
        XYDataset dataset2 = createDataset2();
        XYDataset dataset3 = createDataset3();
        ChartPanel chartPanel1 = new ChartPanel(createChart1(dataset1));
        ChartPanel chartPanel2 = new ChartPanel(createChart2(dataset2));
        ChartPanel chartPanel3 = new ChartPanel(createChart3(dataset3));
        chartPanel1.setBackground(Color.white);
        add(chartPanel1);
        add(chartPanel2);
        add(chartPanel3);
        pack();
        setTitle("Test");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset1() {
        var series = new XYSeries("V(i,1)");
        for (double i = 0.01; i <= 0.5; i += 0.01) {
            imageCrossMethod.setEnergyBuiltInBit(i);
            int v = 0;
            String mb = imageCrossMethod.toBinaryString(TEST_TEXT);
            TEST_IMAGE = imageCrossMethod.encode(TEST_IMAGE, TEST_TEXT);

            String m3 = imageCrossMethod.decode(TEST_IMAGE).substring(0, TEST_TEXT.length());
            String mb3 = imageCrossMethod.toBinaryString(m3);
            for (int j = 0; j < mb.length(); j++) {
                if (mb3.charAt(j) == mb.charAt(j)) {
                    v++;
                }
            }
            vs.add((double) v / mb3.length());
            series.add(i, (double) v / mb3.length());
        }


        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset2() {
        var series = new XYSeries("W(i,1)");
        for (double i = 0.01; i <= 0.5; i += 0.01) {
            imageCrossMethod.setEnergyBuiltInBit(i);
            TEST_IMAGE = imageCrossMethod.encode(TEST_IMAGE, TEST_TEXT);
            double w = 0;
            int c = 0;
            for (int j = imageCrossMethod.getSizeOfPredictionArea(); j + imageCrossMethod.getSizeOfPredictionArea() < TEST_IMAGE.getHeight(); j++) {
                for (int k = j; k + imageCrossMethod.getSizeOfPredictionArea() < TEST_IMAGE.getWidth(); k += imageCrossMethod.getSizeOfPredictionArea()) {
                    w += Math.abs(new Color(TEST_IMAGE.getRGB(k, j)).getBlue() - new Color(bufferedImage.getRGB(k, j)).getBlue());
                    c++;
                    if (c + 1 == TEST_TEXT.length() * 8) {
                        break;
                    }
                }
                if (c + 1 == TEST_TEXT.length() * 8) {
                    break;
                }
            }
            w = w * 100 / (c * 256);
            ws.add(w);
            series.add(i, w);
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset3() {
        var series = new XYSeries("W(i,1)");
        for (int i = 0; i < ws.size(); i++) {
            series.add(vs.get(i), ws.get(i));
        }
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private JFreeChart createChart3(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "V(i,0)",
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

    private JFreeChart createChart1(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cross method probability discover",
                "V(i,0)",
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
                "W(i,0)",
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
