package com.ccg.io.file.oraclefile.model;

import java.io.InputStream;
import java.sql.Blob;

/**
 * 数据库文件，is，blob两个变量取其一即可
 * @author Administrator
 */
public class OracleFile {

	private String name;
	private InputStream is;
	private Blob blob;
	
	public OracleFile() {
		super();
	}
	public OracleFile(String name, InputStream is) {
		super();
		this.setName(name);
		this.setIs(is);
	}
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob getBlob() {
		return blob;
	}
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
}
