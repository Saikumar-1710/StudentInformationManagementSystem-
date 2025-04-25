package com.example.pojo;

public class UserAccount {
	
	private long userId;
	private String uname;
	private String password;
	private String email;
	private long mobile;
	
	public UserAccount(long userId, String uname, String password, String email, long mobile) {
		super();
		this.userId = userId;
		this.uname = uname;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
	}

	public UserAccount() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserAccount [userId=" + userId + ", uname=" + uname + ", password=" + password + ", email=" + email
				+ ", mobile=" + mobile + "]";
	}

	
	
}
