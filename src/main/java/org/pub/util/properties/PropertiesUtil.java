/*
 * PropertiesUtil.java
 */
package org.pub.util.properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 属性文件处理
 *
 */
public class PropertiesUtil {

    private static final Logger logger = Logger.getLogger(PropertiesUtil.class.getName());
    public final static int BY_PROPERTIES = 1;
    public final static int BY_RESOURCEBUNDLE = 2;
    public final static int BY_PROPERTYRESOURCEBUNDLE = 3;
    public final static int BY_CLASS = 4;
    public final static int BY_CLASSLOADER = 5;
    public final static int BY_SYSTEM_CLASSLOADER = 6;

    public static Properties loadProperties(final String name) throws IOException {
        Properties p = null;
        try {
            p = PropertiesUtil.loadProperties(name, PropertiesUtil.BY_CLASSLOADER);
            logger.debug("loadProperties(" + name + ").BY_CLASSLOADER    ok!");
            return p;
        } catch (Exception ex) {
            //logger.debug("loadProperties(" + name + ").BY_CLASSLOADER    err!", ex);
        }
        try {
            p = PropertiesUtil.loadProperties(name, PropertiesUtil.BY_PROPERTIES);
            logger.debug("loadProperties(" + name + ").BY_PROPERTIES    ok!");
            return p;
        } catch (Exception ex) {
            //logger.debug("loadProperties(" + name + ").BY_PROPERTIES    err!", ex);
        }
        try {
            p = PropertiesUtil.loadProperties(name, PropertiesUtil.BY_RESOURCEBUNDLE);
            logger.debug("loadProperties(" + name + ").BY_RESOURCEBUNDLE    ok!");
            return p;
        } catch (Exception ex) {
            //logger.debug("loadProperties(" + name + ").BY_RESOURCEBUNDLE    err!", ex);
        }
        try {
            p = PropertiesUtil.loadProperties(name, PropertiesUtil.BY_PROPERTYRESOURCEBUNDLE);
            logger.debug("loadProperties(" + name + ").BY_PROPERTYRESOURCEBUNDLE    ok!");
            return p;
        } catch (Exception ex) {
            //logger.debug("loadProperties(" + name + ").BY_PROPERTYRESOURCEBUNDLE    err!", ex);
        }
        try {
            p = PropertiesUtil.loadProperties(name, PropertiesUtil.BY_CLASS);
            logger.debug("loadProperties(" + name + ").BY_CLASS    ok!");
            return p;
        } catch (Exception ex) {
            //logger.debug("loadProperties(" + name + ").BY_CLASS    err!", ex);
        }
        try {
            p = PropertiesUtil.loadProperties(name, PropertiesUtil.BY_SYSTEM_CLASSLOADER);
            logger.debug("loadProperties(" + name + ").BY_SYSTEM_CLASSLOADER    ok!");
            return p;
        } catch (Exception ex) {
            //logger.debug("loadProperties(" + name + ").BY_SYSTEM_CLASSLOADER    err!", ex);
        }
        if (p == null) {
            logger.warn("not load, is null! name=" + name);
        }
        return p;
    }

