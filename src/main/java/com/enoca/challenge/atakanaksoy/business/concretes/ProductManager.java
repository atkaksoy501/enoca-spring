package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.ProductService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.CreateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.UpdateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.CreatedProductResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetAllProductsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetProductByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.UpdatedProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductManager implements ProductService {
    @Override
    public CreatedProductResponse add(CreateProductRequest createProductRequest) {
        return null;
    }

    @Override
    public UpdatedProductResponse update(UpdateProductRequest updateProductRequest, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public GetProductByIdResponse getById(int id) {
        return null;
    }

    @Override
    public List<GetAllProductsResponse> getAll() {
        return null;
    }
}
