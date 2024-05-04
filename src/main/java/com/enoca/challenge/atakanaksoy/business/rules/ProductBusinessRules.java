package com.enoca.challenge.atakanaksoy.business.rules;

import com.enoca.challenge.atakanaksoy.business.messages.ProductMessages;
import com.enoca.challenge.atakanaksoy.core.utilities.exceptions.types.BusinessException;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.ProductRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository productRepository;

    public Product productMustExists(int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null || !product.isActive()) {
            throw new BusinessException(ProductMessages.PRODUCT_NOT_EXISTS);
        }
        else return product;
    }
}
