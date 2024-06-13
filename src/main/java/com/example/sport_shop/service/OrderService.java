package com.example.sport_shop.service;

import com.example.sport_shop.model.Order;
import com.example.sport_shop.model.User;
import com.example.sport_shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }
}
