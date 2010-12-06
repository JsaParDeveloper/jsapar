package org.jsapar;

/**
 * The interface for the JsaPar tool box, with handles to all the available tools.
 * 
 * @author JsaPar Developer
 */
public interface Toolbox {

    /**
     * Returns a Parser tool.
     * 
     * @return a Parser tool.
     */
    public Parser getParser();

    /**
     * Returns a Converter tool.
     * 
     * @return a Converter tool.
     */
    public Converter getConverter();

    /**
     * Returns a Composer tool.
     * 
     * @return a Composer tool.
     */
    public Composer getComposer();
    
    /**
     * Returns a Splitter tool.
     * 
     * @return a Splitter tool.
     */
    public Splitter getSplitter();
}
