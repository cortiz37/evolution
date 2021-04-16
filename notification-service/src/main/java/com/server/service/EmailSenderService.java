package com.server.service;

import com.server.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMessage(Email email) throws MessagingException {
        try {
            List<InternetAddress> addresses = new ArrayList<>();
            for (String s : email.getTo()) {
                try {
                    InternetAddress address = new InternetAddress(s);
                    address.validate();
                    addresses.add(address);
                } catch (AddressException ex) {
                    log.error("Invalid email address", ex);
                    throw ex;
                }
            }
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(addresses.toArray(new InternetAddress[addresses.size()]));
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            log.error("Error in email sender", e);
            throw e;
        }
    }
}
