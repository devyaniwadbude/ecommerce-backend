package com.dev.ecommerce.dto;

public class OrderRequest {
    private Long userId;
    private Long productId;
    private int quantity;

    public OrderRequest() {
    }

    // ✅ getters
    public String getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    // ✅ setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
