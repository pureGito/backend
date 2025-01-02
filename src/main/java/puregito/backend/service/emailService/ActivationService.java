package puregito.backend.service.emailService;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ActivationService {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendActivationEmail(String to,String subject,String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public String generateCode() {
        int i = (int) (Math.random() * 90000) + 10000;
        return String.valueOf(i);
    }




}
