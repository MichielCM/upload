package upload;

public class TextFile extends File {
	private String content;
	
	public TextFile(String name, String mimeType, String content) {
		super(name, mimeType, false);
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
}
