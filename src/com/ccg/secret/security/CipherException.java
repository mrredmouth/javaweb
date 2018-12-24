package com.ccg.secret.security;


@SuppressWarnings("serial")
public class CipherException extends Exception
{

	public CipherException(Exception caseE)
	{
		super(caseE);
	}

	public CipherException(String msg)
	{
		super(msg);
	}
}
