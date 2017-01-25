package web.store.order.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.store.cart.domain.Cart;
import web.store.cart.domain.CartItem;
import web.store.order.domain.Order;
import web.store.order.domain.PageBean;
import web.store.order.service.OrderService;
import web.store.orderitem.domain.OrderItem;
import web.store.tools.NewTool;
import web.store.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderService();
	private NewTool news = new NewTool();
	
	/**
	 * 
	* @Title: planPage 
	* @Description: 其他页数 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String planPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String next = request.getParameter("nextPage");
		System.out.println(next);
		PageBean<Order> page = service.findAll(next);
		request.removeAttribute("pageBean");
		request.setAttribute("pageBean", page);
		return "f:/plan.jsp";
	}
	
	/**
	 * 
	* @Title: plan 
	* @Description: 第一页 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String plan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean<Order> page = service.findAll();
		request.setAttribute("pageBean", page);
		return "f:/plan.jsp";
	}

	/**
	 * 
	* @Title: checkout 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String checkout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理预定时间
		String reserveTime = request.getParameter("reserveTime");
		if(reserveTime == null){
			reserveTime = new Date().toString();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date reserve = null;
		try {
			reserve = sdf.parse(reserveTime);
			System.out.println(reserve);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//处理联系电话
		String tel = request.getParameter("phone");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		User user = (User) request.getSession().getAttribute("session_user");
		
		//创建一个新订单
		Order order = new Order();
		order.setOrdertime(new Date());
		order.setState(1);
		order.setTel(tel);
		order.setUser_id(user.getUser_id());
		order.setTotal(cart.getTotal());
		order.setOrder_uid(CommonUtils.uuid());
		order.setOrder_reserve(reserve);
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		// 循环遍历Cart中的所有CartItem，使用每一个CartItem对象创建OrderItem对象，并添加到集合中  但是缺少一个order_id
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem oi = new OrderItem();// 创建订单条目
			oi.setItem_count(cartItem.getCount());
			oi.setCure_id(cartItem.getCure().getCure_id());
			oi.setItem_total(cartItem.getSubtotal());//小计
			orderItemList.add(oi);
		}
		
		service.checkout(order, orderItemList);
		
		//下单成功,返回消息页码
		String content = MessageFormat.format(news.getValue("success_content"), tel, new Timestamp(order.getOrdertime().getTime()) );
		request.setAttribute("msg_title", news.getValue("success_title"));
		request.setAttribute("msg_content", content);
		cart.clear();
		return "f:/msg.jsp";
	}
	
}
