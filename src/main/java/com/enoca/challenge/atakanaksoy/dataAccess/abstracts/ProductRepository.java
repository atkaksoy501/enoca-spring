package com.enoca.challenge.atakanaksoy.dataAccess.abstracts;

import com.enoca.challenge.atakanaksoy.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByActiveTrue();
}
