package com.vyatsu.task14.services;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {productRepository.delete(id);}

    public List<Product> getProductsByTitle(String title) {
        return productRepository.findProductsByTitle(title);
    }

    public List<Product> filterProducts(String title, Integer gt, Integer lt) {
	    return productRepository.filterProducts(title, gt, lt);
    }

    public void edit(Long id, String title, Integer price) {productRepository.edit(id, title, price);}
}

