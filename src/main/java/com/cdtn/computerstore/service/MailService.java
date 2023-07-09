package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.EmailSender;
import jakarta.mail.MessagingException;

public interface MailService {
    void sendEmail(EmailSender emailSender) throws MessagingException;
}
