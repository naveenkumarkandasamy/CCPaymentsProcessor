package com.uganda.pru.payments.model;

import com.poiji.annotation.ExcelCellName;

public class CentenaryBank {
	// @JsonPropertyOrder({ "W/D", "Date", "ValueDate", "Description",
	// "Comments", "Cheque Number", "Amount",
	// "BookBalance" })
	private String bank;
	private String transactionType;
	@ExcelCellName("WD")
	private String wd;
	@ExcelCellName("Date")
	private String date;
	@ExcelCellName("ValueDate")
	private String valueDate;
	@ExcelCellName("Description")
	private String description;
	@ExcelCellName("Comments")
	private String comments;
	@ExcelCellName("Cheque Number")
	private String chequeNumber;
	@ExcelCellName("Amount")
	private String amount;
	@ExcelCellName("BookBalance")
	private String bookBalance;

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getDescription() {
		return comments;
	}

	public void setDescription(String description) {
		this.comments = description;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumer(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBookBalance() {
		return bookBalance;
	}

	public void setBookBalance(String bookBalance) {
		this.bookBalance = bookBalance;
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

}
