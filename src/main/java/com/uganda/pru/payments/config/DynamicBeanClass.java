package com.uganda.pru.payments.config;
import java.lang.annotation.Annotation;

import com.poiji.annotation.ExcelCellName;

public class DynamicBeanClass implements ExcelCellName{

	@Override
	public Class<? extends Annotation> annotationType() {
		return ExcelCellName.class;
	}

	@Override
	public String value() {
		return null;
	}

}

