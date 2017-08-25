/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaplus.util.properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {

    public void readPropertiesFile(String fileName) throws FileNotFoundException {
        String str = "";
        Properties prop = new Properties();
        InputStream stream = null;
//读取这个类在同一包中的properties文件
//stream = getClass().getClassLoader().getResourceAsStream(fileName);
        System.out.println("path:" + getClass().getResource(fileName));
//读取SRC下的的properties文件
        String path = getClass().getResource("/").getPath();
        stream = new BufferedInputStream(new FileInputStream(new File(path + fileName)));
        try {
            prop.load(stream);
            str = prop.getProperty("localname");
            System.out.println("localname:" + str);
            System.out.println("properties:" + prop);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new ReadPropertiesFile().readPropertiesFile("config.properties");
    }
}

