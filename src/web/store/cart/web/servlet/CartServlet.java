package web.store.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.store.cart.domain.Cart;
import web.store.cart.domain.CartItem;
import web.store.cure.domain.Cure;
import web.store.cure.service.CureService;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private CureService service = new CureService();
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除某一项 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.remove(request.getParameter("cure_id"));
		System.out.println("");
		return "f:/checkout.jsp";
	}
	
	/**
	 * 
	* @Title: clear 
	* @Description: 清空购物车 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/checkout.jsp";
	}
	
	/**
	 * 
	* @Title: addCure 
	* @Description:  添加项目到购物车 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String addCure(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");//获得购物车
		String id = request.getParameter("cure_id");//增加的id
		Cure c = service.findByID(id);//
		
		CartItem cartItem = new CartItem();//创建一个新的条目
		cartItem.setCure(c);
		cartItem.setCount(1);
		//添加到购物车
		cart.add(cartItem);
		return "f:/checkout.jsp";
	}
	
	
	
}
