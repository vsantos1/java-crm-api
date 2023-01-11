package com.vsantos1.repository;

import com.vsantos1.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

     Page<Payment> findPaymentByDescriptionContainingIgnoreCase(String description, Pageable pageable);
     Page<Payment> findPaymentByDueDate(LocalDate parse, Pageable pageable);
     Page<Payment> findPaymentByPaymentDate(LocalDate parse, Pageable pageable);
}
