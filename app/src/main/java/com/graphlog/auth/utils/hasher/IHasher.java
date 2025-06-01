package com.graphlog.auth.utils.hasher;

public interface IHasher {
    public Boolean match(String text, String hashedText);
    public String hash(String text);
}
