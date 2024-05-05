package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.CartService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.AddProductToCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.CreateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.RemoveProductFromCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.cart.UpdateCartRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.CreatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetAllCartsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.GetCartByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.cart.UpdatedCartResponse;
import com.enoca.challenge.atakanaksoy.business.rules.CartBusinessRules;
import com.enoca.challenge.atakanaksoy.business.rules.CustomerBusinessRules;
import com.enoca.challenge.atakanaksoy.business.rules.ProductBusinessRules;
import com.enoca.challenge.atakanaksoy.core.utilities.mapping.ModelMapperService;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.CartRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Cart;
import com.enoca.challenge.atakanaksoy.entities.concretes.CartProduct;
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
    private final CustomerBusinessRules customerBusinessRules;
    private final ProductBusinessRules productBusinessRules;

    @Override
    public CreatedCartResponse add(CreateCartRequest createCartRequest) {
        Cart cart = new Cart();
        cart.setCustomer(customerBusinessRules.customerMustExists(createCartRequest.getCustomerId()));
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
        UpdatedCartResponse updatedCartResponse = modelMapperService.forResponse().map(savedCart, UpdatedCartResponse.class);
        updatedCartResponse.setProductIds(savedCart.getCartProducts().stream().filter(CartProduct::isActive).map(cp -> cp.getProduct().getId()).toList());
        return updatedCartResponse;
    }

    @Override
    public void delete(int id) {
        Cart cart = cartBusinessRules.cartMustExists(id);
        cart.setActive(false);
        cart.setDeleteDate(LocalDateTime.now());
        cartRepository.save(cart);
    }

    @Override
    public GetCartByIdResponse getById(int id) {
        Cart cart = cartBusinessRules.cartMustExists(id);
        List<Integer> productIds = cart.getCartProducts().stream().filter(CartProduct::isActive).map(cp -> cp.getProduct().getId()).toList();
        GetCartByIdResponse getCartByIdResponse = modelMapperService.forResponse().map(cart, GetCartByIdResponse.class);
        getCartByIdResponse.setProductIds(productIds);
        return getCartByIdResponse;
    }

    @Override
    public List<GetAllCartsResponse> getAll() {
        List<Cart> carts = cartRepository.findAllByActiveTrue();
        return carts.stream().map(cart -> {
            GetAllCartsResponse getAllCartsResponse = modelMapperService.forResponse().map(cart, GetAllCartsResponse.class);
            getAllCartsResponse.setProductIds(cart.getCartProducts().stream().filter(CartProduct::isActive).map(cp -> cp.getProduct().getId()).toList());
            return getAllCartsResponse;
        }
        ).toList();
    }

    @Override
    public UpdatedCartResponse addProduct(AddProductToCartRequest addProductToCartRequest) {
        productBusinessRules.productStockMustBeEnough(addProductToCartRequest.getProductId(), addProductToCartRequest.getQuantity());
        Cart cart = cartBusinessRules.cartMustExists(addProductToCartRequest.getCartId());
        CartProduct cartProduct = cart.getCartProducts().stream().filter(cp -> cp.getProduct().getId() == addProductToCartRequest.getProductId() && cp.isActive()).findFirst().orElse(new CartProduct());
        cartProduct.setCart(cart);
        cartProduct.setProduct(productBusinessRules.productMustExists(addProductToCartRequest.getProductId()));
        cartProduct.setQuantity(cartProduct.getQuantity() + addProductToCartRequest.getQuantity());
        double totalPrice = cartProduct.getProduct().getPrice() * addProductToCartRequest.getQuantity();
        cartProduct.setTotalPrice(totalPrice);
        cartProduct.setActive(true);
        cartProduct.setCreateDate(LocalDateTime.now());
        cart.getCartProducts().add(cartProduct);
        cart.setUpdateDate(LocalDateTime.now());
        cart.setTotalPrice(cart.getTotalPrice() + totalPrice);
        Cart savedCart = cartRepository.save(cart);
        UpdatedCartResponse updatedCartResponse = modelMapperService.forResponse().map(savedCart, UpdatedCartResponse.class);
        List<Integer> productIds = savedCart.getCartProducts().stream().filter(CartProduct::isActive).map(cp -> cp.getProduct().getId()).toList();
        updatedCartResponse.setProductIds(productIds);
        return updatedCartResponse;
    }

    @Override
    public UpdatedCartResponse removeProduct(RemoveProductFromCartRequest removeProductFromCartRequest) {
        Cart cart = cartBusinessRules.cartMustExists(removeProductFromCartRequest.getCartId());
        CartProduct cartProduct = cartBusinessRules.cartProductMustExists(cart, removeProductFromCartRequest.getProductId());
        cart.getCartProducts().remove(cartProduct);
        cart.setTotalPrice(cart.getTotalPrice() - cartProduct.getTotalPrice());
        if (cartProduct.getQuantity() > removeProductFromCartRequest.getQuantity()) {
            cartProduct.setQuantity(cartProduct.getQuantity() - removeProductFromCartRequest.getQuantity());
            cartProduct.setTotalPrice(cartProduct.getTotalPrice() - (cartProduct.getProduct().getPrice() * removeProductFromCartRequest.getQuantity()));
            cartProduct.setUpdateDate(LocalDateTime.now());
            cart.getCartProducts().add(cartProduct);
            cart.setTotalPrice(cart.getTotalPrice() + cartProduct.getTotalPrice());
        }
        else {
            cartProduct.setActive(false);
            cartProduct.setDeleteDate(LocalDateTime.now());
        }
        cart.setUpdateDate(LocalDateTime.now());
        Cart savedCart = cartRepository.save(cart);
        UpdatedCartResponse updatedCartResponse = modelMapperService.forResponse().map(savedCart, UpdatedCartResponse.class);
        updatedCartResponse.setProductIds(savedCart.getCartProducts().stream().filter(CartProduct::isActive).map(cp -> cp.getProduct().getId()).toList());
        return updatedCartResponse;
    }

    @Override
    public GetCartByIdResponse getByCustomerId(int customerId) {
        Cart cart = cartBusinessRules.cartMustExistsByCustomerId(customerId);
        GetCartByIdResponse getCartByIdResponse = modelMapperService.forResponse().map(cart, GetCartByIdResponse.class);
        getCartByIdResponse.setProductIds(cart.getCartProducts().stream().filter(CartProduct::isActive).map(cp -> cp.getProduct().getId()).toList());
        return getCartByIdResponse;
    }

    @Override
    public void emptyCart(int id) {
        Cart cart = cartBusinessRules.cartMustExists(id);
        cart.getCartProducts().forEach(cp -> {
            cp.setActive(false);
            cp.setDeleteDate(LocalDateTime.now());
        });
        cart.setTotalPrice(0);
        cart.setUpdateDate(LocalDateTime.now());
        cartRepository.save(cart);
    }

    @Override
    public void emptyCartAndReduceStock(Cart cart) {
        cart.getCartProducts().stream().filter(CartProduct::isActive).forEach(cp -> {
            cp.setActive(false);
            cp.setDeleteDate(LocalDateTime.now());
            cp.getProduct().setStock(cp.getProduct().getStock() - cp.getQuantity());
            cp.getProduct().setUpdateDate(LocalDateTime.now());
        });
        cart.setTotalPrice(0);
        cart.setUpdateDate(LocalDateTime.now());
        cartRepository.save(cart);
    }
}
