
package com.ccg.webservice.consumer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "JwsServiceHello", targetNamespace = "http://provider.webservice.ccg.com/")
public interface JwsServiceHello {

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getValue", targetNamespace = "http://provider.webservice.ccg.com/", className = "com.ccg.webservice.consumer.GetValue")
	@ResponseWrapper(localName = "getValueResponse", targetNamespace = "http://provider.webservice.ccg.com/", className = "com.ccg.webservice.consumer.GetValueResponse")
	public String getValue(@WebParam(name = "arg0", targetNamespace = "") String arg0);

}
