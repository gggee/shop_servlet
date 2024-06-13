package com.example.sport_shop.controller;
import com.example.sport_shop.model.Order;
import com.example.sport_shop.model.Product;
import com.example.sport_shop.service.OrderService;
import com.example.sport_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/product_list";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "admin/product_detail";
        } else {
            return "admin/product_not_found";
        }
    }

    @GetMapping("/product/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);
            return "admin/product_edit_form";
        } else {
            return "admin/product_not_found";
        }
    }


    @PostMapping("/product/{id}/edit")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/order_list";
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "admin/order_detail";
        } else {
            return "admin/order_not_found";
        }
    }

    @PostMapping("/order/{id}/updateStatus")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam String newStatus) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setStatus(newStatus);
            orderService.save(existingOrder);
        }
        return "redirect:/admin/orders";
    }

    @PostMapping("/order/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders";
    }
}
