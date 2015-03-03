package com.waylau.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User bean
 * 
 * @author waylau.com
 * 2015年3月3日
 */
@XmlRootElement
public class UserBean {

	private int userId;
	private String name;
	private int age;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
