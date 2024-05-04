package com.enoca.challenge.atakanaksoy.business.concretes;

import com.enoca.challenge.atakanaksoy.business.abstracts.ProductService;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.CreateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.requests.product.UpdateProductRequest;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.CreatedProductResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetAllProductsResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.GetProductByIdResponse;
import com.enoca.challenge.atakanaksoy.business.dtos.responses.product.UpdatedProductResponse;
import com.enoca.challenge.atakanaksoy.business.rules.ProductBusinessRules;
import com.enoca.challenge.atakanaksoy.core.utilities.mapping.ModelMapperService;
import com.enoca.challenge.atakanaksoy.dataAccess.abstracts.ProductRepository;
import com.enoca.challenge.atakanaksoy.entities.concretes.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;
    private final ProductBusinessRules productBusinessRules;
    @Override
    public CreatedProductResponse add(CreateProductRequest createProductRequest) {
        Product product = modelMapperService.forRequest().map(createProductRequest, Product.class);
        product.setActive(true);
        product.setCreateDate(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return modelMapperService.forResponse().map(savedProduct, CreatedProductResponse.class);
    }

    @Override
    public UpdatedProductResponse update(UpdateProductRequest updateProductRequest, int id) {
        Product product = productBusinessRules.productMustExists(id);
        modelMapperService.forUpdate().map(updateProductRequest, product);
        product.setUpdateDate(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return modelMapperService.forResponse().map(savedProduct, UpdatedProductResponse.class);
    }

    @Override
    public void delete(int id) {
        Product product = productBusinessRules.productMustExists(id);
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public GetProductByIdResponse getById(int id) {
        Product product = productBusinessRules.productMustExists(id);
        return modelMapperService.forResponse().map(product, GetProductByIdResponse.class);
    }

    @Override
    public List<GetAllProductsResponse> getAll() {
        List<Product> products = productRepository.findAllByActiveTrue();
        return products.stream().map(
                product -> modelMapperService.forResponse().map(product, GetAllProductsResponse.class)
        ).toList();
    }
}
