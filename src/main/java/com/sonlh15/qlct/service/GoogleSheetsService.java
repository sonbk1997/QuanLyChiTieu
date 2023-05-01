package com.sonlh15.qlct.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class GoogleSheetsService {
    private Sheets sheetsService = null;
    private final String spreadsheetId;

    public GoogleSheetsService(String credentialsPath, String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;

        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        try {
            GoogleCredential credential = GoogleCredential.fromStream(getClass().getResourceAsStream(credentialsPath))
                    .createScoped(Collections.singleton("https://www.googleapis.com/auth/spreadsheets"));

            sheetsService = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, credential)
                    .setApplicationName("Your Application Name")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCellValue(String sheetName, String cell, String newValue) throws IOException {
        ValueRange requestBody = new ValueRange().setValues(Collections.singletonList(Collections.singletonList(newValue)));
        sheetsService.spreadsheets().values()
                .update(spreadsheetId, sheetName + "!" + cell, requestBody)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }

    public void appendRow(String sheetName, List<Object> rowData) throws IOException {
        ValueRange valueRange = new ValueRange();
        valueRange.setValues(Collections.singletonList(rowData));

        AppendValuesResponse response = sheetsService.spreadsheets().values()
                .append(spreadsheetId, sheetName, valueRange)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();

        // You can retrieve the updated range and other information from the response if needed
        String updatedRange = response.getUpdates().getUpdatedRange();
        int numRowsAppended = response.getUpdates().getUpdatedRows();
        System.out.println("Appended " + numRowsAppended + " row(s) to " + updatedRange);
    }

    public List<List<Object>> getData(String sheetName, String range) throws IOException {
        Sheets.Spreadsheets.Values.Get request = sheetsService.spreadsheets().values()
                .get(spreadsheetId, sheetName + "!" + range);

        ValueRange response = request.execute();
        System.out.println(response);
        return response.getValues();
    }
}