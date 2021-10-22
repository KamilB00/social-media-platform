package com.socialmediaplatform.infrastructure.security;

public interface AES {
    String encrypt(String data);
    String decrypt(String encryptedData);
}
