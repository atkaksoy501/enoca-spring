package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.CartService;
import com.enoca.challenge.atakanaksoy.business.abstracts.OrderService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.CreateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.UpdateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.CreatedOrderResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetAllOrdersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetOrderByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.UpdatedOrderResponse;
import com.enoca.challenge.atakanaksoy.business.rules.CartBusinessRules;
import com.enoca.challenge.atakanaksoy.business.rules.CustomerBusinessRules;
import com.enoca.challenge.atakanaksoy.business.rules.OrderBusinessRules;
import com.enoca.challenge.atakanaksoy.core.utilities.mapping.ModelMapperService;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.OrderRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Cart;
import com.enoca.challenge.atakanaksoy.entities.concretes.CartProduct;
import com.enoca.challenge.atakanaksoy.entities.concretes.Order;
import com.enoca.challenge.atakanaksoy.entities.concretes.OrderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderBusinessRules orderBusinessRules;
    private final ModelMapperService modelMapperService;
    private final CartBusinessRules cartBusinessRules;
    private final CartService cartService;
    @Override
    public CreatedOrderResponse add(CreateOrderRequest createOrderRequest) { //placeOrder
        Order order = new Order();
        Cart cart = cartBusinessRules.cartMustExists(createOrderRequest.getCartId());
        cartBusinessRules.cartMustHaveAProduct(cart);
        List<OrderProduct> orderProducts = cart.getCartProducts().stream().filter(CartProduct::isActive).map(
                cartProduct -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProduct(cartProduct.getProduct());
                    orderProduct.setQuantity(cartProduct.getQuantity());
                    orderProduct.setTotalPrice(cartProduct.getTotalPrice());
                    orderProduct.setOrder(order);
                    orderProduct.setCreateDate(LocalDateTime.now());
                    orderProduct.setActive(true);
                    return orderProduct;
                }
        ).toList();
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderProducts(orderProducts);
        order.setCustomer(cart.getCustomer());
        order.setDate(LocalDateTime.now());
        order.setActive(true);
        order.setCreateDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        cartService.emptyCartAndReduceStock(cart);
        CreatedOrderResponse createdOrderResponse = modelMapperService.forResponse().map(savedOrder, CreatedOrderResponse.class);
        createdOrderResponse.setProductIds(savedOrder.getOrderProducts().stream().map(orderProduct -> orderProduct.getProduct().getId()).toList());
        return createdOrderResponse;
    }

    @Override
    public UpdatedOrderResponse update(UpdateOrderRequest updateOrderRequest, int id) {
        Order order = orderBusinessRules.orderMustExists(id);
        modelMapperService.forUpdate().map(updateOrderRequest, order);
        order.setUpdateDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return modelMapperService.forResponse().map(savedOrder, UpdatedOrderResponse.class);
    }

    @Override
    public void delete(int id) {
        Order order = orderBusinessRules.orderMustExists(id);
        order.setActive(false);
        order.setDeleteDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public GetOrderByIdResponse getById(int id) { //getOrderForCode
        Order order = orderBusinessRules.orderMustExists(id);
        GetOrderByIdResponse getOrderByIdResponse = modelMapperService.forResponse().map(order, GetOrderByIdResponse.class);
        getOrderByIdResponse.setProductIds(order.getOrderProducts().stream().map(orderProduct -> orderProduct.getProduct().getId()).toList());
        return getOrderByIdResponse;
    }

    @Override
    public List<GetAllOrdersResponse> getAll() {
        List<Order> orders = orderRepository.findAllByActiveTrue();
        return orders.stream().map(
                order -> modelMapperService.forResponse().map(order, GetAllOrdersResponse.class)
        ).toList();
    }

    @Override
    public List<GetAllOrdersResponse> getAllByCustomerId(int customerId) { //getAllOrdersForCustomer
        List<Order> orders = orderRepository.findAllByActiveTrueAndCustomerId(customerId);
        return orders.stream().map(order -> {
            GetAllOrdersResponse getAllOrdersResponse = modelMapperService.forResponse().map(order, GetAllOrdersResponse.class);
            getAllOrdersResponse.setProductIds(order.getOrderProducts().stream().map(orderProduct -> orderProduct.getProduct().getId()).toList());
            return getAllOrdersResponse;
        }
        ).toList();
    }
}
