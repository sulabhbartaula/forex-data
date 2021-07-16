package com.sulabh.forexdata.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    String path = "b:\\forex\\";

    public void sendEmail(String toEmail, String fileName)
    {
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("naidoojaya1960@gmail.com", "@Sydney123");
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("naidoojaya1960@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            msg.setSubject("FOREX RATE");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Please find attached file",
                    "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(path + fileName );
            multipart.addBodyPart(attachPart);
            msg.setContent(multipart);
            Transport.send(msg);
            System.out.println("Email sent at : " + LocalDateTime.now() + "to" + toEmail);
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
    }
}