
package com.ccg.webservice.czservice.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for oaProject complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="oaProject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="business_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="business_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contract_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contract_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="create_doc_person" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="create_doc_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="create_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crepro_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crepro_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customer_industry_attr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customer_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cz_document_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="document_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="edit_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="efficiency_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedback_company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedback_des" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedback_files" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedback_person" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedback_phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedback_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedback_way" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hand_over_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="integrated_expenditure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="is_cut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="is_huge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lr_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lr_code_group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lr_code_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oa_document_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oa_org_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_ict_cost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_interest_rate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_manager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_profit_margin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_profit_margin_pre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_revenue_ict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_revenue_ict_pre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_revenue_total" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_revenue_total_pre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pro_zq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product_costs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product_revenue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scene_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="send_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oaProject", propOrder = { "businessCode", "businessType", "contractCode", "contractType",
		"createDocPerson", "createDocTime", "createTime", "creproTime", "creproType", "customerIndustryAttr",
		"customerName", "czDocumentId", "documentStatus", "editTime", "efficiencyType", "feedbackCompany",
		"feedbackDes", "feedbackFiles", "feedbackPerson", "feedbackPhone", "feedbackType", "feedbackWay",
		"handOverTime", "id", "integratedExpenditure", "isCut", "isHuge", "lrCode", "lrCodeGroup", "lrCodeName",
		"oaDocumentId", "oaOrgCode", "proCode", "proIctCost", "proInterestRate", "proManager", "proName",
		"proProfitMargin", "proProfitMarginPre", "proRevenueIct", "proRevenueIctPre", "proRevenueTotal",
		"proRevenueTotalPre", "proStatus", "proType", "proZq", "productCosts", "productRevenue", "province",
		"sceneType", "sendTime" })
public class OaProject {

	@XmlElement(name = "business_code")
	protected String businessCode;
	@XmlElement(name = "business_type")
	protected String businessType;
	@XmlElement(name = "contract_code")
	protected String contractCode;
	@XmlElement(name = "contract_type")
	protected String contractType;
	@XmlElement(name = "create_doc_person")
	protected String createDocPerson;
	@XmlElement(name = "create_doc_time")
	protected String createDocTime;
	@XmlElement(name = "create_time")
	protected String createTime;
	@XmlElement(name = "crepro_time")
	protected String creproTime;
	@XmlElement(name = "crepro_type")
	protected String creproType;
	@XmlElement(name = "customer_industry_attr")
	protected String customerIndustryAttr;
	@XmlElement(name = "customer_name")
	protected String customerName;
	@XmlElement(name = "cz_document_id")
	protected String czDocumentId;
	@XmlElement(name = "document_status")
	protected String documentStatus;
	@XmlElement(name = "edit_time")
	protected String editTime;
	@XmlElement(name = "efficiency_type")
	protected String efficiencyType;
	@XmlElement(name = "feedback_company")
	protected String feedbackCompany;
	@XmlElement(name = "feedback_des")
	protected String feedbackDes;
	@XmlElement(name = "feedback_files")
	protected String feedbackFiles;
	@XmlElement(name = "feedback_person")
	protected String feedbackPerson;
	@XmlElement(name = "feedback_phone")
	protected String feedbackPhone;
	@XmlElement(name = "feedback_type")
	protected String feedbackType;
	@XmlElement(name = "feedback_way")
	protected String feedbackWay;
	@XmlElement(name = "hand_over_time")
	protected String handOverTime;
	protected String id;
	@XmlElement(name = "integrated_expenditure")
	protected String integratedExpenditure;
	@XmlElement(name = "is_cut")
	protected String isCut;
	@XmlElement(name = "is_huge")
	protected String isHuge;
	@XmlElement(name = "lr_code")
	protected String lrCode;
	@XmlElement(name = "lr_code_group")
	protected String lrCodeGroup;
	@XmlElement(name = "lr_code_name")
	protected String lrCodeName;
	@XmlElement(name = "oa_document_id")
	protected String oaDocumentId;
	@XmlElement(name = "oa_org_code")
	protected String oaOrgCode;
	@XmlElement(name = "pro_code")
	protected String proCode;
	@XmlElement(name = "pro_ict_cost")
	protected String proIctCost;
	@XmlElement(name = "pro_interest_rate")
	protected String proInterestRate;
	@XmlElement(name = "pro_manager")
	protected String proManager;
	@XmlElement(name = "pro_name")
	protected String proName;
	@XmlElement(name = "pro_profit_margin")
	protected String proProfitMargin;
	@XmlElement(name = "pro_profit_margin_pre")
	protected String proProfitMarginPre;
	@XmlElement(name = "pro_revenue_ict")
	protected String proRevenueIct;
	@XmlElement(name = "pro_revenue_ict_pre")
	protected String proRevenueIctPre;
	@XmlElement(name = "pro_revenue_total")
	protected String proRevenueTotal;
	@XmlElement(name = "pro_revenue_total_pre")
	protected String proRevenueTotalPre;
	@XmlElement(name = "pro_status")
	protected String proStatus;
	@XmlElement(name = "pro_type")
	protected String proType;
	@XmlElement(name = "pro_zq")
	protected String proZq;
	@XmlElement(name = "product_costs")
	protected String productCosts;
	@XmlElement(name = "product_revenue")
	protected String productRevenue;
	protected String province;
	@XmlElement(name = "scene_type")
	protected String sceneType;
	@XmlElement(name = "send_time")
	protected String sendTime;

