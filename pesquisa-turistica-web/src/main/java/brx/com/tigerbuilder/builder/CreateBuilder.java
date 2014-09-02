package brx.com.tigerbuilder.builder;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import brx.com.tigerbuilder.util.UtilVilaQueryReflection;

/**
 * @author Ady Junior - 22/05/2014
 * 
 */
public class CreateBuilder {

	private Class<?> type;
	private boolean formatter = false;

	private CreateBuilder(Class<?> type) {
		this.type = type;
	}

	public static CreateBuilder newInstance(Class<?> type) {
		CreateBuilder insert = new CreateBuilder(type);
		return insert;
	}
	
	public CreateBuilder format() {
		formatter = true;
		return this;
	}
	
	private void formatter(StringBuilder query) {
		if (formatter) {
			query.append("\n");
		}
	}

	public String toString() {
		String tableName = UtilVilaQueryReflection.getTableName(type);

		String scriptColumns = createScriptColumns();

		StringBuilder query = new StringBuilder();
		query.append("create table ");
		query.append(tableName);
		query.append("(");
		formatter(query);
		query.append(scriptColumns);
		formatter(query);
		query.append(");");

		if (UtilVilaQueryReflection.tableContainsForeignKey(type)) {
			query.append(createScriptAllForeignKey());
		}

		return query.toString();
	}

	@SuppressWarnings("unchecked")
	private String createScriptAllForeignKey() {
		List<Field> fields = UtilVilaQueryReflection.getFieldsWithAnyAnnotation(type, ManyToOne.class, OneToOne.class);
		StringBuilder script = new StringBuilder("\n");

		int index = 0;
		for (Field fieldForeignKey : fields) {
			if(index != 0) {
				script.append(", ");
				formatter(script);
			}
			
			script.append(createScriptForeignKey(fieldForeignKey));
			index++;
		}
		
		return script.toString();
	}

	private String createScriptForeignKey(Field fieldForeignKey) {
		StringBuilder script = new StringBuilder();
		Field fieldPrimaryKey = UtilVilaQueryReflection.getFieldPrimaryKey(fieldForeignKey.getType());
		String namePrimaryKey = UtilVilaQueryReflection.getColumnName(fieldPrimaryKey);

		script.append("foreign key").append(" (");
		script.append(UtilVilaQueryReflection.getForeignKeyColumnName(fieldForeignKey)).append(") ");
		script.append("references ").append(UtilVilaQueryReflection.getTableName(fieldForeignKey.getType()));
		script.append("(").append(namePrimaryKey).append(")");

		// FOREIGN KEY (prat_esta_id)REFERENCES estante (esta_id)
		return script.toString();
	}

	private String createScriptColumns() {
		Field[] fields = type.getDeclaredFields();
		StringBuilder script = new StringBuilder();
		int i = 0;
		for (Field field : fields) {
			if (UtilVilaQueryReflection.isColumn(field)) {
				String clausuleColumn = UtilVilaQueryReflection.getNameTypeAndRestrictionsColumn(field);
				String columnSize = UtilVilaQueryReflection.getColumnSize(field);
				if (i != 0) {
					script.append(", ");
					formatter(script);
				}
				script.append(clausuleColumn);
				script.append(columnSize);
				i++;
			}
		}
		return script.toString();
	}

}
