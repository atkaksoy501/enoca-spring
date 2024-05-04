package com.enoca.challenge.atakanaksoy.dataAccess.abstracts;

import com.enoca.challenge.atakanaksoy.entities.concretes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByActiveTrue();
    Optional<Cart> findByCustomerId(int customerId);
}
