package core;

/**
 * Created by yeyh on 2017/7/18.
 */
public abstract  class GeneratedFile implements CodeMaker{
	private String targetProject;
	private String targetPackage;
	private String fileName;


	public String getTargetProject() {
		return targetProject;
	}

	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	public String getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
