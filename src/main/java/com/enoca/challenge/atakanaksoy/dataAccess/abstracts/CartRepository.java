package com.enoca.challenge.atakanaksoy.dataAccess.abstracts;

import com.enoca.challenge.atakanaksoy.entities.concretes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByActiveTrue();
}
