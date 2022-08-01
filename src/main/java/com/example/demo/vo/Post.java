package com.example.demo.vo;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@EntityScan
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
	
	private int postNum;
	private int userNum;
	private String postTitle;
	private String postContent;
	private int postHit;
	private Date postRegdate;
	private Date postUpdatedate;
	private int boardNum;
}
