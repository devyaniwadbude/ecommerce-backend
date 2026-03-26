package com.dev.ecommerce.Service;

import com.dev.ecommerce.Repository.OrderRepository;
import com.dev.ecommerce.Repository.ProductRepository;
import com.dev.ecommerce.Repository.UserRepository;
import com.dev.ecommerce.model.Order;
import com.dev.ecommerce.model.Product;
import com.dev.ecommerce.model.User;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.lang.*;

@Service
    public class OrderService {

        private final OrderRepository orderRepository;
        private final ProductRepository productRepository;
        private UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                            ProductRepository productRepository) {
            this.orderRepository = orderRepository;
            this.productRepository = productRepository;
        }
        public Order placeOrder(String email, Long productId, int quantity) {

             User user = userRepository.findByEmail(email)
                  .orElseThrow(() -> new RuntimeException("User not found"));

             Product product = productRepository.findById(productId)
                 .orElseThrow(() -> new RuntimeException("Product not found"));

             if(product.getQuantity() < quantity){
                 throw new RuntimeException("Not enough stock");
             }

            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            Order order = new Order();
            order.setUserId(user.getId());   // 🔥 auto set
            order.setProductId(productId);
            order.setQuantity(quantity);

            order.setTotalPrice(product.getPrice() * quantity);
            order.setStatus("PLACED");
            order.setOrderDate(LocalDateTime.now());

            return orderRepository.save(order);
        }

        public List<Order> getUserOrders(Long userId){
            return orderRepository.findByUserId(userId);
        }
        public Order updateStatus(Long orderId, String status) {

            Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

            order.setStatus(status);

            return orderRepository.save(order);
        }
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        if(order.getStatus().equals("DELIVERED")){
            throw new RuntimeException("Cannot cancel delivered order");
        }

        Product product = productRepository.findById(order.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setQuantity(product.getQuantity() + order.getQuantity());
        productRepository.save(product);

        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
    }
