package com.clm.auth.jwt;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class Keyloader {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;


    public Keyloader() throws Exception {
//        this.privateKey = loadPrivateKey("keys/private_key.pem");
//        this.publicKey = loadPublicKey("keys/public_key.pem");
        this.privateKey = null;
        this.publicKey = null;
    }

    private PublicKey loadPublicKey(String filePath) throws Exception{
//        byte[] keyBytes = Files.readAllBytes(new ClassPathResource(filePath).getFile().toPath());
//        String publicKeyPEM = new String(keyBytes).replace("-----BEGIN PUBLIC KEY-----", "")
//                .replace("-----END PUBLIC KEY-----", "")
//                .replaceAll("\\s", "");
//        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
//        return KeyFactory.getInstance("RSA").generatePublic(spec);
            return null;
    }

    private PrivateKey loadPrivateKey(String filePath) throws Exception {
//        byte[] keyBytes = Files.readAllBytes(new ClassPathResource(filePath).getFile().toPath());
//        String privateKeyPEM = new String(keyBytes).replace("-----BEGIN PRIVATE KEY-----", "")
//                .replace("-----END PRIVATE KEY-----", "")
//                .replaceAll("\\s", "");
//        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
//        return KeyFactory.getInstance("RSA").generatePrivate(spec);
        return null;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
