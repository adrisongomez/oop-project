package com.oopecommerce.exceptions;

public class InventoryInsufficientException extends RuntimeException {
    public InventoryInsufficientException(String message) {
        super(message);
    }
}
