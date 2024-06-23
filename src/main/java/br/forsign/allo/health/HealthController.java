package br.forsign.allo.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaiooliveira
 * created 22/06/2024
 */

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok().body("pong");
    }
}
