package web.store.tools;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LocaleFilter
 */
@WebFilter({ "/LocaleFilter", "/*" })
public class LocaleFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request; 
		Locale locale = (Locale) req.getSession().getAttribute("locale");
		if(locale == null){//从请求头获取Accept-Language:zh-CN,zh;q=0.8;
			locale = req.getLocale();
			req.getSession().setAttribute("locale", locale);
		}
//		System.out.println(locale.toString());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
    public LocaleFilter() {
    }
	public void destroy() {
	}
}
