package ru.inversionkavkaz.btladmin.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Secure {
    public static String encrypt(String password) {
        String res;
        try {
            SecretKeySpec key = new SecretKeySpec("road to hell".getBytes(), "Blowfish");
            Cipher c = Cipher.getInstance("Blowfish");
            c.init(1, key);
            byte[] encoded = c.doFinal(password.getBytes("Cp1251"));
            res = DatatypeConverter.printBase64Binary(encoded);
        } catch (Exception var5) {
            res = "";
        }

        return res;
    }

    public static String decrypt(String password) {
        String res;
        try {
            SecretKeySpec key = new SecretKeySpec("road to hell".getBytes(), "Blowfish");
            Cipher c = Cipher.getInstance("Blowfish");
            c.init(2, key);
            byte[] encoded = DatatypeConverter.parseBase64Binary(password);
            byte[] decrypted = c.doFinal(encoded);
            res = new String(decrypted, "Cp1251");
        } catch (Exception var6) {
            res = "";
        }

        return res;
    }
}
