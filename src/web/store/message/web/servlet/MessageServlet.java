package web.store.message.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import web.store.message.domain.Message;
import web.store.message.service.MessageService;
import web.store.tools.NewTool;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class MessageServlet extends BaseServlet {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	private MessageService service = new MessageService();
	private NewTool news = new NewTool();//获取参数
	/**
	 * 
	* @Title: addMessage 
	* @Description: TODO 前段发送信息过来 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String addMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Message m =  CommonUtils.toBean(request.getParameterMap(),Message.class);                
		m.setMid(CommonUtils.uuid());
		System.out.println(m.toString());
		service.addMessage(m);
		request.setAttribute("msg_title",	news.getValue("mail_title"));
		request.setAttribute("msg_content",	news.getValue("mail_content"));
		return "f:/msg.jsp";
	}
	
	@Test
	public void fun1(){
		
		System.out.println(news.getValue("mail_title"));
	}
}
