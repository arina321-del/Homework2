package org.example.notificationservice.kafka;

import org.example.notificationservice.dto.UserEvent;
import org.example.notificationservice.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    private final EmailService emailService;

    public KafkaListenerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(UserEvent event) {

        if ("CREATE".equals(event.getOperation())) {
            emailService.send(
                    event.getEmail(),
                    "Создание аккаунта",
                    "Здравствуйте! Ваш аккаунт был успешно создан."
            );
        }

        if ("DELETE".equals(event.getOperation())) {
            emailService.send(
                    event.getEmail(),
                    "Удаление аккаунта",
                    "Здравствуйте! Ваш аккаунт был удалён."
            );
        }
    }
}