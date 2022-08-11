package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.PostFile;

@Mapper
public interface PostFileMapper {

	public void insertPostFile(PostFile postfile);

	public int getNextPostFileNum();

	public List<PostFile> detailPostFile(int postNum);

	public void deletePostFile(int postFileNum);

	public void deletePostFileAll(int postNum);

	public PostFile findByPostFileNum(int postFileNum);

	public List<PostFile> findByPostNum(int postNum);

}
