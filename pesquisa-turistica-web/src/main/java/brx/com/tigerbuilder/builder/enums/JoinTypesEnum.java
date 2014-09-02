package brx.com.tigerbuilder.builder.enums;

public enum JoinTypesEnum {

	INNER("inner join"),
	LEFT("left join"),
	RIGTH("rigth join");
	
	private JoinTypesEnum(String sqlClause) {
		this.sqlClause = sqlClause;
	}
	
	private String sqlClause;
	
	public String getSqlClause() {
		return sqlClause;
	}
	
}
