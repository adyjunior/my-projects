package brx.com.tigerbuilder.builder;

import java.util.List;

import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

/**
 * @author Ady Junior - 08/05/2014
 *
 */
public class InsertBuilder {

	private Class<?> type;

	private InsertBuilder(Class<?> type) {
		this.type = type;
	}

	public static InsertBuilder newInstance(Class<?> type) {
		InsertBuilder insert = new InsertBuilder(type);
		return insert;
	}

	public String toString() {
		List<String> fieldNames = UtilVilaQueryReflection.listColumnsForInsertSqlQuery(type);
		String metaData = UtilVilaQueryReflection.listToStringConcatenated(fieldNames, ", ");
		String interrogations = UtilVilaQueryReflection.createStringWithInterrogators(fieldNames.size());
		String tableName = UtilVilaQueryReflection.getTableName(type);

		StringBuilder query = new StringBuilder();
		query.append("insert into ");
		query.append(tableName);
		query.append("(");
		query.append(metaData);
		query.append(") values (");
		query.append(interrogations);
		query.append(")");

		return query.toString();
	}

}
