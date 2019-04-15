/*
 * Created by JFormDesigner on Sat Feb 09 18:34:32 ICT 2019
 */

package TypeMath;

import Main.Main;
import Main.MainGui;
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
 * @author Trần Gia
 */
public class DienDau extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bảo
    public JPanel panel1;
    public JPanel panel3;
    public JButton button1;
    public JPanel panel2;
    public JTextField textField1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton button5;
    public JPanel panel4;
    public JLabel label3;
    public JPanel panel5;
    public JLabel label4;
    public JButton button12;
    public JButton button6;
    public JLabel label2;
    public JPanel panel6;
    public JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public Integer[] soBieuThuc;
    public Integer[] phepTinhBieuThuc;
    int maxTime = 120;
    int soCau = 1;
    int maxCau = 20;
    float result = 0;
    Timer timer;
    StateGame stateGame;
    StateLevel stateLevel;
    long delay = 0;
    DialogMessage dialogMessage;

    public DienDau() {
        initComponents();
        button1.setFocusPainted(false);
        button2.setFocusPainted(false);
        button3.setFocusPainted(false);
        button4.setFocusPainted(false);
        button5.setFocusPainted(false);
        button6.setFocusPainted(false);
        stateGame = StateGame.WAIT;
        stateLevel = StateLevel.DIENDAU;
        label3.setText("THỜI GIAN: " + Main.convertToTime(maxTime));
        maxTime++;
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle(stateLevel.getTitle());
        dialogMessage = new DialogMessage(this);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxTime--;
                label3.setText("THỜI GIAN: " + Main.convertToTime(maxTime));
                label4.setText("SỐ CÂU: " + soCau + "/" + maxCau);
                if (maxTime <= 0) {
                    timer.stop();
                    dialogMessage.addDialog(TypeDialog.CROSS, "Bạn đã không vượt qua vòng này. Thử lại sau :D");
                    dialogMessage.showDialog();
                    for (int i = 0; i < phepTinhBieuThuc.length; i++) {
                        fillDau(phepTinhBieuThuc[i], true);
                    }
                    stateGame = StateGame.FINISH;
                }
            }

        });
        stateGame = StateGame.GAME;
        timer.start();
        result = generation();
    }

    public void button1ActionPerformed(ActionEvent e) {
        MainGui mainGui = new MainGui();
        Main.changeJFrame(this, mainGui);
    }

    public void fillDau(int type, boolean b) {
        String[] typePhepTinh = new String[]{"+", "-", "×", "÷"};
        String phepTinh = typePhepTinh[type];
        String text = textField1.getText();
        if (text.contains("_")) {
            text = text.replaceFirst("\\_", phepTinh);
            textField1.setText(text);
            if (!text.contains("_") && !b) {
                check();
            }
        } else {
            if (!b) check();
        }
    }

    public int convertDauToNumber(char s) {
        String[] typePhepTinh = new String[]{"+", "-", "×", "÷"};
        for (int i = 0; i < typePhepTinh.length; i++) {
            if (typePhepTinh[i].equalsIgnoreCase(String.valueOf(s))) return i;
        }
        return 0;
    }

    public void removeDau() {
        String text = textField1.getText();
        String[] s = text.split(" = ");
        int cong = s[0].lastIndexOf("+");
        int tru = s[0].lastIndexOf("-");
        int nhan = s[0].lastIndexOf("×");
        int chia = s[0].lastIndexOf("÷");
        int max = Math.max(cong, Math.max(tru, Math.max(nhan, chia)));
        if (max == -1) return;
        s[0] = s[0].substring(0, max) + "_" + s[0].substring(max + 1);
        textField1.setText(s[0] + " = " + s[1]);
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

    public void check() {
        Integer[] phepTinhBieuThuc = new Integer[soBieuThuc.length];
        phepTinhBieuThuc[0] = 0;
        int j = 0;
        String text = textField1.getText();
        String[] s = text.split(" = ");
        s[0] = s[0].replaceAll("[0-9]+", "");
        s[0] = s[0].replaceAll("\\s+", "");
        for (int i = 0; i < s[0].length(); i++) {
            j++;
            phepTinhBieuThuc[j] = convertDauToNumber(s[0].charAt(i));
        }

        if (Main.caculatorMath(soBieuThuc, phepTinhBieuThuc) == result) {
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
                label2.setForeground(Color.GREEN);
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

    public void button2ActionPerformed(ActionEvent e) {
        if (stateGame != StateGame.GAME) return;
        fillDau(0, false);
    }

    public void button3ActionPerformed(ActionEvent e) {
        if (stateGame != StateGame.GAME) return;
        fillDau(1, false);
    }

    public void button4ActionPerformed(ActionEvent e) {
        if (stateGame != StateGame.GAME) return;
        fillDau(2, false);
    }

    public void button5ActionPerformed(ActionEvent e) {
        if (stateGame != StateGame.GAME) return;
        fillDau(3, false);
    }

    public void button12ActionPerformed(ActionEvent e) {
        if (stateGame != StateGame.GAME) return;
        removeDau();
    }

    public float generation() {
        if (stateGame != StateGame.GAME) return 0;
        ArrayList<Integer[]> arrayList = new ArrayList<Integer[]>();
        Integer[] soBieuThuc;
        Integer[] phepTinhBieuThuc;
        float result;

        if (soCau <= 3) {
            arrayList = Main.DienDau.generationMath(new Integer[]{2}, new Integer[]{60, 40, 0, 0}, new Integer[]{0, 0}, new Integer[]{9, 9});
        } else if (soCau <= 7) {
            arrayList = Main.DienDau.generationMath(new Integer[]{2, 3}, new Integer[]{50, 50, 0, 0}, new Integer[]{0, 0}, new Integer[]{14, 14});
        } else if (soCau <= 10) {
            arrayList = Main.DienDau.generationMath(new Integer[]{2, 3}, new Integer[]{40, 60, 0, 0}, new Integer[]{0, 0}, new Integer[]{19, 19});
        } else if (soCau <= 13) {
            arrayList = Main.DienDau.generationMath(new Integer[]{2, 3}, new Integer[]{30, 30, 40, 0}, new Integer[]{0, 0, 0}, new Integer[]{9, 9, 9});
        } else if (soCau <= 15) {
            arrayList = Main.DienDau.generationMath(new Integer[]{2, 3}, new Integer[]{20, 20, 60, 0}, new Integer[]{0, 0, 0}, new Integer[]{14, 14, 14});
        } else if (soCau <= 18) {
            arrayList = Main.DienDau.generationMath(new Integer[]{2, 3}, new Integer[]{10, 10, 40, 40}, new Integer[]{0, 0, 0, 0}, new Integer[]{9, 9, 9, 19});
        } else if (soCau <= 20) {
            arrayList = Main.DienDau.generationMath(new Integer[]{2, 3}, new Integer[]{0, 0, 40, 60}, new Integer[]{0, 0, 0, 0}, new Integer[]{14, 14, 14, 24});
        }

        soBieuThuc = arrayList.get(0);
        this.soBieuThuc = soBieuThuc;
        phepTinhBieuThuc = arrayList.get(1);
        this.phepTinhBieuThuc = phepTinhBieuThuc;
        result = Main.caculatorMath(soBieuThuc, phepTinhBieuThuc);

        String s = "";
        for (int i = 0; i < soBieuThuc.length; i++) {
            s += soBieuThuc[i] + " _ ";
        }
        s = s.substring(0, s.length() - 3) + " = " + Math.round(result);

        textField1.setForeground(Color.BLUE);
        textField1.setText(s);
        label4.setText("S\u1ed0 C\u00c2U: " + soCau + "/" + maxCau);

        return result;
    }

    public void KeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT || key == 16) {
            delay = System.currentTimeMillis();
        }
        if (key == 61) {
            if (System.currentTimeMillis() - delay <= 50) {
                button2.doClick();
                return;
            }
        }
        if (key == KeyEvent.VK_PLUS || key == 107) {
            button2.doClick();
            return;
        }
        if (key == KeyEvent.VK_MINUS || key == 109) {
            button3.doClick();
            return;
        }
        if (key == KeyEvent.VK_MULTIPLY) {
            button4.doClick();
            return;
        }
        if (key == KeyEvent.VK_DIVIDE) {
            button5.doClick();
            return;
        }
        if (key == KeyEvent.VK_BACK_SPACE) {
            button12.doClick();
            return;
        }
    }

    public void button6ActionPerformed(ActionEvent e) {
        if (stateGame != StateGame.GAME) return;
        maxTime -= 3;
        if (maxTime > 0) {
            Color color = label2.getForeground();
            label2.setForeground(Color.RED);
            label2.setText("- 3 giây");
            result = generation();
            new java.util.Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    label2.setText("");
                    label2.setIcon(null);
                }
            }, 1000);
            label3.setText("THỜI GIAN: " + Main.convertToTime(maxTime));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bảo
        panel1 = new JPanel();
        panel3 = new JPanel();
        button1 = new JButton();
        panel2 = new JPanel();
        textField1 = new JTextField();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        panel4 = new JPanel();
        label3 = new JLabel();
        panel5 = new JPanel();
        label4 = new JLabel();
        button12 = new JButton();
        button6 = new JButton();
        label2 = new JLabel();
        panel6 = new JPanel();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 204, 255));
            panel1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    KeyReleased(e);
                }
            });

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

            //======== panel3 ========
            {
                panel3.setBackground(Color.white);
                panel3.setLayout(null);

                //---- button1 ----
                button1.setText("TR\u1ede V\u1ec0");
                button1.setIcon(new ImageIcon(getClass().getResource("/Images/back.png")));
                button1.setFont(new Font("Segoe UI", Font.BOLD, 13));
                button1.setBackground(Color.white);
                button1.addActionListener(e -> button1ActionPerformed(e));
                button1.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        KeyReleased(e);
                    }
                });
                panel3.add(button1);
                button1.setBounds(0, 0, 115, 35);

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
            panel3.setBounds(10, 425, 115, 35);

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
                        KeyReleased(e);
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

            //---- button2 ----
            button2.setText("+");
            button2.setFont(new Font("Ubuntu", Font.BOLD, 24));
            button2.setForeground(new Color(25, 118, 210));
            button2.setBackground(new Color(187, 222, 251));
            button2.addActionListener(e -> button2ActionPerformed(e));
            button2.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    KeyReleased(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(190, 190, 50, 50);

            //---- button3 ----
            button3.setFont(new Font("Ubuntu", Font.BOLD, 24));
            button3.setText("-");
            button3.setBackground(new Color(187, 222, 251));
            button3.setForeground(new Color(25, 118, 210));
            button3.addActionListener(e -> button3ActionPerformed(e));
            button3.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    KeyReleased(e);
                }
            });
            panel1.add(button3);
            button3.setBounds(255, 190, 50, 50);

            //---- button4 ----
            button4.setText("\u00d7");
            button4.setFont(new Font("Ubuntu", Font.BOLD, 24));
            button4.setBackground(new Color(187, 222, 251));
            button4.setForeground(new Color(25, 118, 210));
            button4.addActionListener(e -> button4ActionPerformed(e));
            button4.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    KeyReleased(e);
                }
            });
            panel1.add(button4);
            button4.setBounds(190, 255, 50, 50);

            //---- button5 ----
            button5.setText("\u00f7");
            button5.setFont(new Font("Ubuntu", Font.BOLD, 24));
            button5.setBackground(new Color(187, 222, 251));
            button5.setForeground(new Color(25, 118, 210));
            button5.addActionListener(e -> button5ActionPerformed(e));
            button5.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    KeyReleased(e);
                }
            });
            panel1.add(button5);
            button5.setBounds(255, 255, 50, 50);

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
                label3.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        KeyReleased(e);
                    }
                });
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
            panel4.setBounds(285, 10, 200, 35);

            //======== panel5 ========
            {
                panel5.setBackground(new Color(204, 255, 204));
                panel5.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
                panel5.setLayout(null);

                //---- label4 ----
                label4.setText("S\u1ed0 C\u00c2U: 0/20");
                label4.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label4.setBackground(new Color(204, 255, 204));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        KeyReleased(e);
                    }
                });
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
            panel5.setBounds(285, 55, 200, 35);

            //---- button12 ----
            button12.setBackground(new Color(187, 222, 251));
            button12.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-back-arrow-50 (1).png")));
            button12.setFont(new Font("Ubuntu", Font.PLAIN, 32));
            button12.addActionListener(e -> button12ActionPerformed(e));
            button12.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    KeyReleased(e);
                }
            });
            panel1.add(button12);
            button12.setBounds(255, 320, 50, 50);

            //---- button6 ----
            button6.setText("B\u1ece QUA");
            button6.setIcon(new ImageIcon(getClass().getResource("/Images/skip.png")));
            button6.setFont(new Font("Segoe UI", Font.BOLD, 13));
            button6.setBackground(Color.white);
            button6.setBorderPainted(false);
            button6.setHorizontalTextPosition(SwingConstants.LEFT);
            button6.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    KeyReleased(e);
                }
            });
            button6.addActionListener(e -> button6ActionPerformed(e));
            panel1.add(button6);
            button6.setBounds(370, 425, 115, 35);

            //---- label2 ----
            label2.setForeground(Color.green);
            label2.setFont(new Font("Segoe UI", Font.BOLD, 16));
            panel1.add(label2);
            label2.setBounds(10, 80, 200, 20);

            //======== panel6 ========
            {
                panel6.setBackground(new Color(204, 255, 204));
                panel6.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
                panel6.setLayout(null);

                //---- label1 ----
                label1.setText("\u0110I\u1ec0N D\u1ea4U");
                label1.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label1.setBackground(new Color(204, 255, 204));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                label1.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-star-16.png")));
                panel6.add(label1);
                label1.setBounds(0, 0, 115, 35);

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
            panel6.setBounds(15, 15, 115, 35);

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
