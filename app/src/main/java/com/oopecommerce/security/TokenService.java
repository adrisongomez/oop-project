package com.oopecommerce.security;

import com.oopecommerce.models.users.User;

public interface TokenService {
    String generateToken(User user, String[] claims);
    UserData verify(String token) throws Exception;
}
