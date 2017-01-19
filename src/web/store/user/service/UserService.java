package web.store.user.service;

import java.sql.SQLException;

import web.store.user.dao.UserDao;
import web.store.user.domain.User;
import web.store.user.web.filer.UserException;
import cn.itcast.jdbc.JdbcUtils;

/**
 * 
* @ClassName: UserService 
* @Description: TODO
* @author MangoDai 96555734@qq.com
* @date 2017年1月16日 下午6:16:07 
*
 */
public class UserService {
	private UserDao dao = new UserDao();
	
	public void forgot(User form) throws UserException, SQLException{
		User tmp = dao.findByEmail(form.getEmail());
		if(tmp == null){
			throw new UserException("用户名不存在");
		}
		tmp.setState(0);
		try {//当你要修改用户的State 时候开启事务
			JdbcUtils.beginTransaction();
			dao.updateState(tmp);
			tmp.setUser_password(form.getUser_password());
			dao.resetPassword(tmp);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			JdbcUtils.rollbackTransaction();
			e.printStackTrace();
		}	
	}
	
	public User login(User form) throws UserException{
		System.out.println(form.toString());
		User tmp = dao.findByEmail(form.getEmail());
		if(tmp == null){
			throw new UserException("用户名不存在");
		}
		tmp = dao.Login(form);
		if(tmp == null){
			throw new UserException("密码错误");
		}
		return tmp;
	}
	
	
	/**
	 * 
	* @Title: addUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param form    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addUser(User form) {
		dao.addUser(form);
	}
	public void active(String email) throws UserException {
		System.out.println(email);
		User tmp = dao.findByEmail(email);
		if(tmp == null ){
			throw new UserException("利用该验证码没有找到对象,请重新验证");
		}
		if(! tmp.getEmail().equals(email) ){
			throw new UserException("你的验证码是错误的,请重新验证");
		}
		if(tmp.getState() == 1)
			throw new UserException("已经激活过了,别点了");
		tmp.setState(1);
		dao.updateState(tmp);
	}
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
	}

}
