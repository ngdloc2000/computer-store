package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.text.NumberFormat;
import java.util.Locale;

@Service
public class MailServiceImpl implements MailService{
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

//    @Autowired
//    private SpringTemplateEngine thymeleafTemplateEngine;

    private static String EMAIL_TEMPLATE_ORDER_SUCCESS = null;
    private static final String SUBJECT_FOR_ORDER = "Cảm ơn bạn đã đặt hàng!";

    public MailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
//    private String initTemplateData(String templatePath) {
//        InputStream inputKycApprove = getClass().getClassLoader().getResourceAsStream(templatePath);
//        BufferedReader bufferedKycApprove = new BufferedReader(new InputStreamReader(inputKycApprove, StandardCharsets.UTF_8));
//        return bufferedKycApprove.lines().collect(Collectors.joining("\n"));
//    }

//    public MailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
//        this.templateEngine = templateEngine;
//        this.mailSender = javaMailSender;
//    }
//    @Autowired
//    public MailServiceImpl(TemplateEngine templateEngine) {
//        this.templateEngine = templateEngine;
//    }

    @Override
    public void sendEmail(EmailSender emailSender) throws MessagingException {
//        FileTemplateResolver templateResolver = new FileTemplateResolver();
//        templateResolver.setPrefix("templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//
////         Create a template engine and set the template resolver
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
        Locale vn = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vn);
        currencyFormatter.format(emailSender.getTotalPrice());
        Context context = new Context();
        context.setVariable("emailSender", emailSender);
        context.setVariable("orderId", emailSender.getOrderId());
        context.setVariable("totalPrice", currencyFormatter.format(emailSender.getTotalPrice()));
        context.setVariable("deliveryAddress", emailSender.getDeliveryAddress());
        context.setVariable("orderItemDetailList", emailSender.getOrderItemDetailList());

        String process = templateEngine.process("email_template_order_success", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setSubject(SUBJECT_FOR_ORDER);
        helper.setText(process, true);
        helper.setTo(emailSender.getRecipientEmail());
        mailSender.send(mimeMessage);
    }

}
