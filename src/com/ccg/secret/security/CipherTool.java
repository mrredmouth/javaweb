package com.ccg.secret.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class CipherTool
{

	public CipherTool()
	{
	}

	public static String byteToString(byte b[], boolean bUpper)
		throws CipherException
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}

		if (bUpper)
			return hs.toUpperCase();
		else
			return hs.toLowerCase();
	}

	public static boolean saveKeyToFile(Object obj, String fileName)
		throws CipherException
	{
		try
		{
			File fTemp = new File(fileName);
			if (!fTemp.exists())
				fTemp.createNewFile();
			FileOutputStream foS = new FileOutputStream(fileName);
			ObjectOutputStream ooS = new ObjectOutputStream(foS);
			ooS.reset();
			ooS.writeObject(obj);
			ooS.close();
			return true;
		}
		catch (Exception e)
		{
			throw new CipherException(e);
		}
	}

	public static Object loadKeyFromFile(String fileName)
		throws CipherException
	{
		try
		{
			FileInputStream fiS = new FileInputStream(fileName);
			ObjectInputStream oiS = new ObjectInputStream(fiS);
			Object obj = oiS.readObject();
			oiS.close();
			return obj;
		}
		catch (Exception e)
		{
			throw new CipherException(e);
		}
	}

	public static PublicKey decodePublicKey(byte encodedPubKey[])
		throws CipherException
	{
		X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(encodedPubKey);
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("DSA");
			return keyFactory.generatePublic(bobPubKeySpec);
		}
		catch (Exception e)
		{
			throw new CipherException(e);
		}
	}

	public static PrivateKey decodePrivateKey(byte encodePriKey[])
		throws CipherException
	{
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(encodePriKey);
		try
		{
			KeyFactory keyf = KeyFactory.getInstance("DSA");
			return keyf.generatePrivate(priPKCS8);
		}
		catch (Exception e)
		{
			throw new CipherException(e);
		}
	}
	
	public static void main(String[] args){
		Digest dg = new Digest("MD5");
		String md5Pwd = "";
		String pd = "jsdxcw2233";
		try {
			md5Pwd = CipherTool.byteToString(dg.getDigest(pd), false);
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(md5Pwd);
	}
}
