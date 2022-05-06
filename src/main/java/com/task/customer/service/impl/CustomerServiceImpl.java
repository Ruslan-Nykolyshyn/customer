package com.task.customer.service.impl;

import com.task.customer.DTO.CustomerDTO;
import com.task.customer.entity.Customer;
import com.task.customer.exception.DataNotCorrectException;
import com.task.customer.map.CustomerMap;
import com.task.customer.repository.CustomerRepository;
import com.task.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustomerMap customerMap;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMap customerMap) {
        this.customerRepository = customerRepository;
        this.customerMap = customerMap;
    }

    @Override
    public Customer create(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        if (customerRepository.findByEmail(customerDTO.getEmail()) == null){
           customer = customerMap.map(customerDTO);
            customerRepository.save(customer);
        }

        return customer;
    }

    @Override
    public List<Customer> readAllCustomers() {
        List<Customer> allByActivate = customerRepository.findAllByActivate(true);
        return allByActivate;
    }

    @Override
    public Customer readCustomerByID(Long id) {
        Customer customer = customerRepository.getById(id);
        if (customer.isActivate() == true){
            customer = customerRepository.getById(id);
        }else throw new DataNotCorrectException("Customer with id->"+ customer.getId()+"not found!");
        return customer;
    }

    @Override
    public Customer update(CustomerDTO customerDTO) {
        if (customerRepository.getById(customerDTO.getId()) != null) {
            return customerMap.mapUpdated(customerDTO);
        } else {
            throw new DataNotCorrectException("Check that the recording data is correct.");
        }
    }

    @Override
    public Customer delete(Long id) {
        Customer customer = customerRepository.getById(id);
        if (customer != null){
            customer.setActivate(false);
            customerRepository.save(customerMap.mapUpdated(customerMap.map(customer)));
        }else throw new DataNotCorrectException("Check that the recording data is correct.");

        return customer;
    }
}
