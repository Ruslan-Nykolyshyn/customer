package com.task.customer.map;

import com.task.customer.DTO.CustomerDTO;
import com.task.customer.entity.Customer;
import com.task.customer.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerMap {
    private CustomerRepository customerRepository;

    public CustomerMap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer map(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCreated(customerDTO.getCreated());
        customer.setUpdated(customerDTO.getUpdated());
        customer.setFullName(customerDTO.getFullName());
        customer.setPhone(customerDTO.getPhone());
        customer.setId(customerDTO.getId());
        customer.setEmail(customerDTO.getEmail());
        customer.setCreated(customerDTO.getCreated());
        customer.setUpdated(customerDTO.getUpdated());
        customer.setIs_active(customerDTO.is_active());
        return customer;
    }

    public CustomerDTO map(Customer customer) {
        return CustomerDTO.builder()
                .created(customer.getCreated())
                .updated(customer.getUpdated())
                .fullName(customer.getFullName())
                .phone(customer.getPhone())
                .id(customer.getId())
                .email(customer.getEmail())
                .is_active(customer.is_active())
                .build();
    }

    public Customer mapUpdated(CustomerDTO customerDTO) {
        Date dateNow = new Date();
        Customer customer = customerRepository.getById(customerDTO.getId());
        customer.setFullName(customerDTO.getFullName());
        customer.setPhone(customerDTO.getPhone());
        customer.setId(customerDTO.getId());
        customer.setEmail(customerDTO.getEmail());
        customer.setUpdated(customerDTO.getUpdated());
        customer.setIs_active(customerDTO.is_active());
        customer.setUpdated(dateNow.getTime());
        return customerRepository.save(customer);
    }
}
