package org.jsapar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The tool box of the JsaPar tools: Parser, Converter, Composer and Splitter.<br>
 * <br>
 * Note: This class is not part of the JsaPar API and should remain package-private.
 * 
 * @author JsaPar Developer
 */
class ToolboxImpl implements Toolbox {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(ToolboxImpl.class);
    /**
     * The reference to the JsaPar configuration object.
     */
    private static ConfigRef configRef = null;

    /**
     * Private constructor prevents instantiation from other classes.
     */
    @SuppressWarnings("unused")
    private ToolboxImpl() {
        // Intentionally left blank.
    }

    /**
     * Package-private constructor prevents instantiation from external classes.
     */
    ToolboxImpl(final ConfigRef configuration) {
        configRef = configuration;
        log.trace("Instantiated the Toolbox.");
    }

    @Override
    public Parser getParser() {
        return new ParserImpl(configRef);
    }

    @Override
    public Converter getConverter() {
        Parser parserInstance = new ParserImpl(configRef);
        Composer composerInstance = new ComposerImpl(configRef);
        return new ConverterImpl(parserInstance, composerInstance);
    }

    @Override
    public Composer getComposer() {
        return new ComposerImpl(configRef);
    }

    @Override
    public Splitter getSplitter() {
        Parser parserInstance = new ParserImpl(configRef);
        Composer composerInstance = new ComposerImpl(configRef);
        return new SplitterImpl(parserInstance, composerInstance);
    }
}