package com.vsantos1.service;

import com.vsantos1.exception.ResourceNotFoundException;
import com.vsantos1.model.Category;
import com.vsantos1.model.Payment;
import com.vsantos1.model.Person;
import com.vsantos1.repository.CategoryRepository;
import com.vsantos1.repository.PaymentRepository;
import com.vsantos1.repository.PersonRepository;
import com.vsantos1.service.filter.PaymentFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CategoryRepository categoryRepository;

    private final PersonRepository personRepository;


    public PaymentService(PaymentRepository paymentRepository, CategoryRepository categoryRepository, PersonRepository personRepository) {
        this.paymentRepository = paymentRepository;
        this.categoryRepository = categoryRepository;
        this.personRepository = personRepository;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment execute(Payment payment) {
        Optional<Category> optionalCategory = categoryRepository.findById(payment.getCategory().getId());
        Optional<Person> optionalPerson = personRepository.findById(payment.getPerson().getId());

        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category not found");
        }

        if (optionalPerson.isEmpty()) {
            throw new ResourceNotFoundException("Person not found");
        }

        return paymentRepository.save(payment);
    }

    public Payment findByUUID(UUID id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            return optionalPayment.get();
        }
        throw new ResourceNotFoundException("No records found for this ID");
    }

    public Page<Payment> findByPaymentFilter(PaymentFilter paymentFilter, Pageable pageable) {
        System.out.println(paymentFilter);
        if(paymentFilter.getDescription() != null){
            return paymentRepository.findPaymentByDescriptionContainingIgnoreCase(paymentFilter.getDescription(), pageable);
        }

        if(paymentFilter.getDueDate() != null ){
            return paymentRepository.findPaymentByDueDate(LocalDate.parse(paymentFilter.getDueDate()), pageable);
        }

        if(paymentFilter.getPaymentDate() != null){
            return paymentRepository.findPaymentByPaymentDate(LocalDate.parse(paymentFilter.getPaymentDate()), pageable);
        }
        return  paymentRepository.findAll(pageable);
    }

    public Payment update(UUID id, Payment payment) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }
        payment.setCategory(optionalPayment.get().getCategory());
        payment.setPerson(optionalPayment.get().getPerson());
        BeanUtils.copyProperties(payment, optionalPayment.get(), "id");

        return paymentRepository.save(optionalPayment.get());
    }

    public void deleteByUUID(UUID id) {
        paymentRepository.deleteById(id);
    }

}
