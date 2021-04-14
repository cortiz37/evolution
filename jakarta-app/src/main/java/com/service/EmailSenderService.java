package com.service;

import com.util.EncUtil;

import javax.enterprise.context.Dependent;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Dependent
public class EmailSenderService {

    public static final String email_address;
    private static final String email_pass;

    static {
        email_address = EncUtil.decode("Y3hSMDFIUmxsVWFrSmFWakExTTFscVFrTmliVXBZVW01Q2FWRjZWbkZaYWtsM1pEQXhSVkZZWkU1U1JVWXpUVVJCZDAxRVFYZE5RVDBZM2hTTURGSVVteHNWV0ZyU21GV2FrRXhUVEZzY1ZGclRtbGlWWEJaVlcwMVEyRldSalpXYmtaYVlXdHNNMXBFUVhoU1ZrWlpXa1UxVTFKVldYcFVWVkpDWkRBeFJWRllaRTVSVkRCWk0yaFRUVVJHU1ZWdGVITldWMFp5VTIxR1YyRnJSWGhVVkVaelkxWkdjbFJ0YkdsV1dFSmFWbGN3TVZFeVJsZFNhbHBYWW10YVlWbFhkSE5OTVhCRlVWaG9VMVpyV2xwWGExVXhWVEZLVmxkWWNGVldWa3BEV2tSQmVGSldSbGxhUlRWU1ZrUkNhVmxZVW5Cak0xSm9XVE5DZGxGSFpIUlpWMnh6VEcxT2RtSlhUalJWYWtGNFUwWktjMkpHVm1oaE1IQm9WbTF3UWsxVk1IaGlTRVpTWVRBMWNGbHNWbmRYVmxaMFRsVk9hRlpyV1RKV2JUVkhWMjFHY21KRVRtRlNSVVkwVld4YVIxZFdjRVpPVms1VFZsWnNObFpHVmxOUmJWRjNUVlZXVWxkSFVrOVZWbEYzWTNoU01ERklVbXhzVldGclNtRldha0V4VFRGc2NWRnJUbWxpVlhCWlZXMDFRMkZXUmpaV2JrWmFZV3RzTTFwRVFYaFNWa1paV2tVMVUxSlZXWHBVVlZKQ1pEQXhSVkZZWkU1UlZEQT1jeFIwMUhSbGxVYWtKYVZqQTFNMWxxUWtOaWJVcFlVbTVDYVZGNlZuRlpha2wzWkRBeFJWRllaRTVTUlVZelRVUkJkMDFFUVhkTlFUMA==");
        email_pass = EncUtil.decode("Y3hSMDFIUmxsVWFrSmFWakExTTFscVFrTmliVXBZVW01Q2FWRjZWbkZaYWtsM1pEQXhSVkZZWkU1U1JVWXpUVVJCZDAxRVFYZE5RVDBZM2hTTURGSVVteHNWV0ZyU21GV2FrRXhUVEZzY1ZGclRtbGlWWEJaVlcwMVEyRldSalpXYmtaYVlXdHNNMXBFUVhoU1ZrWlpXa1UxVTFKVldYcFVWVkpDWkRBeFJWRllaRTVSVkRCWk0yaFRUVVJHU1ZWdGVITldWMFp5VTIxR1YyRnJSWGhVVkVaelkxWkdjbFJ0YkdsV1dFSmFWbGN3TVZFeVJsZFNhbHBYWW10YVlWbFhkSE5OTVhCRlVWaG9VMVpyV2xwWGExVXhWVEZLVmxkWWNGVldWa3BEV2tSQmVGSldSbGxhUlRWU1ZrUkNRMWxZVW5Cak0xSm9UMFJWY1V0dFRqUlZha0Y0VTBaS2MySkdWbWhoTUhCb1ZtMXdRazFWTUhoaVNFWlNZVEExY0Zsc1ZuZFhWbFowVGxWT2FGWnJXVEpXYlRWSFYyMUdjbUpFVG1GU1JVWTBWV3hhUjFkV2NFWk9WazVUVmxac05sWkdWbE5SYlZGM1RWVldVbGRIVWs5VlZsRjNZM2hTTURGSVVteHNWV0ZyU21GV2FrRXhUVEZzY1ZGclRtbGlWWEJaVlcwMVEyRldSalpXYmtaYVlXdHNNMXBFUVhoU1ZrWlpXa1UxVTFKVldYcFVWVkpDWkRBeFJWRllaRTVSVkRBPWN4UjAxSFJsbFVha0phVmpBMU0xbHFRa05pYlVwWVVtNUNhVkY2Vm5GWmFrbDNaREF4UlZGWVpFNVNSVVl6VFVSQmQwMUVRWGROUVQw");
    }

    public void sendMail(String to, String title, String body) throws MessagingException {
        List<String> toList = new ArrayList<String>();
        toList.add(to);
        sendMail(toList, title, body);
    }

    private void sendMail(final List<String> toList, final String title, final String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);

        final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email_address, email_pass);
            }
        });

        send(session, title, body, toList);
    }

    private void send(Session session, String title, String body, List<String> toList) throws MessagingException {
        Message message = new MimeMessage(session);
        Multipart multipart = new MimeMultipart();

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html");
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        for (String string : toList) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(string));
        }
        message.setSubject(title);

        Transport.send(message);
    }
}
