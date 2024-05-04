package com.enoca.challenge.atakanaksoy.dataAccess.abstracts;

import com.enoca.challenge.atakanaksoy.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByActiveTrue();
}
