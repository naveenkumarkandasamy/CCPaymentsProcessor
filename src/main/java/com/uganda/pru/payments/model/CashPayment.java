package com.uganda.pru.payments.model;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.poiji.annotation.ExcelCellName;

public class CashPayment {

	//@JsonPropertyOrder({ "Transaction Date", "Value Date", "Description", "Cheque Number", "Credit Amount", "Debit Amount", "Running Balance"})


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




