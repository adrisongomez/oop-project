package com.graphlog.auth.repositories;

import com.graphlog.auth.services.session.ISessionService;
import com.graphlog.auth.services.user.IUserService;

public class AuthRepository {
    IUserService userService;
	ISessionService sessionService;

    AuthRepository() {}

    static private AuthRepository instance;

    static public AuthRepository getInstance() {
        if (AuthRepository.instance != null) {
            return AuthRepository.instance;
        }
        AuthRepository.instance = new AuthRepository();
        return AuthRepository.instance;
    }

    public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setSessionService(ISessionService sessionService) {
		this.sessionService = sessionService;
	}
}

