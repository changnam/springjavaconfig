package com.honsoft.web.entity;

import javax.annotation.Generated;

public class Users {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column users.id
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column users.name
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column users.email
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	private String email;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column users.id
	 * @return  the value of users.id
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column users.id
	 * @param id  the value for users.id
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column users.name
	 * @return  the value of users.name
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column users.name
	 * @param name  the value for users.name
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column users.email
	 * @return  the value of users.email
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column users.email
	 * @param email  the value for users.email
	 * @mbg.generated  Fri May 14 08:38:47 KST 2021
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}