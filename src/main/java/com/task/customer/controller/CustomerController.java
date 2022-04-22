package com.task.customer.controller;

import com.task.customer.DTO.CustomerDTO;
import com.task.customer.entity.Customer;
import com.task.customer.map.CustomerMap;
import com.task.customer.service.CustomerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Log
public class CustomerController {

    private CustomerService customerService;

    private CustomerMap customerMap;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMap customerMap) {
        this.customerService = customerService;
        this.customerMap = customerMap;
    }

    @PostMapping("/customers")
    public CustomerDTO create(@RequestBody() CustomerDTO customerDTO) {
        log.info("Customer has created!!");
        return customerMap.map(customerService.create(customerDTO));
    }

    @GetMapping("/customers")
    public List<CustomerDTO> readAllCustomer() {
        log.info(
                "Here is a list of customers :) "
        );
        List<Customer> customerList = customerService.readAllCustomers();
        List<CustomerDTO> customerDTOList = customerList.stream().map(customer -> customerMap.map(customer)).collect(Collectors.toList());
        return customerDTOList;
    }

    @GetMapping("/customers/{id}")
    public Customer readCustomer(@PathVariable(name = "id") Long id) {
        log.info("Customer with ID -> " + id + " received :) ");
        return customerService.readCustomerByID(id);
    }

    @PutMapping("/customers")
    private CustomerDTO update(@RequestBody() CustomerDTO customerDTO) {
        log.info("Customer with ID -> " + customerDTO.getId() + " and \n Name -> " + customerDTO.getFullName() + " updated :) ");
        return customerMap.map(customerService.update(customerDTO));
    }

    @DeleteMapping("/customers/{id}")
    public CustomerDTO delete(@PathVariable(name = "id") Long id) {
        log.info("Customer with ID -> " + id + " has been delete");
        return customerMap.map(customerService.delete(id));
    }
}
