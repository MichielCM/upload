package upload;

public class BinaryFile extends File {
	private byte[] content;
	
	public BinaryFile(String name, String mimeType, byte[] content) {
		super(name, mimeType, true);
		this.content = content;
	}
	
	public byte[] getContent() {
		return this.content;
	}
}
