package com.study.customerService.controller;

import com.study.customerService.entity.Customer;
import com.study.customerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository m_repository;

    @PostMapping("/customer")
    void createCustomer(@RequestParam final String FN, @RequestParam final String LN, @RequestParam final String email) {
        if (existEmail(email)) {
            System.out.println("Customer don't created.");
        } else {
            m_repository.save(new Customer(FN, LN, email));
            System.out.println("Customer created.");
        }
    }

    boolean existEmail(String email) {
        if (m_repository.findByEmail(email) == null) {
            System.out.println("Customer exist.");
            return false;
        }
        System.out.println("Customer don't exist.");
        return true;
    }

    @GetMapping("/customer")
    Customer getCustomer(@RequestParam final String ID) {
        Customer customer = m_repository.findByid(ID);
        if (customer != null) {
            System.out.println("send customer by ID: " + customer.email);
        } else {
            System.out.println("send no customer");
        }
        return customer;
    }

    //DEBUGGING
    @GetMapping("/customer/db/show")
    ArrayList<Customer> showDb() {
        ArrayList<Customer> arrayListObj = new ArrayList<>();
        for (Customer obj : m_repository.findAll()) {
            arrayListObj.add(obj);
        }
        return arrayListObj;
    }

    //DEBUGGING
    @PostMapping("/customer/db/clear")
    void clear() {
        m_repository.deleteAll();
    }
}