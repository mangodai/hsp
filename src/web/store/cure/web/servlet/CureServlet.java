package web.store.cure.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.store.cure.service.CureService;
import cn.itcast.servlet.BaseServlet;

public class CureServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private CureService service = new CureService();
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("cureList", service.findAll());
		return "f:/addPlan.jsp";
	}
	
}
