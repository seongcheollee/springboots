package gpsdraw.springboots.service;


import gpsdraw.springboots.data.dto.ProductDto;
import gpsdraw.springboots.data.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

    List<ProductResponseDto> getProductsByUserId(String userId);
}