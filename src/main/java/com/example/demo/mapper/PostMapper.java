package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Post;

@Mapper
public interface PostMapper {

	public void insertPost(Post post);
	
	public int getNextPostNum();
	
	public Post detailPost(int postNum);
	
	public void updatePost(Post post);

	public void deletePost(int postNum);

	public List<Post> findAll();
	
	public void plusPostHit(int postNum);
	
	public List<Post> findByBoardNum(int boardNum);
	
}
