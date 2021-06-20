package com.honsoft.web.mapper.h2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.honsoft.web.entity.User;

//@Component("h2UserMapper")
public interface UserMapper {
	  @Select("SELECT * FROM users WHERE id = #{userId}")
	  User getUser(@Param("userId") String userId);
	}