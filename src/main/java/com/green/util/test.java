package com.green.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
        JavaMailer javaMailer = new JavaMailer();

        javaMailer.sendMail("Daily Report", "");


    }
}
