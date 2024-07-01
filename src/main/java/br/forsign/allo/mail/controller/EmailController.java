package br.forsign.allo.mail.controller;

import br.forsign.allo.mail.service.EmailService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaiooliveira
 * created 30/06/2024
 */

@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String to,
                                    @RequestParam String subject,
                                    @RequestParam String body){

        emailService.send(to, subject, body);
        return ResponseEntity.ok("Email sent successfully");
    }
}
