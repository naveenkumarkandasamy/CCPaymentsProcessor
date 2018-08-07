
package com.uganda.pru.payments.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.csc.smart/msp/schemas/MSPContext}MSPContext" minOccurs="0"/&gt;
 *         &lt;element name="ACTION"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BANKCODE"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RFCODE"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RFNUM"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TRANDATEX"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CCYY"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                         &lt;totalDigits value="4"/&gt;
 *                         &lt;pattern value="((2[0-9][0-9][0-9])|(1[0-9][0-9][0-9])|9999|0)"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="MM"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                         &lt;totalDigits value="2"/&gt;
 *                         &lt;pattern value="(([0-9])|(0[0-9])|(1[0-2])|99|0)"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="DD"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                         &lt;totalDigits value="2"/&gt;
 *                         &lt;pattern value="(([0-9])|(0[1-9]|[1-2][0-9])|(3[0-1])|99|0)"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SFL"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="BANKDESC01"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="30"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="BANKDESC02"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="30"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="BANKDESC03"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="30"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="BANKKEY"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="CHEQNO"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="9"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="DOCORIGAMT"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                         &lt;totalDigits value="17"/&gt;
 *                         &lt;fractionDigits value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ORIGCURR"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="PAYTYPE"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="SCRATE"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                         &lt;totalDigits value="12"/&gt;
 *                         &lt;fractionDigits value="7"/&gt;
 *                         &lt;minInclusive value="0"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="TCHQDATE"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="CCYY"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                                   &lt;totalDigits value="4"/&gt;
 *                                   &lt;pattern value="((2[0-9][0-9][0-9])|(1[0-9][0-9][0-9])|9999|0)"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="MM"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                                   &lt;totalDigits value="2"/&gt;
 *                                   &lt;pattern value="(([0-9])|(0[0-9])|(1[0-2])|99|0)"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="DD"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                                   &lt;totalDigits value="2"/&gt;
 *                                   &lt;pattern value="(([0-9])|(0[1-9]|[1-2][0-9])|(3[0-1])|99|0)"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ZCHQTYP"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="S2610_SFL" maxOccurs="20"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ACCTAMT"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                         &lt;totalDigits value="17"/&gt;
 *                         &lt;fractionDigits value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ENTITY"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="16"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ORIGAMT"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                         &lt;totalDigits value="17"/&gt;
 *                         &lt;fractionDigits value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="SACSCODE"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="SACSTYPW"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ADDITIONAL_FIELDS"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TRANCDE"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="PRTIND"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="DISSEC"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                         &lt;totalDigits value="2"/&gt;
 *                         &lt;minInclusive value="0"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="REM_FLD1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="REM_FLD2"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mspContext",
    "action",
    "bankcode",
    "rfcode",
    "rfnum",
    "trandatex",
    "sfl",
    "s2610SFL",
    "additionalfields"
})
@XmlRootElement(name = "RCTCRTI_REC",namespace="http://www.csc.smart/bo/schemas/RCTCRTI")
public class RCTCRTIREC {

    @XmlElement(name = "MSPContext", namespace = "http://www.csc.smart/msp/schemas/MSPContext")
    protected MSPContext mspContext;
    @XmlElement(name = "ACTION", required = true)
    protected String action;
    @XmlElement(name = "BANKCODE", required = true)
    protected String bankcode;
    @XmlElement(name = "RFCODE", required = true)
    protected String rfcode;
    @XmlElement(name = "RFNUM", required = true)
    protected String rfnum;
    @XmlElement(name = "TRANDATEX", required = true)
    protected RCTCRTIREC.TRANDATEX trandatex;
    @XmlElement(name = "SFL", required = true)
    protected RCTCRTIREC.SFL sfl;
    @XmlElement(name = "S2610_SFL", required = true)
    protected List<RCTCRTIREC.S2610SFL> s2610SFL;
    @XmlElement(name = "ADDITIONAL_FIELDS", required = true)
    protected RCTCRTIREC.ADDITIONALFIELDS additionalfields;

    /**
     * Gets the value of the mspContext property.
     * 
     * @return
     *     possible object is
     *     {@link MSPContext }
     *     
     */
    public MSPContext getMSPContext() {
        return mspContext;
    }

