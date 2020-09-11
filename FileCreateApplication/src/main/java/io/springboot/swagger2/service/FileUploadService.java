package io.springboot.swagger2.service;

import java.io.File;
import java.io.IOException;
//import io.springboot.swagger2.controller.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service

public class FileUploadService  {

	public void uploadFile(MultipartFile file) throws IllegalStateException, IOException
	{
		file.transferTo(new File("D:\\Youtube\\FileUpload\\"+file.getOriginalFilename()));
	}

}
