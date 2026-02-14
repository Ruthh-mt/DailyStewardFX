package services;

import java.security.SecureRandom;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import security.Passwords;

public class EmailServices {
    private static final SecureRandom random = new SecureRandom();
    private static Passwords password = new Passwords();
    private static final String EMAIL_SENDER = "testcinema92@gmail.com";
    private static final String PASSWORD = password.getEMAILPASSWORD();

    public static void envoyerEmail(String destinataire, String sujet, String messageTexte) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_SENDER, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_SENDER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
            message.setSubject(sujet);
            message.setText(messageTexte);

            Transport.send(message);
            System.out.println("E-mail envoyé avec succès !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String genererCode() {
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}