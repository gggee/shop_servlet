package com.example.sport_shop.dto;

import java.util.List;

public class OrderDto {
    private Long id;
    private String customerName;
    private String address;
    private List<ProductDto> products;
    private double totalAmount;

    public OrderDto() {}

    public OrderDto(Long id, String customerName, String address, List<ProductDto> products, double totalAmount) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.products = products;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
