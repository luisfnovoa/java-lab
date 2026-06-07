package com.bunker;

import javax.ejb.Stateless;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

/**
 * ENTERPRISE JAVABEAN (EJB) - CORE CRYPTOGRAPHIC PROVIDER
 * Architecture Layer: Stateless Session Bean
 * Handles mathematical operations for data integrity, dynamic validation, 
 * and low-overhead secure token generation aligned with CPM workflows.
 */
@Stateless
public class SecurityService {

    // Symmetric shared key emulating the CPM server-side microkernel secret
    private static final String VAULT_SECRET_KEY = "Cyb3rArk_Vault_S3cr3t_K3y_2026";

    /**
     * Enforces an HMAC-SHA256 cryptographic scheme.
     * Combines a dynamic payload with a server-side symmetric key to generate 
     * an unalterable digital signature for anti-replay verification.
     * * @param base Raw payload string containing dynamic tokens and timestamps
     * @return String Hexadecimal HMAC-SHA256 computed cryptographic signature
     */
    public String generateSHA256(String base) {
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(VAULT_SECRET_KEY.getBytes("UTF-8"), "HmacSHA256");
            sha256HMAC.init(secretKey);

            byte[] rawHmac = sha256HMAC.doFinal(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            
            // Bitwise operations to convert byte array into hex formatting
            for (byte b : rawHmac) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            return "HMAC_ERROR_RUNTIME_BREACH"; // Fail-safe fallback string
        }
    }

    /**
     * Generates a Globally Unique Identifier (UUID) to serve as a transient token.
     * Enforces cryptographic uniqueness per interface transaction.
     * * @return String Standardized alpha-numeric token format in uppercase
     */
    public String generateSecureToken() {
        return UUID.randomUUID().toString().toUpperCase();
    }
    
    /**
     * Creates an Ephemeral Operational Technology (OT) key signature.
     * Binds a transient UUID token and high-resolution timestamps with the server's 
     * symmetric key via HMAC-SHA256 to ensure data integrity and strict non-repudiation.
     * * @return String Real-time dynamic security signature
     */
    public String generateEphemeralKey() {
        String transientUuid = generateSecureToken();
        long highResTimestamp = System.currentTimeMillis();
        String payload = transientUuid + ":" + highResTimestamp;
        
        return "KEY-OT-" + generateSHA256(payload);
    }
}