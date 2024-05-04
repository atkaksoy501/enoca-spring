package com.enoca.challenge.atakanaksoy.business.abstracts;

import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.CreateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.UpdateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.CreatedProductResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetAllProductsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetProductByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.UpdatedProductResponse;

import java.util.List;

public interface ProductService {
    CreatedProductResponse add(CreateProductRequest createProductRequest);
    UpdatedProductResponse update(UpdateProductRequest updateProductRequest, int id);
    void delete(int id);
    GetProductByIdResponse getById(int id);
    List<GetAllProductsResponse> getAll();
}
