package src.Models;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassWordHash {
    public String generateHash(String PasswordToHash){
        String generatedPasswordHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(PasswordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPasswordHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPasswordHash;
    }
    private String connectionKey = "dreamhouse";

    private byte[] generateKeyFromString(){
        return new PassWordHash().generateHash(connectionKey).substring(0, 16).getBytes();
    }

    public Cipher getEncryptionCipher() {
        return createCipher(Cipher.ENCRYPT_MODE);
    }

    public Cipher getDecryptionCipher(){
        return createCipher(Cipher.DECRYPT_MODE);
    }

    private Cipher createCipher(int mode){
        try {
            IvParameterSpec iv = new IvParameterSpec(getIv());
            SecretKeySpec sKeySpec = new SecretKeySpec(generateKeyFromString(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(mode, sKeySpec, iv);

            return cipher;

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getIv(){
        return new byte[]
                {
                        0x04, 0x02, 0x0f, 0x0a,
                        0x08, 0x08, 0x05, 0x0c,
                        0x03, 0x01, 0x0e, 0x0f,
                        0x08, 0x08, 0x05, 0x0c
                };
    }
}
