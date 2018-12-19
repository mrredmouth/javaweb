
package com.ccg.webservice.czservice.consumer3.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.ccg.webservice.czservice.consumer3.wsdl package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _IfService_QNAME = new QName("http://provider.czservice.webservice.oapro.zjcw.com/",
			"ifService");
	private final static QName _IfServiceResponse_QNAME = new QName(
			"http://provider.czservice.webservice.oapro.zjcw.com/", "ifServiceResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.ccg.webservice.czservice.consumer3.wsdl
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link IfService }
	 * 
	 */
	public IfService createIfService() {
		return new IfService();
	}

	/**
	 * Create an instance of {@link IfServiceResponse }
	 * 
	 */
	public IfServiceResponse createIfServiceResponse() {
		return new IfServiceResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link IfService
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://provider.czservice.webservice.oapro.zjcw.com/", name = "ifService")
	public JAXBElement<IfService> createIfService(IfService value) {
		return new JAXBElement<IfService>(_IfService_QNAME, IfService.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link IfServiceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://provider.czservice.webservice.oapro.zjcw.com/", name = "ifServiceResponse")
	public JAXBElement<IfServiceResponse> createIfServiceResponse(IfServiceResponse value) {
		return new JAXBElement<IfServiceResponse>(_IfServiceResponse_QNAME, IfServiceResponse.class, null, value);
	}

}
