package com.example.Toko.service;

import com.example.Toko.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProduct();
    Product updateProduct(Product product);
    void deleteProduct(String ProductCode);
    Product getProductDetails(String ProductCode);
}
