package com.example.sport_shop.service;

import com.example.sport_shop.model.Product;
import com.example.sport_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> search(String keyword) {
        return productRepository.findByName(keyword, keyword, keyword);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
