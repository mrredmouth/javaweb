
package com.ccg.webservice.czservice.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.ccg.webservice.czservice.wsdl package.
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

	private final static QName _SendOrg_QNAME = new QName("http://czprovide.webservice.oapro.zjcw.com/", "sendOrg");
	private final static QName _SendOrgResponse_QNAME = new QName("http://czprovide.webservice.oapro.zjcw.com/",
			"sendOrgResponse");
	private final static QName _SendOA_QNAME = new QName("http://czprovide.webservice.oapro.zjcw.com/", "sendOA");
	private final static QName _SendOAResponse_QNAME = new QName("http://czprovide.webservice.oapro.zjcw.com/",
			"sendOAResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.ccg.webservice.czservice.wsdl
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link SendOrgResponse }
	 * 
	 */
	public SendOrgResponse createSendOrgResponse() {
		return new SendOrgResponse();
	}

	/**
	 * Create an instance of {@link SendOrg }
	 * 
	 */
	public SendOrg createSendOrg() {
		return new SendOrg();
	}

	/**
	 * Create an instance of {@link OaProject }
	 * 
	 */
	public OaProject createOaProject() {
		return new OaProject();
	}

	/**
	 * Create an instance of {@link SendOAResponse }
	 * 
	 */
	public SendOAResponse createSendOAResponse() {
		return new SendOAResponse();
	}

	/**
	 * Create an instance of {@link SendOA }
	 * 
	 */
	public SendOA createSendOA() {
		return new SendOA();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SendOrg
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://czprovide.webservice.oapro.zjcw.com/", name = "sendOrg")
	public JAXBElement<SendOrg> createSendOrg(SendOrg value) {
		return new JAXBElement<SendOrg>(_SendOrg_QNAME, SendOrg.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SendOrgResponse
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://czprovide.webservice.oapro.zjcw.com/", name = "sendOrgResponse")
	public JAXBElement<SendOrgResponse> createSendOrgResponse(SendOrgResponse value) {
		return new JAXBElement<SendOrgResponse>(_SendOrgResponse_QNAME, SendOrgResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SendOA
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://czprovide.webservice.oapro.zjcw.com/", name = "sendOA")
	public JAXBElement<SendOA> createSendOA(SendOA value) {
		return new JAXBElement<SendOA>(_SendOA_QNAME, SendOA.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SendOAResponse
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://czprovide.webservice.oapro.zjcw.com/", name = "sendOAResponse")
	public JAXBElement<SendOAResponse> createSendOAResponse(SendOAResponse value) {
		return new JAXBElement<SendOAResponse>(_SendOAResponse_QNAME, SendOAResponse.class, null, value);
	}

}
