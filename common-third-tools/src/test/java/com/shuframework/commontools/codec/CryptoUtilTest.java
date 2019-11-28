package com.shuframework.commontools.codec;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.shuframework.commonbase.util.codec.AesUtil;
import com.shuframework.commonbase.util.codec.Base64Util;
import org.junit.Test;

public class CryptoUtilTest {

    @Test
    public void aes_test(){
        byte[] key = CryptoUtil.generateKeyAES();
        String content = "test中文";
        byte[] encryptData = CryptoUtil.encryptByAES(content, key);

        String decryptData = CryptoUtil.decryptByAES(encryptData, key);
        System.out.println(decryptData);
        // 2种加密，解密的方法一样
        byte[] decrypt = AesUtil.decrypt(encryptData, Base64Util.encodeBase64(key));
        System.out.println(new String(decrypt));
    }


    @Test
    public void rsa_encrypt_test(){
        RSA rsa = new RSA();
        System.out.println("PrivateKey:" + rsa.getPrivateKeyBase64());
        System.out.println("PublicKey:" + rsa.getPublicKeyBase64());
        String content = "虎头闯杭州,多抬头看天,切勿只管种地";

        // 公钥加密，私钥解密，加密完的数据base64
        byte[] encryptPub = rsa.encrypt(content.getBytes(), KeyType.PublicKey);
        System.out.println(Base64Util.encodeBase64(encryptPub));

        // 私钥加密，公钥解密，加密完的数据base64
        byte[] encryptPri = rsa.encrypt(content.getBytes(), KeyType.PrivateKey);
        System.out.println(Base64Util.encodeBase64(encryptPri));
    }

    @Test
    public void rsa_encrypt_public_test(){
        // key和data 都经过了 base64，使用工具类时privateKey 不用进行base64解密，data需要解密
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQWjfucJ1/Up56iqfPm1GDH3m3x+e0TNQ+hOf2a+6FQDI1X1enz1QdjEgu0Q82E8NL3UevWzqKIiXigXT335A1aiEe8o6fyOfebuojTSdOLYITaCwXA1ZElM+YGZ5ZmmWwwOC2blc1Fdj3oh2FcOr/JxNubFh0CK1aqb6JHcgRFQIDAQAB";
        String data = "虎头闯杭州,多抬头看天,切勿只管种地";

        byte[] encryptPub = CryptoUtil.encryptByPublicRSA(data, publicKey);
        System.out.println(Base64Util.encodeBase64(encryptPub));
    }

