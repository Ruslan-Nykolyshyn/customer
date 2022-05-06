package com.task.customer.map;

import com.task.customer.DTO.CustomerDTO;
import com.task.customer.entity.Customer;
import com.task.customer.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerMap {
    private CustomerRepository customerRepository;

    public CustomerMap(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    public Customer map(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        Date dateNow = new Date();
        long created = dateNow.getTime();
        customer.setFullName(customerDTO.getFullName());
        customer.setPhone(customerDTO.getPhone());
        customer.setId(customerDTO.getId());
        customer.setEmail(customerDTO.getEmail());
        customer.setCreated(created);
        customer.setActivate(true);
        return customer;
    }

    public CustomerDTO map(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
    }

    public Customer mapUpdated(CustomerDTO customerDTO) {
        Date dateNow = new Date();
        Customer customer = customerRepository.getById(customerDTO.getId());
        customer.setFullName(customerDTO.getFullName());
        customer.setPhone(customerDTO.getPhone());
        customer.setId(customerDTO.getId());
        customer.setEmail(customerDTO.getEmail());
        customer.setUpdated(dateNow.getTime());
        return customerRepository.save(customer);
    }



}
