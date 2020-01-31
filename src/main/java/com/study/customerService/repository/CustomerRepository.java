package com.study.customerService.repository;

import com.study.customerService.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByEmail(String email);

    public Customer findByid(String ID);
}