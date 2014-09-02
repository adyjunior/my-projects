package brx.com.tigerbuilder.enums;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.util.Date;

/**
 * @author Ady Junior - 23/05/2014
 *
 */
public enum EnumColumnTypes {

	STRING(String.class, Types.VARCHAR, "VARCHAR"), 
	INTEGER(Integer.class, Types.INTEGER, "INTEGER"), 
	LONG(Long.class, Types.BIGINT, "BIGINT"), 
	DATE(Date.class, Types.DATE, "TIMESTAMP"),
	BOOLEAN(Boolean.class, Types.BOOLEAN, "BOOLEAN"), 
	BOOLEAN_CORE(boolean.class, Types.BOOLEAN, "BOOLEAN"), 
	DOUBLE(Double.class, Types.DOUBLE, "DOUBLE"), 
	BIGDECIMAL(BigDecimal.class, Types.REAL, "REAL"), 
	FLOAT(Float.class, Types.FLOAT, "FLOAT"), 
	CHARACTER(Character.class, Types.CHAR, "CHAR"), 
	NUMBER(Number.class, Types.REAL, "REAL"), 
	SHORT(Short.class, Types.SMALLINT, "SMALLINT"), 
	BYTE(Byte.class, Types.SMALLINT, "SMALLINT"), 
	BIGINTEGER(BigInteger.class, Types.INTEGER, "INTEGER");

	private Class<?> typeClass;
	private int typeSql;
	private String nameType;

	private EnumColumnTypes(Class<?> typeClass, int typeSql, String nameType) {
		this.typeClass = typeClass;
		this.typeSql = typeSql;
		this.nameType = nameType;
	}

	public static EnumColumnTypes getFromClass(Class<?> type) {
		for (EnumColumnTypes item : values()) {
			if (item.getTypeClass().equals(type)) {
				return item;
			}
		}
		return null;
	}

	public static boolean containsWithClass(Class<?> type) {
		return getFromClass(type) == null;
	}

	public Class<?> getTypeClass() {
		return typeClass;
	}

	public int getTypeSql() {
		return typeSql;
	}

	public String getNameType() {
		return nameType;
	}
}
