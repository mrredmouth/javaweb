package com.ccg.secret.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Digest
{

	private String algorithm;
	public static final String ALGORITHM_MD5 = "MD5";
	public static final String ALGORITHM_SHA = "SHA";

	public Digest(String algorithm)
	{
		this.algorithm = "";
		this.algorithm = algorithm;
	}

	public byte[] getDigest(String source)
		throws CipherException
	{
		MessageDigest digest = null;
		try
		{
			digest = MessageDigest.getInstance(algorithm);
		}
		catch (NoSuchAlgorithmException nsE)
		{
			throw new CipherException(nsE);
		}
		digest.update(source.getBytes());
		return digest.digest();
	}

	public static String md5(String source)
		throws CipherException
	{
		Digest dg = new Digest("MD5");
		return CipherTool.byteToString(dg.getDigest(source), false);
	}

	public static boolean isEqual(byte digest1[], byte digest2[])
		throws CipherException
	{
		return MessageDigest.isEqual(digest1, digest2);
	}
}
