package com.prior.bootCamp.modules.FileStorage.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prior.bootCamp.entity.FileStorage;
import com.prior.bootCamp.repository.FileStorageRepository;


@RestController
public class FileStorageController {
	
	@Autowired
	private FileStorageRepository fileStorageRepository;

	@GetMapping("/download/{id}")
	public ResponseEntity<?> download(@PathVariable Long id) {
		
		  FileStorage fileStorage = fileStorageRepository.getOne(id);
	      return ResponseEntity.ok()
	            .contentType
	            (MediaType.parseMediaType(fileStorage.getFileType()))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileStorage.getFileName()  + "\"")
	            .body(new ByteArrayResource(fileStorage.getContent()));
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<?> getAll() {
		List<FileStorage> list = fileStorageRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		
		try {
			FileStorage entity = new FileStorage();
			
			byte[] imageBytes = file.getBytes();
			entity.setContent(imageBytes);
			entity.setFileType(file.getContentType());
			entity.setFileName(file.getOriginalFilename());
			
			entity = fileStorageRepository.save(entity);
			System.out.print(entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

}