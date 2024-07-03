package br.forsign.allo.mail.service;

import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author kaiooliveira
 * created 30/06/2024
 */

@Service
public class EmailService {

    @Resource
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage message) {
        mailSender.send(message);
    }

    public void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        this.send(message);
    }

    public SimpleMailMessage generateResetPassEmail(String contextPath, Locale locale, String token, Usuario usuario) {
        String url = contextPath + "/change-password?token=" + token;

        String message = "Uma solicitação foi feita em " + locale + "\n\nClique no link abaixo para redefinir sua senha:\n" + url;

        return constructEmail("Solicitação de redefinição de senha", message, usuario);
    }

    private SimpleMailMessage constructEmail(String subject, String body, Usuario usuario) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(usuario.getLogin());
        email.setSubject(subject);
        email.setText(body);

        return email;
    }
}
