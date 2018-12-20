package com.ccg.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import com.ccg.common.utils.MyStringUtils;

/**
 * 用HashMap代替JavaBean
 * @Time 5:12:09 PM
 * @author mengxiankong
 */
public class MapBean extends HashMap<String, Object> implements Serializable {

	private static final long serialVersionUID = -1915261641942078690L;

	public MapBean() {
	}

	public MapBean(Object... args) {
		put(args);
	}
	
	public MapBean(String[] arrays){
		for (int i = 1; i < arrays.length; i += 2) {
			put(arrays[i-1],arrays[i]);
		}
	}

	public void put(Object... args) {
		for (int i = 1; i < args.length; i += 2) {
			put(String.valueOf(args[i - 1]), args[i]);
		}
	}

	/**
	 * 当存在当前Key时便不插入
	 * @param key
	 * @param value
	 */
	public Object putNotEmpty(Object key, Object value) {
		if (MyStringUtils.isNull(key))
			return null;
		Object v = get(key);
		if (v == null) {
			put(key, value);
			return value;
		}
		return v;
	}

	public int getInt(Object key) {
		return getInt(key, 0);
	}

	public int getInt(Object key, int defaultInt) {
		Object val = get(key);
		if (val instanceof BigDecimal) {
			return ((BigDecimal) val).intValue();
		}
		if (val instanceof String) {
			return Integer.parseInt((String) val);
		}
		Integer i = (Integer) get(key);
		return i == null ? defaultInt : i;
	}

	public long getLong(Object key) {
		return getLong(key, 0);
	}

	public long getLong(Object key, long defaultInt) {
		Object val = get(key);
		if (val instanceof BigDecimal) {
			return ((BigDecimal) val).longValue();
		}
		if (val instanceof String) {
			return Long.parseLong((String) val);
		}
		Long i = (Long) val;
		return i == null ? defaultInt : i;
	}

	public String getString(Object key) {		
		return get(key)==null ? "" : obj2str(get(key));
	}

	public double getDouble(Object key) {
		Object val = get(key);
		if (val instanceof BigDecimal) {
			return ((BigDecimal) val).doubleValue();
		}
		if (val instanceof String) {
			return Double.parseDouble((String) val);
		}
		return val == null ? 0.00 : (Double) val;
	}

	public String getString(Object key, String defaultValue) {
		String value = (String) get(key);
		return value == null ? defaultValue : value;
	}
	
	public String getClobString(Object key) throws SQLException{
		Object obj = get(key);
		if(obj == null) return null;
		if (obj instanceof Clob) {
			Clob clob = (Clob) obj;
			return clob.getSubString(1, (int) clob.length());
		}
		return null;
	}

	public Timestamp getTimestamp(Object key) {
		return (Timestamp) get(key);
	}
	
	/**
	 * 对象转字符串
	 * @param val
	 * @return
	 */
	public String obj2str(Object val){
		if (val instanceof Clob) {
			Clob clob = (Clob) val;
			try {
				return clob.getSubString(1, (int) clob.length());
			} catch (SQLException e) {
				e.printStackTrace();
				return val.toString();
			}
		}
		return val.toString();
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");

		Iterator<Entry<String, Object>> i = entrySet().iterator();
		boolean hasNext = i.hasNext();
		while (hasNext) {
			Entry<String, Object> e = i.next();
			String key = e.getKey();
			Object value = e.getValue();
			buf.append(key);
			buf.append("=");
			buf.append(value);
			hasNext = i.hasNext();
			if (hasNext)
				buf.append(", ");
		}

		buf.append("}");
		return buf.toString();
	}

	public JSONObject toJson() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		Iterator<?> iter = entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("unchecked")
			Entry<String, Object> entry = (Entry<String, Object>) iter.next();
			String jsonKey = (String) entry.getKey();
			Object jsonValue = entry.getValue();
			if(jsonValue!=null&&jsonValue.getClass().getName().equals("oracle.sql.CLOB")){
				jsonValue=obj2str(entry.getValue());
			}
			jsonObject.put(jsonKey, jsonValue==null?"":jsonValue);
		}
		return jsonObject;
	}

	public String toJsonString() throws JSONException {
		return toJson().toString();
	}

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		MapBean mb = new MapBean();
		mb.put("a", "astr", "b", new Date());
		System.out.println(mb.toJsonString());
		System.out.println(mb);
		Object i = new Integer(1234);
		Object d = new Double(123.4567);
		System.out.println( i.toString());
		System.out.println( d.toString());
		//System.out.println((String) i);
	}
}
