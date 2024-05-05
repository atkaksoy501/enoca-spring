package com.enoca.challenge.atakanaksoy.business.abstracts;

import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.AddProductToCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.CreateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.RemoveProductFromCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.UpdateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.CreatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetAllCartsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetCartByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.UpdatedCartResponse;
import com.enoca.challenge.atakanaksoy.entities.concretes.Cart;

import java.util.List;

public interface CartService {
    CreatedCartResponse add(CreateCartRequest createCartRequest);
    UpdatedCartResponse update(UpdateCartRequest updateCartRequest, int id);
    void delete(int id);
    GetCartByIdResponse getById(int id);
    List<GetAllCartsResponse> getAll();
    UpdatedCartResponse addProduct(AddProductToCartRequest addProductToCartRequest);
    UpdatedCartResponse removeProduct(RemoveProductFromCartRequest removeProductFromCartRequest);
    GetCartByIdResponse getByCustomerId(int customerId);
    void emptyCart(int id);
    void emptyCartAndReduceStock(Cart cart);
}
