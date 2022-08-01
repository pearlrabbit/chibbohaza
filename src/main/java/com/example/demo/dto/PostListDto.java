package com.example.demo.dto;

import lombok.Data;

@Data
public class PostListDto {
	
	private int postNum;
	private String postTitle;
	private String userNick;
	private int postHit;
}
