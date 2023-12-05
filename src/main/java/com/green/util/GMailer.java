package com.green.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Set;

import static javax.mail.Message.RecipientType.TO;

public class GMailer {
    private static final String TEST_EMAIL = "javagreengroupandersen@gmail.com";
    private static final String TEST_EMAIL2 = "orxanaxbeats@gmail.com";
    private Gmail service;
    public GMailer() throws GeneralSecurityException, IOException {
        NetHttpTransport HttpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(HttpTransport, jsonFactory, getCredentials(HttpTransport, jsonFactory))
                .setApplicationName("Test mailer")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), new InputStreamReader(GMailer.class.getResourceAsStream("/client_secret_433714454210-nnursdefao4drn8s28tvnnuivonuo8im.apps.googleusercontent.com.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, GsonFactory.getDefaultInstance(), clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }
    public void sendMail(String subject, String message) throws GeneralSecurityException, IOException, MessagingException {
        //send email to yourself



        // Encode as MIME msg2
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_EMAIL));
        email.addRecipient(TO, new InternetAddress(TEST_EMAIL2));
        email.setSubject(subject);
        email.setText(message);

        // Encode and wrap the MIME msg2 into a gmail msg2
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg2 = new Message();
        msg2.setRaw(encodedEmail);

        try {
            // Create the draft msg2

            msg2 = service.users().messages().send("me", msg2).execute();
            System.out.println("Draft id: " + msg2.getId());
            System.out.println(msg2.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to create draft: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }

//    public static void main(String[] args) throws GeneralSecurityException, IOException, MessagingException {
//
//
//
//        new GMailer().sendMail("A sddddd", "WORKS");
//    }


}
