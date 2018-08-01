package com.uganda.pru.payments.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uganda.pru.payments.model.Workbench;


@Component
public class Mapper {

	@Autowired
	private ModelConverter converter;

	@Autowired
	Mapper instance;


	static final Logger logger = Logger.getLogger(Mapper.class);

	public <T> List<Workbench> mapToWorkbenchObject(List<T> recordsList, Class<T> classType, Map<String, String> recordToWorkbenchMap) {
		List<Workbench> workbenchList = null;
		if (null != recordsList && !recordsList.isEmpty()) {

			workbenchList = new ArrayList<>();

			for (T record : recordsList) {
				if (null != record) {
					Workbench workbenchRecord = (Workbench)converter.map(record, classType, Workbench.class, recordToWorkbenchMap);	
					workbenchList.add(workbenchRecord);
				} else {
					logger.debug("Record list is null");
				}
			}
		} else {
			logger.debug("RecordsList is either null or empty");
		}
		return workbenchList;
	}



}
