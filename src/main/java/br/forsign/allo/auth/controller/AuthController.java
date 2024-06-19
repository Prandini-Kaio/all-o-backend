package br.forsign.allo.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaiooliveira
 * created 19/06/2024
 */

@RestController
public class AuthController {

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

}
