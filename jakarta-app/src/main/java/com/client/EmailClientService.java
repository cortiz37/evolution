package com.client;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Dependent
public class EmailClientService {

    private static String URL;

    static {
        URL = System.getenv("NOTIFICATION_SERVICE_URL");
        if (URL == null || URL.trim().isEmpty()) {
            URL = "http://localhost:9090/v1/emails";
        }
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            create(to, subject, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void create(String to, String subject, String body) {
        Client client = ClientBuilder.newClient();
        WebTarget myResource = client.target(URL);
        myResource
            .request(MediaType.APPLICATION_JSON)
            .rx()
            .post(Entity.json(Email.of(to, subject, body)));
    }
}