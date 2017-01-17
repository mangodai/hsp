package web.store.user.domain;

import java.util.Date;

public class User {
	private String user_name;
	private int user_id;
	private int user_tel;
	private String user_privilege;//系统分配
	private String user_password;
	private Date user_regist_time;//系统分配
	private String email;
	private String code;//系统分配 
	private int state;//系统控制   0 未激活 1激活
	
	@Override
	public String toString() {
		return "User [user_name=" + user_name + ", user_id=" + user_id
				+ ", user_tel=" + user_tel + ", user_privilege="
				+ user_privilege + ", user_password=" + user_password
				+ ", user_regist_time=" + user_regist_time + ", email=" + email
				+ ", code=" + code + ", state=" + state + "]";
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(int user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_privilege() {
		return user_privilege;
	}
	public void setUser_privilege(String user_privilege) {
		this.user_privilege = user_privilege;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public Date getUser_regist_time() {
		return user_regist_time;
	}
	public void setUser_regist_time(Date user_regist_time) {
		this.user_regist_time = user_regist_time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
