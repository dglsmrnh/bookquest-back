package io.bookquest.entrypoint.v1;

import br.com.caelum.stella.boleto.*;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @PostMapping("users/{username}/payments")
    public ResponseEntity<Object> generatePaymentMethod() {
        return ResponseEntity.created(URI.create("/"))
                .body(Map.of("data", UUID.randomUUID(), "payment_type", "boleto"));
    }
}
