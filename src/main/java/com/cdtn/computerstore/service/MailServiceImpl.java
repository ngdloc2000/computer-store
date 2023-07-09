package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class MailServiceImpl implements MailService{
    JavaMailSender mailSender;
    TemplateEngine templateEngine;

    private static String EMAIL_TEMPLATE_ORDER_SUCCESS = null;
    private static final String SUBJECT_FOR_ORDER = "Cảm ơn bạn đã đặt hàng!";
    private String initTemplateData(String templatePath) {
        InputStream inputKycApprove = getClass().getClassLoader().getResourceAsStream(templatePath);
        BufferedReader bufferedKycApprove = new BufferedReader(new InputStreamReader(inputKycApprove, StandardCharsets.UTF_8));
        return bufferedKycApprove.lines().collect(Collectors.joining("\n"));
    }

//    @Autowired
//    public MailServiceImpl(TemplateEngine templateEngine) {
//        this.templateEngine = templateEngine;
//    }

    @Override
    public void sendEmail(EmailSender emailSender) throws MessagingException {
        Context context = new Context();
        context.setVariable("emailSender", emailSender);

        String process = templateEngine.process("template/email_template_order_success.html", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setSubject(SUBJECT_FOR_ORDER);
        helper.setText(process, true);
        helper.setTo(emailSender.getRecipientEmail());
        mailSender.send(mimeMessage);
    }

}
