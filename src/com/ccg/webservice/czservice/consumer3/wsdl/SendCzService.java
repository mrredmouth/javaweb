
package com.ccg.webservice.czservice.consumer3.wsdl;

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
@WebService(name = "SendCzService", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/")
public interface SendCzService {

	/**
	 * 
	 * @param ifCode
	 * @param ifUser
	 * @param ifEvent
	 * @param ifPass
	 * @param info
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "ifService", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/", className = "com.ccg.webservice.czservice.consumer3.wsdl.IfService")
	@ResponseWrapper(localName = "ifServiceResponse", targetNamespace = "http://provider.czservice.webservice.oapro.zjcw.com/", className = "com.ccg.webservice.czservice.consumer3.wsdl.IfServiceResponse")
	public String ifService(@WebParam(name = "ifUser", targetNamespace = "") String ifUser,
			@WebParam(name = "ifPass", targetNamespace = "") String ifPass,
			@WebParam(name = "ifCode", targetNamespace = "") String ifCode,
			@WebParam(name = "ifEvent", targetNamespace = "") String ifEvent,
			@WebParam(name = "info", targetNamespace = "") String info);

}
