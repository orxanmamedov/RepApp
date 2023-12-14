package com.green.util;





import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import java.util.Properties;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class JavaMailer {


    public void sendMail(String sub, String mes) {
        ReportJasper reportJasper = new ReportJasper();

        try {
            reportJasper.report();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        final String emailFrom = "javagreengroupandersen@gmail.com";
        String emailTo = "orxanaxbeats@gmail.com";


        final Properties properties = new Properties();
        try {
            properties.load(JavaMailer.class.getClassLoader().getResourceAsStream("email.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Session mailSession = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFrom, "lrul llas ztnd ract");
                    }
                });

//        mailSession.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(sub);
            message.setText(mes);
            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart attachment = new MimeBodyPart();

            String exportFileName = "reportGreen.pdf";
            URL exportPath = JavaMailer.class.getClassLoader().getResource(exportFileName);
            if (exportPath == null) {
                throw new RuntimeException("Resource not found: " + exportFileName);
            }
            attachment.attachFile(new File(Objects.requireNonNull((exportPath.toURI()))));


            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<h1 style=\"color:green;\">Green Group Daily Report</h1>", "text/html");

            multipart.addBodyPart(attachment);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email was sent at " + LocalDateTime.now());
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }
}
