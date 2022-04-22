package com.task.customer.service.impl;

import com.task.customer.DTO.CustomerDTO;
import com.task.customer.entity.Customer;
import com.task.customer.map.CustomerMap;
import com.task.customer.repository.CustomerRepository;
import com.task.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustomerMap customerMap;


    @Override
    public Customer create(CustomerDTO customerDTO) {
        Customer customer = customerMap.map(customerDTO);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> readAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer readCustomerByID(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public Customer update(CustomerDTO customerDTO) {
        if (customerDTO.getId() != 0) {
            return customerMap.mapUpdated(customerDTO);
        } else {
            throw new IllegalArgumentException("Check that the recording data is correct.");
        }
    }

    @Override
    public Customer delete(Long id) {
        Customer customer = customerRepository.getById(id);
        customer.setIs_NoActive(false);
        return customerRepository.save(customer);
    }
}
