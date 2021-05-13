package com.honsoft.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.honsoft.web.entity.User;
import com.honsoft.web.entity.Users;
import com.honsoft.web.mapper.mysql.UserMapper;
import com.honsoft.web.mapper.mysql.UsersMapper;

@Service
@Transactional
public class FooService {

	private final UserMapper userMapper;
	private final UsersMapper usersMapper;

	public FooService(UserMapper userMapper, UsersMapper usersMapper) {
		this.userMapper = userMapper;
		this.usersMapper = usersMapper;
	}

	public User doSomeBusinessStuff(String userId) {
		return this.userMapper.getUser(userId);
	}

	public List<Users> getUsersList() {
		return this.usersMapper.selectAll();
	}

}