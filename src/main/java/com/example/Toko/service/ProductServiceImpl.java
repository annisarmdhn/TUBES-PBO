package com.example.Toko.service;

import ch.qos.logback.classic.Logger;
import com.example.Toko.model.Product;
import com.example.Toko.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    private Logger log;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String ProductCode) {
        productRepository.deleteById(ProductCode);
    }

    @Override
    public Product getProductDetails(String ProductCode) {

        return Optional.ofNullable(productRepository.findById(ProductCode))
                .map(product -> Product.builder()
                        .ProductCode(product.get().getProductCode())
                        .name(product.get().getName())
                        .price(product.get().getPrice())
                        .merchant(product.get().getMerchant())
                        .build())
                .orElse(null);
    }


}
