package com.liuxi.action.abs;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.liuxi.action.abs.pojo.Page;


public class AbstractAction {
	
	private static final long serialVersionUID = 5832539421080466622L;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private Map<String, Object> application;

	public static Logger logger = Logger.getLogger(AbstractAction.class);

	protected Page page;
	
	protected String layout;
	
	protected String resStr = "";

	protected String state;
	
	protected String stateMsg;
	
	protected File file;
	/**
	 * �ļ���
	 */
	protected String fileFileName;
	/**
	 * �ļ�����
	 */
	protected String fileExts = "txt";
	/**
	 * �ļ���С
	 */
	protected int fileMaxSize = 2;// ��λM
	
	protected FileInputStream inputStream;
	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileExts() {
		return fileExts;
	}

	public void setFileExts(String fileExts) {
		this.fileExts = fileExts;
	}

	public int getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileMaxSize(int fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	public FileInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public AbstractAction() {
		page = new Page();
	}

	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}

	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getResStr() {
		return resStr;
	}

	public void setResStr(String resStr) {
		this.resStr = resStr;
	}

	/**
	 * ת����utf-8�ַ����
	 * 
	 * @param s
	 * @return
	 */
	protected String transcode(String s) {
		if (null != s && !"".equals(s)) {
			try {
				String result = new String(s.getBytes("ISO-8859-1"), "UTF-8");
				return result;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("AbstractAction.transcode Error!", e);
				return null;
			}
		}
		return null;
	}
	protected String transcode_ISO(String s) {
		if (null != s && !"".equals(s)) {
			try {
				String result = new String(s.getBytes("UTF-8"), "ISO-8859-1");
				return result;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("AbstractAction.transcode_ISO Error!", e);
				return null;
			}
		}
		return null;
	}

	public String getStateMsg() {
		return stateMsg;
	}

	public void setStateMsg(String stateMsg) {
		this.stateMsg = stateMsg;
	}
	
	/*
	 * ����Ϊmap����ֵ��Ϣƴװ
	 */
	protected Map<String, Object> getSuccessMap(Map<String, Object> returnMap, String msg){
        Map<String, Object> map = this.getMap(true, "1", msg);
        map.put("ReturnData", returnMap);
        return map;
    }

    protected Map<String, Object> getSuccessMap(List<Map<String, Object>> returnList, int total, String msg){
        Map<String, Object> map = this.getMap(true, "1", msg);
        map.put("total", total);
        map.put("ReturnData", returnList);
        return map;
    }

    protected Map<String, Object> getErrorMap(Map<String, Object> returnMap, String state, String msg){
        Map<String, Object> map = this.getMap(false, state, msg);
        map.put("ReturnData", returnMap);
        return map;
    }

    protected Map<String, Object> getErrorMap(List<Map<String, Object>> returnList, String state, String msg){
        Map<String, Object> map = this.getMap(false, state, msg);
        map.put("ReturnData", returnList);
        return map;
    }

    private Map<String, Object> getMap(boolean success, String state, String msg){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Success", success);
        map.put("state", state);
        map.put("Message", msg);
        return map;
    }

	

}
