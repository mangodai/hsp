package web.store.user.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.store.cart.domain.Cart;
import web.store.tools.NewTool;
import web.store.user.domain.User;
import web.store.user.service.UserService;
import web.store.user.web.filer.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.vcode.utils.VerifyCode;
/**
 * 
* @ClassName: UserServlet 
* @Description: TODO 用户操作
* @author MangoDai 96555734@qq.com
* @date 2017年1月16日 下午5:33:46 
*
 */
public class UserServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	private NewTool news = new NewTool();
	
	public void setLocale(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lang = request.getParameter("locale");
		Locale locale = null;
		if(lang.equals("en")){
			locale = Locale.US;
		} else {
			locale = Locale.CHINA;
		}
		//默认中文
		request.getSession().setAttribute("locale", locale);
	}
	
	
	public void getVerify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VerifyCode vc = new VerifyCode(); 
		BufferedImage image = vc.getImage();
		request.getSession().setAttribute("verify_code", vc.getText());
		VerifyCode.output(image, response.getOutputStream());
	}
	
	/**
	 * 
	* @Title: reSendMail 
	* @Description:  重新发送一份邮件 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String reSendMail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User tmp = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			sendMail(tmp);
			request.setAttribute("msg_title", news.getValue("reSendMail_title"));
			String reSend = MessageFormat.format(news.getValue("email_content"), tmp.getEmail());
			request.setAttribute("msg_content", reSend);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "f:/msg.jsp";
	}
	
	/**
	 * 
	* @Title: quit 
	* @Description: 退出登录  
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "f:/index.jsp";
	}
	
	/**
	 * 
	* @Title: forgot 
	* @Description: TODO 忘记密码 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String forgot(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		System.out.println(form);
		try {
			service.forgot(form);
		} catch (Exception e) {
			request.setAttribute("errors", e);
			return "f:/forgot.jsp";
		}
		
		try {
			sendMail(form);//能走到这一步,代表邮箱和密码是正确的
			request.setAttribute("msg_title", news.getValue("forgot_title"));
			String reSend = MessageFormat.format(news.getValue("forgot_content"), form.getEmail());
			request.setAttribute("msg_content", reSend);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "f:/msg.jsp";
	}
	/**
	 * 
	* @Title: active 
	* @Description: TODO 
	* @param @param request
	* @param @param response
	* @param @return 转发到登录首页
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User form = CommonUtils.toBean(request.getParameterMap() ,User.class);
		
		try {
			User tmp = service.login(form);
			request.getSession().setAttribute("session_user", tmp);
			request.getSession().setAttribute("cart", new Cart());
		} catch (UserException e) {
			request.setAttribute("errors", e.getMessage());
			return "f:/login.jsp";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		request.getSession().setAttribute("nowDate", sdf.format(new Date()));
		return "f:/index.jsp";
	}
	/**
	 * 
	* @Title: active 
	* @Description: TODO 激活方法 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		request.setAttribute("msg_title", news.getValue("active_title"));
		try {
			service.active(email);
			request.setAttribute("msg_content", news.getValue("acitve_successfully"));			
		} catch (Exception e) {
			request.setAttribute("msg_content", news.getValue("acitve_unsuccessfully")+e.getMessage());
		}
		
		return "f:/msg.jsp";
	}
	
	/**
	 * 
	* @Title: addUser 
	* @Description: TODO 增加用户 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		form.setState(0);
		form.setUser_regist_time(new Date());
		System.out.println(form.toString());
		Map<String,String> errors = testForm(form);
		if(errors.size() > 0) {
			// 1. 保存错误信息
			// 3. 转发到regist.jsp
			request.setAttribute("errors",errors);
			System.out.println(errors.toString());
			return "f:/register.jsp";
		}
		service.addUser(form);
		try {
			sendMail(form);
		} catch (MessagingException e) {
			request.setAttribute("msg_title", news.getValue("email_title"));
			String reSend = MessageFormat.format(news.getValue("email_content"), form.getEmail());
			request.setAttribute("msg_content", reSend);
			return "f:/msg.jsp";
		}
		
		request.setAttribute("msg_title", news.getValue("regist_title"));
		request.setAttribute("msg_content", news.getValue("regist_content"));
		return "f:/msg.jsp";
	}

	private Map<String,String> testForm(User form) {
		/*
		 * 输入校验
		 * 1. 创建一个Map，用来封装错误信息，其中key为表单字段名称，值为错误信息
		 */
		Map<String,String> errors = new HashMap<String,String>();
		/*
		 * 2. 获取form中的username、password、email进行校验
		 */
		String username = form.getUser_name();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名长度必须在3~10之间！");
		}
		
		String password = form.getUser_password();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码长度必须在3~10之间！");
		}
		
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if(!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "Email格式错误！");
		}
		return errors;
	}
	
	/**
	 * 
	* @Title: sendMail 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param form
	* @param @throws IOException
	* @param @throws MessagingException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void sendMail(User form) throws IOException, MessagingException {
		/*
		 * 发邮件
		 * 准备配置文件！
		 */
		// 获取配置文件内容
		String host = news.getValue("host");//获取服务器主机
		String uname = news.getValue("uname");//获取用户名
		String pwd = news.getValue("pwd");//获取密码
		String from = news.getValue("from");//获取发件人
		String to = form.getEmail();//获取收件人
		String subject = news.getValue("subject");//获取主题
		String content = news.getValue("content");//获取邮件内容
		content = MessageFormat.format(content, form.getCode(),form.getEmail());//替换{0}{1}
		
		Session session = MailUtils.createSession(host, uname, pwd);//得到session
		Mail mail = new Mail(from, to, subject, content);//创建邮件对象
		MailUtils.send(session, mail);//发邮件！
	}
	
}
