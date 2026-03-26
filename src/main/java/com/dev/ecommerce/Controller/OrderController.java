package com.dev.ecommerce.Controller;

import com.dev.ecommerce.Service.OrderService;
import com.dev.ecommerce.dto.OrderRequest;
import com.dev.ecommerce.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest request) {

        return orderService.placeOrder(
                request.getUserId(),
                request.getProductId(),
                request.getQuantity()
        );
    }
    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId){
        return orderService.getUserOrders(userId);
    }
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id,
                                   @RequestParam String status) {

        return orderService.updateStatus(id, status);
    }
    @DeleteMapping("/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "Order Cancelled Successfully";
    }
}
