
package com.ccg.webservice.czservice.consumer.wsdl;

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
@WebService(name = "SendService", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/")
public interface SendService {

	/**
	 * 
	 * @param param
	 * @return returns boolean
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "sendOA", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/", className = "com.ccg.webservice.czservice.consumer.wsdl.SendOA")
	@ResponseWrapper(localName = "sendOAResponse", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/", className = "com.ccg.webservice.czservice.consumer.wsdl.SendOAResponse")
	public boolean sendOA(@WebParam(name = "param", targetNamespace = "") String param);

	/**
	 * 
	 * @param arg0
	 * @return returns boolean
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "sendOrg", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/", className = "com.ccg.webservice.czservice.consumer.wsdl.SendOrg")
	@ResponseWrapper(localName = "sendOrgResponse", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/", className = "com.ccg.webservice.czservice.consumer.wsdl.SendOrgResponse")
	public boolean sendOrg(@WebParam(name = "arg0", targetNamespace = "") OaProject arg0);

}
