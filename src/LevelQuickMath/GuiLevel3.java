/*
 * Created by JFormDesigner on Tue Dec 25 16:41:11 ICT 2018
 */

package LevelQuickMath;

import Main.Main;
import TypeMath.TinhNhanhGui;
import Utils.DialogMessage;
import Utils.StateGame;
import Utils.StateLevel;
import Utils.TypeDialog;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimerTask;

/**
 * @author Bảo
 */
public class GuiLevel3 extends JFrame {

    public static int maxTime = 50;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bảo
    public JPanel panel1;
    public JPanel panel2;
    public JTextField textField1;
    public JPanel panel3;
    public JLabel label1;
    public JPanel panel4;
    public JLabel label3;
    public JPanel panel5;
    public JLabel label4;
    public JPanel panel6;
    public JButton button1;
    public JLabel label2;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton button5;
    public JButton button6;
    public JButton button7;
    public JButton button8;
    public JButton button9;
    public JButton button10;
    public JButton button11;
    public JButton button12;
    public JButton button13;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    int soCau = 1;
    int maxCau = 10;
    int soPhepTinh = 2;
    float result = 0;
    Timer timer;
    StateGame stateGame;
    StateLevel stateLevel;
    DialogMessage dialogMessage;

    public GuiLevel3() {
        stateGame = StateGame.WAIT;
        stateLevel = StateLevel.LEVEL3;
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(stateLevel.getTitle());
        label3.setText("THỜI GIAN: " + Main.convertToTime(maxTime));
        maxTime++;
        dialogMessage = new DialogMessage(this);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxTime--;
                label3.setText("THỜI GIAN: " + Main.convertToTime(maxTime));
                if (maxTime == 0) {
                    timer.stop();
                    dialogMessage.addDialog(TypeDialog.CROSS, "Bạn đã không vượt qua vòng này. Thử lại sau :D");
                    dialogMessage.showDialog();
                    String result = String.valueOf((int) GuiLevel3.this.result);
                    for (int i = 0; i < result.length(); i++) {
                        addResult(result.charAt(i), true);
                    }
                    stateGame = StateGame.FINISH;
                }
            }

        });
        stateGame = StateGame.GAME;
        timer.start();
        result = generation();
    }

    public float generation() {
        ArrayList<Integer[]> arrayList = new ArrayList<Integer[]>();
        Integer[] bieuThuc;
        Integer[] phepTinh;
        if (soCau <= 5) {
            arrayList = Main.TinhNhanh.generationMath(new Integer[]{2}, new Integer[]{50, 50, 0, 0}, false, false, 0, 15);
        } else {
            arrayList = Main.TinhNhanh.generationMath(new Integer[]{2}, new Integer[]{50, 50, 0, 0}, false, false, 15, 30);
        }
        bieuThuc = arrayList.get(0);
        phepTinh = arrayList.get(1);
        String s = "";
        String[] typePhepTinh = new String[]{"+", "-", "×", "÷"};

        s += bieuThuc[0];
        for (int i = 1; i < bieuThuc.length; i++) {
            s += " " + typePhepTinh[phepTinh[i]] + " " + bieuThuc[i];
        }
        s += " = ";

        textField1.setForeground(Color.BLUE);
        textField1.setText(s);
        label4.setText("S\u1ed0 C\u00c2U: " + soCau + "/" + maxCau);

        return Main.caculatorMath(bieuThuc, phepTinh);
    }

    public void button1ActionPerformed(ActionEvent e) {
        TinhNhanhGui tinhNhanhGui = new TinhNhanhGui();
        timer.stop();
        Main.changeJFrame(this, tinhNhanhGui);
    }

    public boolean check() {
        String[] s = textField1.getText().split(" = ");
        if (s.length == 2) {
            String text = s[1];
            try {
                float resultTemp = Float.parseFloat(text);
                if (resultTemp == result) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean updateTime(int finish) {
        try {
            int current = Integer.parseInt(Main.readFile(stateLevel.toString().toLowerCase(), 0));
            if (finish > current) {
                Main.updateFile("quickmath", 0, stateLevel.toString().toLowerCase(), "" + finish);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addResult(char key, boolean b) {
        if (stateGame != StateGame.GAME) return;
        if (key == '-') {
            String[] s = textField1.getText().split(" = ");
            if (s.length >= 2) {
                return;
            } else {
                textField1.setText(textField1.getText() + key);
                return;
            }
        }
        textField1.setText(textField1.getText() + key);
        if (!b && check()) {
            if (soCau == maxCau) {
                stateGame = StateGame.FINISH;
                textField1.setForeground(Color.GREEN);
                textField1.setText("CHÚC MỪNG BẠN!");
                dialogMessage.addDialog(TypeDialog.SUCCESS, "<html> Bạn đã hoàn thành vòng này <br> <center> Thời gian còn lại: " + Main.convertToTime(maxTime) + " </center> </html>");
                dialogMessage.showDialog();
                timer.stop();
                Properties properties = new Properties();
                int time = Main.getData(stateLevel.toString());
                if (updateTime(maxTime)) {
                    dialogMessage.addDialog(TypeDialog.INFORMATION, "<html> Chức mừng bạn đã phá vỡ kỉ lục trước <br> <center> ( " + Main.convertToTime(time) + " -> " + Main.convertToTime(maxTime) + ") </center> </html>");
                }
            } else {
                soCau++;
                result = generation();
                label2.setText("CHÍNH XÁC !!!");
                label2.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-ok-16.png")));
                new java.util.Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        label2.setText("");
                        label2.setIcon(null);
                    }
                }, 1000);
            }
        }
    }

    public void removeResult() {
        if (stateGame != StateGame.GAME) return;
        String s = textField1.getText();
        try {
            if (s.charAt(s.length()) != ' ')
                textField1.setText(textField1.getText().substring(0, textField1.getText().length() - 1));
        } catch (StringIndexOutOfBoundsException event) {
            if (s.charAt(s.length() - 1) != ' ')
                textField1.setText(textField1.getText().substring(0, textField1.getText().length() - 1));
        }
        if (check()) {
            if (soCau == maxCau) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Bạn đã vượt qua vòng. Chúc mừng bạn. Thời gian còn lại: " + Main.convertToTime(maxTime), "Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
            } else {
                soCau++;
                result = generation();
                label2.setText("CHÍNH XÁC !!!");
                label2.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-ok-16.png")));
                new java.util.Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        label2.setText("");
                        label2.setIcon(null);
                    }
                }, 1000);
            }
        }
    }

    public void textField1KeyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if (key >= '0' && key <= '9') {
            if (key == '0') button11.doClick();
            else if (key == '1') button2.doClick();
            else if (key == '2') button3.doClick();
            else if (key == '3') button4.doClick();
            else if (key == '4') button5.doClick();
            else if (key == '5') button6.doClick();
            else if (key == '6') button7.doClick();
            else if (key == '7') button8.doClick();
            else if (key == '8') button9.doClick();
            else if (key == '9') button10.doClick();
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            button12.doClick();
        }
        if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == 109) {
            button13.doClick();
        }
    }

    public void button2ActionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.equals(button11)) {
            addResult('0', false);
        } else if (button.equals(button2)) {
            addResult('1', false);
        } else if (button.equals(button3)) {
            addResult('2', false);
        } else if (button.equals(button4)) {
            addResult('3', false);
        } else if (button.equals(button5)) {
            addResult('4', false);
        } else if (button.equals(button6)) {
            addResult('5', false);
        } else if (button.equals(button7)) {
            addResult('6', false);
        } else if (button.equals(button8)) {
            addResult('7', false);
        } else if (button.equals(button9)) {
            addResult('8', false);
        } else if (button.equals(button10)) {
            addResult('9', false);
        } else if (button.equals(button12)) {
            removeResult();
        } else if (button.equals(button13)) {
            addResult('-', false);
        }
    }

    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bảo
        panel1 = new JPanel();
        panel2 = new JPanel();
        textField1 = new JTextField();
        panel3 = new JPanel();
        label1 = new JLabel();
        panel4 = new JPanel();
        label3 = new JLabel();
        panel5 = new JPanel();
        label4 = new JLabel();
        panel6 = new JPanel();
        button1 = new JButton();
        label2 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        button13 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 204, 255));

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });

            panel1.setLayout(null);

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- textField1 ----
                textField1.setEditable(false);
                textField1.setFont(new Font("Segoe UI", Font.BOLD, 22));
                textField1.setHorizontalAlignment(SwingConstants.CENTER);
                textField1.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        textField1KeyReleased(e);
                    }
                });
                panel2.add(textField1);
                textField1.setBounds(0, 0, 500, 50);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel2);
            panel2.setBounds(0, 105, 500, 50);

            //======== panel3 ========
            {
                panel3.setBackground(new Color(204, 255, 204));
                panel3.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
                panel3.setLayout(null);

                //---- label1 ----
                label1.setText("C\u1ea4P \u0110\u1ed8 3");
                label1.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label1.setBackground(new Color(204, 255, 204));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                label1.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-star-16.png")));
                panel3.add(label1);
                label1.setBounds(0, 0, 115, 35);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel3);
            panel3.setBounds(15, 15, 115, 35);

            //======== panel4 ========
            {
                panel4.setBackground(new Color(204, 255, 204));
                panel4.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
                panel4.setLayout(null);

                //---- label3 ----
                label3.setText("TH\u1edcI GIAN: 00:00");
                label3.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label3.setBackground(new Color(204, 255, 204));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(label3);
                label3.setBounds(0, 0, 200, 35);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel4.getComponentCount(); i++) {
                        Rectangle bounds = panel4.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel4.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel4.setMinimumSize(preferredSize);
                    panel4.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel4);
            panel4.setBounds(285, 20, 200, 35);

            //======== panel5 ========
            {
                panel5.setBackground(new Color(204, 255, 204));
                panel5.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
                panel5.setLayout(null);

                //---- label4 ----
                label4.setText("S\u1ed0 C\u00c2U: 0/10");
                label4.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label4.setBackground(new Color(204, 255, 204));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(label4);
                label4.setBounds(0, 0, 200, 35);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel5.getComponentCount(); i++) {
                        Rectangle bounds = panel5.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel5.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel5.setMinimumSize(preferredSize);
                    panel5.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel5);
            panel5.setBounds(285, 60, 200, 35);

            //======== panel6 ========
            {
                panel6.setBackground(new Color(204, 255, 204));
                panel6.setLayout(null);

                //---- button1 ----
                button1.setText("TR\u1ede V\u1ec0");
                button1.setIcon(new ImageIcon(getClass().getResource("/Images/back.png")));
                button1.setFont(new Font("Segoe UI", Font.BOLD, 13));
                button1.setBackground(new Color(204, 255, 204));
                button1.addActionListener(e -> button1ActionPerformed(e));
                panel6.add(button1);
                button1.setBounds(0, 0, 115, 35);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel6.getComponentCount(); i++) {
                        Rectangle bounds = panel6.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel6.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel6.setMinimumSize(preferredSize);
                    panel6.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel6);
            panel6.setBounds(15, 420, 115, 35);

            //---- label2 ----
            label2.setForeground(Color.green);
            label2.setFont(new Font("Segoe UI", Font.BOLD, 16));
            panel1.add(label2);
            label2.setBounds(10, 80, 200, 20);

            //---- button2 ----
            button2.setBackground(new Color(187, 222, 251));
            button2.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-1-50.png")));
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(155, 165, 50, 50);

            //---- button3 ----
            button3.setBackground(new Color(187, 222, 251));
            button3.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-2-50.png")));
            button3.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button3);
            button3.setBounds(220, 165, 50, 50);

            //---- button4 ----
            button4.setBackground(new Color(187, 222, 251));
            button4.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-3-50.png")));
            button4.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button4);
            button4.setBounds(285, 165, 50, 50);

            //---- button5 ----
            button5.setBackground(new Color(187, 222, 251));
            button5.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-4-50.png")));
            button5.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button5);
            button5.setBounds(155, 225, 50, 50);

            //---- button6 ----
            button6.setBackground(new Color(187, 222, 251));
            button6.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-5-50.png")));
            button6.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button6);
            button6.setBounds(220, 225, 50, 50);

            //---- button7 ----
            button7.setBackground(new Color(187, 222, 251));
            button7.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-6-50.png")));
            button7.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button7);
            button7.setBounds(285, 225, 50, 50);

            //---- button8 ----
            button8.setBackground(new Color(187, 222, 251));
            button8.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-7-50.png")));
            button8.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button8);
            button8.setBounds(155, 285, 50, 50);

            //---- button9 ----
            button9.setBackground(new Color(187, 222, 251));
            button9.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-8-50.png")));
            button9.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button9);
            button9.setBounds(220, 285, 50, 50);

            //---- button10 ----
            button10.setBackground(new Color(187, 222, 251));
            button10.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-9-50.png")));
            button10.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button10);
            button10.setBounds(285, 285, 50, 50);

            //---- button11 ----
            button11.setBackground(new Color(187, 222, 251));
            button11.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-0-50.png")));
            button11.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button11);
            button11.setBounds(220, 345, 50, 50);

            //---- button12 ----
            button12.setBackground(new Color(187, 222, 251));
            button12.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-back-arrow-50 (1).png")));
            button12.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button12);
            button12.setBounds(285, 345, 50, 50);

            //---- button13 ----
            button13.setBackground(new Color(187, 222, 251));
            button13.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-minus.png")));
            button13.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button13);
            button13.setBounds(155, 345, 50, 50);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 500, 500);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}

