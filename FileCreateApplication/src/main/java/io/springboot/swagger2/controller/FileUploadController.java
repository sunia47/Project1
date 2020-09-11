package io.springboot.swagger2.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.springboot.swagger2.service.FileUploadService;

@RestController
public class FileUploadController {
	@Autowired
	FileUploadService fileuploadservice;
	@PostMapping
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException
	{
		fileuploadservice.uploadFile(file);
	}
	

}

