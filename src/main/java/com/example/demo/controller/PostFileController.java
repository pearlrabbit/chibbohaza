package com.example.demo.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.PostFileService;

@Controller
public class PostFileController {

	@Autowired
	private PostFileService pfs;

	@GetMapping("/deletePostFile/{postFileNum}")
	public void deletePostFile(@PathVariable int postFileNum) {
		pfs.deletePostFile(postFileNum);
		// 나중에 서버 이미지까지 삭제 처리 구현 할 것.
		String root = "/Users/feelj/boardImage";
		new File(root + "\\" + pfs.findByPostFileNum(postFileNum).getSavedFileName()).delete();
	}
}
