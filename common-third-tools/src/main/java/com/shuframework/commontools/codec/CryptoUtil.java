package com.shuframework.commontools.codec;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * byte[] 可以进行base64加密，弄成文本方便存储
 * privateKey或publicKey 不用进行base64解密(内部已经解析了)
 *  SecureUtil.decode --> return Validator.isHex(key) ? HexUtil.decodeHex(key) : Base64.decode(key);
 *
 * 对称加密 和非对称加密，底层封装的是 javax.crypto.Cipher 和 javax.crypto.SecretKey
 *
 * 非对称加密有公钥和私钥两个概念，私钥自己拥有，不能给别人，公钥公开。根据应用的不同，我们可以选择使用不同的密钥加密：
 *  1.签名：使用私钥加密，公钥解密。用于让所有公钥所有者验证私钥所有者的身份并且用来防止私钥所有者发布的内容被篡改，但是不用来保证内容不被他人获得。
 *  2.加密：用公钥加密，私钥解密。用于向公钥所有者发布信息,这个信息可能被他人篡改,但是无法被他人获得。
 *
 * @author shuheng
 */
public class CryptoUtil {

    // 随机生成AES密钥
    public static byte[] generateKeyAES() {
        return SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
    }

    // AES加密
    public static byte[] encryptByAES(String data, byte[] key) {
        AES aes = SecureUtil.aes(key);
        return aes.encrypt(data);
    }

    // AES解密
    public static String decryptByAES(byte[] encryptData, byte[] key) {
        AES aes = SecureUtil.aes(key);
        return aes.decryptStr(encryptData);
    }

//    // 随机生成DES密钥
//    public static byte[] generateKeyDES() {
//        return SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
//    }
//
//    // DES加密
//    public static byte[] encryptByDES(String data, byte[] key) {
//        DES des = SecureUtil.des(key);
//        return des.encrypt(data);
//    }
//
//    // AES解密
//    public static String decryptByDES(byte[] encryptData, byte[] key) {
//        DES des = SecureUtil.des(key);
//        return des.decryptStr(encryptData);
//    }

    // 随机生成RSA密钥
    public static RSA generateKeyRSA() {
        RSA rsa = new RSA();
        return rsa;
//        //获得私钥
//        rsa.getPrivateKey();
//        rsa.getPrivateKeyBase64();// 推荐
//        //获得公钥
//        rsa.getPublicKey();
//        rsa.getPublicKeyBase64();// 推荐
    }

    // RSA 公钥加密
    public static byte[] encryptByPublicRSA(String data, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        return rsa.encrypt(data.getBytes(), KeyType.PublicKey);
    }
    // RSA 私钥解密
    public static String decryptByPrivateRSA(byte[] encryptData, String privateKey) {
        RSA rsa = new RSA(privateKey, null);
        byte[] decryptData = rsa.decrypt(encryptData, KeyType.PrivateKey);
        return new String(decryptData);
    }

    // RSA 私钥加密
    public static byte[] encryptByPrivateRSA(String data, String privateKey) {
        RSA rsa = new RSA(privateKey, null);
        return rsa.encrypt(data.getBytes(), KeyType.PrivateKey);
    }
    // RSA 公钥解密
    public static String decryptByPublicRSA(byte[] encryptData, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        byte[] decryptData = rsa.decrypt(encryptData, KeyType.PublicKey);
        return new String(decryptData);
    }

}
