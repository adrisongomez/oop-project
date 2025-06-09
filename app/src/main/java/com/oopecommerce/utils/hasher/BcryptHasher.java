package com.oopecommerce.utils.hasher;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Implementation of {@link Hasher} using the jBCrypt library.
 */
public class BcryptHasher implements Hasher {

    @Override
    public String hash(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    @Override
    public boolean match(String text, String hashedText) {
        if (text == null || hashedText == null) {
            return false;
        }
        return BCrypt.checkpw(text, hashedText);
    }
}
