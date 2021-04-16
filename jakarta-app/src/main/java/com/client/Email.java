package com.client;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Email {

    private List<String> to;

    private String subject;
    private String body;

    public static Email of(String to, String subject, String body) {
        Email email = new Email();
        email.to = Collections.singletonList(to);
        email.subject = subject;
        email.body = body;
        return email;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
