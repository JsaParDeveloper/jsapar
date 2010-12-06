package org.jsapar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jsapar.exceptions.JsaParException;
import org.jsapar.exceptions.internal.DuplicateSchemasInListException;
import org.jsapar.exceptions.internal.InvalidJsaParConfigFileException;
import org.jsapar.exceptions.internal.InvalidXmlException;
import org.jsapar.schema.JsaParSchema;
import org.jsapar.schema.cfg.Jsaparsettings;
import org.jsapar.schema.cfg.SchemaSource;
import org.jsapar.schema.cfg.SchemasType;
import org.jsapar.utils.ReaderInputStream;
import org.jsapar.utils.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The configuration of the JsaPar library, containing the default configurations or configurations
 * loaded from an external configuration file. This class loads it's configuration details from the
 * configuration file called jsapar.cfg.xml.<br>
 * The default values are stored in a configuration file that is located in the 'cfg' directory
 * within JsaPar Library jar.<br>
 * User specific values are stored in an external configuration file, located on the class path.
 * When available, the external configuration file overrides the internal configuration file.
 * 
 * @author JsaPar Developer
 */
public class Configuration implements ConfigRef {
    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(Configuration.class);
    /**
     * TODO
     */
    private static Configuration configuration = null;
    /**
     * Indicates whether the library is configured or not.
     */
    private boolean configured = false;
    /**
     * The path of the internal XML configuration file and XML schema file.
     */
    private static final String JSAPAR_CONFIG_PATH = "/cfg/";
    /**
     * The name of the JsaPar XML schema file where the configuration file is validated against.
     */
    private static final String JSAPAR_CONFIG_SCHEMA = "jsapar.cfg.xsd";
    /**
     * The name of the JsaPar XML configuration file that the user can include on the class path. If
     * the user does not specify such file, the default XML configuration file within the jsapar
     * library jar is loaded instead.
     */
    private static final String JSAPAR_CONFIG_DOCUMENT = "jsapar.cfg.xml";
    /**
     * The path of the JsaPar XML schema file.
     */
    private static final String JSAPAR_SCHEMA_PATH = "/doc/";
    /**
     * The name of the JsaPar XML schema file where the configuration file is validated against.
     */
    private static final String JSAPAR_SCHEMA_SCHEMA = "jsapar.cfg.xsd";
    /**
     * The loaded jsapar XML schema file as an {@link InputStream}.
     */
    private InputStream jsaparSchema = null;
    /**
     * The loaded jsapar XML configuration file as an {@link InputStream}.
     */
    private InputStream jsaparConfig = null;
    /**
     * The actual settings of the JsaPar library as an object representation.
     */
    private Jsaparsettings jsaparsettings = null;

    /**
     * The tool box containing the JsaPar tools.
     */
    private ToolboxImpl toolbox = null;

    /**
     * Private constructor prevents instantiation from other classes.<br>
     * This is done because the configuration class may only be instantiated ONCE in order to
     * guarantee that the number of tools are managed by only one Toolbox object.
     */
    private Configuration() {
        this.toolbox = new ToolboxImpl(this);
        // TODO load resource bundle for errors
    }

