package com.davison.web_screping.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriter {
    private static final String CSV_FILE_PATH = "web_scraper\\src\\main\\csv\\dataReport.csv";

    public static void writeToCsv(String[] args) throws IOException{
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH,true))) {
            for (String data : args) {
                writer.print(data+",");
            }
            writer.println();
        }
    }
}
