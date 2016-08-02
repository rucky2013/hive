package com.hive.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESOperator {
	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private String KEY = "1234567890123456";
	private String IVS = "1234567890123456";

	private static class AESOperatorHolder{
		private static AESOperator instance=new AESOperator();
	}

	public static AESOperator getInstance() {
		return  AESOperatorHolder.instance;
	}
	/**
	 * 加密
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String content) throws Exception {
		return encrypt(content, KEY, IVS);
	}
	/**
	 * 加密
	 * @param content
	 * @param key
	 * @param vector
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String content, String key, String vector) throws Exception {
		if(StringUtils.isEmpty(content)){
			throw new RuntimeException("AES加密密文为空");
		}
		if(StringUtils.isEmpty(key)){
			key=KEY;
		}
		if(key.length()!=16){
			throw new RuntimeException("AES加密秘钥不是16位");
		}
		if(StringUtils.isEmpty(vector)){
			vector=IVS;
		}
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
	}
	/**
	 * 解密
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String content) throws Exception {
		return decrypt(content, KEY, IVS);
	}
	
	/**
	 * 解密
	 * @param content
	 * @param key
	 * @param ivs
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String content, String key, String ivs) throws Exception {
		try {
			if(StringUtils.isEmpty(content)){
				throw new RuntimeException("AES加密密文为空");
			}
			if(StringUtils.isEmpty(key)){
				key=KEY;
			}
			if(key.length()!=16){
				throw new RuntimeException("AES加密秘钥不是16位");
			}
			if(StringUtils.isEmpty(ivs)){
				ivs=IVS;
			}
			byte[] raw = key.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}
	public static void main(String[] args) throws Exception {
		// 需要加密的字串
		String str="1";
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESOperator.getInstance().encrypt(str);
		System.out.println("加密后的字串是：" + enString);

		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AESOperator.getInstance().decrypt(enString);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}
}
