package Main;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class Main {

    public static MainGui mainGui;
    public static File file;
    public static double X = -1;
    public static double Y = -1;
    public static DocumentBuilderFactory documentBuilderFactory;
    public static DocumentBuilder documentBuilder;
    public static String url;

    public static void main(String[] args) {
        url = System.getenv("APPDATA") + "\\Mathematics";
        if (!new File(url).exists()) {
            new File(url).mkdir();
        }
        url += "\\";
        createFileIfNotExist();
        mainGui = new MainGui();
        makeApp(mainGui);
    }

    public static void removeFile(String s) {
        File file = new File(s);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void createFileIfNotExist() {
        file = new File(url + "data.xml");
        File fileResource = new File(Main.class.getResource("/data.xml").getFile());
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                Files.copy(fileResource.toPath(), fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        updateFileIfNotExist();
    }

    public static void updateFile(String element, int index, String node, String value) throws IOException, SAXException {
        Document document = documentBuilder.parse(file);
        Node time = document.getElementsByTagName(element).item(0);
        NodeList level = time.getChildNodes();
        for (int i = 0; i < level.getLength(); i++) {
            if (level.item(i).getNodeName().equalsIgnoreCase(node)) {
                level.item(i).setTextContent(value);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String s, int index) {
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException e) {
            removeFile(url + "data.xml");
            createFileIfNotExist();
            return readFile(s, index);
        } catch (IOException e) {
            removeFile(url + "data.xml");
            createFileIfNotExist();
            return readFile(s, index);
        }
        return document.getElementsByTagName(s).item(index).getTextContent();
    }

    public static void updateFileIfNotExist() {
        try {
            String[] level = new String[]{
                    readFile("level1", 0),
                    readFile("level2", 0),
                    readFile("level3", 0),
                    readFile("level4", 0),
                    readFile("level5", 0),
                    readFile("level6", 0)
            };
            for (int i = 0; i < level.length; i++) {
                if (level[i] == null || !level[i].matches("[0-9]+")) {
                    updateFile("quickmath", 0, level[i], "-1");
                }
            }
            if (readFile("diendau", 0) == null || !readFile("diendau", 0).matches("[0-9]+")) {
                updateFile("quickmath", 0, "diendau", "-1");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static int getData(String s) {
        updateFileIfNotExist();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            if (properties.get(s) == null) return -1;
            else return Integer.parseInt((String) properties.get(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void makeApp(JFrame frame) {
        frame.setSize(500, 500);
        setLocation(frame);
        ImageIcon imageIcon = new ImageIcon(frame.getClass().getResource("/Images/icon_logo.png"));
        frame.setIconImage(imageIcon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void setLocation(JFrame frame) {
        if (X == -1 || Y == -1) {
            int w = frame.getWidth();
            int h = frame.getHeight();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = screenSize.getSize().width;
            int y = screenSize.getSize().height;
            Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
            X = (x - w - scnMax.left - scnMax.right) / 2;
            Y = (y - h - scnMax.top - scnMax.bottom) / 2;
        }
        Point point = new Point();
        point.setLocation(X, Y);
        frame.setLocation(point);
    }

    public static String convertToTime(int time) {
        if (time <= 0) return "00:00";
        String s = "";
        if (time / 60 > 0) {
            int minute = time / 60;
            if (minute < 10) s = "0" + minute;
            else s = "" + minute;
        } else {
            s = "00";
        }
        s += ":";
        int second = time % 60;
        if (second < 10) s += "0" + second;
        else s += second;
        return s;
    }

    public static int getMaxValueOfArray(Integer[] integers) {
        int maxNumber = Integer.MIN_VALUE;
        for (int i : integers) {
            maxNumber = Math.max(maxNumber, i);
        }
        return maxNumber;
    }

    public static int phepTinhGeneration(Integer[] phepTinh) {
        Random random = new Random();
        int chance = random.nextInt(100);
        for (int i = 0; i < phepTinh.length; i++) {
            if (chance < phepTinh[i]) {
                return i;
            }
            chance = random.nextInt(100);
        }
        for (int i = 0; i < phepTinh.length; i++) {
            if (phepTinh[i] != 0) return i;
        }
        return 0;
    }

    public static int rangeRandom(int from, int to) {
        return new Random().nextInt(to - from) + from + 1;
    }

    public static float caculatorMath(Integer[] soBieuThucTemp, Integer[] phepTinhBieuThucTemp) {
        float result = 0;
        float previous = 0;

        int[] soBieuThuc = new int[soBieuThucTemp.length + 1];
        int[] phepTinhBieuThuc = new int[phepTinhBieuThucTemp.length + 1];

        Arrays.fill(soBieuThuc, 0);
        Arrays.fill(phepTinhBieuThuc, 0);

        for (int i = 0; i < soBieuThucTemp.length; i++) {
            soBieuThuc[i] = soBieuThucTemp[i];
        }

        for (int i = 0; i < phepTinhBieuThucTemp.length; i++) {
            phepTinhBieuThuc[i] = phepTinhBieuThucTemp[i];
        }

        for (int i = 0; i < soBieuThucTemp.length; i++) {
            int nextPhepTinh = phepTinhBieuThuc[i + 1];
            int currentPhepTinh = phepTinhBieuThuc[i];
            if (nextPhepTinh == 2 || nextPhepTinh == 3) {
                if (previous == 0) {
                    if (currentPhepTinh == 0) previous += soBieuThuc[i];
                    if (currentPhepTinh == 1) previous -= soBieuThuc[i];
                } else {
                    if (nextPhepTinh == 2) previous *= soBieuThuc[i];
                    if (nextPhepTinh == 3) previous /= soBieuThuc[i];
                }
            } else {
                if (previous != 0) {
                    if (currentPhepTinh == 2) previous *= soBieuThuc[i];
                    if (currentPhepTinh == 3) previous /= soBieuThuc[i];
                    result += previous;
                    previous = 0;
                }
                if (currentPhepTinh == 0) {
                    result += soBieuThuc[i];
                } else if (currentPhepTinh == 1) {
                    result -= soBieuThuc[i];
                }
            }
        }
        return result;
    }

    public static void changeJFrame(JFrame jFrame1, JFrame jFrame2) {
        X = jFrame1.getLocation().getX();
        Y = jFrame1.getLocation().getY();
        Main.makeApp(jFrame2);
        jFrame1.dispose();
    }

    public static class TinhNhanh {

        public static ArrayList<Integer[]> generationMath(Integer[] soPhepTinh, Integer[] phepTinh, boolean hieuDuong, boolean chiaHet, int from, int to) {
            Random random = new Random();
            ArrayList<Integer[]> arrayList = new ArrayList<Integer[]>();

            int amountPhepTinh = soPhepTinh[random.nextInt(soPhepTinh.length)];
            Integer[] soBieuThuc = new Integer[amountPhepTinh]; // Lưu Số Từng Biểu Thức
            Integer[] phepTinhBieuThuc = new Integer[amountPhepTinh]; // Lưu Phép Tính Từng Biểu Thức

            Arrays.fill(soBieuThuc, 0);
            Arrays.fill(phepTinhBieuThuc, 0);


            for (int i = 0; i < amountPhepTinh; i++) {
                int phep = phepTinhGeneration(phepTinh);
                if (i == 0) {
                    phepTinhBieuThuc[i] = phepTinhGeneration(new Integer[]{0, 0, 0, 0});
                    soBieuThuc[i] = rangeRandom(from, to);
                } else {
                    if (phep == 1) {
                        if (hieuDuong) {
                            int so = rangeRandom(from, to);
                            while (so > soBieuThuc[i - 1]) {
                                so = rangeRandom(from, to);
                            }
                            soBieuThuc[i] = so;
                        } else {
                            soBieuThuc[i] = rangeRandom(from, to);
                        }
                    } else if (phep == 3) {
                        if (chiaHet) {
                            int current = soBieuThuc[i - 1];
                            int next = current;
                            for (int j = current; j >= from; j--) {
                                if (j != 0 && current % j == 0) {
                                    if (random.nextInt(100) < 50) {
                                        next = j;
                                    }
                                }
                            }
                            soBieuThuc[i] = next;
                        } else {
                            soBieuThuc[i] = rangeRandom(from, to);
                        }
                    } else {
                        soBieuThuc[i] = rangeRandom(from, to);
                    }
                    phepTinhBieuThuc[i] = phep;
                }
            }

            arrayList.add(soBieuThuc);
            arrayList.add(phepTinhBieuThuc);

            return arrayList;
        }
    }

    public static class DienDau {

        public static ArrayList<Integer[]> generationMath(Integer[] soPhepTinh, Integer[] phepTinh, Integer[] chuSoPhepTinhFrom, Integer[] chuSoPhepTinhTo) {
            Random random = new Random();
            ArrayList<Integer[]> arrayList = new ArrayList<Integer[]>();
            int amountPhepTinh = soPhepTinh[random.nextInt(soPhepTinh.length)];
            Integer[] soBieuThuc = new Integer[amountPhepTinh]; // Lưu Số Từng Biểu Thức
            Integer[] phepTinhBieuThuc = new Integer[amountPhepTinh]; // Lưu Phép Tính Từng Biểu Thức

            Arrays.fill(soBieuThuc, 0);
            Arrays.fill(phepTinhBieuThuc, 0);


            for (int i = 0; i < amountPhepTinh; i++) {
                int phep = phepTinhGeneration(phepTinh);
                if (i == 0) {
                    phepTinhBieuThuc[i] = 0;
                } else {
                    phepTinhBieuThuc[i] = phep;
                }
                if (phep == 3 && i > 0) {
                    int current = soBieuThuc[i - 1];
                    int next = current;
                    for (int j = current; j >= phepTinhBieuThuc[i]; j--) {
                        if (j != 0 && current % j == 0) {
                            if (random.nextInt(100) < 50) {
                                next = j;
                            }
                        }
                    }
                    soBieuThuc[i] = next;
                } else {
                    soBieuThuc[i] = rangeRandom(chuSoPhepTinhFrom[phep], chuSoPhepTinhTo[phep]);
                }
            }

            arrayList.add(soBieuThuc);
            arrayList.add(phepTinhBieuThuc);

            return arrayList;
        }

    }

}
