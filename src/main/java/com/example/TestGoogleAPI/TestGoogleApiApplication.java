package com.example.TestGoogleAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

@SpringBootApplication
public class TestGoogleApiApplication {

	public static void main(String... args) throws GeneralSecurityException, IOException {
		SpringApplication.run(TestGoogleApiApplication.class, args);
		ProcessData processData = new ProcessData();
		Date date = new Date();
		date.setDate(3);
		date.setMonth(3);
		System.out.println(date);
		processData.getInfoByDay("Em", date, "Danh sách");
	}
}
