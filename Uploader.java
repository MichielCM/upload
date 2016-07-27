package upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.ArrayUtils;

import upload.File.FileType;

public class Uploader {

	private HashMap<String, Upload[]> index;
	private Upload[] uploads;
	private String[] binaryMimetypes;
	private String[] textMimetypes;
	
	public Uploader() {}
	
	public Uploader registerMimeTypes(FileType fileType, String... mimeTypes) {
		if (fileType == FileType.BINARY)
			this.binaryMimetypes = (String[]) ArrayUtils.addAll(this.binaryMimetypes, mimeTypes);
		else
			this.textMimetypes = (String[]) ArrayUtils.addAll(this.textMimetypes, mimeTypes);
		
		return this;
	}
	
	public Upload get(String name) {
		if (this.index.containsKey(name))
			return this.index.get(name)[0];
		else return null;
	}
	
	public Upload[] getAll() {
		return this.uploads;
	}
	
	public Upload[] getAll(String name) {
		if (this.index.containsKey(name))
			return this.index.get(name);
		else return null;
	}
	
	public Uploader upload(HttpServletRequest request) {
		try {
			ArrayList<Upload> uploads = new ArrayList<Upload>();
			this.index = new HashMap<String, Upload[]>();
			
			Iterator<FileItem> fileItems = new ServletFileUpload(
				new DiskFileItemFactory()
			).parseRequest(request).iterator();
		
			while (fileItems.hasNext()) {
			    FileItem fileItem = fileItems.next();
			    if (fileItem.isFormField()) {
			    	uploads.add(
			    		new Text(
			    			fileItem.getFieldName(),
			    			fileItem.getString()
			    		)
			    	);
			    } else {
			    	if (this.isBinary(fileItem.getContentType())) {
				    	uploads.add(
				    		new BinaryFile(
				    			fileItem.getFieldName(),
				    			fileItem.getContentType(),
				    			fileItem.get()
				    		)
				    	);
				    } else {
				    	uploads.add(
				    		new TextFile(
			    				fileItem.getFieldName(),
				    			fileItem.getContentType(),
				    			fileItem.getString()
				    		)
				    	);
				    }
				}
			    
			    if (this.index.containsKey(fileItem.getFieldName())) {
		    		this.index.put(
		    			fileItem.getFieldName(),
		    			(Upload[]) ArrayUtils.addAll(this.index.get(fileItem.getFieldName()), new Upload[] { uploads.get(uploads.size() - 1) })
		    		);
		    	} else {
		    		this.index.put(
		    			fileItem.getFieldName(),
		    			new Upload[] { uploads.get(uploads.size() - 1) }
		    		);
		    	}
			}
			
			this.uploads = uploads.toArray(new Upload[uploads.size()]);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;
	}
	
	private boolean isBinary(String mimeType) {
		if (ArrayUtils.contains(this.binaryMimetypes, mimeType)) {
			return true;
		} else if (ArrayUtils.contains(this.textMimetypes, mimeType)) {
			return false;
		} else if (mimeType.startsWith("text/")) {
			return false;
		} else {
			return true;
		}
		
		/*case "application/json":
		case "application/x-javascript":
		case "application/javascript":
		case "application/csv":
		case "application/xml":*/
	}
	
}