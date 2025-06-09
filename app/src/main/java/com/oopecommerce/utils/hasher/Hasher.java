package com.oopecommerce.utils.hasher;

/**
 * Simple interface to hash text and verify hashes.
 */
public interface Hasher {
    /**
     * Hash the provided text.
     *
     * @param text plain text to hash
     * @return hashed representation
     */
    String hash(String text);

    /**
     * Validate that the text corresponds to the hashed value.
     *
     * @param text       plain text
     * @param hashedText previously hashed text
     * @return true if match; false otherwise
     */
    boolean match(String text, String hashedText);
}
