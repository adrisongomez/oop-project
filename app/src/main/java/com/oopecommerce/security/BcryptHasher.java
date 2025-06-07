package com.oopecommerce.security;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptHasher implements IHasher {
    private final String secret;

    public BcryptHasher(String secret) {
        this.secret = secret == null ? "" : secret;
    }

    @Override
    public String hash(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        return BCrypt.hashpw(text + secret, BCrypt.gensalt());
    }

    @Override
    public Boolean match(String text, String hashedText) {
        if (text == null || hashedText == null) {
            return false;
        }
        return BCrypt.checkpw(text + secret, hashedText);
    }
}
