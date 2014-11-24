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
	
	private String sqlSequence = "";

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
		
		query.append("\n");
		query.append("\n");

		if (UtilVilaQueryReflection.tableContainsForeignKey(type)) {
			query.append(createScriptAllForeignKey());
		}
		
		query.append(sqlSequence);

		return query.toString();
	}

	@SuppressWarnings("unchecked")
	private String createScriptAllForeignKey() {
		List<Field> fields = UtilVilaQueryReflection.getFieldsWithAnyAnnotation(type, ManyToOne.class, OneToOne.class);
		StringBuilder script = new StringBuilder();

		for (Field fieldForeignKey : fields) {
			script.append(createScriptForeignKey(fieldForeignKey));
		}
		
		script.append("\n");
		script.append("\n");
		return script.toString();
	}

	public CreateBuilder createPostgresSequence() {
		String tableName = UtilVilaQueryReflection.getTableName(type);
		String columnPrimaryKey = UtilVilaQueryReflection.getPrimaryKeyColumnName(type);
		String nameSequence = tableName + "_" + columnPrimaryKey + "_seq";
		
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE SEQUENCE ").append(nameSequence).append(" ");
		formatter(sql);
		sql.append("INCREMENT 1").append(" ");
		formatter(sql);
		sql.append("MINVALUE 1").append(" ");
		formatter(sql);
		sql.append("MAXVALUE 9223372036854775807").append(" ");
		formatter(sql);
		sql.append("START 1").append(" ");
		formatter(sql);
		sql.append("CACHE 1;").append(" ");
		sql.append("\n");
		sql.append("\n");
		
		sql.append("ALTER TABLE ").append(tableName).append(" ");
		formatter(sql);
		sql.append("ALTER COLUMN ").append(columnPrimaryKey).append(" ");
		formatter(sql);
		sql.append("SET DEFAULT NEXTVAL('").append(nameSequence).append("'::regclass);");
		sql.append("\n");
		
		sqlSequence = sql.toString();
		
		return this;
	}
	
	private String createScriptForeignKey(Field fieldForeignKey) {
//		ALTER TABLE Orders
//		ADD CONSTRAINT fk_PerOrders
//		FOREIGN KEY (P_Id)
//		REFERENCES Persons(P_Id)
		
		StringBuilder script = new StringBuilder();
		String tableName = UtilVilaQueryReflection.getTableName(type);
		Field fieldPrimaryKey = UtilVilaQueryReflection.getFieldPrimaryKey(fieldForeignKey.getType());
		String primaryKeyName = UtilVilaQueryReflection.getColumnName(fieldPrimaryKey);
		String foreignKeyColumnName = UtilVilaQueryReflection.getForeignKeyColumnName(fieldForeignKey);
		String constraintforeignKeyName = "fk_" + foreignKeyColumnName;
		String tableForeignKeyName = UtilVilaQueryReflection.getTableName(fieldForeignKey.getType());

		script.append("alter table ").append(tableName).append(" ");
		script.append("add constraint ").append(constraintforeignKeyName).append(" ");
		script.append("foreign key").append(" (");
		script.append(foreignKeyColumnName).append(") ");
		script.append("references ").append(tableForeignKeyName);
		script.append("(").append(primaryKeyName).append(");\n");

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
