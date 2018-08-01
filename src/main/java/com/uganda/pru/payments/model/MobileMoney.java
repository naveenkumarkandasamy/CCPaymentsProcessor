package com.uganda.pru.payments.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.poiji.annotation.ExcelCellName;

public class MobileMoney {

	@JsonPropertyOrder({ "Transaction Id", "MSISDN", "Payment Date", "Status", "Amount", "Reference"})

	private String bank;
	private String transactionType;
	@ExcelCellName("Transaction Id")
	private String transactionId;
	@ExcelCellName("MSISDN")
	private String msisdn;
	@ExcelCellName("Payment Date")
	private String paymentDate;
	@ExcelCellName("Payment Date")
	private String valueDate;
	@ExcelCellName("Status")
	private String status;
	@ExcelCellName("Amount")
	private String amount;
	@ExcelCellName("Reference")
	private String reference;


	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}



}
