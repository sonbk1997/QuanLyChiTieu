package com.sonlh15.qlct.main;

import com.sonlh15.qlct.view.Application;

import java.awt.*;
public class Main {

    public static void main(String... args) {
        EventQueue.invokeLater(() -> {
            Application f = new Application();
            f.setVisible(true);
        });
    }
}
