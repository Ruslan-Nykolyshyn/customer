package com.task.customer.repository;


import com.task.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer getById(Long id);

    Customer findByEmail(String email);

    List<Customer> findAllByActivate(boolean condition);
}
