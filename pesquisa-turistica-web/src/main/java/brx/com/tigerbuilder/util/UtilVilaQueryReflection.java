package brx.com.tigerbuilder.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import brx.com.tigerbuilder.enums.EnumColumnTypes;

import com.google.common.base.CaseFormat;

/**
 * @author Ady Junior - 08/05/2014
 * 
 */
public class UtilVilaQueryReflection {

	private static final Set<Class<?>> collectionTypes = new HashSet<Class<?>>();

	private static final String CLAUSULE_NOT_NULL = "NOT NULL";
	private static final String CLAUSULE_PRIMARY_KEY = "PRIMARY KEY";

	static {
		buildCollectionTypes();
	}

	private static void buildCollectionTypes() {
		collectionTypes.add(Collection.class);
		collectionTypes.add(List.class);
		collectionTypes.add(ArrayList.class);
		collectionTypes.add(LinkedList.class);
		collectionTypes.add(Set.class);
		collectionTypes.add(HashSet.class);
		collectionTypes.add(LinkedHashSet.class);
		collectionTypes.add(TreeSet.class);
		collectionTypes.add(Map.class);
		collectionTypes.add(HashMap.class);
		collectionTypes.add(LinkedHashMap.class);
		collectionTypes.add(TreeMap.class);
	}

	public static List<Field> listFieldsValidColumnOfEntity(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		List<Field> fieldValid = new ArrayList<Field>();
		
		for (Field field : fields) {
			if (isColumn(field)) {
				fieldValid.add(field);
			}
		}
		
		return fieldValid;
	}
	public static List<String> listColumnsForSqlQuery(Class<?> type) {

		Field[] fields = type.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();

		for (Field field : fields) {
			if (isColumn(field)) {
				String fieldName = getColumnName(field);
				fieldList.add(fieldName);
			}
		}

		return fieldList;
	}

	public static List<String> listColumnsForInsertSqlQuery(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();

		for (Field field : fields) {
			if (isColumn(field) && !isPrimaryKey(field)) {
				String fieldName = getColumnName(field);
				fieldList.add(fieldName);
			}
		}

		return fieldList;
	}

	public static String getTableName(Class<?> type, String alias) {
		Table table = type.getAnnotation(Table.class);
		StringBuilder tableNameBuilder = new StringBuilder();
		String nameLowerUnderscore = toLowerUnderscore(type.getSimpleName());
		if (table == null) {
			tableNameBuilder.append(nameLowerUnderscore);
		} else {
			String schema = table.schema();
			if (StringUtils.isNotBlank(schema)) {
				tableNameBuilder.append(schema).append(".");
			}

			String nameFromAnnotation = table.name();
			if (StringUtils.isNotBlank(nameFromAnnotation)) {
				tableNameBuilder.append(nameFromAnnotation);
			} else {
				tableNameBuilder.append(nameLowerUnderscore);
			}
		}

		if (StringUtils.isNotEmpty(alias)) {
			tableNameBuilder.append(" as ").append(alias);
		}

		return tableNameBuilder.toString();
	}

	public static String getTableName(Class<?> type) {
		return getTableName(type, null);
	}

	public static String getColumnName(Field field) {
		String fieldName = field.getName();

		if (isForeignKey(field)) {
			fieldName = getForeignKeyColumnName(field);
		} else {
			fieldName = getNormalColumnName(field);
		}
		return fieldName;
	}

	private static String getColumnType(Field field) {
		Class<?> type = field.getType();

		if (isForeignKey(field)) {
			type = Long.class;
		}

		EnumColumnTypes columnTypes = EnumColumnTypes.getFromClass(type);

		if (columnTypes == null) {
			throw new IllegalArgumentException("Field " + field.getName() + " is not mapped in types default of VILA QUERY.");
		}

		return columnTypes.getNameType();
	}

	private static String getNormalColumnName(Field field) {
		String fieldName = field.getName();
		Column column = field.getAnnotation(Column.class);
		if (column != null && !StringUtils.isBlank(column.name())) {
			fieldName = column.name();
		}
		return fieldName;
	}

	public static String getForeignKeyColumnName(Field field) {
		Column foreignKey = field.getAnnotation(Column.class);
		String nameForeignKey = null;

		if (foreignKey != null && StringUtils.isNotBlank(foreignKey.name())) {
			return foreignKey.name();
		}

		Class<?> typeField = field.getType();
		if (isCollectionType(field)) {
			Class<?> clazzParameter = ReflectionUtils.getGenericTypeParameter(typeField);
			if (clazzParameter != null) {
				nameForeignKey = toLowerUnderscore(clazzParameter.getSimpleName()) + "_id";
			}
		} else {
			nameForeignKey = toLowerUnderscore(field.getType().getSimpleName()) + "_id";
		}
		return nameForeignKey;
	}

