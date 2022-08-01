package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public List<User> findAll(){
		return userMapper.findAll();
	}
	
	public void insertUser(User user) {
		userMapper.insert(user);
	}
	
	public int getNextUserNum() {
		return userMapper.getNextUserNum();
	}
	
	public User loginCheck(User user) {
		return userMapper.loginCheck(user);
	}

	public User findByUserNum(int userNum) {
		return userMapper.findByUserNum(userNum);
	}
}
