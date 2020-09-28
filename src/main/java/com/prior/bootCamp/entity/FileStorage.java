package com.prior.bootCamp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class FileStorage {

	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
    private byte[] content;
	
	private String fileName;
	
	private String fileType;
}
