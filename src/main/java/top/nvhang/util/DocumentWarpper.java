package top.nvhang.util;

import org.dom4j.*;

/**
 * Created by yeyh on 2017/7/31.
 */
public class DocumentWarpper  {
	private Document document;
	private String targetPath;
	private String fileName;
	public DocumentWarpper(Document document,String targetPath,String fileName){
		this.document=document;
		this.targetPath=targetPath;
		this.fileName=fileName;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
