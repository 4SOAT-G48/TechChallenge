package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {


    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }
}
