package com.server.controller;

import com.server.model.Email;
import com.server.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(EmailController.PATH)
@Tag(name = "Email Controller", description = "Notifications via email")
public class EmailController {

    public static final String PATH = "/v1/emails";

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(emailService.getAll());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Email email) {
        Email created = emailService.create(email);
        return ResponseEntity.created(URI.create(PATH + '/' + created.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name = "id") String id) {
        emailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
