package com.uganda.pru.payments.model;

public class InputSchema {

	private String fieldName;
	private Integer cellNumber;
	private String expression;
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(Integer cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "InputSchema [fieldName=" + fieldName + ", cellNumber=" + cellNumber + ", expression=" + expression
				+ "]";
	}

	
}
