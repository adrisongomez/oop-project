package com.oopecommerce.security;

public interface IHasher {
    String hash(String text);
    Boolean match(String text, String hashedText);
}
