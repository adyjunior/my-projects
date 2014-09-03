package br.com.ptw.util.springjdbc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.ptw.geral.generic.model.Entidade;
import brx.com.tigerbuilder.util.ReflectionUtils;
import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

public class SpringJdbcUtil {

	@SuppressWarnings("unchecked")
	public static <T extends Entidade> Object[] getValuesEntityForInsert(T t) {
		Field[] fields = t.getClass().getDeclaredFields();

		List<Object> objects = new ArrayList<Object>();

		for (Field field : fields) {
			if (UtilVilaQueryReflection.isColumn(field) && !UtilVilaQueryReflection.isPrimaryKey(field)) {
				Object objectFinal = null;

				if (UtilVilaQueryReflection.isForeignKey(field)) {
					Class<T> typeForeignKey = (Class<T>) field.getType();
					Field fieldPrimaryKeyOfForeignKey = UtilVilaQueryReflection.getFieldPrimaryKey(typeForeignKey);
					Object objectForeignKey = ReflectionUtils.runGetter(field, t);
					objectFinal = ReflectionUtils.runGetter(fieldPrimaryKeyOfForeignKey, objectForeignKey);
				} else {
					objectFinal = ReflectionUtils.runGetter(field, t);
				}

				objects.add(objectFinal);
			}
		}

		return objects.toArray();
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entidade> Object[] getValuesEntityForUpdate(T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		
		List<Object> objects = new ArrayList<Object>();
		
		for (Field field : fields) {
			if (UtilVilaQueryReflection.isColumn(field)) {
				Object objectFinal = null;
				
				if (UtilVilaQueryReflection.isForeignKey(field)) {
					Class<T> typeForeignKey = (Class<T>) field.getType();
					Field fieldPrimaryKeyOfForeignKey = UtilVilaQueryReflection.getFieldPrimaryKey(typeForeignKey);
					Object objectForeignKey = ReflectionUtils.runGetter(field, t);
					objectFinal = ReflectionUtils.runGetter(fieldPrimaryKeyOfForeignKey, objectForeignKey);
				} else {
					objectFinal = ReflectionUtils.runGetter(field, t);
				}
				
				objects.add(objectFinal);
			}
		}
		
		return objects.toArray();
	}

}
