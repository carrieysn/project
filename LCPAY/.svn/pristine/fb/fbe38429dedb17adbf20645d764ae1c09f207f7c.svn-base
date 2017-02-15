package com.cifpay.lc.util.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

@SuppressWarnings("restriction")
public class ThreeDESUtil {
	
    private static String key="YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4";
    private static byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
    
    // 算法名称 
    private static final String KEY_ALGORITHM = "desede";
    // 算法名称/加密模式/填充方式 
    private static final String CIPHER_ALGORITHM = "desede/CBC/PKCS5Padding";
	
    
    /**
     * 加密工具
     * @param pwd
     * @return
     * @throws Exception
     */
    public static String desEncryption(String pwd) throws Exception {
    	byte[] data = pwd.getBytes("UTF-8");
    	byte[] str5 = des3EncodeCBC(key, keyiv, data);
    	String m=new BASE64Encoder().encode(str5);
        return m;
    }
    
    /**
     * 解密工具
     * @param params
     * @return
     * @throws Exception
     */
    public static String decDecrypt(String params)throws Exception{
    	  byte[] str5 =new BASE64Decoder().decodeBuffer(params);
    	  byte[] str6 = des3DecodeCBC(key, keyiv, str5);
    	  return new String(str6, "UTF-8");
    }


	public static byte[] des3EncodeECB(String key, byte[] data)
            throws Exception {
    	byte[] keys=new BASE64Decoder().decodeBuffer(key);
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(keys);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }

    public static byte[] ees3DecodeECB(String key, byte[] data)
            throws Exception {
    	byte[] keys=new BASE64Decoder().decodeBuffer(key);
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(keys);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(data);

        return bOut;

    }

    public static byte[] des3EncodeCBC(String key, byte[] keyiv, byte[] data)
            throws Exception {
    	byte[] keys=new BASE64Decoder().decodeBuffer(key);
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(keys);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    public static byte[] des3DecodeCBC(String key, byte[] keyiv, byte[] data)
            throws Exception {
    	byte[] keys=new BASE64Decoder().decodeBuffer(key);
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(keys);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(keyiv);

        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] bOut = cipher.doFinal(data);

        return bOut;

    }
    
    public static void main(String[] args) throws Exception {

        System.out.println("CBC加密解密");
        //desEncryption("000000");
        System.out.println("加密后密文："+desEncryption("000000"));
        System.out.println("解密后明文："+decDecrypt("eVsN2xEezT8="));

    }

}