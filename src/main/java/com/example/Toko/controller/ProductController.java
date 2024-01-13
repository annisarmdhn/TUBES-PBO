package com.example.Toko.controller;

import ch.qos.logback.classic.Logger;
import com.example.Toko.model.Product;
import com.example.Toko.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/api/product")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    private static final Logger log = (Logger) LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value="/get-product",produces = "application/json")
    @Operation(summary = "Api to get all product")
    public List<Product> getProduct() {
        return productService.getAllProduct();
    }

    @PostMapping(value = "/add-product",produces = "application/json")
    public ResponseEntity addProduct(@RequestBody Product product){
        log.info("Received product data: {}", product);
        productService.addProduct(product.builder()
                        .ProductCode(product.getProductCode())
                        .name(product.getName())
                        .price(product.getPrice())
                        .merchant(product.getMerchant())
                .build());
        return ResponseEntity.ok("Product added successfully");
    }

    @PostMapping(value = "/update-product",produces = "application/json")
    public ResponseEntity updateProduct(@RequestBody Product product) {
        Product updateProduct = productService.updateProduct(product);
        if (updateProduct != null) {
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to update Product", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{ProductCode}")
    public String deleteProduct(@PathVariable("ProductCode") String ProductCode) {
        productService.deleteProduct(ProductCode);
        return "Delete Product " + ProductCode+ " success!";
    }

    @GetMapping("/detail/{ProductCode}")
    public ResponseEntity getProductDetail(@PathVariable("ProductCode") String ProductCode) {
        Product product = productService.getProductDetails(ProductCode);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }


//    @GetMapping(value = "/detail")
//    @Operation(summary = "Getting detail of one product by product code")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Product found"),
//            @ApiResponse(responseCode = "404", description = "Inputted product code not found")
//    })
//
//    private List<?> testWildCard(){
//        return Arrays.asList("String", 40);
//    }


}
