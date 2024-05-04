package com.enoca.challenge.atakanaksoy.business.rules;

import com.enoca.challenge.atakanaksoy.business.messages.OrderMessages;
import com.enoca.challenge.atakanaksoy.core.utilities.exceptions.types.BusinessException;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.OrderRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderBusinessRules {
    private final OrderRepository orderRepository;

    public Order orderMustExists(int orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null || !order.isActive()) {
            throw new BusinessException(OrderMessages.ORDER_NOT_EXISTS);
        }
        else return order;
    }
}
