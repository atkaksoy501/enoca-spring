package com.enoca.challenge.atakanaksoy.business.abstracts;

import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.CreateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.UpdateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.CreatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetAllCartsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetCartByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.UpdatedCartResponse;

public interface CartService {
    CreatedCartResponse add(CreateCartRequest createCartRequest);
    UpdatedCartResponse update(UpdateCartRequest updateCartRequest);
    void delete(int id);
    GetCartByIdResponse getById(int id);
    GetAllCartsResponse getAll();
}
