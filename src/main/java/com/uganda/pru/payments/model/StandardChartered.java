package com.uganda.pru.payments.model;

import com.poiji.annotation.ExcelCell;

public class StandardChartered {

	private String Bank;
	private String transactionType;
	@ExcelCell(2)
	private String chequeNumber;
	@ExcelCell(3)
	private String bankAccountNumber;
	@ExcelCell(4)
	private String customerName;
	@ExcelCell(5)
	private String description;
	@ExcelCell(6)
	private String creditAmount;
	@ExcelCell(7)
	private String transactionDate;
	@ExcelCell(7)
	private String valueDate;
	@ExcelCell(8)
	private String month;

	public String getBank() {
		return Bank;
	}
	public void setBank(String bank) {
		Bank = bank;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}


}
