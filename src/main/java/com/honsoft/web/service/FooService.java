package com.honsoft.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.honsoft.web.entity.User;
import com.honsoft.web.mapper.mysql.UserMapper;

@Service
@Transactional
public class FooService {

	private final UserMapper userMapper;

	public FooService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User doSomeBusinessStuff(String userId) {
		return this.userMapper.getUser(userId);
	}

}