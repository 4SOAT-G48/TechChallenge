package br.com.fiap.soat.grupo48.apoio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SaudacoesController {
    private static final String template = "Olá, %s!";
    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/saudacoes")
    public Saudacoes greeting(@RequestParam(value = "nome", defaultValue = "Mundo") String name) {
        return new Saudacoes(contador.incrementAndGet(), String.format(template, name));
    }
}
