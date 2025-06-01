package com.graphlog.auth.utils.otp;

public interface IOtpHandler {
    public Boolean isValid();
    public String generateOtp();
    
}
