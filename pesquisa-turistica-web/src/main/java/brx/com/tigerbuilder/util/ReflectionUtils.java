package brx.com.tigerbuilder.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
	
	public static boolean isMethodGetOfAttribute(Method m, Field f){
		String getPrefix = "get";
        return (m.getName().startsWith(getPrefix)) && (m.getName().toLowerCase().endsWith(f.getName().toLowerCase()));
    }	
	
	public static Object runGetter(Field field, Object o) {
        for (Method method : o.getClass().getDeclaredMethods()) {
            if (isMethodGetOfAttribute(method, field)) {
                try {
                    return method.invoke(o);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }	

}
