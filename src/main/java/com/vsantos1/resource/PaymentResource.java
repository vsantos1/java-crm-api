package com.vsantos1.resource;

import com.vsantos1.model.Payment;
import com.vsantos1.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class PaymentResource {

    private final PaymentRepository paymentRepository;

    public PaymentResource(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping(value = "/payments")
    public ResponseEntity<List<Payment>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentRepository.findAll());
    }

    @PostMapping(value = "/payments",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> create(@RequestBody Payment payment){
        // TODO: Implement payment creation and exceptions handling
        return null;
    }

}
