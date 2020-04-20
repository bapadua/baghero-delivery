package br.com.baghero.delivery.token;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public class TOTP {

	private static final long TIME = 60L;

	public static String getToken(String key) {

		String secretSeed = convertStringToHex(key);
		String result = null;

		long exactTime = System.currentTimeMillis() / 1000L;
		long preRounded = (long) (exactTime / TIME);

		String roundedTime = Long.toHexString(preRounded).toUpperCase();

		while (roundedTime.length() < 16) {
			roundedTime = "0" + roundedTime;
		}

		byte[] hash = HMAC.hmac(hexStr2Bytes(secretSeed), hexStr2Bytes(roundedTime));
		int offset = hash[hash.length - 1] & 0xf;
		int otp = ((hash[offset + 0] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16)
				| ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);
		
		otp = otp % 1000000;

		result = Integer.toString(otp);

		while (result.length() < 6) {
			result = "0" + result;
		}
		
		log.info("token generated: {}", result);
		return result;

	}

	public static String convertStringToHex(String str) {
		char[] chars = str.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	private static byte[] hexStr2Bytes(String hex) {
		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();
		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++)
			ret[i] = bArray[i + 1];
		return ret;
	}
}
