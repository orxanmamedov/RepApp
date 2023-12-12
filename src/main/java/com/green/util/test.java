package com.green.util;

public class test {
    public static void main(String[] args) {
        ReportBuilder reportBuilder = new ReportBuilder();
        JavaMailer javaMailer = new JavaMailer();
        javaMailer.sendMail("Daily Report", "");
    }
}
