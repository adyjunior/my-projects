package brx.com.tigerbuilder.builder;

import org.apache.commons.lang3.StringUtils;

public class Projection {

	private String property;
	private boolean forAllColmns;
	
	private Projection() {
	}

	public Projection addAllColumns() {
		forAllColmns = true;
		return this;
	}
	
	public static Projection property(String property) {
		Projection projection = new Projection();
		projection.setProperty(property);
		return projection;
	}
	
	public boolean isValidProjection() {
		return StringUtils.isNotBlank(property);
	}
	
	@Override
	public String toString() {
		String format = property;
		if (isForAllColmns()) {
			format += ".*";
		}
		
		return format;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	public boolean isForAllColmns() {
		return forAllColmns;
	}

	public String getProperty() {
		return property;
	}
	
}
