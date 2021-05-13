package com.honsoft.web.mapper.mysql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.honsoft.web.entity.User;

public interface UserMapper {
	  @Select("SELECT * FROM users WHERE id = #{userId}")
	  User getUser(@Param("userId") String userId);
	}