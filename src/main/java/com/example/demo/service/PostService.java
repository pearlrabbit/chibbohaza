package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.PostMapper;
import com.example.demo.vo.Post;

@Service
public class PostService {

	@Autowired
	private PostMapper postMapper;

	public void insertPost(Post post) {
		postMapper.insertPost(post);
	}

	public int getNextPostNum() {
		return postMapper.getNextPostNum();
	}

	public Post detailPost(int postNum) {
		return postMapper.detailPost(postNum);
	}

	public void updatePost(Post post) {
		postMapper.updatePost(post);
	}

	public void deletePost(int postNum) {
		postMapper.deletePost(postNum);

	}

	public List<Post> findAll() {
		return postMapper.findAll();
	}

	public void plusPostHit(int postNum) {
		postMapper.plusPostHit(postNum);
	}

	public List<Post> findByBoardNum(int boardNum) {
		return postMapper.findByBoardNum(boardNum);
	}

}
