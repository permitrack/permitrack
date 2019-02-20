package com.sehinc.common.util.crypto;

import org.apache.log4j.Logger;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class CryptoUtils
{
    private static
    String
        ALGORITHM =
        "PBEWithMD5AndDES";
    @SuppressWarnings("FieldCanBeLocal")
    private static
    String
        PASSWORD_KEY =
        "P@SSW0RDK3Y_!2E";
    private static
    int
        ITERATIONS =
        1000;
    private static
    Logger
        log =
        Logger.getLogger(CryptoUtils.class);

    public static String decrypt(String password, String text)
        throws CryptoException
    {
        try
        {
            // If the text isn't in encrypted form, then return null.
            if (text.length()
                < 12)
            {
                return text;
            }
            // Below we split the text into salt and text strings.
            String
                salt =
                text.substring(0,
                               12);
            String
                ciphertext =
                text.substring(12,
                               text.length());
            // BASE64Decode the bytes for the salt and the ciphertext
            BASE64Decoder
                decoder =
                new BASE64Decoder();
            byte[]
                saltArray =
                decoder.decodeBuffer(salt);
            byte[]
                ciphertextArray =
                decoder.decodeBuffer(ciphertext);
            // Create the PBEKeySpec with the given password
            char[]
                passwordChars =
                password.toCharArray();
            PBEKeySpec
                keySpec =
                new PBEKeySpec(passwordChars);
            // Get a SecretKeyFactory for PBEWithSHAAndTwofish
            SecretKeyFactory
                keyFactory =
                SecretKeyFactory.getInstance(ALGORITHM);
            // Create our key
            SecretKey
                key =
                keyFactory.generateSecret(keySpec);
            // Now create a parameter spec for our salt and iterations
            PBEParameterSpec
                paramSpec =
                new PBEParameterSpec(saltArray,
                                     ITERATIONS);
            // Create a cipher and initialize it for encrypting
            Cipher
                cipher =
                Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,
                        key,
                        paramSpec);
            // Perform the actual decryption
            byte[]
                plaintextArray =
                cipher.doFinal(ciphertextArray);
            return new String(plaintextArray);
        }
        catch (InvalidKeyException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (NoSuchAlgorithmException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (InvalidKeySpecException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (NoSuchPaddingException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (InvalidAlgorithmParameterException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (IllegalStateException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (IllegalBlockSizeException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (BadPaddingException e)
        {
            String
                msg =
                "decrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
    }

    public static String encrypt(String password, String plaintext)
        throws CryptoException
    {
        try
        {
            // Begin by creating a random salt of 64 bits (8 bytes)
            byte[]
                salt =
                new byte[8];
            Random
                random =
                new Random();
            random.nextBytes(salt);
            // Create the PBEKeySpec with the given password
            char[]
                passwordChars =
                password.toCharArray();
            PBEKeySpec
                keySpec =
                new PBEKeySpec(passwordChars);
            // Get a SecretKeyFactory for the defined algorithm.
            SecretKeyFactory
                keyFactory =
                SecretKeyFactory.getInstance(ALGORITHM);
            // Create our key
            SecretKey
                key =
                keyFactory.generateSecret(keySpec);
            // Now create a parameter spec for our salt and iterations
            PBEParameterSpec
                paramSpec =
                new PBEParameterSpec(salt,
                                     ITERATIONS);
            // Create a cipher and initialize it for encrypting
            Cipher
                cipher =
                Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,
                        key,
                        paramSpec);
            byte[]
                ciphertext =
                cipher.doFinal(plaintext.getBytes());
            BASE64Encoder
                encoder =
                new BASE64Encoder();
            String
                saltString =
                encoder.encode(salt);
            String
                ciphertextString =
                encoder.encode(ciphertext);
            return saltString
                   + ciphertextString;
        }
        catch (InvalidKeyException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (NoSuchAlgorithmException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (InvalidKeySpecException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (NoSuchPaddingException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (InvalidAlgorithmParameterException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (IllegalStateException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (IllegalBlockSizeException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
        catch (BadPaddingException e)
        {
            String
                msg =
                "encrypt(String password, String message): "
                + e.toString();
            log.error(msg);
            throw new CryptoException(msg);
        }
    }

    public static String getDefaultKey()
    {
        return PASSWORD_KEY;
    }
}
