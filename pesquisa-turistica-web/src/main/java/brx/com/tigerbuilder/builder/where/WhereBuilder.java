package brx.com.tigerbuilder.builder.where;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ady Junior - 03/09/2014
 *
 */
public class WhereBuilder {

	private Class<?> type;
	private boolean formatter = false;
	private List<String> statemests = new ArrayList<String>();

	private WhereBuilder(Class<?> type) {
		this.type = type;
	}

	public static WhereBuilder forClass(Class<?> type) {
		WhereBuilder update = new WhereBuilder(type);
		return update;
	}
	
	private void add(String statement) {
		
	}
	
}
