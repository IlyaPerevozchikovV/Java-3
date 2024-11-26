package com.vyatsu.task14.entities;

public class Product {
    private Long id;
    private String title;
    private int price;
    private int viewCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(Long id, String title, int price, int viewCount) {
        this.id = id;
        this.title = title;
        this.price = price;
	this.viewCount = viewCount;
    }
}
