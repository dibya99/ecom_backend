package com.djm.ecom.exception;

public class NotEnoughQuantityException extends RuntimeException {
    public NotEnoughQuantityException(String message) {
        super(message);
    }

}
