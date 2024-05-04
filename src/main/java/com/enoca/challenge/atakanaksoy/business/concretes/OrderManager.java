package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.OrderService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.CreateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.UpdateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.CreatedOrderResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetAllOrdersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetOrderByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.UpdatedOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderManager implements OrderService {
    @Override
    public CreatedOrderResponse add(CreateOrderRequest createOrderRequest) {
        return null;
    }

    @Override
    public UpdatedOrderResponse update(UpdateOrderRequest updateOrderRequest, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public GetOrderByIdResponse getById(int id) {
        return null;
    }

    @Override
    public List<GetAllOrdersResponse> getAll() {
        return null;
    }
}
