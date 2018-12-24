/**
 * Guid.java Created on Nov 14, 2013
 * Copyright(c) 2011@JSHX
 * ALL Rights Reserved.
 */
package com.ccg.secret;


/**
 * Guid RandomGUID
 * 
 * @time: 4:29:16 PM
 * @author mengxiankong
 */
public class Guid extends RandomGUID {
//	private static Log logger = LogFactory.getLog(Guid.class);
	
	/*
	 * Convert to the standard format for GUID
	 * (Useful for SQL Server UniqueIdentifiers, etc.)
	 * Example: C2FEEEAC-CFCD-11D1-8B05-00600806D9B6
	 */
	public String toString() {
		String raw = valueAfterMD5.toUpperCase();
		//  StringBuffer sb = new StringBuffer(64);
		//  sb.append(raw.substring(0, 8));
		// sb.append("-");
		//  sb.append(raw.substring(8, 12));
		//  sb.append("-");
		// sb.append(raw.substring(12, 16));
		//  sb.append("-");
		//  sb.append(raw.substring(16, 20));
		//  sb.append("-");
		// sb.append(raw.substring(20));
		return raw;
	}

	/*
	 * Default constructor.  With no specification of security option,
	 * this constructor defaults to lower security, high performance.
	 */
	public Guid() {
		super(false);
	}

	/*
	 * Constructor with security option.  Setting secure true
	 * enables each random number generated to be cryptographically
	 * strong.  Secure false defaults to the standard Random function seeded
	 * with a single cryptographically strong random number.
	 */
	public Guid(boolean secure) {
		super(secure);
	}

	public static String newGuid() {
		Guid guid = new Guid();
		return guid.toString();
	}

	public static void main(String[] args) {
		System.out.println(Guid.newGuid());
	}
}
