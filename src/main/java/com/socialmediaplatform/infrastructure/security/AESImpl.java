package com.socialmediaplatform.infrastructure.security;

import org.springframework.stereotype.Component;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class AESImpl implements AES{
    private final String ALGORITHM = "AES";

    @Override
    public String encrypt(String data) {
        if(data == null){
            return"";
        }
        Key key = generateKey();
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encValue);
        }catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException| BadPaddingException e){
            System.out.println(e);
        }
        return data;
    }

    @Override
    public String decrypt(String encryptedData) {
        if(encryptedData == null){
            return"";
        }
        Key key = generateKey();
        try{
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
            byte[] value = c.doFinal(decodedValue);
            return new String(value);
        }catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException| BadPaddingException e){
            System.out.println(e);
        }
        return encryptedData;
    }

    private Key generateKey(){
        String secretKey = "B4rd20_B3zp1ec2nY-kLuc2_L0sow3_2Nac2k1";
        return new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
    }
}
