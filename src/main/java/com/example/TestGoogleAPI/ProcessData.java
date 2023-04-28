package com.example.TestGoogleAPI;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

public class ProcessData {

    GoogleSheetAPI googleSheetAPI = new GoogleSheetAPI();

    public ProcessData() {
    }

    public void getInfoByDay(String User, Date date, String spendingSubject) {
        String column = "Z";
        String row = "1000";
        switch (User) {
            case "Em":
                column = "B";
                break;
            case "Anh":
                column = "D";
                break;
        }
        row = String.valueOf(date.getDate() + 2);
        System.out.println("Month: " + date.getMonth());
        System.out.println("index: " + column+row);

        try {
//            System.out.println(googleSheetAPI.getData(date.getMonth() + 1, column + row));
            googleSheetAPI.writeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
