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
    private JCheckBox checkBoxEatFee;
    private JCheckBox checkBoxMoveFee;
    private JCheckBox checkBoxDailyFee;
    private JCheckBox checkBoxClothesFee;
    private JCheckBox checkBoxCosmeticFee;
    private JCheckBox checkBoxOtherFee;
    private JCheckBox checkBoxFriendlyFee;
    private JButton btnSaveInfo;
    private JComboBox comboBox1;
    private JButton sửaButton;
    private JButton xóaButton;
    private JTextField textFee;
    private JLabel lbDateTime;
    private JCheckBox checkBoxHouseFee;
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
                       + calendar.getTime().getDate() + "/"
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
            Calendar calendar = Calendar.getInstance();
            String contentColumn = "";
            String feeColumn  = "";

            if(checkBoxEatFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else if(checkBoxDailyFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else if(checkBoxClothesFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else if(checkBoxCosmeticFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else if(checkBoxHouseFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else if(checkBoxMoveFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else if(checkBoxFriendlyFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else if(checkBoxOtherFee.isSelected()) {
                contentColumn = "A";
                feeColumn = "C";
            } else {
                JOptionPane.showMessageDialog(this, "Phải chọn danh mục chi tiêu", "Cảnh báo!!!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            service.updateCellValue("Tháng " + (calendar.getTime().getMonth()+1), contentColumn + (calendar.getTime().getDate() + 2), textContent.getText());
            service.updateCellValue("Tháng " + (calendar.getTime().getMonth()+1), feeColumn + (calendar.getTime().getDate() + 2), textFee.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
