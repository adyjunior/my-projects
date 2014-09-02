package brx.com.tigerbuilder.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import brx.com.tigerbuilder.builder.enums.JoinTypesEnum;
import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

public class SelectBuilder {

	private StringBuilder sql = new StringBuilder();
	private Class<?> type;
	private String aliasType;
	private List<Projection> projections = new ArrayList<Projection>();
	private List<Join> joins = new ArrayList<Join>();
	
	private SelectBuilder(Class<?> type, String alias) {
		this.type = type;
		this.aliasType = alias;
	}
	
	public static SelectBuilder newInstance(Class<?> type, String alias) {
		return new SelectBuilder(type, alias);
	}
	
	public static SelectBuilder newInstance(Class<?> type) {
		return newInstance(type, null);
	}
	
	/**
	 * Create
	 * 
	 */
	public SelectBuilder addProjection(Projection projection) {
		if (projection != null && projection.isValidProjection()) {
			projections.add(projection);
		}
		return this;
	}
	
	public SelectBuilder join(Class<?> typeClass, String alias) {
		String tableName = UtilVilaQueryReflection.getTableName(typeClass);
		joins.add(Join.create(tableName, JoinTypesEnum.INNER, alias));
		return this;
	}
	
	public SelectBuilder join(Class<?> typeClass) {
		join(typeClass, null);
		return this;
	}
	
	/**
	 * Validate
	 */
	
	private boolean containsProjections() {
		return CollectionUtils.isNotEmpty(projections);
	}
	
	
	/**
	 * Format
	 */
	private String formatProjections() {
		StringBuilder sqlProjection = new StringBuilder();
		int index = 0;
		for (Projection p : projections) {
			
			if (index != 0) {
				sqlProjection.append(", ");
			}
			
			sqlProjection.append(p.toString());
			
			index++;
		}
		
		return sqlProjection.toString();
	}
	
	private String formatJoins() {
		StringBuilder sqlJoins = new StringBuilder();
		for (Join p : joins) {
			
			sqlJoins.append(p.toString());
			
		}
		
		return sqlJoins.toString();
	}
	
	@Override
	public String toString() {
		String tableName = UtilVilaQueryReflection.getTableName(type, aliasType);
		sql.append("select ");
		
		if (containsProjections()) {
			sql.append(formatProjections()).append(" ");
		} else {
			sql.append(" * ");
		}
		
		sql.append(" from ");
		sql.append(tableName).append(" ");
		
		sql.append(formatJoins()).append(" ");
		
		return sql.toString();
	}
	
//	public static void main(String[] args) {
//		String x = SelectBuilder.newInstance(Task.class, "t")
//			.addProjection(Projection.property("nome"))
//			.addProjection(Projection.property("telefone"))
//			.addProjection(Projection.property("email"))
//			.addProjection(Projection.property("estsaude").addAllColumns())
//			.join(EstabelecimentoSaude.class, "estsaude")
//			.toString();
//	}
	
}
