package com.task.customer.controller;

import com.task.customer.DTO.CustomerDTO;
import com.task.customer.entity.Customer;
import com.task.customer.map.CustomerMap;
import com.task.customer.repository.CustomerRepository;
import com.task.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Log
public class CustomerController {

    private CustomerService customerService;

    private CustomerMap customerMap;

    private  CustomerRepository customerRepository;


    @PostMapping("/customers")
    @ResponseBody
    public CustomerDTO create(@RequestBody() CustomerDTO customerDTO) {
        log.info("Customer has created!!");
        customerMap.map(customerService.create(customerDTO));
        return customerMap.map(customerMap.map(customerDTO));
    }

    @GetMapping("/customers")
    @ResponseBody
    public List<CustomerDTO> readAllCustomer() {
        log.info(
                "Here is a list of customers :) "
        );
        List<Customer> customerList = customerService.readAllCustomers();
        List<CustomerDTO> responseCustomerList = customerList.stream().map(customer -> customerMap.map(customer)).collect(Collectors.toList());
        return responseCustomerList;
    }

    @GetMapping("/customers/{id}")
    @ResponseBody
    public CustomerDTO readCustomer(@PathVariable(name = "id") Long id) {
        log.info("Customer with ID -> " + id + " received :) ");
        customerService.readCustomerByID(id);
        return customerMap.map(customerRepository.getById(id));
    }

    @PutMapping("/customers")
    @ResponseBody
    private CustomerDTO update(@RequestBody() CustomerDTO customerDTO) {
        log.info("Customer with ID -> " + customerDTO.getId() + " and \n Name -> " + customerDTO.getFullName() + " updated :) ");
        customerMap.map(customerService.update(customerDTO));
        return  customerMap.map(customerService.update(customerDTO));
    }

    @DeleteMapping("/customers/{id}")
    @ResponseBody
    public CustomerDTO delete(@PathVariable(name = "id") Long id) {
        log.info("Customer with ID -> " + id + " has been delete");
        customerMap.map(customerService.delete(id));
        return customerMap.map(customerService.delete(id));
    }
}
