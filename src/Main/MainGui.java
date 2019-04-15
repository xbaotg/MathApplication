/*
 * Created by JFormDesigner on Mon Dec 24 21:11:25 ICT 2018
 */

package Main;

import TypeMath.DienDau;
import TypeMath.TinhNhanhGui;
import Utils.DialogMessage;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * @author Bảo
 */
public class MainGui extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bảo
    public JPanel panel1;
    public JLabel label3;
    public JLabel label2;
    public JPanel panel2;
    public JButton button1;
    public JPanel panel3;
    public JButton button2;
    public JLabel label1;
    public JLabel label4;
    public JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    // Generated using JFormDesigner Evaluation license - Trần Gia


    public MainGui() {
        initComponents();
        this.setTitle("Toán học - Rèn tính nhanh");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        button1.setFocusPainted(false);
        button2.setFocusPainted(false);
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void loadData() throws IOException, SAXException, ParserConfigurationException {
        Integer data = Integer.parseInt(Main.readFile("diendau", 0));
        if (data == -1) {
            return;
        }
        label4.setText("Thành tích: " + Main.convertToTime(data));
    }

    private void button1ActionPerformed(ActionEvent e) {
        TinhNhanhGui tinhNhanhGui = new TinhNhanhGui();
        Main.changeJFrame(this, tinhNhanhGui);
    }

    public void button2ActionPerformed(ActionEvent e) {
        DienDau dienDau = new DienDau();
        Main.changeJFrame(this, dienDau);
    }

    public void button3ActionPerformed(ActionEvent e) {
        new DialogMessage(this).show();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bảo
        panel1 = new JPanel();
        label3 = new JLabel();
        label2 = new JLabel();
        panel2 = new JPanel();
        button1 = new JButton();
        panel3 = new JPanel();
        button2 = new JButton();
        label1 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();

        //======== this ========
        setBackground(Color.white);
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

            //---- label3 ----
            label3.setText("R\u00c8N T\u00cdNH TO\u00c1N");
            label3.setFont(new Font("Segoe UI", Font.BOLD, 36));
            panel1.add(label3);
            label3.setBounds(115, 55, 798, 48);

            //---- label2 ----
            label2.setText("TO\u00c1N H\u1eccC");
            label2.setFont(new Font("Segoe UI", Font.BOLD, 36));
            panel1.add(label2);
            label2.setBounds(160, 10, 295, 48);

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0, 153, 255));
                panel2.setLayout(null);

                //---- button1 ----
                button1.setText("T\u00cdNH NHANH");
                button1.setBackground(Color.white);
                button1.setFont(new Font("Segoe UI", Font.BOLD, 16));
                button1.addActionListener(e -> button1ActionPerformed(e));
                panel2.add(button1);
                button1.setBounds(0, 0, 200, 40);
            }
            panel1.add(panel2);
            panel2.setBounds(150, 150, 200, 40);

            //======== panel3 ========
            {
                panel3.setBackground(new Color(0, 153, 255));
                panel3.setLayout(null);

                //---- button2 ----
                button2.setText("\u0110I\u1ec0N D\u1ea4U NHANH");
                button2.setBackground(Color.white);
                button2.setFont(new Font("Segoe UI", Font.BOLD, 16));
                button2.addActionListener(e -> button2ActionPerformed(e));
                panel3.add(button2);
                button2.setBounds(0, 0, 200, 40);
            }
            panel1.add(panel3);
            panel3.setBounds(150, 205, 200, 40);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/Images/logo.png")));
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(20, 10), label1.getPreferredSize()));

            //---- label4 ----
            label4.setFont(new Font("Ubuntu", Font.BOLD, 16));
            label4.setForeground(Color.orange);
            panel1.add(label4);
            label4.setBounds(355, 205, 135, 40);

            //---- label5 ----
            label5.setFont(new Font("Segoe UI", Font.BOLD, 24));
            label5.setIcon(UIManager.getIcon("OptionPane.questionIcon"));
            label5.setToolTipText("<html>\nPh\u1ea7n m\u1ec1m luy\u1ec7n t\u00ednh nhanh - D\u1ef1 thi KHKT tr\u01b0\u1eddng THCS L\u00ea H\u1ed3ng Phong - Tr\u1ea7n Gia B\u1ea3o - 9/2\n<br>\n<br>\n\u0110\u1ed1i t\u01b0\u1ee3ng: T\u1ea5t c\u1ea3 m\u1ecdi ng\u01b0\u1eddi mu\u1ed1n r\u00e8n kh\u1ea3 n\u0103ng t\u00ednh nhanh\n<br>\nG\u1ed3m: \u0110i\u1ec1n k\u1ebft qu\u1ea3 \u0111\u00fang, \u0111i\u1ec1n d\u1ea5u \u0111\u00fang.\n<br>\nPh\u1ea1m vi s\u1ed1: < 100\n<br>\n<br>\nNg\u00f4n ng\u1eef l\u1eadp tr\u00ecnh: Java\n<br>\nTh\u1eddi gian: 3 tu\u1ea7n\n</html>\n");
            panel1.add(label5);
            label5.setBounds(new Rectangle(new Point(455, 425), label5.getPreferredSize()));

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
