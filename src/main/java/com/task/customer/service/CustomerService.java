package com.task.customer.service;

import com.task.customer.DTO.CustomerDTO;
import com.task.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(CustomerDTO customerDTO);

    List<Customer> readAllCustomers();

    Customer readCustomerByID(Long id);

    Customer update(CustomerDTO customerDTO);

    Customer delete(Long id);
}
