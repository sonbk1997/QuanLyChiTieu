package com.sonlh15.qlct.main;

import com.sonlh15.qlct.view.Application;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@Log4j2
@SpringBootApplication
public class Main {

    public static void main(String... args) {
        EventQueue.invokeLater(() -> {
            Application f = new Application();
            f.setVisible(true);
            f.threadUpdateDate();
        });
        SpringApplication.run(Main.class, args);
    }
}
