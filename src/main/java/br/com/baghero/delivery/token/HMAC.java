package br.com.baghero.delivery.token;

public class HMAC {
	private static final int OPAD = 0x5c;
	private static final int IPAD = 0x36;
	private static final int BLOCKSIZE = 64;
	
	public static String hmac(String key, String value) {
		byte[] keyBites = key.getBytes();
		byte[] valueBytes = value.getBytes();
		byte[] hmac = hmac(keyBites, valueBytes);
		return new String(hmac);
	}

	public static byte[] hmac(byte[] key, byte[] value) {
		byte[] newKey = new byte[64];
		byte[] oKeyPad = new byte[64];
		byte[] iKeyPad = new byte[64];
		byte[] appendedIKey;
		byte[] appendedOKey;
		byte[] sha1Ikey;
		int i;
		int x = 0;
		
		//se a chave for maior que o tamanho do bloco, tira o hash
		if (key.length > BLOCKSIZE) {
			key = SHA1.sha1(key);
		}
		
		//popula o newKey com a key e completa com o byte 0x00
		for(i = 0; i < BLOCKSIZE; i++) {
			if(key.length>i) {
				newKey[i]=key[i];
			} else {
				newKey[i]=0x00;
			}
		}
        //Calculamos o Inner Key Pad e o Outer Key Pad utilizando XOR com duas constantes
		for(i = 0; i < BLOCKSIZE; i++) {
			iKeyPad[i] = (byte) (newKey[i]^IPAD);
			iKeyPad[i] = (byte) (newKey[i]^OPAD);
		}
		
		appendedIKey = new byte[iKeyPad.length+value.length];
		
		for(i = 0; i < appendedIKey.length; i++) {
			if(iKeyPad.length>i) {
				appendedIKey[i] = iKeyPad[i];
			} else {
				appendedIKey[i] = value[x];
				x++;
			}
		}
		
		//Executa-se o Hash do InnerKey+Message
		sha1Ikey = SHA1.sha1(appendedIKey); 
		
		//popular array
		x=0;
		appendedOKey = new byte[oKeyPad.length+sha1Ikey.length];
		for(i = 0; i < appendedIKey.length; i++) {
			if(iKeyPad.length > i) {
				appendedOKey[i] = oKeyPad[i];
			} else {
				appendedOKey[i] = sha1Ikey[x];
				x++;
			}
		}
		
		return SHA1.sha1(appendedOKey);
	}
}
