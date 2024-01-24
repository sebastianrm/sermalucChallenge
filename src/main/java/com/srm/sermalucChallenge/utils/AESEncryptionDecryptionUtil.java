/**
 * 
 */
package com.srm.sermalucChallenge.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Sermaluc Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Component
public class AESEncryptionDecryptionUtil {
	
	private static final Logger logger = LogManager.getLogger(AESEncryptionDecryptionUtil.class);
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";
    
	@Value("${srm.app.jwtSecret}")
	private String secret;
    
   
	
	public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(secret.getBytes(StandardCharsets.UTF_8));
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}

    public String encrypt(String strToEncrypt) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
        	
        	// TODO MEJORAR EL CONTROL DE ERRORES
        	logger.error("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
        	logger.error("Error while decrypting: " + e.toString());
        }
        return null;
    }
	
}
