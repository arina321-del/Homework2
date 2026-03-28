package org.example.notificationservice.controller;

import org.example.notificationservice.dto.UserEvent;
import org.example.notificationservice.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void send(@RequestBody UserEvent event) {

        if ("CREATE".equals(event.getOperation())) {
            emailService.send(event.getEmail(), "Создание", "Аккаунт создан");
        } else {
            emailService.send(event.getEmail(), "Удаление", "Аккаунт удалён");
        }
    }
}