package upload;

public abstract class Upload {

	public enum UploadType {
		TEXT, FILE
	}
	
	private String name;
	private UploadType type;
	private boolean multiple;
	
	public Upload(String name, UploadType type, boolean multiple) {
		this.name = name;
		this.type = type;
		this.multiple = multiple;
	}
	
	public Upload(String name, UploadType type) {
		this(name, type, false);
	}
	
	public UploadType getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isMultiple() {
		return this.multiple;
	}
	
	public abstract Object getContent();
	
}
