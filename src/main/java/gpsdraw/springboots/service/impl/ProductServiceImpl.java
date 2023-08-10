package gpsdraw.springboots.service.impl;


import gpsdraw.springboots.data.dto.ProductDto;
import gpsdraw.springboots.data.dto.ProductResponseDto;
import gpsdraw.springboots.data.entity.Product;
import gpsdraw.springboots.data.repository.ProductRepository;
import gpsdraw.springboots.service.ProductService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        LOGGER.info("[getProduct] product number : {}", number);
        Product product = productRepository.findById(number).orElseThrow(RuntimeException::new);

        LOGGER.info(
            "[getProduct] found Product :: productId : {}, productName : {}, productPrice : {}, productStock : {}",
            product.getNumber(), product.getName(), product.getPrice(), product.getStock());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponseDto(
                        product.getNumber(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock()))
                .collect(Collectors.toList());
    }


    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        LOGGER.info("[saveProduct] productName : {}", productDto.getName());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] saved ProductId : {}", savedProduct.getNumber());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) {
        LOGGER.info("[changeProductName] request productId : {}", number);
        Product foundProduct = productRepository.findById(number)
            .orElseThrow(RuntimeException::new);
        LOGGER.info("[changeProductName] found Product's name : {}", foundProduct.getName());
        foundProduct.setName(name);
        LOGGER.info("[changeProductName] change Product's name : {}", name);

        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) {
        LOGGER.info("[deleteProduct] delete ProductId : {}", number);
        productRepository.deleteById(number);
    }

    @Override
    public List<ProductResponseDto> getProductsByUserId(String userId) {
        List<Product> userProducts = productRepository.findByNameLike(userId); // 데이터베이스에서 해당 유저의 제품들을 조회하는 예시 코드

        List<ProductResponseDto> productResponseDtos = userProducts.stream()
                .map(product -> new ProductResponseDto(product.getNumber(), product.getName(), product.getPrice(), product.getStock()))
                .collect(Collectors.toList());

        return productResponseDtos;
    }
}
