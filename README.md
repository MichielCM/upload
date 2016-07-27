# Synopsis
A convenience implementation of the org.apache.commons.fileupload library, which makes uploading files from the web easier.
Features:
- Support for multiple files
- Automatic detection of text or binary files based on mimetype
- Support for other non-file form elements (text, checkbox, etc.)

# Sample code
Uploader uploader = new Uploader()
  .registerMimeTypes(FileType.TEXT, "application/json", "application/csv", "application/xml") //specify which application mimetypes should be processed as plain text
  .upload(request); //process data from form element

for (Upload upload : uploader.getAll()) { //iterate through all form elements
	System.out.println(upload.getType().toString()); //e.g. FILE, or TEXT
	System.out.println(upload.getName()); //corresponding 'name' attribute, server as id
	
	if (upload.getType() == UploadType.FILE) {
		System.out.println(((File)upload).getMimeType()); //files have their mimetype specified, e.g. text/xml
		System.out.println(((File)upload).isBinary()); //whether or not this is plain text, based on mimetype
	}
	
	System.out.println(upload.getContent()); //field contents, either as String or byte[] for binary data.
}
