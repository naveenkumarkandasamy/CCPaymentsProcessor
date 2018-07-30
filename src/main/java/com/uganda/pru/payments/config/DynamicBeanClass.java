package com.uganda.pru.payments.config;
import com.poiji.annotation.*;
import java.lang.annotation.Annotation;

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

