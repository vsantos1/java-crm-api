package com.vsantos1.service;

import com.vsantos1.exception.ResourceNotFoundException;
import com.vsantos1.model.Category;
import com.vsantos1.model.Payment;
import com.vsantos1.model.Person;
import com.vsantos1.repository.CategoryRepository;
import com.vsantos1.repository.PaymentRepository;
import com.vsantos1.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Payment> findAll(){
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
}
