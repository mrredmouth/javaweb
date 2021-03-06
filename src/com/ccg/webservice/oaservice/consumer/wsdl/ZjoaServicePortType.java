
package com.ccg.webservice.oaservice.consumer.wsdl;

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
@WebService(name = "ZjoaServicePortType", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService")
public interface ZjoaServicePortType {

	/**
	 * 
	 * @param ifCode
	 * @param ifUser
	 * @param ifEvent
	 * @param ifPass
	 * @param info
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "IfService")
	@WebResult(name = "out", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService")
	@RequestWrapper(localName = "IfService", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService", className = "com.ccg.webservice.oaservice.consumer.wsdl.IfService")
	@ResponseWrapper(localName = "IfServiceResponse", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService", className = "com.ccg.webservice.oaservice.consumer.wsdl.IfServiceResponse")
	public String ifService(
			@WebParam(name = "ifUser", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService") String ifUser,
			@WebParam(name = "ifPass", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService") String ifPass,
			@WebParam(name = "ifCode", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService") String ifCode,
			@WebParam(name = "ifEvent", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService") String ifEvent,
			@WebParam(name = "info", targetNamespace = "http://zjfm.zjtelecom.cn/ZjoaService") String info);

}
