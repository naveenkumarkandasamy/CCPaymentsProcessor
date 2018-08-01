package com.uganda.pru.payments.model;

import com.poiji.annotation.ExcelCellName;

public class Barclays {

	//@JsonPropertyOrder({ "Transaction Date", "Value Date", "Description", "Cheque Number", "Credit Amount", "Debit Amount", "Running Balance"})
	private String bank;
	private String transactionType;
	@ExcelCellName("Transaction Date")
	private String transactionDate;
	@ExcelCellName("Value Date")
	private String valueDate;
	@ExcelCellName("Description")
	private String description;
	@ExcelCellName("Cheque Number")
	private String chequeNumber;
	@ExcelCellName("Credit Amount")
	private String creditAmount;
	@ExcelCellName("Debit Amount")
	private String debitAmount;
	@ExcelCellName("Running Balance")
	private String runningBalance;


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
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getRunningBalance() {
		return runningBalance;
	}
	public void setRunningBalance(String runningBalance) {
		this.runningBalance = runningBalance;
	}

}




