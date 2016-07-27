package upload;

public abstract class File extends Upload {
	
	private String mimeType;
	private boolean binary;
	
	public enum FileType {
		TEXT, BINARY
	}
	
	public File(String name, String mimeType, boolean binary) {
		super(name, UploadType.FILE, false);
		
		this.mimeType = mimeType;
		this.binary = binary;
	}
	
	public String getMimeType() {
		return this.mimeType;
	}
	
	public boolean isBinary() {
		return this.binary;
	}
}