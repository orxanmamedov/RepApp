package com.green.util;




import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class JavaMailer {

    public void sendMail(String sub, String mes) {
        final String emailFrom = "javagreengroupandersen";
        final String emailTo = "orxanaxbeats@gmail.com";

        final String subject = sub;
        final String messageText = mes;

        final Properties properties = new Properties();
        try {
            properties.load(JavaMailer.class.getClassLoader().getResourceAsStream("mail.properties"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(subject);
            message.setText(messageText);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        try {
            Transport tr = mailSession.getTransport();
            try {
                tr.connect(null, "lrul llas ztnd ract");
                tr.sendMessage(message, message.getAllRecipients());
                System.out.println("MESSAGE WAS SENT SUCCESSFULLY AT " + LocalTime.now());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    tr.close();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }


    }
}