    /**
     * Sets the value of the mspContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSPContext }
     *     
     */
    public void setMSPContext(MSPContext value) {
        this.mspContext = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTION() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTION(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the bankcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBANKCODE() {
        return bankcode;
    }

    /**
     * Sets the value of the bankcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBANKCODE(String value) {
        this.bankcode = value;
    }

    /**
     * Gets the value of the rfcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFCODE() {
        return rfcode;
    }

    /**
     * Sets the value of the rfcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFCODE(String value) {
        this.rfcode = value;
    }

    /**
     * Gets the value of the rfnum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFNUM() {
        return rfnum;
    }

    /**
     * Sets the value of the rfnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFNUM(String value) {
        this.rfnum = value;
    }

    /**
     * Gets the value of the trandatex property.
     * 
     * @return
     *     possible object is
     *     {@link RCTCRTIREC.TRANDATEX }
     *     
     */
    public RCTCRTIREC.TRANDATEX getTRANDATEX() {
        return trandatex;
    }

    /**
     * Sets the value of the trandatex property.
     * 
     * @param value
     *     allowed object is
     *     {@link RCTCRTIREC.TRANDATEX }
     *     
     */
    public void setTRANDATEX(RCTCRTIREC.TRANDATEX value) {
        this.trandatex = value;
    }

    /**
     * Gets the value of the sfl property.
     * 
     * @return
     *     possible object is
     *     {@link RCTCRTIREC.SFL }
     *     
     */
    public RCTCRTIREC.SFL getSFL() {
        return sfl;
    }

    /**
     * Sets the value of the sfl property.
     * 
     * @param value
     *     allowed object is
     *     {@link RCTCRTIREC.SFL }
     *     
     */
    public void setSFL(RCTCRTIREC.SFL value) {
        this.sfl = value;
    }

    /**
     * Gets the value of the s2610SFL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the s2610SFL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getS2610SFL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RCTCRTIREC.S2610SFL }
     * 
     * 
     */
    public List<RCTCRTIREC.S2610SFL> getS2610SFL() {
        if (s2610SFL == null) {
            s2610SFL = new ArrayList<RCTCRTIREC.S2610SFL>();
        }
        return this.s2610SFL;
    }

    /**
     * Gets the value of the additionalfields property.
     * 
     * @return
     *     possible object is
     *     {@link RCTCRTIREC.ADDITIONALFIELDS }
     *     
     */
    public RCTCRTIREC.ADDITIONALFIELDS getADDITIONALFIELDS() {
        return additionalfields;
    }

    /**
     * Sets the value of the additionalfields property.
     * 
     * @param value
     *     allowed object is
     *     {@link RCTCRTIREC.ADDITIONALFIELDS }
     *     
     */
    public void setADDITIONALFIELDS(RCTCRTIREC.ADDITIONALFIELDS value) {
        this.additionalfields = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="TRANCDE"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="PRTIND"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="DISSEC"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *               &lt;totalDigits value="2"/&gt;
     *               &lt;minInclusive value="0"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="REM_FLD1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="REM_FLD2"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "trancde",
        "prtind",
        "dissec",
        "remfld1",
        "remfld2"
    })
    public static class ADDITIONALFIELDS {

        @XmlElement(name = "TRANCDE", required = true)
        protected String trancde;
        @XmlElement(name = "PRTIND", required = true)
        protected String prtind;
        @XmlElement(name = "DISSEC", required = true)
        protected BigInteger dissec;
        @XmlElement(name = "REM_FLD1", required = true)
        protected String remfld1;
        @XmlElement(name = "REM_FLD2", required = true)
        protected String remfld2;

        /**
         * Gets the value of the trancde property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTRANCDE() {
            return trancde;
        }

        /**
         * Sets the value of the trancde property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTRANCDE(String value) {
            this.trancde = value;
        }

        /**
         * Gets the value of the prtind property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPRTIND() {
            return prtind;
        }

        /**
         * Sets the value of the prtind property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPRTIND(String value) {
            this.prtind = value;
        }

        /**
         * Gets the value of the dissec property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getDISSEC() {
            return dissec;
        }

        /**
         * Sets the value of the dissec property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setDISSEC(BigInteger value) {
            this.dissec = value;
        }

        /**
         * Gets the value of the remfld1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREMFLD1() {
            return remfld1;
        }

        /**
         * Sets the value of the remfld1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREMFLD1(String value) {
            this.remfld1 = value;
        }

        /**
         * Gets the value of the remfld2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREMFLD2() {
            return remfld2;
        }

        /**
         * Sets the value of the remfld2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREMFLD2(String value) {
            this.remfld2 = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="ACCTAMT"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *               &lt;totalDigits value="17"/&gt;
     *               &lt;fractionDigits value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ENTITY"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="16"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ORIGAMT"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *               &lt;totalDigits value="17"/&gt;
     *               &lt;fractionDigits value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="SACSCODE"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="SACSTYPW"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "acctamt",
        "entity",
        "origamt",
        "sacscode",
        "sacstypw"
    })
    public static class S2610SFL {

        @XmlElement(name = "ACCTAMT", required = true)
        protected BigDecimal acctamt;
        @XmlElement(name = "ENTITY", required = true)
        protected String entity;
        @XmlElement(name = "ORIGAMT", required = true)
        protected BigDecimal origamt;
        @XmlElement(name = "SACSCODE", required = true)
        protected String sacscode;
        @XmlElement(name = "SACSTYPW", required = true)
        protected String sacstypw;

        /**
         * Gets the value of the acctamt property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getACCTAMT() {
            return acctamt;
        }

        /**
         * Sets the value of the acctamt property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setACCTAMT(BigDecimal value) {
            this.acctamt = value;
        }

        /**
         * Gets the value of the entity property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getENTITY() {
            return entity;
        }

        /**
         * Sets the value of the entity property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setENTITY(String value) {
            this.entity = value;
        }

        /**
         * Gets the value of the origamt property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getORIGAMT() {
            return origamt;
        }

        /**
         * Sets the value of the origamt property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setORIGAMT(BigDecimal value) {
            this.origamt = value;
        }

        /**
         * Gets the value of the sacscode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSACSCODE() {
            return sacscode;
        }

        /**
         * Sets the value of the sacscode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSACSCODE(String value) {
            this.sacscode = value;
        }

        /**
         * Gets the value of the sacstypw property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSACSTYPW() {
            return sacstypw;
        }

        /**
         * Sets the value of the sacstypw property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSACSTYPW(String value) {
            this.sacstypw = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="BANKDESC01"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="30"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="BANKDESC02"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="30"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="BANKDESC03"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="30"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="BANKKEY"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="CHEQNO"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="9"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="DOCORIGAMT"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *               &lt;totalDigits value="17"/&gt;
     *               &lt;fractionDigits value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ORIGCURR"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="PAYTYPE"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="SCRATE"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *               &lt;totalDigits value="12"/&gt;
     *               &lt;fractionDigits value="7"/&gt;
     *               &lt;minInclusive value="0"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="TCHQDATE"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="CCYY"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *                         &lt;totalDigits value="4"/&gt;
     *                         &lt;pattern value="((2[0-9][0-9][0-9])|(1[0-9][0-9][0-9])|9999|0)"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="MM"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *                         &lt;totalDigits value="2"/&gt;
     *                         &lt;pattern value="(([0-9])|(0[0-9])|(1[0-2])|99|0)"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="DD"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *                         &lt;totalDigits value="2"/&gt;
     *                         &lt;pattern value="(([0-9])|(0[1-9]|[1-2][0-9])|(3[0-1])|99|0)"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ZCHQTYP"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bankdesc01",
        "bankdesc02",
        "bankdesc03",
        "bankkey",
        "cheqno",
        "docorigamt",
        "origcurr",
        "paytype",
        "scrate",
        "tchqdate",
        "zchqtyp"
    })
    public static class SFL {

        @XmlElement(name = "BANKDESC01", required = true)
        protected String bankdesc01;
        @XmlElement(name = "BANKDESC02", required = true)
        protected String bankdesc02;
        @XmlElement(name = "BANKDESC03", required = true)
        protected String bankdesc03;
        @XmlElement(name = "BANKKEY", required = true)
        protected String bankkey;
        @XmlElement(name = "CHEQNO", required = true)
        protected String cheqno;
        @XmlElement(name = "DOCORIGAMT", required = true)
        protected BigDecimal docorigamt;
        @XmlElement(name = "ORIGCURR", required = true)
        protected String origcurr;
        @XmlElement(name = "PAYTYPE", required = true)
        protected String paytype;
        @XmlElement(name = "SCRATE", required = true)
        protected BigDecimal scrate;
        @XmlElement(name = "TCHQDATE", required = true)
        protected RCTCRTIREC.SFL.TCHQDATE tchqdate;
        @XmlElement(name = "ZCHQTYP", required = true)
        protected String zchqtyp;

        /**
         * Gets the value of the bankdesc01 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBANKDESC01() {
            return bankdesc01;
        }

        /**
         * Sets the value of the bankdesc01 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBANKDESC01(String value) {
            this.bankdesc01 = value;
        }

        /**
         * Gets the value of the bankdesc02 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBANKDESC02() {
            return bankdesc02;
        }

        /**
         * Sets the value of the bankdesc02 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBANKDESC02(String value) {
            this.bankdesc02 = value;
        }

        /**
         * Gets the value of the bankdesc03 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBANKDESC03() {
            return bankdesc03;
        }

        /**
         * Sets the value of the bankdesc03 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBANKDESC03(String value) {
            this.bankdesc03 = value;
        }

        /**
         * Gets the value of the bankkey property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBANKKEY() {
            return bankkey;
        }

        /**
         * Sets the value of the bankkey property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBANKKEY(String value) {
            this.bankkey = value;
        }

        /**
         * Gets the value of the cheqno property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCHEQNO() {
            return cheqno;
        }

        /**
         * Sets the value of the cheqno property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCHEQNO(String value) {
            this.cheqno = value;
        }

        /**
         * Gets the value of the docorigamt property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getDOCORIGAMT() {
            return docorigamt;
        }

        /**
         * Sets the value of the docorigamt property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setDOCORIGAMT(BigDecimal value) {
            this.docorigamt = value;
        }

        /**
         * Gets the value of the origcurr property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getORIGCURR() {
            return origcurr;
        }

        /**
         * Sets the value of the origcurr property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setORIGCURR(String value) {
            this.origcurr = value;
        }

        /**
         * Gets the value of the paytype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPAYTYPE() {
            return paytype;
        }

        /**
         * Sets the value of the paytype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPAYTYPE(String value) {
            this.paytype = value;
        }

        /**
         * Gets the value of the scrate property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSCRATE() {
            return scrate;
        }

        /**
         * Sets the value of the scrate property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSCRATE(BigDecimal value) {
            this.scrate = value;
        }

        /**
         * Gets the value of the tchqdate property.
         * 
         * @return
         *     possible object is
         *     {@link RCTCRTIREC.SFL.TCHQDATE }
         *     
         */
        public RCTCRTIREC.SFL.TCHQDATE getTCHQDATE() {
            return tchqdate;
        }

        /**
         * Sets the value of the tchqdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link RCTCRTIREC.SFL.TCHQDATE }
         *     
         */
        public void setTCHQDATE(RCTCRTIREC.SFL.TCHQDATE value) {
            this.tchqdate = value;
        }

        /**
         * Gets the value of the zchqtyp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZCHQTYP() {
            return zchqtyp;
        }

        /**
         * Sets the value of the zchqtyp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZCHQTYP(String value) {
            this.zchqtyp = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="CCYY"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
         *               &lt;totalDigits value="4"/&gt;
         *               &lt;pattern value="((2[0-9][0-9][0-9])|(1[0-9][0-9][0-9])|9999|0)"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="MM"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
         *               &lt;totalDigits value="2"/&gt;
         *               &lt;pattern value="(([0-9])|(0[0-9])|(1[0-2])|99|0)"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="DD"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
         *               &lt;totalDigits value="2"/&gt;
         *               &lt;pattern value="(([0-9])|(0[1-9]|[1-2][0-9])|(3[0-1])|99|0)"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "ccyy",
            "mm",
            "dd"
        })
        public static class TCHQDATE {

            @XmlElement(name = "CCYY", required = true)
            protected BigInteger ccyy;
            @XmlElement(name = "MM", required = true)
            protected BigInteger mm;
            @XmlElement(name = "DD", required = true)
            protected BigInteger dd;

            /**
             * Gets the value of the ccyy property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getCCYY() {
                return ccyy;
            }

            /**
             * Sets the value of the ccyy property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setCCYY(BigInteger value) {
                this.ccyy = value;
            }

            /**
             * Gets the value of the mm property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getMM() {
                return mm;
            }

            /**
             * Sets the value of the mm property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setMM(BigInteger value) {
                this.mm = value;
            }

            /**
             * Gets the value of the dd property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getDD() {
                return dd;
            }

            /**
             * Sets the value of the dd property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setDD(BigInteger value) {
                this.dd = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="CCYY"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *               &lt;totalDigits value="4"/&gt;
     *               &lt;pattern value="((2[0-9][0-9][0-9])|(1[0-9][0-9][0-9])|9999|0)"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="MM"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *               &lt;totalDigits value="2"/&gt;
     *               &lt;pattern value="(([0-9])|(0[0-9])|(1[0-2])|99|0)"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="DD"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *               &lt;totalDigits value="2"/&gt;
     *               &lt;pattern value="(([0-9])|(0[1-9]|[1-2][0-9])|(3[0-1])|99|0)"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ccyy",
        "mm",
        "dd"
    })
    public static class TRANDATEX {

        @XmlElement(name = "CCYY", required = true)
        protected BigInteger ccyy;
        @XmlElement(name = "MM", required = true)
        protected BigInteger mm;
        @XmlElement(name = "DD", required = true)
        protected BigInteger dd;

        /**
         * Gets the value of the ccyy property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getCCYY() {
            return ccyy;
        }

        /**
         * Sets the value of the ccyy property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setCCYY(BigInteger value) {
            this.ccyy = value;
        }

        /**
         * Gets the value of the mm property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMM() {
            return mm;
        }

        /**
         * Sets the value of the mm property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMM(BigInteger value) {
            this.mm = value;
        }

        /**
         * Gets the value of the dd property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getDD() {
            return dd;
        }

        /**
         * Sets the value of the dd property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setDD(BigInteger value) {
            this.dd = value;
        }

    }

}
