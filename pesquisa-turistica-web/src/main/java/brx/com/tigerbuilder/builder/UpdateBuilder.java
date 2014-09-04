package brx.com.tigerbuilder.builder;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

/**
 * @author Ady Junior - 08/05/2014
 *
 */
public class UpdateBuilder {

	private Class<?> type;
	private String whereClausule;
	private boolean formatter = false;

	private UpdateBuilder(Class<?> type) {
		this.type = type;
	}

	public static UpdateBuilder forClass(Class<?> type) {
		UpdateBuilder update = new UpdateBuilder(type);
		return update;
	}

	private boolean containsWhereClausule() {
		return StringUtils.isNotBlank(whereClausule);
	}

	public UpdateBuilder format() {
		formatter = true;
		return this;
	}

	private String formatter(String query) {
		return formatter ? query += "\n" : query;
	}

	public UpdateBuilder addWhere(String whereClausule) {
		this.whereClausule = whereClausule;
		return this;
	}

	public String toString() {
		String patternConcat = formatter("=?, ");

		List<String> fieldNames = UtilVilaQueryReflection.listColumnsForSqlQuery(type);
		String metaData = UtilVilaQueryReflection.listToStringConcatenated(fieldNames, patternConcat);
		metaData += "=?";
		String tableName = UtilVilaQueryReflection.getTableName(type);

		StringBuilder query = new StringBuilder();
		query.append("update ");
		query.append(tableName).append(" ");
		query.append("set ");
		query.append(metaData);

		if (containsWhereClausule()) {
			query.append(" where ");
			query.append(whereClausule);
		}

		return query.toString();
	}

}
