package com.uganda.pru.payments.model;

public class ILInputModel {

private String bank;
private String transactionType;
private String chequeNumber;
private String productDescription;
private Double creditAmount;
private String date;
private String year;
private String month;
private String company;
private String policyName;
private String policyNumber;
private String receiptNumber;
private String rowNum;


public String getRowNum() {
	return rowNum;
}
public void setRowNum(String rowNum) {
	this.rowNum = rowNum;
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
public String getChequeNumber() {
	return chequeNumber;
}
public void setChequeNumber(String chequeNumber) {
	this.chequeNumber = chequeNumber;
}
public String getProductDescription() {
	return productDescription;
}
public void setProductDescription(String productDescription) {
	this.productDescription = productDescription;
}
public Double getCreditAmount() {
	return creditAmount;
}
public void setCreditAmount(Double creditAmount) {
	this.creditAmount = creditAmount;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getPolicyName() {
	return policyName;
}
public void setPolicyName(String policyName) {
	this.policyName = policyName;
}
public String getPolicyNumber() {
	return policyNumber;
}
public void setPolicyNumber(String policyNumber) {
	this.policyNumber = policyNumber;
}
public String getReceiptNumber() {
	return receiptNumber;
}
public void setReceiptNumber(String receiptNumber) {
	this.receiptNumber = receiptNumber;
}
@Override
public String toString() {
	return "ILInputModel [bank=" + bank + ", transactionType=" + transactionType + ", chequeNumber=" + chequeNumber
			+ ", productDescription=" + productDescription + ", creditAmount=" + creditAmount + ", date=" + date
			+ ", year=" + year + ", month=" + month + ", company=" + company + ", policyName=" + policyName
			+ ", policyNumber=" + policyNumber + ", receiptNumber=" + receiptNumber + "]";
}


}