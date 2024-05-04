package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.CartService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.CreateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.UpdateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.CreatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetAllCartsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetCartByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.UpdatedCartResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartManager implements CartService {
    @Override
    public CreatedCartResponse add(CreateCartRequest createCartRequest) {
        return null;
    }

    @Override
    public UpdatedCartResponse update(UpdateCartRequest updateCartRequest) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public GetCartByIdResponse getById(int id) {
        return null;
    }

    @Override
    public GetAllCartsResponse getAll() {
        return null;
    }
}
