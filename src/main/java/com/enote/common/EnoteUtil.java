package com.enote.common;

import java.util.Collection;
import java.util.Map;

public final class EnoteUtil {
	
	
	
	public EnoteUtil() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static boolean isNullOrEmpty(Object object) {
		if (object ==null) {
			return true;
		}
		if (object instanceof String) {
			String str=((String)object).trim();
			return str.isEmpty() || ("null").equalsIgnoreCase(str);
		}
		if (object  instanceof Collection) {
			return ((Collection<?>)object).isEmpty();
		}
		if (object  instanceof Map) {
			return ((Map<?, ?>)object).isEmpty();
		}
		return false;
	}
	
	

}
