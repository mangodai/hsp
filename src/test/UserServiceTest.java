package test;

import org.junit.Test;

import web.store.user.domain.User;
import web.store.user.service.UserService;
import web.store.user.web.filer.UserException;


public class UserServiceTest {
	private UserService service = new UserService();
	@Test
	public void loginTest()
	{
		User tmp = new User();
		tmp.setEmail("965557340@qq.com");
		tmp.setUser_password("qwe");
		try {
			tmp = service.login(tmp);
		} catch (UserException e) {
			System.out.println("我去你妈");
		}
		System.out.println(tmp);
	}
	
	@Test 
	public void forgotTest(){
		User tmp = new User();
		tmp.setEmail("965557340@qq.com");
		tmp.setUser_password("zxc");
		try {
			service.forgot(tmp);
		} catch (Exception e) {
			System.out.println("我去你妈");
		}
		System.out.println(tmp);
	}
}
