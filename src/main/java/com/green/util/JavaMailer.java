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
        final String emailFrom = "javagreengroupandersen@gmail.com";
        String emailTo = "orxanaxbeats@gmail.com";
        String host = "smtp.gmail.com";
        String smtpPort = "465";

        final Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session mailSession = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFrom, "lrul llas ztnd ract");
                    }
                });


        mailSession.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(sub);
            message.setText(mes);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
