package com.example.demo.vo;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostFile {
	
	private int postFileNum;
	private int postNum;
	private int isDeleted;
	private String fileName;
	private String savedFileName;
	private String filePathUrl;
	
	
}
