package exp.jsapar.types;

import java.io.Serializable;
import java.util.List;

public class Document<L> implements Serializable {
	private static final long serialVersionUID = -7670157623198896076L;

	private List<L> lines;

	public void setLines(List<L> lines) {
		this.lines = lines;
	}

	public List<L> getLines() {
		return lines;
	}
}
