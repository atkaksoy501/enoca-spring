package com.enoca.challenge.atakanaksoy.api.controllers;

import com.enoca.challenge.atakanaksoy.business.abstracts.ProductService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.CreateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.UpdateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.CreatedProductResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetAllProductsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetProductByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.UpdatedProductResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Management", description = "Product Management APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    @Operation(summary = "Add new Product")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.add(createProductRequest);
    }

    @Operation(summary = "Update an existing Product with id")
    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse update(@Valid @RequestBody UpdateProductRequest updateProductRequest, @PathVariable int id) {
        return productService.update(updateProductRequest, id);
    }

    @Operation(summary = "Soft delete an existing Product with id")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @Operation(summary = "Get an existing Product with id")
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductByIdResponse getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @Operation(summary = "Get all Products")
    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductsResponse> getAll() {
        return productService.getAll();
    }

}
