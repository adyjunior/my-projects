package brx.com.tigerbuilder.util;

import java.lang.reflect.ParameterizedType;

/**
 * @author Ady Junior - 08/05/2014
 *
 */
public class ReflectionUtils {

	public static <T> Class<?> getGenericTypeParameter(Class<T> type) {
		try {
			return  (Class<?>)((ParameterizedType) 
					type.getGenericSuperclass()).getActualTypeArguments()[0];			
		} catch (Exception e) {
		}
		return null;
	}

}
