package br.com.ptw.util;

/**
 * @author Ady Junior - 03/09/2014
 *
 */
public class NumberUtil {

	public static Boolean isNullOrZero(Number value) {
		if (value == null) {
			return true;
		}

		double doubleValue = value.doubleValue();
		System.out.println(doubleValue);
		if (doubleValue == 0.0) {
			return true;
		}

		return false;
	}

	public static Boolean containsNullOrZero(Number... values) {
		for (Number value : values) {
			if (isNullOrZero(value)) {
				return true;
			}
		}
		return false;
	}
}
