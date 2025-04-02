package com.clm.auth.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Getter
public class Keyloader {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public Keyloader(@Value("${rsa.private-key-path}") String privateKeyPath,
                     @Value("${rsa.public-key-path}") String publicKeyPath) throws Exception {
//        this.privateKey = loadPrivateKey("keys/private_key.pem");
//        this.publicKey = loadPublicKey("keys/public_key.pem");

        System.out.println("Private key path: " + privateKeyPath);
        System.out.println("Public key path: " + publicKeyPath);

        this.privateKey = loadPrivateKey(privateKeyPath);
        this.publicKey = loadPublicKey(publicKeyPath);
    }

    private PublicKey loadPublicKey(String path) throws Exception{
        byte[] keyBytes = getBytes(path);
        String publicKeyPEM = new String(keyBytes).replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(spec);

    }

    private PrivateKey loadPrivateKey(String path) throws Exception {
        byte[] keyBytes = getBytes(path);
        String privateKeyPEM = new String(keyBytes).replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    private static byte[] getBytes(String path) throws IOException {
        String filePath = path.substring("file:".length());
        return Files.readAllBytes(Path.of(filePath));
    }

}