	public static boolean isColumn(Field field) {
		if (isTrasient(field)) {
			return false;
		}

		if (isStatic(field)) {
			return false;
		}

		if (isValidColumnType(field)) {
			return true;
		}

		if (isForeignKey(field)) {
			return true;
		}

		return true;
	}

	public static String listToStringConcatenated(List<String> list, String caracterToConcat) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (String item : list) {
			builder.append(item);

			if (i < list.size() - 1) {
				builder.append(caracterToConcat);
			}
			i++;
		}
		return builder.toString();
	}

	public static String createStringWithInterrogators(int numberIterations) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numberIterations; i++) {
			builder.append("?");
			if (i < numberIterations - 1) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}

	public static String getNameTypeAndRestrictionsColumn(Field field) {
		StringBuilder builder = new StringBuilder();
		String name = getColumnName(field);
		String type = getColumnType(field);
		builder.append(name);
		builder.append(" ").append(type);

		if (isPrimaryKey(field)) {
			builder.append(" ").append(CLAUSULE_PRIMARY_KEY);
			builder.append(" ").append(CLAUSULE_NOT_NULL);
		} else if (isColumnNotNull(field)) {
			builder.append(" ").append(CLAUSULE_NOT_NULL);
		}

		return builder.toString();
	}

	public static String getColumnSize(Field field) {
		final String EMPTY_SIZE = "";
		Column column = field.getAnnotation(Column.class);
		if (containsExplictColumnSize(column)) {
			return "(" + column.length() + ")";
		}
		return EMPTY_SIZE;
	}

	private static boolean containsExplictColumnSize(Column column) {
		final int DEFAULT_SIZE_INVALID_FOR_COLUMN = -1;
		return column != null && column.length() != DEFAULT_SIZE_INVALID_FOR_COLUMN;
	}

	public static boolean tableContainsForeignKey(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			if (isForeignKey(field)) {
				return true;
			}
		}
		return false;
	}

	public static String getPrimaryKeyColumnName(Class<?> type) {
		Field field = getFieldPrimaryKey(type);
		return getColumnName(field);
	}

	public static Field getFieldPrimaryKey(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			if (isPrimaryKey(field)) {
				return field;
			}
		}
		throw new IllegalStateException("Type " + type.getName() + " don't haves PRIMARY KEY.");
	}

	@SuppressWarnings("unchecked")
	public static List<Field> getFieldsWithAnnotation(Class<?> type, Class<? extends Annotation> annotationClass) {
		return getFieldsWithAnyAnnotation(type, annotationClass);
	}

	public static List<Field> getFieldsWithAnyAnnotation(Class<?> type, Class<? extends Annotation>... annotationsClass) {
		Field[] fields = type.getDeclaredFields();
		List<Field> fieldsWithAnnotation = new ArrayList<Field>();
		for (Field field : fields) {
			if (fieldContainsAnyAnnotation(field, annotationsClass)) {
				fieldsWithAnnotation.add(field);
			}
		}

		return fieldsWithAnnotation;
	}

	public static boolean isTrasient(Field field) {
		return fieldContainsAnnotation(field, Transient.class);
	}

	public static boolean isStatic(Field field) {
		return Modifier.isStatic(field.getModifiers());
	}

	public static boolean isPrimaryKey(Field field) {
		return fieldContainsAnnotation(field, Id.class);
	}

	@SuppressWarnings("unchecked")
	public static boolean isForeignKey(Field field) {
		return fieldContainsAnyAnnotation(field, OneToOne.class, ManyToOne.class);
	}

	public static boolean isColumnNotNull(Field field) {
		Column column = field.getAnnotation(Column.class);
		return isColumnNotNull(column);
	}

	private static boolean isColumnNotNull(Column column) {
		return column != null && !column.nullable();
	}

	public static boolean fieldContainsAnnotation(Field field, Class<? extends Annotation> annotation) {
		return field.getAnnotation(annotation) != null;
	}

	public static boolean fieldContainsAnyAnnotation(Field field, Class<? extends Annotation>... annotations) {
		for (Class<? extends Annotation> type : annotations) {
			if (field.getAnnotation(type) != null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidColumnType(Field field) {
		return EnumColumnTypes.containsWithClass(field.getType());
	}

	public static boolean isCollectionType(Field field) {
		return collectionTypes.contains(field.getType());
	}

	private static String toLowerUnderscore(String name) {
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
	}

}
