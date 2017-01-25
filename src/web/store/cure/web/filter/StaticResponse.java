package web.store.cure.web.filter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticResponse extends HttpServletResponseWrapper {
	private PrintWriter pw =null;
	
	public StaticResponse(HttpServletResponse response , String path) throws FileNotFoundException, UnsupportedEncodingException {
		super(response);
		this.pw = new PrintWriter(path,"UTF-8");
	}
	public PrintWriter getWriter(){
		return this.pw;
	}
	public void close(){
		this.pw.close();
	}

}
