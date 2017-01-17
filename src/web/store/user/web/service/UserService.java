package web.store.user.web.service;

import web.store.user.domain.User;
import web.store.user.filer.UserException;
import web.store.user.web.dao.UserDao;

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
	
	public User forgot(User form) throws UserException{
		User tmp = dao.findByEmail(form.getEmail());
		if(tmp == null){
			throw new UserException("用户名不存在");
		}
		return dao.resetPassword(tmp);		
	}
	
	public User login(User form) throws UserException{
		System.out.println(form.toString());
		User tmp = dao.findByEmail(form.getEmail());
		if(tmp == null){
			throw new UserException("用户名不存在");
		}
		return dao.Login(form);
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
	public void active(String code) throws UserException {
		User tmp = dao.findByCode(code);
		if(tmp == null ){
			throw new UserException("利用该验证码没有找到对象,请重新验证");
		}
		if(!tmp.getCode().equals(code) ){
			throw new UserException("你的验证码是错误的,请重新验证");
		}
		if(tmp.getState() == 1)
			throw new UserException("已经激活过了,别点了");
		dao.updateState(tmp);
	}
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
	}

}
