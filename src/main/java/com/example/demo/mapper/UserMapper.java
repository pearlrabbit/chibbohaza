package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.User;

@Mapper
public interface UserMapper {
	
	public List<User> findAll();
	
	public void insert(User user);
	
	public int getNextUserNum();
	
	public User loginCheck(User user);

	public User findByUserNum(int userNum);
	
}
