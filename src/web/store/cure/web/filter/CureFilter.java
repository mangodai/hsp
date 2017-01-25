package web.store.cure.web.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CureFilter
 */
@WebFilter(urlPatterns = { "/CureFilter" }, servletNames = { "CureServlet" })
public class CureFilter implements Filter {
	private ServletContext sc = null;//第一域,出生到消亡
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String fileName = "addPlan.html";
		String pathURL = httpRequest.getContextPath()+"/"+fileName;
		String realPath = sc.getRealPath(fileName);
		File htmlFile = new File(realPath);
		htmlFile.setWritable(true, false);
		/**
		 * 如果页面存在就转发
		 */
		if(htmlFile.exists()){
			httpResponse.sendRedirect(pathURL);
			return;
		}
		/**
		 * 如果不存在就替换response的getWriter 输出服务器本地,转发静态页面
		 */
		StaticResponse sr = new StaticResponse(httpResponse, htmlFile.getAbsolutePath());
		/**
		 * 写入文件完成
		 */
		chain.doFilter(request, sr);
		/**
		 * 转发
		 */
		sr.close();
		httpResponse.sendRedirect(pathURL);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.sc = arg0.getServletContext();
	}


}
