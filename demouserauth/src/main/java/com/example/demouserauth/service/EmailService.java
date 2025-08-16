package com.example.demouserauth.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendWelcomeEmail(String toEmail, String username) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("🎉 Welcome to JuriCloud!");

            // HTML styled email
            String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; "
                    + "border-radius: 10px; background: #f9f9f9; box-shadow: 0 4px 8px rgba(0,0,0,0.1);'>"
                    + "<h2 style='color: #4CAF50; text-align: center;'>Welcome to <span style='color:#2c3e50;'>JuriCloud</span> 🚀</h2>"
                    + "<p style='font-size: 16px; color: #333;'>Hi <b>" + username + "</b>,</p>"
                    + "<p style='font-size: 15px; color: #555;'>Thank you for registering with <b>JuriCloud</b>. "
                    + "We’re thrilled to have you onboard! 🎊</p>"
                    + "<div style='margin: 20px 0; text-align: center;'>"
                    + "   <a href='https://juricloud.com' style='background: #4CAF50; color: white; padding: 12px 20px; "
                    + "   text-decoration: none; border-radius: 6px; font-weight: bold;'>Get Started</a>"
                    + "</div>"
                    + "<p style='font-size: 14px; color: #888;'>Best regards,<br>The JuriCloud Team</p>"
                    + "</div>";

            helper.setText(htmlContent, true); // true enables HTML

            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
