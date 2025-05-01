package Util;

import com.teamdev.jxmaps.internal.internal.ipc.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DeEncryption {

    Properties outlet = new Properties();

    public String encrypt(String clearText)  {
        try (InputStream is = DeEncryption.class
                .getResourceAsStream("/Settings/Outlet.properties");
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            outlet.load(reader);
        } catch (IOException ex) {
            Logger.getLogger(DeEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
            SecretKeySpec key = deriveKey("secretKey", "saltValue");
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, key, ivspec);

            byte[] encrypted = c.doFinal(clearText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchAlgorithmException |InvalidKeySpecException | NoSuchPaddingException 
                | InvalidKeyException |InvalidAlgorithmParameterException 
                | IllegalBlockSizeException |BadPaddingException ex) {
            Logger.getLogger(DeEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String decrypt(String cipherText) {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
            SecretKeySpec key = deriveKey("secretKey", "saltValue");
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, key, ivspec);

            byte[] decoded = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = c.doFinal(decoded);
            return new String(decrypted, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException |InvalidKeySpecException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException 
                | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(DeEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private SecretKeySpec deriveKey(String pass, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(
                pass.toCharArray(),
                salt.getBytes(StandardCharsets.UTF_8),
                65536, 256);
        byte[] keybytes = f.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keybytes, "AES");
    }
    
    public static void main(String[] args) throws Exception {
    DeEncryption enc = new DeEncryption();
    String original = "Señor Ñandú 😊";
    String blob     = enc.encrypt(original);
    String round    = enc.decrypt(blob);

    System.out.println("OK? " + original.equals(round));
    System.out.println(" ➜ “" + original + "” → “" + blob + "” → “" + round + "”");
}

}
