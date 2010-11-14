package org.jsapar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>AppTest</code> contains tests for the class <code>{@link App}</code>.
 *
 * @generatedBy CodePro at 14-11-10 17:24
 * @author JsaPar Developer
 * @version $Revision: 1.0 $
 */
public class AppTest {
    /**
     * Run the void main(String[]) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 14-11-10 17:24
     */
    @Test
    public void testMain_1()
        throws Exception {
        String[] args = new String[] {};

        App.main(args);

        // add additional test code here
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *         if the initialization fails for some reason
     *
     * @generatedBy CodePro at 14-11-10 17:24
     */
    @Before
    public void setUp()
        throws Exception {
        // add additional set up code here
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception
     *         if the clean-up fails for some reason
     *
     * @generatedBy CodePro at 14-11-10 17:24
     */
    @After
    public void tearDown()
        throws Exception {
        // Add additional tear down code here
    }

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     *
     * @generatedBy CodePro at 14-11-10 17:24
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(AppTest.class);
    }
}