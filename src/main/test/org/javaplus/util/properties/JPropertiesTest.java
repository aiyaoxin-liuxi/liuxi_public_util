/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaplus.util.properties;

import junit.framework.*;

//import javax.servlet.ServletContext;
import java.util.Properties;

public class JPropertiesTest extends TestCase {

    JProperties jProperties;
    String key = "helloworld.title";
    String value = "Hello World!";

    public void testLoadProperties() throws Exception {
        String name = null;
        Properties p = new Properties();

        name = "C:\\IDEAP\\Properties4Methods\\src\\com\\kindani\\test\\LocalStrings.properties";
        p = JProperties.loadProperties(name, JProperties.BY_PROPERTIES);
        assertEquals(value, p.getProperty(key));

        name = "com.kindani.test.LocalStrings";
        p = JProperties.loadProperties(name, JProperties.BY_RESOURCEBUNDLE);
        assertEquals(value, p.getProperty(key));
        assertEquals(value, ((JProperties.ResourceBundleAdapter) p).getString(key));

        name = "C:\\IDEAP\\Properties4Methods\\src\\com\\kindani\\test\\LocalStrings.properties";
        p = JProperties.loadProperties(name, JProperties.BY_PROPERTYRESOURCEBUNDLE);
        assertEquals(value, p.getProperty(key));
        assertEquals(value, ((JProperties.ResourceBundleAdapter) p).getString(key));

        name = "\\com\\kindani\\test\\LocalStrings.properties";
        p = JProperties.loadProperties(name, JProperties.BY_SYSTEM_CLASSLOADER);
        assertEquals(value, p.getProperty(key));

        name = "\\com\\kindani\\test\\LocalStrings.properties";
        p = JProperties.loadProperties(name, JProperties.BY_CLASSLOADER);
        assertEquals(value, p.getProperty(key));

        name = "test\\LocalStrings.properties";
        p = JProperties.loadProperties(name, JProperties.BY_CLASS);
        assertEquals(value, p.getProperty(key));
    }
}
