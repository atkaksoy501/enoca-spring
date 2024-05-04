package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.CartService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.CreateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.UpdateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.CreatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetAllCartsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetCartByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.UpdatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.rules.CartBusinessRules;
import com.enoca.challenge.atakanaksoy.core.utilities.mapping.ModelMapperService;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.CartRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class CartManager implements CartService {
    private final CartRepository cartRepository;
    private final CartBusinessRules cartBusinessRules;
    private final ModelMapperService modelMapperService;
    @Override
    public CreatedCartResponse add(CreateCartRequest createCartRequest) {
        Cart cart = modelMapperService.forRequest().map(createCartRequest, Cart.class);
        cart.setActive(true);
        cart.setCreateDate(LocalDateTime.now());
        Cart savedCart = cartRepository.save(cart);
        return modelMapperService.forResponse().map(savedCart, CreatedCartResponse.class);
    }

    @Override
    public UpdatedCartResponse update(UpdateCartRequest updateCartRequest, int id) {
        Cart cart = cartBusinessRules.cartMustExists(id);
        modelMapperService.forUpdate().map(updateCartRequest, cart);
        cart.setUpdateDate(LocalDateTime.now());
        Cart savedCart = cartRepository.save(cart);
        return modelMapperService.forResponse().map(savedCart, UpdatedCartResponse.class);
    }

    @Override
    public void delete(int id) { //todo: implement emptyCart
        Cart cart = cartBusinessRules.cartMustExists(id);
        cart.setActive(false);
        cart.setDeleteDate(LocalDateTime.now());
        cartRepository.save(cart);
    }

    @Override
    public GetCartByIdResponse getById(int id) {
        Cart cart = cartBusinessRules.cartMustExists(id);
        return modelMapperService.forResponse().map(cart, GetCartByIdResponse.class);
    }

    @Override
    public List<GetAllCartsResponse> getAll() {
        List<Cart> carts = cartRepository.findAllByActiveTrue();
        return carts.stream().map(
                cart -> modelMapperService.forResponse().map(cart, GetAllCartsResponse.class)
        ).toList();
    }

    @Override
    public UpdatedCartResponse addProduct(int cartId, int productId) {
        Cart cart = cartBusinessRules.cartMustExists(cartId);
        return null; //todo: implement
    }

    @Override
    public UpdatedCartResponse removeProduct(int cartId, int productId) {
        Cart cart = cartBusinessRules.cartMustExists(cartId);
        return null; //todo: implement
    }

    @Override
    public GetCartByIdResponse getByCustomerId(int customerId) {
        Cart cart = cartBusinessRules.cartMustExistsByCustomerId(customerId);
        return modelMapperService.forResponse().map(cart, GetCartByIdResponse.class);
    }
}
