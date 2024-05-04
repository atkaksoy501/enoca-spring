package com.enoca.challenge.atakanaksoy.api.controllers;

import com.enoca.challenge.atakanaksoy.business.abstracts.ProductService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.CreateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.UpdateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.CreatedProductResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetAllProductsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetProductByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.UpdatedProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(CreateProductRequest createProductRequest) {
        return productService.add(createProductRequest);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse update(UpdateProductRequest updateProductRequest, @PathVariable int id) {
        return productService.update(updateProductRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductByIdResponse getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductsResponse> getAll() {
        return productService.getAll();
    }

}
