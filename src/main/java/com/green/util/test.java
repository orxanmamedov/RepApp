package com.green.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
        ReportBuilder reportBuilder = new ReportBuilder();
        JavaMailer javaMailer = new JavaMailer();
      //  reportBuilder.generateReportFile();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.of(2023, 12, 11);
        javaMailer.sendMail("Daily Report", "Hello!",reportBuilder.generateReportFileExcel(today));

    }
}
