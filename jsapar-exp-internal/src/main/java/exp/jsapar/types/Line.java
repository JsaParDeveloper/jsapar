package exp.jsapar.types;

import java.io.Serializable;
import java.util.List;

public class Line<S> implements Serializable {
	private static final long serialVersionUID = 3938267479847599665L;
	private String name;
	private List<S> cells;

	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
	public void setCells(List<S> cells) {
		this.cells = cells;
	}
	
	public List<S> getCells() {
		return cells;
	}
	
	
}
