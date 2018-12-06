
package com.ccg.webservice.oaservice.consumer.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ifUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifPass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifEvent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="info" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "ifUser", "ifPass", "ifCode", "ifEvent", "info" })
@XmlRootElement(name = "IfService")
public class IfService {

	@XmlElement(required = true, nillable = true)
	protected String ifUser;
	@XmlElement(required = true, nillable = true)
	protected String ifPass;
	@XmlElement(required = true, nillable = true)
	protected String ifCode;
	@XmlElement(required = true, nillable = true)
	protected String ifEvent;
	@XmlElement(required = true, nillable = true)
	protected String info;

	/**
	 * Gets the value of the ifUser property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIfUser() {
		return ifUser;
	}

	/**
	 * Sets the value of the ifUser property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIfUser(String value) {
		this.ifUser = value;
	}

	/**
	 * Gets the value of the ifPass property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIfPass() {
		return ifPass;
	}

	/**
	 * Sets the value of the ifPass property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIfPass(String value) {
		this.ifPass = value;
	}

	/**
	 * Gets the value of the ifCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIfCode() {
		return ifCode;
	}

	/**
	 * Sets the value of the ifCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIfCode(String value) {
		this.ifCode = value;
	}

	/**
	 * Gets the value of the ifEvent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIfEvent() {
		return ifEvent;
	}

	/**
	 * Sets the value of the ifEvent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIfEvent(String value) {
		this.ifEvent = value;
	}

	/**
	 * Gets the value of the info property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets the value of the info property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInfo(String value) {
		this.info = value;
	}

}
