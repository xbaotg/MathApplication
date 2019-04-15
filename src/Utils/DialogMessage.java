/*
 * Created by JFormDesigner on Tue Mar 05 14:41:53 ICT 2019
 */

package Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Bảo
 */
public class DialogMessage extends JDialog {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bảo
    public JPanel dialogPane;
    public JPanel contentPanel;
    public JLabel label2;
    public JPanel buttonBar;
    public JButton okButton;
    public JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    Frame frame;
    ArrayList<HashMap<TypeDialog, String>> dialogs;
    boolean clicked;

    public DialogMessage(Frame owner) {
        super(owner);
        frame = owner;
        initComponents();
        setup();
    }

    public DialogMessage(Dialog owner) {
        super(owner);
        initComponents();
        setup();
    }

    public void setup() {
        dialogs = new ArrayList<HashMap<TypeDialog, String>>();
        setSize(400, 250);
        setTitle("Thông Báo");
        okButton.setFocusPainted(false);
        int x = frame.getX() + frame.getWidth() / 2 - this.getWidth() / 2;
        int y = frame.getY() + frame.getHeight() / 2 - this.getHeight() / 2;
        Point point = new Point();
        point.setLocation(x, y);
        setLocation(point);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (clicked) {
                    clicked = false;
                    return;
                }
                if (dialogs.isEmpty()) return;
                dialogs.remove(0);
                showDialog();
                super.windowClosed(e);
            }
        });
    }

    public void addDialog(TypeDialog typeDialog, String message) {
        HashMap<TypeDialog, String> hashMap = new HashMap<TypeDialog, String>();
        hashMap.put(typeDialog, message);
        dialogs.add(hashMap);
    }

    public void showDialog() {
        if (dialogs.isEmpty()) return;
        HashMap<TypeDialog, String> hashMap = dialogs.get(0);
        TypeDialog typeDialog = null;
        String message = null;
        for (TypeDialog e : hashMap.keySet()) {
            typeDialog = e;
            message = hashMap.get(e);
        }
        if (typeDialog == null || message == null) return;
        if (typeDialog == TypeDialog.SUCCESS) {
            label1.setIcon(new ImageIcon(getClass().getResource("/Images/success.png")));
            label2.setForeground(Color.GREEN);
            label2.setText(message);
        }
        if (typeDialog == TypeDialog.CROSS) {
            label1.setIcon(new ImageIcon(getClass().getResource("/Images/cross.jpg")));
            label2.setForeground(Color.RED);
            label2.setText(message);
        }
        if (typeDialog == TypeDialog.INFORMATION) {
            label1.setIcon(new ImageIcon(getClass().getResource("/Images/information.png")));
            label2.setForeground(Color.BLACK);
            label2.setText(message);
        }
        this.show();
    }

    public void okButtonActionPerformed(ActionEvent e) {
        this.hide();
        this.dispose();
        dialogs.remove(0);
        clicked = true;
        showDialog();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bảo
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label2 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(Color.white);

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), dialogPane.getBorder()));
            dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setBackground(Color.white);
                contentPanel.setLayout(new BorderLayout());

                //---- label2 ----
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setForeground(Color.red);
                label2.setFont(new Font("Segoe UI", Font.BOLD, 14));
                contentPanel.add(label2, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(Color.white);
                buttonBar.setLayout(new FlowLayout());

                //---- okButton ----
                okButton.setText("\u0110\u1ed2NG \u00dd");
                okButton.setForeground(Color.green);
                okButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
                okButton.setBackground(new Color(51, 102, 255));
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton);
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //---- label1 ----
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setBackground(Color.white);
            dialogPane.add(label1, BorderLayout.NORTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
