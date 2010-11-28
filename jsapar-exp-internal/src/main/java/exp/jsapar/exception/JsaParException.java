package exp.jsapar.exception;

/**
 * See official branch for 2.0 with correct implementation!
 * 
 * @author JsaPar Developer
 */
public class JsaParException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5742344919500817567L;

	/**
	 * 
	 * @param explanation
	 */
	public JsaParException(String explanation) {
		super(explanation);
	}
}
