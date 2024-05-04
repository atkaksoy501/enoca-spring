package com.enoca.challenge.atakanaksoy.business.abstracts;

import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.CreateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.order.UpdateOrderRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.CreatedOrderResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetAllOrdersResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.GetOrderByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.order.UpdatedOrderResponse;

import java.util.List;

public interface OrderService {
    CreatedOrderResponse add(CreateOrderRequest createOrderRequest);
    UpdatedOrderResponse update(UpdateOrderRequest updateOrderRequest, int id);
    void delete(int id);
    GetOrderByIdResponse getById(int id);
    List<GetAllOrdersResponse> getAll();

    List<GetAllOrdersResponse> getAllByCustomerId(int customerId);
}
