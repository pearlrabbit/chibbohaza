package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.PostFileMapper;
import com.example.demo.vo.Post;
import com.example.demo.vo.PostFile;

@Service
public class PostFileService {
	
	@Autowired
	private PostFileMapper postFileMapper;

	public void insertPostFile(PostFile postfile) {
		postFileMapper.insertPostFile(postfile);
	}
	
	public int getNextPostNum() {
		return postFileMapper.getNextPostFileNum();
	}
	
	public List<PostFile> detailPostFile(int postNum) {
		return postFileMapper.detailPostFile(postNum);
	}

	public void deletePostFile(int postFileNum) {
		postFileMapper.deletePostFile(postFileNum);
	}
	
}
