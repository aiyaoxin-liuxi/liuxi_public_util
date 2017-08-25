/*
 * FileOperate.java
 */
package org.pub.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.pub.util.date.DateConverter;

/**
 *
 */
public class FileOperate {

    private static final Logger logger = Logger.getLogger(FileOperate.class.getName());
    public static final int DEFAULT_BUFFER_SIZE = 1024;

    /**
     * 创建一个 FileOperate 对象
     */
    public FileOperate() {
    }

    /**
     * 将字符串保存成文件
     *
     * @param fileDir 保存的文件路径
     * @param strContent 要保存的字符串
     */
    public static void saveFile(String fileDir,
            String fileName, String strContent) {
        FileWriter out = null;
        BufferedWriter buff_out = null;
        try {
            mkdir(fileDir);
            out = new FileWriter(fileDir + "/" + fileName);
            buff_out = new BufferedWriter(out);
            buff_out.write(strContent);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            try {
                if (null != buff_out) {
                    buff_out.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * 将字符串保存成文件
     *
     * @param filePath 保存的文件路径
     * @param strContent 要保存的字符串
     */
    public static void saveFile(String filePath, String strContent) {
        saveFile(parseFileDir(filePath), parseFileName(filePath), strContent);
    }

    /**
     * 将字符串保存成文件 按指定的字符编码保存
     *
     * @param filePath 保存的文件路径
     * @param strContent 要保存的字符串
     * @param encoding 字符编码
     */
    public static void saveFileEncoding(String filePath, String strContent, String encoding) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
            osw.write(strContent);
            osw.flush();
            osw.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 将字符串保存成文件
     *
     * @param filePath 保存的文件路径
     * @param strContent 要保存的字符串
     */
    public static void saveFileAndBack(String filePath, String strContent) {
        File file = new File(filePath);
        if (file.exists()) {
            String fileName = filePath + "_"
                    + DateConverter.date2String(
                    new Date(), DateConverter.DF_YMDHMS_B) + ".bak";
            File newFile = new File(fileName);
            file.renameTo(newFile);
        }
        saveFile(parseFileDir(filePath), parseFileName(filePath), strContent);
    }

    /**
     * 通过url，得到的流保存成文件
     *
     * @param destUrl 目标url
     * @param filePath 文件保存的路径
     */
    public static void saveFileForUrl(String destUrl, String filePath) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        try {
            URL url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            fos = new FileOutputStream(filePath);
            int size;
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
        } catch (IOException e) {
        } catch (ClassCastException e) {
        } finally {
            try {
                fos.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
        }
    }

    /**
     * 打开指定的文件
     *
     * @param filePath 文件路径
     * @return 文件字符串
     */
    public static String openFile(String filePath) {
        StringBuilder strBuff = new StringBuilder();
        File file = new File(filePath);
        FileReader in = null;
        BufferedReader buff_in = null;
        try {
            in = new FileReader(file);
            buff_in = new BufferedReader(in);
            String str = buff_in.readLine();
            while (str != null) {
                strBuff.append(str).append("\r\n");
                str = buff_in.readLine();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            try {
                if (buff_in != null) {
                    buff_in.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return strBuff.toString();
    }

    /**
     * 创建指定的文件目录
     *
     * @param fileDir
     */
    public static void mkdir(String fileDir) {
        File file = new File(fileDir);
        // 如果目录不存在，则创建
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 解析出文件目录
     *
     * @param filePath 文件路径
     * @return 文件目录
     */
    public static String parseFileDir(String filePath) {
        String fileDir = null;
        if (filePath != null) {
            filePath = filePath.replace("\\", "/");
            fileDir = filePath.substring(0, filePath.lastIndexOf("/")).replace("//", "/");
        }
        return fileDir;
    }

    /**
     * 解析出文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String parseFileName(String filePath) {
        String fileName = null;
        if (filePath != null) {
            filePath = filePath.replace("\\", "/");
            fileName = filePath.substring(
                    filePath.lastIndexOf("/")).replace("/", "");
        }
        return fileName;
    }

    /**
     * 拷贝文件或文件夹
     *
     * @param fromPath 原文件路径
     * @param toPath 目标路径
     * @param filtrs 要过滤的文件规则 全文件名:a.txt; 文件后缀名: *.class;文件夹: |.svn
     */
    public static void copyFiles(String fromPath, String toPath, String... filtrs) throws IOException {
        File file = new File(fromPath);
        if (file != null && !isInFiltrs(file.getName(), filtrs)) {
            if (file.isFile()) {
                copyFile(fromPath, toPath);
            } else {
                copyDirectiory(fromPath, toPath, filtrs);
            }
        }
    }

    /**
     * 拷贝文件
     *
     * @param fromPath 原文件路径
     * @param toPath 目标路径
     */
    public static void copyFile(String fromPath, String toPath) throws IOException {
        File fromFile = new File(fromPath);
        if (fromFile.isFile()) {
            FileInputStream input = new FileInputStream(fromFile);
            FileOutputStream output = new FileOutputStream(toPath);
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();
            output.close();
            input.close();
        }
    }

    /**
     * 拷贝文件夹
     *
     * @param fromPath 源文件夹路径
     * @param toPath 目标路径
     * @param filtrs 要过滤的文件规则 全文件名:a.txt; 文件后缀名: *.class;文件夹: |.svn
     */
    public static void copyDirectiory(String fromPath, String toPath, String... filtrs) throws IOException {
        (new File(toPath)).mkdirs();
        File[] file = (new File(fromPath)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (!isInFiltrs(file[i].getName(), filtrs)) {
                if (file[i].isFile()) {
                    copyFile(fromPath + "/" + file[i].getName(), toPath + "/" + file[i].getName());
                }
                if (file[i].isDirectory()) {
                    copyDirectiory(fromPath + "/" + file[i].getName(), toPath + "/" + file[i].getName(), filtrs);
                }
            }
        }
    }

    /**
     * 检验文件名是否在要过滤的文件规则中 全文件名:a.txt; 文件后缀名: *.class;文件夹: |.svn
     *
     * @param fileName 文件名
     * @param filtrs 要过滤的文件规则字符串,多个规则用 : 分开
     */
    public static boolean isInFiltrs(String fileName, String... filtrs) {
        boolean inFlag = false;
        if (fileName != null && filtrs != null) {
            if (filtrs.length == 1) {
                filtrs = filtrs[0].split(":");
            }
            for (String filtr : filtrs) {
                if (filtr != null) {
                    String filtr_t = filtr;
                    String fileName_t = fileName;
                    int f_idx = filtr.lastIndexOf("*");
                    if (f_idx > -1) {//文件后缀名匹配
                        filtr_t = filtr.substring(f_idx + 1);
                        int f_len = fileName.length() - filtr_t.length();
                        if (f_len > -1) {
                            fileName_t = fileName.substring(f_len);
                        }
                    } else if (filtr.lastIndexOf("|") > -1) {//文件夹名称匹配
                        filtr_t = filtr.substring(filtr.lastIndexOf("|") + 1);
                    }
                    if (filtr_t.equalsIgnoreCase(fileName_t)) {
                        return true;
                    }
                }
            }
        }
        return inFlag;
    }

    public static boolean string2File(String filePath, String res) {
        return string2File(filePath, res, null);
    }

    /**
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
     *
     * @param filePath 文件路径
     * @param res 原字符串
     * @param encoding 保存的文件的字符编码
     * @return 成功标记
     */
    public static boolean string2File(String filePath, String res, String encoding) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) {
                distFile.getParentFile().mkdirs();
            }
            bufferedReader = new BufferedReader(new StringReader(res));
            if (encoding == null || "".equals(encoding.trim())) {
                bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            } else {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(distFile), encoding));
            }
            char buf[] = new char[DEFAULT_BUFFER_SIZE];//字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
        }
        return flag;
    }

    public static String file2String(File file) {
        return file2String(file, null);
    }

    public static String file2String(String filePath) {
        return file2String(new File(filePath), null);
    }

    public static String file2String(String filePath, String encoding) {
        return file2String(new File(filePath), encoding);
    }

    /**
     * 文本文件转换为指定编码的字符串
     *
     * @param file 文本文件
     * @param encoding 编码类型
     * @return 转换后的字符串
     */
    public static String file2String(File file, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file));
            } else {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            }
            //将输入流写入输出流
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
        }
        //返回转换结果
        if (writer != null) {
            return writer.toString();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        test_isInFiltrs();
    }

    /**
     * 测试 检验文件名是否在要过滤的文件规则中
     */
    public static void test_isInFiltrs() {
        String[] files = {"a.txt", "b.txt", "c.java", "d.java", "e.class", "f.class", "abc", "ddd", ".svn", "cvs", "", null};
        String[] filtrs = {"*.class", "|.svn", "b.txt", "aa.txt", "*.exe", "|xxx", "abc", "", null};
        System.out.print("test_isInFiltrs().filtrs=");
        for (String filtr : filtrs) {
            System.out.print("[" + filtr + "],");
        }
        System.out.println();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            System.out.println("\tfileName=" + fileName + " >>> " + isInFiltrs(fileName, filtrs));
        }
        try {
            String filtrs_str = "|.svn:*.js:*.jar:aa.txt:*.exe:|applicationContext-dao.xml::null";
            String fromPath = "D:\\22test22\\from";
            String toPath = "D:\\22test22\\to";
            FileOperate.copyFiles(fromPath, toPath, filtrs_str);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