    /**
     * Returns the configuration object for the JsaPar library.<br>
     * 
     * Only ONE instance of the configuration is handed out to the client code.
     * 
     * @return the configuration of the JsaPar library.
     */
    public static Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
        }
        return configuration;
    }

    /**
     * Configures the JsaPar library with the default values that are defined in the jsapar.cfg.xml
     * configuration file.
     * 
     * @throws JsaParException
     *             thrown when the library configuration was not successful.
     */
    public Configuration configure() throws JsaParException {
        if (!configured) {
            try {
                loadJsaParSchemaFile();
                loadJsaParConfigFile();
                validateJsaParConfigFile();
                readJsaParConfigFile();
                detectAndRemoveDuplicateSchemaSources();
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
                throw new JsaParException(e);
            } catch (InvalidJsaParConfigFileException e) {
                log.error(e.getMessage());
                throw new JsaParException(e);
            } catch (DuplicateSchemasInListException e) {
                log.error(e.getMessage());
                throw new JsaParException(e);
            } catch (JAXBException e) {
                log.error(e.getMessage());
                throw new JsaParException(e);
            }
            configured = true;
        }
        return this;
    }

    /**
     * Configures the JsaPar library with the values defined within the jsapar.cfg.xml configuration
     * file, thus overriding the default values.
     * 
     * @param externalConfigFile
     *            the external jsapar.cfg.xml file to be loaded.
     * 
     * @return the current configuration (this).
     * 
     * @throws JsaParException
     *             thrown when the JsaPar library configuration was not successful.
     */
    public Configuration configure(final InputStream externalConfigFile) throws JsaParException {
        if (externalConfigFile == null) {
            log.error("External config file not specified (= NULL). Will try to load the default configuration file instead.");
            // TODO load default config file
            throw new NullPointerException("input parameter is NULL");
        } else {
            // set the given external config file as the default config file.
            jsaparConfig = externalConfigFile;
        }
        return configure();
    }

    /**
     * 
     * @param externalConfigFile
     *            the external jsapar.cfg.xml file to be loaded.
     * 
     * @return the current configuration (this).
     * 
     * @throws JsaParException
     *             thrown when the JsaPar library configuration was not successful.
     */
    public Configuration configure(final Reader externalConfigFile) throws JsaParException {
        if (externalConfigFile == null) {
            log.error("External config file not specified (= NULL). Will try to load the default configuration file instead.");
            // TODO load default config file
            throw new NullPointerException("input parameter is NULL");
        } else {
            // set the given external config file as the default config file.
            try {
                jsaparConfig = new ReaderInputStream(externalConfigFile);
            } catch (IOException e) {
                // TODO
                log.debug("Conversion from Reader to InputStream failed");
                e.printStackTrace();
            }
        }
        return configure();
    }

    /**
     * Adds the given schema source to the library configuration ONLY when the schemaName is not
     * present in the schema collection AND when the schema validation is successful.
     * 
     * @param schemaName
     *            the name under which the schema should be stored.
     * @param schemaSource
     *            the schema that needs to be added to the configuration.
     * 
     * @throws JsaParException
     *             thrown when the given JsaPar schema isn't valid.
     * 
     */
    public void addSchemaSource(final String schemaName, final String schemaLocation) throws JsaParException {
        InputStream is = Configuration.class.getResourceAsStream(schemaLocation);
        addSchemaSource(schemaName, schemaLocation, is);
    }

    /**
     * Adds the given schema source to the library configuration ONLY when the schemaName is not
     * present in the schema collection AND when the schema validation is successful.
     * 
     * @param schemaName
     *            the name under which the schema should be stored.
     * @param schemaFile
     *            the schema that needs to be added to the configuration.
     * 
     * @throws JsaParException
     *             thrown when the given JsaPar schema isn't valid.
     */
    public void addSchemaSource(final String schemaName, final File schemaFile) throws JsaParException {
        InputStream is = null;
        String schemaLocation = schemaFile.getPath(); // FIXME is this correct?
        try {
            is = new FileInputStream(schemaFile);
        } catch (FileNotFoundException e) {
            throw new JsaParException(e);
        }
        addSchemaSource(schemaName, schemaLocation, is);
    }

    /**
     * Adds the given schema source to the library configuration ONLY when the schemaName is not
     * present in the schema collection AND when the schema validation is successful.
     * 
     * @param schemaName
     *            the name under which the schema should be stored.
     * @param schemaUri
     *            the schema that needs to be added to the configuration.
     * 
     * @throws JsaParException
     *             thrown when the given JsaPar schema isn't valid.
     */
    public void addSchemaSource(final String schemaName, final URI schemaUri) throws JsaParException {
        InputStream is = null;
        String schemaLocation = null;

        try {
            is = schemaUri.toURL().openStream();

        } catch (MalformedURLException e) {
            throw new JsaParException(e);
        } catch (IOException e) {
            throw new JsaParException(e);
        }
        addSchemaSource(schemaName, schemaLocation, is);
    }

    /**
     * Adds a bunch of schema sources all at once to the library configuration. ONLY when the
     * schemaNames are not currently present in the schema collection AND when the schema
     * validations are successful.
     * 
     * @param schemaSources
     *            a map holding the schema sources with their respective names and locations.
     * @throws JsaParException
     *             thrown when the given JsaPar schema isn't valid.
     */
    public void addSchemaSources(final Map<String, String> schemaSources) throws JsaParException {

        log.trace("Looping through the given schema sources...");
        for (Map.Entry<String, String> entry : schemaSources.entrySet()) {
            log.trace("Schema name: " + entry.getKey() + "/ schema location: " + entry.getValue()
                    + " is being investigated.");

            addSchemaSource(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public JsaParSchema loadJsaParSchema(final String name) throws JsaParException {
        if ((name == null) || (name.isEmpty())) {
            throw new JsaParException("Name is null or empty");
        }
        // look up schema with this name

        // TODO load the schema with schemaName from the configuration and set
        // this schema as the current schema to be used by the parser.

        // validation of the schema should be done when adding it to the configuration!

        // TODO get the schema source with 'name' from list
        // read the schema from the location
        // make an JsaParSchema instance of it with the name and location from
        // the schemaSource object in it.

        // this method is called by the .setup() methods!

        // TODO setup 1 or 2 schema files: converter needs 2!!!
        // so we need a list with more than one JsaParSchema's in it.
        // TODO question: should we load the JsaParSchema's directly from the
        // configure() moment or later on, when requested for the first time?
        // Using configure() we know for sure these sources are fine -> no dupes
        // and schema's are validated
        // Using the load-when-requested option, we need to make sure the
        // location is still valid and the content is
        // still valid xml
        // Loading at startup is okay, but uses more memory

        // TODO fetch from List<JsaParSchema> or should we call it Map<String,
        // Schema>
        // actually what we need is a Schema defined by a name.
        // How do we keep track of the name/location vs schema combinations?

        return null;
    }

    /**
     * Returns the tool box with the JsaPar tools: Parser, Converter, Composer, Splitter.
     * 
     * @return the tool box containing the tools.
     */
    public Toolbox getToolbox() {
        return this.toolbox;
    }

    /*
     * TODO implement jsaparsettings.getErrorhandling().getParser();
     * jsaparsettings.getErrorhandling().getComposer(); Not for converter because Converter = Parser
     * + Composer.
     */

    /**
     * TODO
     * 
     * @param schemaName
     * @param schemaLocation
     * @param jsaParSchema
     * @throws JsaParException
     *             thrown when the given JsaPar schema isn't valid.
     */
    private void addSchemaSource(final String schemaName, final String schemaLocation, final InputStream jsaParSchema)
            throws JsaParException {

        if (jsaParSchema == null) {
            log.trace("JsaPar Schema was NULL. The schema was NOT added to the configuration.");
            throw new JsaParException("JsaPar Schema was NULL.");
        }

        InputStream jsaParSchemaSchema = Configuration.class.getResourceAsStream(JSAPAR_SCHEMA_PATH
                + JSAPAR_SCHEMA_SCHEMA);
        try {
            XmlUtil.validateXML(jsaParSchema, jsaParSchemaSchema);
            // if no exceptions where thrown, set the schemaSource and
            // add it to the configuration.
            SchemaSource schemaSource = new SchemaSource();
            schemaSource.setName(schemaName);
            schemaSource.setLocation(schemaLocation);
            addSchemaSourceToSchemaSourceList(schemaSource);

        } catch (InvalidXmlException e) {
            jsaParSchemaSchema = null;
            log.trace("No valid JsaPar Schema. The schema was NOT added to the configuration.");
            throw new JsaParException(e);
        }
    }

    /**
     * Adds the schema source to the list of schema sources within the JsaPar configuration ONLY if
     * the name and location are not duplicated in the list.
     * 
     * @param schemaSource
     *            the schema source to be added.
     */
    private void addSchemaSourceToSchemaSourceList(final SchemaSource schemaSource) {

        // TODO check this method carefully!

        if (jsaparsettings == null) {
            log.error("The JsaPar library was not yet configured. Could not add the SchemaSource with name: "
                    + schemaSource.getName() + ".");
        }

        // check for duplicate names and locations.
        if (detectDuplicateSchemaSource(schemaSource.getName(), schemaSource.getLocation())) {
            // duplicate found, do not add to configuration
            // TODO throw an exception? or continue with the other entries
            // first?
            log.error("Duplicate found in SchemaSource list. The schema source with name: " + schemaSource.getName()
                    + " will not be added to the list");
        } else {
            // no duplicate found, add to the configuration.
            jsaparsettings.getSchemas().getSchema().add(schemaSource);
            log.trace("SchemaSource with name: " + schemaSource.getName() + " added to the list.");
        }
    }

    /**
     * TODO
     */
    private boolean detectDuplicateSchemaSource(final String schemaName, final String schemaSource) {
        // TODO find out if list is duplicate or not. What to do with the
        // name/location combination?
        return false;
    }

    /**
     * Detects and safely removes duplicate SchemaSource objects.<br>
     * <br>
     * These are the possible duplicate situations:
     * <ul>
     * <li>Same names, different locations: throw an exception.
     * <li>Same locations, different names: throw an exception.
     * <li>Same names, same locations: safely remove the duplicates!
     * </ul>
     * The JsaPar library does not decide <b>for</b> the user when duplicate names or duplicate
     * locations are found in the configuration file.<br>
     * In these situations an exception is thrown to warn the user for the lack of clarity in the
     * jsapar.cfg.xml config file.
     * 
     * @throws DuplicateSchemasInListException
     *             thrown when the name in the list occurs more than once, or when the location in
     *             the list occurs more than once.
     */
    private void detectAndRemoveDuplicateSchemaSources() throws DuplicateSchemasInListException {
        boolean badDupeInList = false;
        boolean nameDuped = false;
        boolean locationDuped = false;
        List<SchemaSource> srcList = null;
        HashSet<String> nameSet = new HashSet<String>();
        HashSet<String> locationSet = new HashSet<String>();
        ListIterator<SchemaSource> listIterator;

        SchemasType st = jsaparsettings.getSchemas();
        if (st == null) {
            throw new NullPointerException("Cannot detect duplicates in list when list instance is NULL.");
        }

        srcList = st.getSchema();
        if ((srcList == null) || srcList.isEmpty()) {
            // list does not contain any SchemaSource objects, so quit here.
            return;
        }

        SchemaSource ss = null;
        listIterator = srcList.listIterator();
        while (listIterator.hasNext()) {
            ss = listIterator.next();

            // find duplicates in srcList
            nameDuped = !nameSet.add(ss.getName());
            locationDuped = !locationSet.add(ss.getLocation());

            if (nameDuped && locationDuped) {
                // Safely remove this entry from the list
                listIterator.remove();
            } else if (nameDuped) {
                log.error("Bad duplication in SchemaSource list regarding the 'name' attribute with value: "
                        + ss.getName());
                badDupeInList = true;
            } else if (locationDuped) {
                log.error("Bad duplication in SchemaSource list regarding the 'location' attribute with value: "
                        + ss.getLocation());
                badDupeInList = true;
            }
        }

        // release memory.
        srcList = null;
        nameSet = null;
        locationSet = null;
        listIterator = null;

        // throw an exception?
        if (badDupeInList) {
            throw new DuplicateSchemasInListException();
        }
    }

    /**
     * Loads the JsaPar XML Schema file (jsapar.cfg.xsd).
     * 
     * @throws FileNotFoundException
     *             thrown when the JsaPar schema file could not be found.
     */
    private void loadJsaParSchemaFile() throws FileNotFoundException {
        jsaparSchema = Configuration.class.getResourceAsStream(JSAPAR_CONFIG_PATH + JSAPAR_CONFIG_SCHEMA);

        if (jsaparSchema == null) {
            throw new FileNotFoundException("Could not find JsaPar schema file: " + JSAPAR_CONFIG_SCHEMA);
        }
    }

    /**
     * Loads the JsaPar configuration file (jsapar.cfg.xml).<br>
     * <br>
     * First checks if an (external) jsapar.cfg.xml config file is available and loads this file. If
     * there is no external config file available, it loads the internal config file (available
     * within the jar).
     * 
     * @throws FileNotFoundException
     *             thrown when the external or internal JsaPar configuration file could not be
     *             loaded.
     */
    private void loadJsaParConfigFile() throws FileNotFoundException {
        // jsapar config file already loaded outside this method?
        // see #configure(final URI externalConfigFile)
        if (jsaparConfig == null) {
            // first look at external location for jsapar config file.
            jsaparConfig = Configuration.class.getResourceAsStream("/" + JSAPAR_CONFIG_DOCUMENT);

            if (jsaparConfig == null) {
                // next, look at internal location for jsapar config file.
                jsaparConfig = Configuration.class.getResourceAsStream(JSAPAR_CONFIG_PATH + JSAPAR_CONFIG_DOCUMENT);
            }

            // verify if config file is indeed loaded.
            if (jsaparConfig == null) {
                throw new FileNotFoundException("Could not find JsaPar config file: " + JSAPAR_CONFIG_PATH
                        + JSAPAR_CONFIG_DOCUMENT);
            }
        }
    }

    /**
     * Validates the XML of the configuration file against the XML schema file.
     * 
     * @throws InvalidJsaParConfigFileException
     *             thrown when the JsaPar configuration XML file is not valid.
     */
    private void validateJsaParConfigFile() throws InvalidJsaParConfigFileException {
        try {
            XmlUtil.validateXML(jsaparConfig, jsaparSchema);
        } catch (InvalidXmlException e) {
            log.error("Invalid JsaPar configuration XML file.");
            throw new InvalidJsaParConfigFileException();
        }
    }

    /**
     * Reads the JsaPar configuration file into memory.<br>
     * <br>
     * Unmarshalles XML data from the configuration file and creates a Jsaparsettings object
     * representation of this XML file.
     * 
     * @throws JAXBException
     *             thrown when the XML data cannot be unmarshalled.
     */
    private void readJsaParConfigFile() throws JAXBException {
        JAXBContext jaxbContext = null;
        Unmarshaller unmarshaller = null;

        jaxbContext = JAXBContext.newInstance("org.jsapar.schema.cfg");
        unmarshaller = jaxbContext.createUnmarshaller();
        jsaparsettings = (Jsaparsettings) unmarshaller.unmarshal(jsaparConfig);

    }

    /**
     * TODO
     * 
     * @return
     */
    private ResourceBundle loadResourceBundle() {
        String baseName = "jsaparmsg"; // TODO make static
        try {
            // TODO see ErrorMessages.java
            // Get the resource bundle for the default locale
            ResourceBundle rb = ResourceBundle.getBundle(baseName);
            String key = "hello";
            String s = rb.getString(key); // Hello
            key = "bye";
            s = rb.getString(key); // Goodbye
            // Get the resource bundle for the current locale in the VM
            rb = ResourceBundle.getBundle(baseName, Locale.getDefault());
            key = "welcome.msg";
            s = rb.getString(key); // Bonjour
            key = "bye";
            s = rb.getString(key); // Au Revoir
        } catch (MissingResourceException e) {
            // The resource bundle cannot be found or
            // the key does not exist in the resource bundle
        }

        return null;
    }
}