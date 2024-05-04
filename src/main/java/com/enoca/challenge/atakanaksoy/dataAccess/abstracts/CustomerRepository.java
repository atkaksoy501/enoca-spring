package com.enoca.challenge.atakanaksoy.dataAccess.abstracts;

import com.enoca.challenge.atakanaksoy.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByActiveTrue();
}
