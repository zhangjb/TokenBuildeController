package org.cics.demo2;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;

public class RSAUtils {
    private static String ALGORITHM = "RSA";
    public static int KEYSIZE = 1024;
    public static String ENCODE = "UTF-8";
    public static String encrypt(String source, String path) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + File.separator +"public.pem"));
        Key key = (Key) ois.readObject();
        ois.close();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(1, key);
        byte[] b = source.getBytes();
        byte[] b1 = cipher.doFinal(b);
        return new String(Base64.encodeBase64(b1), ENCODE);
    }
}