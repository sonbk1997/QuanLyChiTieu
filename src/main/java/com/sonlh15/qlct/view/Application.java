package com.sonlh15.qlct.view;

import com.sonlh15.qlct.service.GoogleSheetsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
@EnableScheduling
public class Application extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextArea textContent;
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
    private JTextField textFee;
    private JLabel lbDateTime;
    GoogleSheetsService service = new GoogleSheetsService("/psychic-iridium-340607-c352e99e635e.json", "16XuCt15k0uUoNdFUjNCbDRx7lD-N1CpqykJxzZkjDc0");

    public Application() {
        initUI();
        btnSaveInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userAddData();
            }
        });
    }

    public void threadUpdateDate() {
        log.info("Thread update date");
        Thread updateDate = new Thread(()->{
           while (true) {
               Calendar calendar = Calendar.getInstance();

               lbDateTime.setText(((calendar.getTime().getDate() < 10)?"0":"")
                       + calendar.getTime().getDay() + "/"
                       + ((calendar.getTime().getMonth()+1 < 10)?"0":"")
                       + (calendar.getTime().getMonth()+1) + "/"
                       + calendar.getWeekYear());
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });
        updateDate.start();
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

    private void userAddData() {
        try {
            service.updateCellValue("Tháng " + 4, "B30", textContent.getText());
            service.updateCellValue("Tháng " + 4, "C30", textFee.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
