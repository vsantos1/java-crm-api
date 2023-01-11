package com.vsantos1.service.filter;

import org.springframework.format.annotation.DateTimeFormat;

public class PaymentFilter {


    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String paymentDate;

    PaymentFilter(){}

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }


    public String getPaymentDate() {
        return paymentDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
