package brx.com.tigerbuilder.builder.enums;

import java.util.Date;

public enum RestrictionByType {

	STRING(String.class, "'%s'"),
	INTEGER(Integer.class, "%d"),
	DOUBLE(Double.class, "%f"),
	DATE(Date.class, "%s"),
	DEFAULT(Object.class, "%s");
	
	private Class<?> type;
	private String pattern;
	
	private RestrictionByType(Class<?> type, String pattern) {
		this.type = type;
		this.pattern = pattern;
	}
	
	public static RestrictionByType fromType(Class<?> typeParam) {
		for (RestrictionByType item : values()) {
			if(item.getType().getName().equals(typeParam.getName())) {
				return item;
			}
		}
		return DEFAULT;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public String convertSqlPatternValue(String value) {
		return String.format(this.pattern, value);
	}
	
}
