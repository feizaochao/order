package com.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
* @Description: 金额处理类
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
public class MathUtils {

	public static BigDecimal getBigDecimal(Object value) {
		BigDecimal ret = BigDecimal.ZERO;
		if(value != null && !"".equals(value)) {
			if(value instanceof BigDecimal) {
				ret = (BigDecimal) value;
			} else if(value instanceof String) {
				ret = new BigDecimal((String)value);
			} else if(value instanceof BigInteger) {
				ret = new BigDecimal((BigInteger)value);
			} else if(value instanceof Number) {
				ret = new BigDecimal(((Number)value).doubleValue());
			} else {
				throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");  
			}
		}
		return ret;
	}
}
