package com.vyatsu.task14.repositories.specification;

import com.vyatsu.task14.entities.Product;


public class ProductSpecification {
    public static ListSpecification<Product> hasTitle(String title) {
        return product -> product.getTitle() != null && product.getTitle().contains(title);
    }
    public static ListSpecification<Product> hasPriceGreaterThan(int price) {
        return product -> product.getPrice() >= price;
    }
    public static ListSpecification<Product> hasPriceLessThan(int price) {
        return product -> product.getPrice() <= price;
    }
}