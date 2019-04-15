/*
 * Created by JFormDesigner on Mon Dec 24 21:29:16 ICT 2018
 */

package TypeMath;

import LevelQuickMath.*;
import Main.Main;
import Main.MainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Bảo
 */
public class TinhNhanhGui extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bảo
    public JPanel panel2;
    public JPanel panel8;
    public JLabel label7;
    public JLabel label15;
    public JLabel label16;
    public JLabel label21;
    public JLabel label31;
    public JPanel panel9;
    public JLabel label4;
    public JLabel label19;
    public JLabel label20;
    public JLabel label14;
    public JLabel label28;
    public JPanel panel5;
    public JLabel label3;
    public JLabel label9;
    public JLabel label10;
    public JLabel label11;
    public JLabel label27;
    public JPanel panel6;
    public JLabel label6;
    public JLabel label25;
    public JLabel label26;
    public JLabel label24;
    public JLabel label32;
    public JPanel panel11;
    public JLabel label5;
    public JLabel label12;
    public JLabel label13;
    public JLabel label18;
    public JLabel label30;
    public JPanel panel3;
    public JButton button1;
    public JPanel panel10;
    public JLabel label8;
    public JLabel label22;
    public JLabel label23;
    public JLabel label17;
    public JLabel label29;
    public JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    long current = 0;

    public TinhNhanhGui() {
        initComponents();
        this.setTitle("Chọn cấp độ tham gia");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        button1.setFocusPainted(false);
        loadData();
    }

    private void button1ActionPerformed(ActionEvent e) {
        if (!checkTime()) return;
        MainGui mainGui = new MainGui();
        Main.changeJFrame(this, mainGui);
    }

    private void loadData() {
        JLabel[] labels = new JLabel[]{
                label27,
                label28,
                label30,
                label29,
                label31,
                label32
        };
        String[] levels = new String[]{
                "level1",
                "level2",
                "level3",
                "level4",
                "level5",
                "level6"
        };
        for (int i = 0; i < levels.length; i++) {
            JLabel label = labels[i];
            String level = levels[i];
            int time = Integer.parseInt(Main.readFile(level, 0));
            String textTime = "";
            if (time != -1) textTime = Main.convertToTime(time);
            else textTime = "Chưa chơi";
            if (textTime.equalsIgnoreCase("Chưa chơi")) {
                label.setForeground(Color.RED);
            } else {
                label.setForeground(Color.GREEN);
            }
            label.setText(textTime);
        }
    }

    public boolean checkTime() {
        if (current == 0) {
            current = System.currentTimeMillis();
            return true;
        } else {
            return (System.currentTimeMillis() - current) / 1000 >= 1;
        }
    }

    public void panel5MousePressed(MouseEvent e) {
        if (!checkTime()) return;
        GuiLevel1 guiLevel1 = new GuiLevel1();
        Main.changeJFrame(this, guiLevel1);
    }

    public void panel9MousePressed(MouseEvent e) {
        if (!checkTime()) return;
        GuiLevel2 guiLevel2 = new GuiLevel2();
        Main.changeJFrame(this, guiLevel2);
    }

    public void panel11MousePressed(MouseEvent e) {
        if (!checkTime()) return;
        GuiLevel3 guiLevel3 = new GuiLevel3();
        Main.changeJFrame(this, guiLevel3);
    }

    public void panel10MousePressed(MouseEvent e) {
        if (!checkTime()) return;
        GuiLevel4 guiLevel4 = new GuiLevel4();
        Main.changeJFrame(this, guiLevel4);
    }

    public void panel8MousePressed(MouseEvent e) {
        if (!checkTime()) return;
        GuiLevel5 guiLevel5 = new GuiLevel5();
        Main.changeJFrame(this, guiLevel5);
    }

    public void panel6MousePressed(MouseEvent e) {
        if (!checkTime()) return;
        GuiLevel6 guiLevel6 = new GuiLevel6();
        Main.changeJFrame(this, guiLevel6);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bảo
        panel2 = new JPanel();
        panel8 = new JPanel();
        label7 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        label21 = new JLabel();
        label31 = new JLabel();
        panel9 = new JPanel();
        label4 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        label14 = new JLabel();
        label28 = new JLabel();
        panel5 = new JPanel();
        label3 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label27 = new JLabel();
        panel6 = new JPanel();
        label6 = new JLabel();
        label25 = new JLabel();
        label26 = new JLabel();
        label24 = new JLabel();
        label32 = new JLabel();
        panel11 = new JPanel();
        label5 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label18 = new JLabel();
        label30 = new JLabel();
        panel3 = new JPanel();
        button1 = new JButton();
        panel10 = new JPanel();
        label8 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        label17 = new JLabel();
        label29 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setBackground(new Color(0, 204, 255));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0, 204, 255));

            // JFormDesigner evaluation mark
            panel2.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), panel2.getBorder()));
            panel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });

            panel2.setLayout(null);

            //======== panel8 ========
            {
                panel8.setBackground(new Color(255, 255, 102));
                panel8.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        panel8MousePressed(e);
                    }
                });
                panel8.setLayout(null);

                //---- label7 ----
                label7.setText("C\u1ea4P \u0110\u1ed8 5");
                label7.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label7.setHorizontalAlignment(SwingConstants.CENTER);
                label7.setForeground(Color.red);
                panel8.add(label7);
                label7.setBounds(0, 0, 220, 35);

                //---- label15 ----
                label15.setText("S\u1ed1 c\u00e2u: 10");
                label15.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel8.add(label15);
                label15.setBounds(10, 25, 200, 20);

                //---- label16 ----
                label16.setText("Th\u1eddi gian: 45s");
                label16.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel8.add(label16);
                label16.setBounds(10, 45, 195, 20);

                //---- label21 ----
                label21.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label21.setText("Th\u00e0nh t\u00edch:");
                panel8.add(label21);
                label21.setBounds(10, 65, 100, 20);

                //---- label31 ----
                label31.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label31.setText("Ch\u01b0a ch\u01a1i");
                panel8.add(label31);
                label31.setBounds(100, 65, 90, 20);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel8.getComponentCount(); i++) {
                        Rectangle bounds = panel8.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel8.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel8.setMinimumSize(preferredSize);
                    panel8.setPreferredSize(preferredSize);
                }
            }
            panel2.add(panel8);
            panel8.setBounds(10, 300, 220, 100);

            //======== panel9 ========
            {
                panel9.setBackground(new Color(255, 255, 102));
                panel9.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        panel9MousePressed(e);
                    }
                });
                panel9.setLayout(null);

                //---- label4 ----
                label4.setText("C\u1ea4P \u0110\u1ed8 2");
                label4.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.setForeground(Color.red);
                panel9.add(label4);
                label4.setBounds(0, 0, 220, 35);

                //---- label19 ----
                label19.setText("Th\u1eddi gian: 30s");
                label19.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel9.add(label19);
                label19.setBounds(10, 45, 195, 20);

                //---- label20 ----
                label20.setText("S\u1ed1 c\u00e2u: 10");
                label20.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel9.add(label20);
                label20.setBounds(10, 25, 200, 20);

                //---- label14 ----
                label14.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label14.setText("Th\u00e0nh t\u00edch:");
                panel9.add(label14);
                label14.setBounds(10, 65, 100, 20);

                //---- label28 ----
                label28.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label28.setText("Ch\u01b0a ch\u01a1i");
                panel9.add(label28);
                label28.setBounds(100, 65, 90, 20);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel9.getComponentCount(); i++) {
                        Rectangle bounds = panel9.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel9.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel9.setMinimumSize(preferredSize);
                    panel9.setPreferredSize(preferredSize);
                }
            }
            panel2.add(panel9);
            panel9.setBounds(270, 60, 220, 100);

            //======== panel5 ========
            {
                panel5.setBackground(new Color(255, 255, 102));
                panel5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        panel5MousePressed(e);
                    }
                });
                panel5.setLayout(null);

                //---- label3 ----
                label3.setText("C\u1ea4P \u0110\u1ed8 1");
                label3.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setForeground(Color.red);
                panel5.add(label3);
                label3.setBounds(0, 0, 220, 35);

                //---- label9 ----
                label9.setText("S\u1ed1 c\u00e2u: 10");
                label9.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel5.add(label9);
                label9.setBounds(10, 25, 200, 20);

                //---- label10 ----
                label10.setText("Th\u1eddi gian: 30s");
                label10.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel5.add(label10);
                label10.setBounds(10, 45, 195, 20);

                //---- label11 ----
                label11.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label11.setText("Th\u00e0nh t\u00edch:");
                panel5.add(label11);
                label11.setBounds(10, 65, 90, 20);

                //---- label27 ----
                label27.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label27.setText("Ch\u01b0a ch\u01a1i");
                panel5.add(label27);
                label27.setBounds(100, 65, 90, 20);

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
            panel2.add(panel5);
            panel5.setBounds(10, 60, 220, 100);

            //======== panel6 ========
            {
                panel6.setBackground(new Color(255, 255, 102));
                panel6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        panel6MousePressed(e);
                    }
                });
                panel6.setLayout(null);

                //---- label6 ----
                label6.setText("C\u1ea4P \u0110\u1ed8 6");
                label6.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                label6.setForeground(Color.red);
                panel6.add(label6);
                label6.setBounds(0, 0, 220, 35);

                //---- label25 ----
                label25.setText("Th\u1eddi gian: 45s");
                label25.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel6.add(label25);
                label25.setBounds(10, 45, 195, 20);

                //---- label26 ----
                label26.setText("S\u1ed1 c\u00e2u: 10");
                label26.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel6.add(label26);
                label26.setBounds(10, 25, 200, 20);

                //---- label24 ----
                label24.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label24.setText("Th\u00e0nh t\u00edch:");
                panel6.add(label24);
                label24.setBounds(10, 65, 100, 20);

                //---- label32 ----
                label32.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label32.setText("Ch\u01b0a ch\u01a1i");
                panel6.add(label32);
                label32.setBounds(100, 65, 90, 20);

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
            panel2.add(panel6);
            panel6.setBounds(270, 300, 220, 100);

            //======== panel11 ========
            {
                panel11.setBackground(new Color(255, 255, 102));
                panel11.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        panel11MousePressed(e);
                    }
                });
                panel11.setLayout(null);

                //---- label5 ----
                label5.setText("C\u1ea4P \u0110\u1ed8 3");
                label5.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                label5.setForeground(Color.red);
                panel11.add(label5);
                label5.setBounds(0, 0, 230, 35);

                //---- label12 ----
                label12.setText("S\u1ed1 c\u00e2u: 10");
                label12.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel11.add(label12);
                label12.setBounds(10, 25, 200, 20);

                //---- label13 ----
                label13.setText("Th\u1eddi gian: 50s");
                label13.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel11.add(label13);
                label13.setBounds(10, 45, 195, 20);

                //---- label18 ----
                label18.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label18.setText("Th\u00e0nh t\u00edch:");
                panel11.add(label18);
                label18.setBounds(10, 65, 100, 20);

                //---- label30 ----
                label30.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label30.setText("Ch\u01b0a ch\u01a1i");
                panel11.add(label30);
                label30.setBounds(100, 65, 90, 20);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel11.getComponentCount(); i++) {
                        Rectangle bounds = panel11.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel11.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel11.setMinimumSize(preferredSize);
                    panel11.setPreferredSize(preferredSize);
                }
            }
            panel2.add(panel11);
            panel11.setBounds(10, 180, 220, 100);

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
            panel2.add(panel3);
            panel3.setBounds(10, 425, 115, 35);

            //======== panel10 ========
            {
                panel10.setBackground(new Color(255, 255, 102));
                panel10.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        panel10MousePressed(e);
                    }
                });
                panel10.setLayout(null);

                //---- label8 ----
                label8.setText("C\u1ea4P \u0110\u1ed8 4");
                label8.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label8.setHorizontalAlignment(SwingConstants.CENTER);
                label8.setForeground(Color.red);
                panel10.add(label8);
                label8.setBounds(-5, 0, 225, 35);

                //---- label22 ----
                label22.setText("Th\u1eddi gian: 40s");
                label22.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel10.add(label22);
                label22.setBounds(10, 45, 195, 20);

                //---- label23 ----
                label23.setText("S\u1ed1 c\u00e2u: 10");
                label23.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panel10.add(label23);
                label23.setBounds(10, 25, 200, 20);

                //---- label17 ----
                label17.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label17.setText("Th\u00e0nh t\u00edch:");
                panel10.add(label17);
                label17.setBounds(10, 65, 100, 20);

                //---- label29 ----
                label29.setFont(new Font("Segoe UI", Font.BOLD, 16));
                label29.setText("Ch\u01b0a ch\u01a1i");
                panel10.add(label29);
                label29.setBounds(100, 65, 90, 20);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel10.getComponentCount(); i++) {
                        Rectangle bounds = panel10.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel10.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel10.setMinimumSize(preferredSize);
                    panel10.setPreferredSize(preferredSize);
                }
            }
            panel2.add(panel10);
            panel10.setBounds(270, 180, 220, 100);

            //---- label1 ----
            label1.setText("CH\u1eccN C\u1ea4P \u0110\u1ed8");
            label1.setFont(new Font("Segoe UI", Font.BOLD, 16));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setBackground(new Color(0, 204, 255));
            label1.setIcon(new ImageIcon(getClass().getResource("/Images/icons8-star-16.png")));
            panel2.add(label1);
            label1.setBounds(150, 10, 200, 30);

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
        contentPane.add(panel2);
        panel2.setBounds(0, 0, 500, 500);

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
