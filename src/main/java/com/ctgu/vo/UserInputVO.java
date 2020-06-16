package com.ctgu.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UserInputVO
{
	private String userName;

	private String password;

	private String phoneNumber;

	private String email;

	private Integer gender;

	private Date birthDate;

	private String city;

	private String province;

	private String country;

	private String address;

}
