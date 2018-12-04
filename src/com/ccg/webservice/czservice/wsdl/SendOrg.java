
package com.ccg.webservice.czservice.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for sendOrg complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="sendOrg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://czprovide.webservice.oapro.zjcw.com/}oaProject" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendOrg", propOrder = { "arg0" })
public class SendOrg {

	protected OaProject arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link OaProject }
	 * 
	 */
	public OaProject getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link OaProject }
	 * 
	 */
	public void setArg0(OaProject value) {
		this.arg0 = value;
	}

}
