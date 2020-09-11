package io.springboot.swagger2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@RestController
public class FileDownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileDownloadApplication.class, args);
	}
	@RequestMapping(value="/download", method=RequestMethod.GET)
	
	
	public ResponseEntity<Object>downloadFile() throws IOException
	{
		FileWriter filewriter=null;
		try
		{
		CSVData csv1=new CSVData();
		csv1.setId("12");
		csv1.setPhone("123456678");
		csv1.setName("john");
		CSVData csv2=new CSVData();
		csv2.setId("1");
		csv2.setPhone("1099678");
		csv2.setName("Daniel");
		List<CSVData> csvDataList=new ArrayList<>();
		csvDataList.add(csv1);
		csvDataList.add(csv2);
		StringBuilder sb=new StringBuilder("ID,NAME,PHONE\n");
		for(CSVData csv:csvDataList)
		{
			sb.append(csv.getId()).append(",").append(csv.getName()).append(",").append(csv.getPhone()).append("\n");
		}
		String filename="C:\\Users\\susanta\\Downloads\\csvdata.txt";
		 filewriter=new FileWriter(filename);
		filewriter.write(sb.toString());
		filewriter.flush();
		File file=new File(filename);
		InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition",String.format("attachment;filename=\"%s\"",file.getName()));
		headers.add("Cache-Control","no-cache,no-store ,must-revalidate");
		headers.add("Progrma","no-cache");
		headers.add("Expires","O");
		ResponseEntity<Object> responseEntity=ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("error occured",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		finally {
			if(filewriter!=null)
			filewriter.close();
		}
	
		
	}
}
