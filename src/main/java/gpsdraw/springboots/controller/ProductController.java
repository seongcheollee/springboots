package gpsdraw.springboots.controller;



import gpsdraw.springboots.data.dto.ChangeProductNameDto;
import gpsdraw.springboots.data.dto.ProductDto;
import gpsdraw.springboots.data.dto.ProductResponseDto;
import gpsdraw.springboots.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getProduct] request Data :: productId : {}", number);
        ProductResponseDto productResponseDto = productService.getProduct(number);

        LOGGER.info(
            "[getProduct] response Data :: productId : {}, productName : {}, productPrice : {}, productStock : {}",
            productResponseDto.getNumber(), productResponseDto.getName(),
            productResponseDto.getPrice(), productResponseDto.getStock());
        LOGGER.info("[getProduct] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByUserId(@PathVariable String userId) {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getProductsByUserId] request Data :: userId : {}", userId);

        List<ProductResponseDto> userProducts = productService.getProductsByUserId(userId);

        LOGGER.info("[getProductsByUserId] response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(userProducts);
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        System.out.println("너 들어옴??? getallproducts");
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getAllProducts] request Data");

        List<ProductResponseDto> allProducts = productService.getAllProducts();

        LOGGER.info("[getAllProducts] response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        long currentTime = System.currentTimeMillis();
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        LOGGER.info("[createProduct] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(
        @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[changeProductName] request Data :: productNumber : {}, productName : {}",
            changeProductNameDto.getNumber(), changeProductNameDto.getName());

        ProductResponseDto productResponseDto = productService.changeProductName(
            changeProductNameDto.getNumber(),
            changeProductNameDto.getName());

        LOGGER.info(
            "[changeProductName] response Data :: productNumber : {}, productName : {}, productPrice : {}, productStock : {}",
            productResponseDto.getNumber(), productResponseDto.getName(),
            productResponseDto.getPrice(), productResponseDto.getStock());
        LOGGER.info("[changeProductName] response Time : {}ms",
            System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[deleteProduct] request Data :: productNumber : {}", number);

        productService.deleteProduct(number);

        LOGGER.info("[deleteProduct] response Time : {}ms",
            System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
