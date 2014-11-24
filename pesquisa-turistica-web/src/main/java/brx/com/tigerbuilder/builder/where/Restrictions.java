package brx.com.tigerbuilder.builder.where;

import java.util.Date;

import br.com.ptw.util.DataUtil;
import brx.com.tigerbuilder.builder.enums.RestrictionByType;

/**
 * @author Ady Junior - 03/09/2014
 *
 */
public class Restrictions {

	private static String objectTostring(Object value) {
		String valueString = "";
		if (value instanceof Date) {
			valueString = DataUtil.dataToString((Date) value, DataUtil.DATE_PATTERN_DEFAULT);
		} else{
			valueString = String.valueOf(value);
		}
		
		String formattedValue = RestrictionByType.fromType(value.getClass()).convertSqlPatternValue(valueString);
		
		return formattedValue;
	}
	
	public static String eq(String column, Object value) {
		String formattedValue = objectTostring(value);
		return column + " = " + formattedValue;
	}
	
	public static String eqProperty(String leftColumn, String rightColumn) {
		return leftColumn + " = " + rightColumn; 
	}
	
	
	public static String notEq(String column, String value) {
		String formattedValue = objectTostring(value);
		return column + " <> " + formattedValue; 
	}
	
	public static String notEqProperty(String leftColumn, String rightColumn) {
		return leftColumn + " <> " + rightColumn; 
	}
	
	public static String greaterThan(String column, String value) {
		String formattedValue = objectTostring(value);
		return column + " > " + formattedValue; 
	}
	
	public static String greaterThanProperty(String leftColumn, String rightColumn) {
		return leftColumn + " > " + rightColumn; 
	}
	
	public static String lessThan(String column, String value) {
		String formattedValue = objectTostring(value);
		return column + " < " + formattedValue; 
	}
	
	public static String lessThanProperty(String leftColumn, String rightColumn) {
		return leftColumn + " < " + rightColumn; 
	}
	
	public static String between(String column, Object leftValue, Object rightValue) {
		String formattedValueLeft = objectTostring(leftValue);
		String formattedValueRight = objectTostring(rightValue);
		return column + " between " + formattedValueLeft + " and " + formattedValueRight;
	}
	
	public static String like(String column, String value) {
		return column + " like " + value;
	}
	
	
}
