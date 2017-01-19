package web.store.user.web.filer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import web.store.user.domain.User;

/**
 * Servlet Filter implementation class LoginFilter1
 */
@WebFilter(
		urlPatterns = { 
				"/LoginFilter1", 
				"/checkout.jsp"
		}, 
		servletNames = { "CartServlet" })
public class LoginFilter1 implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User)httpRequest.getSession().getAttribute("session_user");
		if(user != null){
			chain.doFilter(request, response);
		} else {
			httpRequest.setAttribute("errors", "您还没有登录");
			httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest , response);
		}
	}


	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
