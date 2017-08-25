/*
 * FileUpload.java
 */
package org.pub.util.file;

import java.io.*;


/**
 * 文件上传
 *
 */
public class FileUpload extends FileOperate {

    /**
     * 创建一个 FileUpload 对象
     */
    public FileUpload() {
    }

    /**
     * 将输入流保存成指定的文件
     *
     * @param is 文件输入流
     * @param fileDir 文件保存的目录
     * @param fileName 文件保存的文件名
     * @return 文件保存后的路径
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String upload(InputStream is, String fileDir, String fileName)
            throws FileNotFoundException, IOException {
        String filePath = fileDir + "/" + fileName;
        byte buffer[] = new byte[8192];

        // 创建文件目录
        mkdir(fileDir);
        OutputStream os = new FileOutputStream(filePath);
        try {
            int bytesRead;
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
        return filePath;
    }
}
