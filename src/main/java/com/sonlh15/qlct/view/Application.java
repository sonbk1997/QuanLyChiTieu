package com.sonlh15.qlct.view;

import com.sonlh15.qlct.controller.GoogleSheetAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Application extends JFrame {

    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextArea textArea1;
    private JTable table1;
    private JCheckBox tienNhaDienNuocCheckBox;
    private JCheckBox ănUốngCheckBox;
    private JCheckBox điLạiCheckBox;
    private JCheckBox chiTiêuHằngNgàyCheckBox;
    private JCheckBox quầnÁoCheckBox;
    private JCheckBox mỹPhẩmCheckBox;
    private JCheckBox chiPhíKhácCheckBox;
    private JCheckBox phíGiaoLưuCheckBox;
    private JButton btnSaveInfo;
    private JComboBox comboBox1;
    private JButton sửaButton;
    private JButton xóaButton;
    private JTextField textField1;
    GoogleSheetAPI googleSheetAPI = new GoogleSheetAPI();

    public Application() {
        initUI();
        btnSaveInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    googleSheetAPI.getData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (GeneralSecurityException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void initUI() {
        setTitle("QUAN LY CHI TIEU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        ImageIcon icon = new ImageIcon("icon.jpg");
        setIconImage(icon.getImage());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500, 0));
        setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
    }
}