    public static Properties loadProperties(final String name, final int type) throws IOException {
        Properties p = new Properties();
        InputStream in = null;
        if (type == BY_PROPERTIES) {
            in = new BufferedInputStream(new FileInputStream(name));
            assert (in != null);
            p.load(in);
        } else if (type == BY_RESOURCEBUNDLE) {
            ResourceBundle rb = ResourceBundle.getBundle(name, Locale.getDefault());
            assert (rb != null);
            p = new ResourceBundleAdapter(rb);
        } else if (type == BY_PROPERTYRESOURCEBUNDLE) {
            in = new BufferedInputStream(new FileInputStream(name));
            assert (in != null);
            ResourceBundle rb = new PropertyResourceBundle(in);
            p = new ResourceBundleAdapter(rb);
        } else if (type == BY_CLASS) {
            assert (PropertiesUtil.class.equals(new PropertiesUtil().getClass()));
            in = PropertiesUtil.class.getResourceAsStream(name);
            assert (in != null);
            p.load(in);
            //return new JProperties().getClass().getResourceAsStream(name);
        } else if (type == BY_CLASSLOADER) {
            assert (PropertiesUtil.class.getClassLoader().equals(new PropertiesUtil().getClass().getClassLoader()));
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
            assert (in != null);
            p.load(in);
            //return new JProperties().getClass().getClassLoader().getResourceAsStream(name);
        } else if (type == BY_SYSTEM_CLASSLOADER) {
            in = ClassLoader.getSystemResourceAsStream(name);
            assert (in != null);
            p.load(in);
        }
        if (in != null) {
            in.close();
        }
        return p;
    }

    // ---------------------------------------------- servlet used
    // ---------------------------------------------- support class
    public static class ResourceBundleAdapter extends Properties {

        public ResourceBundleAdapter(ResourceBundle rb) {
            assert (rb instanceof java.util.PropertyResourceBundle);
            this.rb = rb;
            java.util.Enumeration e = rb.getKeys();
            while (e.hasMoreElements()) {
                Object o = e.nextElement();
                this.put(o, rb.getObject((String) o));
            }
        }
        private ResourceBundle rb = null;

        public ResourceBundle getBundle(String baseName) {
            return ResourceBundle.getBundle(baseName);
        }

        public ResourceBundle getBundle(String baseName, Locale locale) {
            return ResourceBundle.getBundle(baseName, locale);
        }

        public ResourceBundle getBundle(String baseName, Locale locale, ClassLoader loader) {
            return ResourceBundle.getBundle(baseName, locale, loader);
        }

        public Enumeration getKeys() {
            return rb.getKeys();
        }

        public Locale getLocale() {
            return rb.getLocale();
        }

        public Object getObject(String key) {
            return rb.getObject(key);
        }

        public String getString(String key) {
            return rb.getString(key);
        }

        public String[] getStringArray(String key) {
            return rb.getStringArray(key);
        }

        protected Object handleGetObject(String key) {
            return ((PropertyResourceBundle) rb).handleGetObject(key);
        }
    }

    public static void saveFile(String filename, Properties p, String description) throws IOException {
        try {
            File f = new File(filename);
            FileOutputStream out = new FileOutputStream(f);
            p.store(out, description);
            out.close();
        } catch (IOException ex) {
            throw new IOException("无法保存指定的配置文件:" + filename, ex);
        }
    }
    
    
    private static Properties props = null;
    /**
     * 读取配置文件方法（通用）
     * @param fileName
     * @param key
     * @return value
     */
    public static String getReadProperties(String fileName,String key){
        InputStream in = PropsHandler.class.getResourceAsStream("/"+fileName+".properties");
        String value = "";
        try{
            props.load(in);
            if (null == key) {
                return null;
            }
            value = props.getProperty(key);
        }catch(IOException e){
            logger.error("PayCutServer:获取"+fileName+".properties文件时异常：",e);
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch(IOException e){
                logger.error("PayCutServer:关闭文件流："+fileName+".properties文件时异常：",e);
            }
        }
        return value;
    }
    
    public static void main(String args[]) {
        try {
            String file = "D:\\log4j.properties";
            Properties p = loadProperties(file);
            //PropertiesUnit pu = new PropertiesUnit(file);
            p.list(System.out);
            logger.debug("----------------------------------------------- <<<");
            logger.debug("\tlog4j.appender.stdout=" + p.getProperty("log4j.appender.stdout"));
            p.remove("datasource.username");
            logger.debug("\tdatasource.username=" + p.getProperty("datasource.username"));
            logger.debug("----------------------------------------------- >>>");
            saveFile(file, p, "xxxxxxxxx");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
