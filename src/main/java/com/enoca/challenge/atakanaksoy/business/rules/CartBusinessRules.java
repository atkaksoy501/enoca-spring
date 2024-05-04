package com.enoca.challenge.atakanaksoy.business.rules;

import com.enoca.challenge.atakanaksoy.business.messages.CartMessages;
import com.enoca.challenge.atakanaksoy.core.utilities.exceptions.types.BusinessException;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.CartRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartBusinessRules {
    private final CartRepository cartRepository;

    public Cart cartMustExists(int cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null || !cart.isActive()) {
            throw new BusinessException(CartMessages.CART_NOT_EXISTS);
        }
        else return cart;
    }
}
