package brx.com.tigerbuilder.builder;

import org.apache.commons.lang3.StringUtils;

import brx.com.tigerbuilder.builder.enums.JoinTypesEnum;

public class Join {

	private String table;
	private String alias;
	private JoinTypesEnum joinType;
	
	public Join(String table, JoinTypesEnum joinType, String alias) {
		this.table = table;
		this.joinType = joinType;
		this.alias = alias;
	}
	
	public static Join create(String table, JoinTypesEnum joinType, String alias) {
		return new Join(table, joinType, alias);
	}
	
	public static Join create(String table, String alias) {
		return new Join(table, null, alias);
	}
	
	public static Join create(String table, JoinTypesEnum joinType) {
		return new Join(table, joinType, null);
	}
	
	public static Join create(String table) {
		return new Join(table, null, null);
	}
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public JoinTypesEnum getJoinType() {
		return joinType;
	}
	public void setJoinType(JoinTypesEnum joinType) {
		this.joinType = joinType;
	}

	@Override
	public String toString() {
		String join = getJoinType().getSqlClause() + " ";
		join += getTable() + " ";
		
		if (StringUtils.isNotEmpty(getAlias())) {
			join += " as " + getAlias() + " ";
		}
		
		return join;
	}
}
