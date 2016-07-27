package upload;

public class Text extends Upload {

	private String content;
	
	public Text(String name, String content) {
		super(name, UploadType.TEXT);
		this.content = content;
	}

	@Override
	public String getContent() {
		return this.content;
	}
	
}
