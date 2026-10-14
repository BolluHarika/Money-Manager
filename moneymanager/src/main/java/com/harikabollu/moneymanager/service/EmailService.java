package com.harikabollu.moneymanager.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String BREVO_URL = "https://api.brevo.com/v3/smtp/email";

    private final JavaMailSender mailSender;
    private final RestClient restClient = RestClient.create();

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;


    @Value("${BREVO_API_KEY:}")
    private String brevoApiKey;

    public void sendEmail(String to, String subject, String body) {
        if (useApi()) {
            sendViaApi(to, subject, body, null, null);
            return;
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String filename) throws MessagingException {
        if (useApi()) {
            sendViaApi(to, subject, body, attachment, filename);
            return;
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment(filename, new ByteArrayResource(attachment));
        mailSender.send(message);
    }

    private boolean useApi() {
        return brevoApiKey != null && !brevoApiKey.isBlank();
    }

    private void sendViaApi(String to, String subject, String body, byte[] attachment, String filename) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("sender", Map.of("email", fromEmail));
        payload.put("to", List.of(Map.of("email", to)));
        payload.put("subject", subject);
        if (body.contains("<")) {
            payload.put("htmlContent", body);
        } else {
            payload.put("textContent", body);
        }
        if (attachment != null) {
            payload.put("attachment", List.of(Map.of(
                    "name", filename,
                    "content", Base64.getEncoder().encodeToString(attachment))));
        }
        try {
            restClient.post()
                    .uri(BREVO_URL)
                    .header("api-key", brevoApiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            throw new RuntimeException("Brevo API email failed: " + e.getMessage());
        }
    }
}