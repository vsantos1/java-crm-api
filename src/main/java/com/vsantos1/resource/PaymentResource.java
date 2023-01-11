package com.vsantos1.resource;

import com.vsantos1.event.CreatedResourceEvent;
import com.vsantos1.model.Payment;
import com.vsantos1.service.PaymentService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class PaymentResource {

    private final PaymentService paymentService;
    private final ApplicationEventPublisher publisher;

    public PaymentResource(PaymentService paymentService, ApplicationEventPublisher publisher) {
        this.paymentService = paymentService;
        this.publisher = publisher;
    }

    @GetMapping(value = "/payments")
    public ResponseEntity<List<Payment>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll());
    }

    @GetMapping(value = "/payments/{payment_id}")
    public ResponseEntity<Payment> getById(@PathVariable("payment_id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findByUUID(id));
    }

    @PostMapping(value = "/payments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> create(@RequestBody Payment payment, HttpServletResponse response) {
        // TODO : publish event on creation
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.execute(payment));
    }

    @PutMapping(value = "/payments/{payment_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> update(@PathVariable("payment_id") UUID id, @RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.update(id, payment));
    }

}
