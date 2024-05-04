package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.OrderService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.CreateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.UpdateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.CreatedOrderResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetAllOrdersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetOrderByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.UpdatedOrderResponse;
import com.enoca.challenge.atakanaksoy.business.rules.OrderBusinessRules;
import com.enoca.challenge.atakanaksoy.core.utilities.mapping.ModelMapperService;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.OrderRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Order;
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
    @Override
    public CreatedOrderResponse add(CreateOrderRequest createOrderRequest) { //placeOrder
        Order order = modelMapperService.forRequest().map(createOrderRequest, Order.class);
        order.setActive(true);
        order.setCreateDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return modelMapperService.forResponse().map(savedOrder, CreatedOrderResponse.class);
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
    public GetOrderByIdResponse getById(int id) {
        Order order = orderBusinessRules.orderMustExists(id);
        return modelMapperService.forResponse().map(order, GetOrderByIdResponse.class);
    }

    @Override
    public List<GetAllOrdersResponse> getAll() {
        List<Order> orders = orderRepository.findAllByActiveTrue();
        return orders.stream().map(
                order -> modelMapperService.forResponse().map(order, GetAllOrdersResponse.class)
        ).toList();
    }
}
