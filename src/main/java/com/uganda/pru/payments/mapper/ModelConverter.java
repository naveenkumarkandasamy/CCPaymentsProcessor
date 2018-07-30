/**
 * 
 */
package com.uganda.pru.payments.mapper;

import java.util.Map;

/**
 * @author megha
 *
 */

public interface ModelConverter {

	Object map(Object source, Class sourceClass,Class targetClass,Map<String,String> map);
	
}