    @Test
    public void rsa_decrypt_private_test(){
        // key和data 都经过了 base64，使用工具类时privateKey 不用进行base64解密(内部已经解析了)，data需要解密
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJBaN+5wnX9SnnqKp8+bUYMfebfH57RM1D6E5/Zr7oVAMjVfV6fPVB2MSC7RDzYTw0vdR69bOooiJeKBdPffkDVqIR7yjp/I595u6iNNJ04tghNoLBcDVkSUz5gZnlmaZbDA4LZuVzUV2PeiHYVw6v8nE25sWHQIrVqpvokdyBEVAgMBAAECgYA0Nn3OoCtHt4GdP3Ptcver3wGuyP71R2jp6Dbi+i0/Q3YlugPBrgt12JzvWi0PWVylnzdjpTLqbemYIU1GrzXAoJEA8hY31Y8Nwk+Het/D6B1BtK2y/FZkbMXMPWBOTw5GH8/U/4yQZ0shO33CglyRhqQtlT2OOdHfZegCSwW2DQJBANQvvmHjGNDVEZsRDhTRn7mKheFt7BiEYO34MS5ODvNpGK4ZldXD2RjFEVxZbc/7qDPwuP23CyPp9iZtuzQamDsCQQCuKL2A/M3vMpGLrucUo9bSjqj9bIndgswFVXahZYbSnCoGxPVtOIEhGUskoivdjRlAHfGaGLz5R/P5hYZgjrbvAkAB6QQfwXmht6lW6gpkaZa0hui5pNGxTs/IlKlsr0s3KcUNHDHbIM49iNilKjDw5GUZQpuHvTQeeKa8pdQ/ndWtAkATFIuOciPtrNCdSRAwBrMmZlLFUPrZtda8TtysWf8nW8yO6U0qJiKfibHd1ZaQs0Cs8ZrikwtBFEz6bVKHiZspAkAFAUOUpLzlTB4R0AIgt1qov8O2CBzkJBGf5XcdYJuSq1IgjNW9FBBwEuJ6xe1UKbEVqPvIJJZ71guuD+kpWgJe";
//        String data = "JvszQ61e7C/n/tJE5CLYIVk58UcOvaRrKLl1uiF1V4Kra7I9iwP6nEMxntZf1ePZSRhJ3IobfNa9MOOtsk7wM0eTUH/yII07ISSSnvJnYUuSCbN/btUIyI8iID/JVpdQ5B/A7PNe7tC9qi9mqn0zQ2+v8LQ4kiciXL53GS5QXQI=";
        String data = "GFI/qHAivGnUpVhL52DZb0PQbPTZChAurS7DVQ6Wr+jHL0b/5c47F7KtyXATpV+KVtmjeohibL4pNw9AvRVBo95btm/4fmWhS3teK1aAAnS9i1JlmyDWWWrrK7UA73qq+EQDMWeWuQGVoOgRqn1F0mnqzwv8yNc/NZELmNgEuGk=";

        byte[] bytes = Base64Util.decodeBase64toByte(data);
        String decrypt = CryptoUtil.decryptByPrivateRSA(bytes, privateKey);
        System.out.println(decrypt);
        // 虎头闯杭州,多抬头看天,切勿只管种地
    }

    @Test
    public void rsa_decrypt_public_test(){
        // key和data 都经过了 base64，使用工具类时publicKey 不用进行base64解密(内部已经解析了)，data需要解密
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLq2GsJF8WNwPh8in+dDrHavo77/rEsISqJtv5UYh7J8mVrBqsEA9hNyP/VnjhT5qUuEoe1ukwmKsoE92PuJZhlgo8NEbn8Y0we5XMw5ar422MbDrbkV3PJUvezCW5ZrPi2oEHamTUkIqTGFC2tBI++27VK+5pwdvFzarSTRGFpQIDAQAB";
        String data = "kxzCUFyXF2JmlzYQ8XcrNlO2aI4lH6BBbsmX4h3wpohdNxFzsgnZpyxB3gYj/eCMX4A5MpCShcCxxA/dR4HypgxmHiy7g494qW8BIOZ3zVhjDHdIRRjih/mxByYMtFIQSx/qxHmQViQKv+wftJ+cAu9B9xfLW093cwGZEfvjl+4=";

        byte[] bytes = Base64Util.decodeBase64toByte(data);
        String decrypt = CryptoUtil.decryptByPublicRSA(bytes, publicKey);
        System.out.println(decrypt);
        // 虎头闯杭州,多抬头看天,切勿只管种地
    }



    @Test
    public void rsa_test(){
        RSA rsa = new RSA();
        System.out.println(rsa.getPrivateKeyBase64());
        System.out.println(rsa.getPublicKeyBase64());
        String content = "test中文";

        // 公钥加密，私钥解密(一般是这种用来加密)
        byte[] encryptPub = rsa.encrypt(content.getBytes(), KeyType.PublicKey);
        byte[] decryptPri = rsa.decrypt(encryptPub, KeyType.PrivateKey);
        System.out.println(new String(decryptPri));

        // 私钥加密，公钥解密(一般是这种用来签名)
        byte[] encryptPri = rsa.encrypt(content.getBytes(), KeyType.PrivateKey);
        byte[] decryptPub = rsa.decrypt(encryptPri, KeyType.PublicKey);
        System.out.println(new String(decryptPub));
    }

}