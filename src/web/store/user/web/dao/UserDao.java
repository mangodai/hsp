package web.store.user.web.dao;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import web.store.user.domain.User;
import web.store.user.filer.UserException;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public User resetPassword(User form ) throws UserException{
		String sql = "UPDATE t_user set user_password=PASSWORD(?) where email=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), form.getUser_password(), form.getEmail());
		} catch (SQLException e) {
			throw new UserException(e);
		}
		
	}
	
	/**
	 * 
	* @Title: Login 
	* @Description:  登录 
	* @param @param form
	* @param @return
	* @param @throws UserException    设定文件 
	* @return User    返回类型 
	* @throws
	 */
	public User Login(User form) throws UserException{
		String sql = "SELECT * FROM t_user WHERE email=? AND user_password=PASSWORD(?)";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), form.getEmail(), form.getUser_password());
		} catch (SQLException e) {
			throw new UserException("密码不对");
		}
	}
	/**
	 * 
	* @Title: addUser 
	* @Description: TODO 增加用户 
	* @param @param form    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addUser(User form) {
		Timestamp timestamp = new Timestamp(form.getUser_regist_time().getTime());
		String sql = "INSERT INTO `t_user` (`user_name`, `user_tel`, `user_password`, `user_regist_time`, `email`, `code`, `state`) VALUES(?,?,?,?,?,?,?)";
		Object[] obj = {form.getUser_name(), form.getUser_tel(), form.getUser_password(), timestamp, form.getEmail(), form.getCode(), form.getState()};
		try {
			qr.update(sql, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User findByCode(String code) {
		String sql = "SELECT * FROM t_user WHERE code=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			System.out.println("null");
			return null;
		}
	}
	
	public User findByID(int id) {
		String sql = "select * from t_user where user_id=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), id);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public User findByEmail(String email) {
		String sql = "select * from t_user where email=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public void updateState(User tmp) {
		String sql = "update t_user set state=1 where user_id=?";
		try {
			qr.update(sql, tmp.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
