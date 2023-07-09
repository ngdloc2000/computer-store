package com.cdtn.computerstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailSender {
    private String recipientEmail;
    private String fullName;
    private String subject;
    private String sendFrom;
    private String content;
}