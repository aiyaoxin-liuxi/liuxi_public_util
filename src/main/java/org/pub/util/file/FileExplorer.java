/*
 * FileExplorer.java
 */
package org.pub.util.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * 文件 资源管理器
 *
 */
public class FileExplorer {

    private static final Logger logger = Logger.getLogger(FileExplorer.class.getName());
    private static FileExplorer me = null;
    private static int maxUploadWriteBlockLength = 1024 * 1024 * 10;
    private static int maxDownloadReadBlockLength = 1024 * 1024 * 10;

    private FileExplorer() {
    }

    /**
     * 获得一个文件资源管理器实例。
     */
    public static FileExplorer getInstance() {
        if (me == null) {
            me = new FileExplorer();
        }
        return me;
    }

    /**
     * 从文件库中读取指定范围的数据。
     *
     * @param filePath 文件路径
     * @param pos 准备读取数据的位置相对文件开头的偏移。
     * @param data 将要读入数据的缓冲区
     * @param offset 在<code>data</code>数组中开始写入读取数据的位置。
     * @param len 最多读入的字节数
     * @return 读入缓冲区的总字节数。若由于抵达文件结尾而没有读取到数据则返回-1。
     */
    public int read(String filePath, long pos, byte[] data, int offset, int len) throws FileException {
        logger.debug("filePath=" + filePath);
        logger.debug("     pos=" + pos);
        logger.debug("    data=" + data);
        logger.debug("  offset=" + offset);
        logger.debug("     len=" + len);
        File file = new File(filePath);
        int total = 0;
        if (!file.exists()) {
            throw new FileException("需要读取内容的文件（" + filePath + "）不存在！");
        }
        if (file.isDirectory()) {
            throw new FileException("不能从文件夹（" + filePath + "）中读取文件内容！");
        }
        if (pos > file.length()) {
            total = -1;
        } else {
            int length = Math.min(len, new Long(file.length() - pos).intValue());
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(pos);
                total = raf.read(data, offset, length);
                raf.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
                throw new FileException("I/O错导致读取文件内容失败", ex);
            }
        }
        return total;
    }

    /**
     * 向文件库中写入数据。
     *
     * @param filePath 文件路径
     * @param pos 准备写入数据的位置相对文件开头的偏移。
     * @param bytes 将要写入数据的缓冲区
     * @param offset 在<code>data</code>数组中开始读取写入数据的位置。
     * @param len 最多写入的字节数
     */
    public void write(String filePath, long pos, byte[] bytes, int offset, int len) throws FileException {
        logger.debug("filePath=" + filePath);
        logger.debug("     pos=" + pos);
        logger.debug("   bytes=" + bytes);
        logger.debug("  offset=" + offset);
        logger.debug("     len=" + len);
        if (bytes == null) {
            throw new IllegalArgumentException("\'bytes\'参数不能为null！");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileException("需要写入内容的文件（" + filePath + "）不存在！");
        }
        if (file.isDirectory()) {
            throw new FileException("不能向文件夹（" + filePath + "）中写入文件内容！");
        }
        if (pos + len > file.length()) {
            throw new FileException("写入内容不允许越过文件结尾！");
        }
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(pos);
            raf.write(bytes, offset, len);
            raf.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new FileException("I/O错导致写入文件内容失败", ex);
        }
    }

    /**
     * 创建一个新文件。
     *
     * @param filePath 文件路径
     * @param size 文件大小。
     * @return 若创建成功则返回<code>true</code>，否则返回<code>false</code>
     */
    public boolean makeNewFile(String filePath, long size) throws FileException {
        logger.debug("filePath=" + filePath);
        logger.debug("    size=" + size);
        File file = new File(filePath);
        if (file.exists()) {
            throw new FileException("需要创建的文件（" + filePath + "）已经存在！");
        }
        boolean b = false;
        try {
            // 确保所需的目录存在
            file.getParentFile().mkdirs();
            b = file.createNewFile();
            if (b) {
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                raf.setLength(size);
                raf.close();
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new FileException("I/O错导致创建文件失败", ex);
        }
        return b;
    }

    /**
     * 删除给定的文件。
     *
     * @param filePath 文件路径
     * @return 若文件被成功删除则返回<code>true</code>，否则返回<code>false</code>
     */
    public boolean deleteFile(String filePath) throws FileException {
        logger.debug("filePath=" + filePath);
        File file = new File(filePath);
        boolean b = false;
        if (!file.exists()) {
            logger.warn("要删除的文件不存在！" + filePath);
        } else {
            b = file.delete();
        }
        return b;
    }

    /**
     * 删除一组文件。
     *
     * @param filePaths 一组文件路径
     */
    public void deleteFiles(String... filePaths) throws FileException {
        if (filePaths == null) {
            throw new IllegalArgumentException("\'filePaths\'参数不能为null！");
        }
        for (int i = 0; i < filePaths.length; i++) {
            logger.info("删除文件[" + i + "]=" + filePaths[i]);
            deleteFile(filePaths[i]);
        }
    }

    /**
     * <p>Perform the standard request processing for this request, and create
     * the corresponding response.</p>
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception is thrown
     */
    public void process(String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("filePath=" + filePath);
        File file = new File(filePath);
        if (!file.isFile()) {
            throw new IllegalArgumentException("\'filePath\'参数错误，没有找到文件！filePath=" + filePath);
        }
        String encoding = "utf-8";
        String filename = file.getName();
        Long fileSize = file.length();
        try {
            ServletOutputStream out = null;
            int maxlen = maxDownloadReadBlockLength;
            response.reset();
            response.setHeader("Server", "jpc.javaplus.org");
            //告诉客户端允许断点续传多线程连接下载
            //响应的格式是: >>>>> Accept-Ranges: bytes
            response.setHeader("Accept-Ranges", "bytes");
            //响应的格式是: >>>>> HTTP/1.1 200 OK
            //客户端请求的下载的文件块的开始字节
            logger.debug("Range: " + request.getHeader("Range"));
            if (request.getHeader("Range") != null) {
                //如果是下载文件的范围而不是全部,向客户端声明支持并开始文件块下载要设置状态
                //响应的格式是: >>>>> HTTP/1.1 206 Partial Content
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
                //从请求中得到开始的字节
                //请求的格式是: >>>>> Range: bytes=[文件块的开始字节]-[last byte],
                logger.debug("Is range valid ? " + request.getHeader("Range").
                        matches("bytes=((\\d+-(\\d)*)|(-\\d+))(,((\\d+-(\\d)*)|(-\\d+)))*"));
                if (!request.getHeader("Range").matches("bytes=((\\d+-(\\d)*)|(-\\d+))(,((\\d+-(\\d)*)|(-\\d+)))*")) {
                    response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                }
                String rSet = request.getHeader("Range").substring("bytes=".length());
                StringTokenizer tokenizer = new StringTokenizer(rSet, ",");
                String token;
                long byteRangeSet[][] = new long[tokenizer.countTokens()][2];
                int i = 0;
                int idx;
                int contentLength = 0;
                while (tokenizer.hasMoreTokens()) {
                    token = tokenizer.nextToken();
                    logger.debug("token[" + i + "]=" + token);
                    idx = token.indexOf('-');
                    logger.debug("Is token valid? " + token.matches("(\\d+-(\\d)*)|(-\\d+)"));
                    if (idx < 0 || !token.matches("(\\d+-(\\d)*)|(-\\d+)")) {
                        response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                    } else if (token.startsWith("-")) { // suffix-byte-range-spec
                        byteRangeSet[i][0] = fileSize - Long.parseLong(token.substring(1));
                        byteRangeSet[i][1] = fileSize - 1;
                    } else if (token.endsWith("-")) {
                        byteRangeSet[i][0] = Long.parseLong(token.substring(0, token.length() - 1));
                        byteRangeSet[i][1] = fileSize - 1;
                    } else {
                        byteRangeSet[i][0] = Long.parseLong(token.substring(0, idx));
                        byteRangeSet[i][1] = Long.parseLong(token.substring(idx + 1, token.length()));
                    }
                    contentLength += byteRangeSet[i][1] - byteRangeSet[i][0] + 1;
                    i++;
                }
                //下载的文件(或块)长度
                response.setHeader("Content-Length", contentLength + "");
                logger.debug("Content-Length: " + contentLength);
                for (i = 0; i < byteRangeSet.length; i++) {
                    logger.debug("byteRangeSet[{" + i + "}][0]=" + byteRangeSet[i][0] + "\t\tbyteRangeSet[" + i + "][1]=" + byteRangeSet[i][1]);
                }
                if (byteRangeSet.length > 1) { // multipart/byteranges
                    String separator = "THIS_STRING_SEPARATES";
                    //使客户端直接下载
                    //响应的格式是: >>>>> Content-Type: application/octet-stream
                    response.setContentType("multipart/byteranges;" + "boundary=" + separator);
                    //为客户端下载指定默认的下载文件名称
                    //响应的格式是: >>>>> Content-Disposition: attachment;filename="[文件名]"
                    response.setHeader(
                            "Content-Disposition",
                            "attachment;filename=\""
                            + java.net.URLEncoder.encode(filename, encoding) + "\"");
                    byte[] fileData = new byte[maxlen];
                    int count;
                    int bytes;
                    for (int k = 0; k < byteRangeSet.length; k++) {
                        if (k == 0) {
                            out.print("\n");
                        }
                        //响应的格式是: >>>>> Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
                        out.println("--" + separator);
                        out.println("Content-type: application/octet-stream");
                        out.println("Content-Range: bytes " + byteRangeSet[k][0] + "-" + byteRangeSet[k][1] + "/" + fileSize);
                        //如果有此句话不能用 IE 直接下载
                        //response.setHeader("Connection", "Close");
                        try {
                            for (long j = byteRangeSet[k][0]; j < byteRangeSet[k][1]; j += maxlen) {
                                bytes = Long.valueOf(Math.min(byteRangeSet[k][1] - j, maxlen)).intValue();
                                count = this.read(filePath, j, fileData, 0, bytes);
                                if (out == null) {
                                    out = response.getOutputStream();
                                }
                                out.write(fileData, 0, count);
                            }
                        } catch (IOException ioex) {
                            logger.error("客户端中断连接：", ioex);
                            break;
                        }
                    }
                    out.println("--" + separator + "--");
                    out.close();
                } else { // octet-stream
                    //使客户端直接下载
                    //响应的格式是: >>>>> Content-Type: application/octet-stream
                    response.setContentType("application/octet-stream");
                    //响应的格式是: >>>>> Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
                    response.setHeader("Content-Range", "bytes " + byteRangeSet[0][0] + "-" + byteRangeSet[0][1] + "/" + fileSize);
                    logger.debug("Content-Range: bytes " + byteRangeSet[0][0] + "-" + byteRangeSet[0][1] + "/" + fileSize);
                    //如果有此句话不能用 IE 直接下载
                    //response.setHeader("Connection", "Close");
                    //为客户端下载指定默认的下载文件名称
                    //响应的格式是: >>>>> Content-Disposition: attachment;filename="[文件名]"
                    response.setHeader(
                            "Content-Disposition",
                            "attachment;filename=\""
                            + URLEncoder.encode(filename, encoding) + "\"");
                    byte[] fileData = new byte[maxlen];
                    int count;
                    int bytes;
                    for (long j = byteRangeSet[0][0]; j <= byteRangeSet[0][1]; j += maxlen) {
                        bytes = Long.valueOf(Math.min(byteRangeSet[0][1] - j + 1, maxlen)).intValue();
                        count = this.read(filePath, j, fileData, 0, bytes);
                        logger.debug("read bytes : " + count + "/" + bytes + " (" + j + "-" + (j + count - 1) + ")");
                        try {
                            if (out == null) {
                                out = response.getOutputStream();
                            }
                            out.write(fileData, 0, count);
                        } catch (IOException ioex) {
                            logger.error("客户端中断连接：", ioex);
                            break;
                        }
                    }
                    out.close();
                }
            } else {
                // 没有提供Range请求头一次性返回整个文件的内容
                //下载的文件(或块)长度
                //响应的格式是: >>>>> Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
                response.setHeader("Content-Length", fileSize + "");
                //如果有此句话不能用 IE 直接下载
                //response.setHeader("Connection", "Close");
                //使客户端直接下载
                //响应的格式是: >>>>> Content-Type: application/octet-stream
                response.setContentType("application/octet-stream");
                //为客户端下载指定默认的下载文件名称
                //响应的格式是: >>>>> Content-Disposition: attachment;filename="[文件名]"
                response.setHeader(
                        "Content-Disposition",
                        "attachment;filename=\""
                        + URLEncoder.encode(filename, encoding) + "\"");
                byte[] fileData = new byte[maxlen];
                int count;
                for (int i = 0; i < fileSize; i += maxlen) {
                    count = this.read(filePath, i, fileData, 0, maxlen);
                    try {
                        if (out == null) {
                            out = response.getOutputStream();
                        }
                        out.write(fileData, 0, count);
                    } catch (IOException ioex) {
                        logger.error("客户端中断连接：", ioex);
                        break;
                    }
                }
                out.close();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