	/**
	 * Gets the value of the businessCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBusinessCode() {
		return businessCode;
	}

	/**
	 * Sets the value of the businessCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBusinessCode(String value) {
		this.businessCode = value;
	}

	/**
	 * Gets the value of the businessType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * Sets the value of the businessType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBusinessType(String value) {
		this.businessType = value;
	}

	/**
	 * Gets the value of the contractCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * Sets the value of the contractCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContractCode(String value) {
		this.contractCode = value;
	}

	/**
	 * Gets the value of the contractType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContractType() {
		return contractType;
	}

	/**
	 * Sets the value of the contractType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContractType(String value) {
		this.contractType = value;
	}

	/**
	 * Gets the value of the createDocPerson property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateDocPerson() {
		return createDocPerson;
	}

	/**
	 * Sets the value of the createDocPerson property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateDocPerson(String value) {
		this.createDocPerson = value;
	}

	/**
	 * Gets the value of the createDocTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateDocTime() {
		return createDocTime;
	}

	/**
	 * Sets the value of the createDocTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateDocTime(String value) {
		this.createDocTime = value;
	}

	/**
	 * Gets the value of the createTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the value of the createTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateTime(String value) {
		this.createTime = value;
	}

	/**
	 * Gets the value of the creproTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreproTime() {
		return creproTime;
	}

	/**
	 * Sets the value of the creproTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreproTime(String value) {
		this.creproTime = value;
	}

	/**
	 * Gets the value of the creproType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreproType() {
		return creproType;
	}

	/**
	 * Sets the value of the creproType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreproType(String value) {
		this.creproType = value;
	}

	/**
	 * Gets the value of the customerIndustryAttr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustomerIndustryAttr() {
		return customerIndustryAttr;
	}

	/**
	 * Sets the value of the customerIndustryAttr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustomerIndustryAttr(String value) {
		this.customerIndustryAttr = value;
	}

	/**
	 * Gets the value of the customerName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the value of the customerName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustomerName(String value) {
		this.customerName = value;
	}

	/**
	 * Gets the value of the czDocumentId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCzDocumentId() {
		return czDocumentId;
	}

	/**
	 * Sets the value of the czDocumentId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCzDocumentId(String value) {
		this.czDocumentId = value;
	}

	/**
	 * Gets the value of the documentStatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDocumentStatus() {
		return documentStatus;
	}

	/**
	 * Sets the value of the documentStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDocumentStatus(String value) {
		this.documentStatus = value;
	}

	/**
	 * Gets the value of the editTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEditTime() {
		return editTime;
	}

	/**
	 * Sets the value of the editTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEditTime(String value) {
		this.editTime = value;
	}

	/**
	 * Gets the value of the efficiencyType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEfficiencyType() {
		return efficiencyType;
	}

	/**
	 * Sets the value of the efficiencyType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEfficiencyType(String value) {
		this.efficiencyType = value;
	}

	/**
	 * Gets the value of the feedbackCompany property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeedbackCompany() {
		return feedbackCompany;
	}

	/**
	 * Sets the value of the feedbackCompany property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeedbackCompany(String value) {
		this.feedbackCompany = value;
	}

	/**
	 * Gets the value of the feedbackDes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeedbackDes() {
		return feedbackDes;
	}

	/**
	 * Sets the value of the feedbackDes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeedbackDes(String value) {
		this.feedbackDes = value;
	}

	/**
	 * Gets the value of the feedbackFiles property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeedbackFiles() {
		return feedbackFiles;
	}

	/**
	 * Sets the value of the feedbackFiles property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeedbackFiles(String value) {
		this.feedbackFiles = value;
	}

	/**
	 * Gets the value of the feedbackPerson property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeedbackPerson() {
		return feedbackPerson;
	}

	/**
	 * Sets the value of the feedbackPerson property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeedbackPerson(String value) {
		this.feedbackPerson = value;
	}

	/**
	 * Gets the value of the feedbackPhone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeedbackPhone() {
		return feedbackPhone;
	}

	/**
	 * Sets the value of the feedbackPhone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeedbackPhone(String value) {
		this.feedbackPhone = value;
	}

	/**
	 * Gets the value of the feedbackType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeedbackType() {
		return feedbackType;
	}

	/**
	 * Sets the value of the feedbackType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeedbackType(String value) {
		this.feedbackType = value;
	}

	/**
	 * Gets the value of the feedbackWay property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeedbackWay() {
		return feedbackWay;
	}

	/**
	 * Sets the value of the feedbackWay property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeedbackWay(String value) {
		this.feedbackWay = value;
	}

	/**
	 * Gets the value of the handOverTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHandOverTime() {
		return handOverTime;
	}

	/**
	 * Sets the value of the handOverTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHandOverTime(String value) {
		this.handOverTime = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the integratedExpenditure property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIntegratedExpenditure() {
		return integratedExpenditure;
	}

	/**
	 * Sets the value of the integratedExpenditure property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIntegratedExpenditure(String value) {
		this.integratedExpenditure = value;
	}

	/**
	 * Gets the value of the isCut property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsCut() {
		return isCut;
	}

	/**
	 * Sets the value of the isCut property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsCut(String value) {
		this.isCut = value;
	}

	/**
	 * Gets the value of the isHuge property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsHuge() {
		return isHuge;
	}

	/**
	 * Sets the value of the isHuge property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsHuge(String value) {
		this.isHuge = value;
	}

	/**
	 * Gets the value of the lrCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLrCode() {
		return lrCode;
	}

	/**
	 * Sets the value of the lrCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLrCode(String value) {
		this.lrCode = value;
	}

	/**
	 * Gets the value of the lrCodeGroup property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLrCodeGroup() {
		return lrCodeGroup;
	}

	/**
	 * Sets the value of the lrCodeGroup property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLrCodeGroup(String value) {
		this.lrCodeGroup = value;
	}

	/**
	 * Gets the value of the lrCodeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLrCodeName() {
		return lrCodeName;
	}

	/**
	 * Sets the value of the lrCodeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLrCodeName(String value) {
		this.lrCodeName = value;
	}

	/**
	 * Gets the value of the oaDocumentId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOaDocumentId() {
		return oaDocumentId;
	}

	/**
	 * Sets the value of the oaDocumentId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOaDocumentId(String value) {
		this.oaDocumentId = value;
	}

	/**
	 * Gets the value of the oaOrgCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOaOrgCode() {
		return oaOrgCode;
	}

	/**
	 * Sets the value of the oaOrgCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOaOrgCode(String value) {
		this.oaOrgCode = value;
	}

	/**
	 * Gets the value of the proCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProCode() {
		return proCode;
	}

	/**
	 * Sets the value of the proCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProCode(String value) {
		this.proCode = value;
	}

	/**
	 * Gets the value of the proIctCost property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProIctCost() {
		return proIctCost;
	}

	/**
	 * Sets the value of the proIctCost property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProIctCost(String value) {
		this.proIctCost = value;
	}

	/**
	 * Gets the value of the proInterestRate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProInterestRate() {
		return proInterestRate;
	}

	/**
	 * Sets the value of the proInterestRate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProInterestRate(String value) {
		this.proInterestRate = value;
	}

	/**
	 * Gets the value of the proManager property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProManager() {
		return proManager;
	}

	/**
	 * Sets the value of the proManager property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProManager(String value) {
		this.proManager = value;
	}

	/**
	 * Gets the value of the proName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProName() {
		return proName;
	}

	/**
	 * Sets the value of the proName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProName(String value) {
		this.proName = value;
	}

	/**
	 * Gets the value of the proProfitMargin property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProProfitMargin() {
		return proProfitMargin;
	}

	/**
	 * Sets the value of the proProfitMargin property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProProfitMargin(String value) {
		this.proProfitMargin = value;
	}

	/**
	 * Gets the value of the proProfitMarginPre property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProProfitMarginPre() {
		return proProfitMarginPre;
	}

	/**
	 * Sets the value of the proProfitMarginPre property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProProfitMarginPre(String value) {
		this.proProfitMarginPre = value;
	}

	/**
	 * Gets the value of the proRevenueIct property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProRevenueIct() {
		return proRevenueIct;
	}

	/**
	 * Sets the value of the proRevenueIct property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProRevenueIct(String value) {
		this.proRevenueIct = value;
	}

	/**
	 * Gets the value of the proRevenueIctPre property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProRevenueIctPre() {
		return proRevenueIctPre;
	}

	/**
	 * Sets the value of the proRevenueIctPre property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProRevenueIctPre(String value) {
		this.proRevenueIctPre = value;
	}

	/**
	 * Gets the value of the proRevenueTotal property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProRevenueTotal() {
		return proRevenueTotal;
	}

	/**
	 * Sets the value of the proRevenueTotal property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProRevenueTotal(String value) {
		this.proRevenueTotal = value;
	}

	/**
	 * Gets the value of the proRevenueTotalPre property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProRevenueTotalPre() {
		return proRevenueTotalPre;
	}

	/**
	 * Sets the value of the proRevenueTotalPre property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProRevenueTotalPre(String value) {
		this.proRevenueTotalPre = value;
	}

	/**
	 * Gets the value of the proStatus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProStatus() {
		return proStatus;
	}

	/**
	 * Sets the value of the proStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProStatus(String value) {
		this.proStatus = value;
	}

	/**
	 * Gets the value of the proType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProType() {
		return proType;
	}

	/**
	 * Sets the value of the proType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProType(String value) {
		this.proType = value;
	}

	/**
	 * Gets the value of the proZq property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProZq() {
		return proZq;
	}

	/**
	 * Sets the value of the proZq property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProZq(String value) {
		this.proZq = value;
	}

	/**
	 * Gets the value of the productCosts property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductCosts() {
		return productCosts;
	}

	/**
	 * Sets the value of the productCosts property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductCosts(String value) {
		this.productCosts = value;
	}

	/**
	 * Gets the value of the productRevenue property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductRevenue() {
		return productRevenue;
	}

	/**
	 * Sets the value of the productRevenue property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductRevenue(String value) {
		this.productRevenue = value;
	}

	/**
	 * Gets the value of the province property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the value of the province property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProvince(String value) {
		this.province = value;
	}

	/**
	 * Gets the value of the sceneType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSceneType() {
		return sceneType;
	}

	/**
	 * Sets the value of the sceneType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSceneType(String value) {
		this.sceneType = value;
	}

	/**
	 * Gets the value of the sendTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * Sets the value of the sendTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSendTime(String value) {
		this.sendTime = value;
	}

}
