package web.store.order.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.store.cure.service.CureService;
import web.store.order.domain.Order;
import web.store.order.service.OrderService;
import web.store.orderitem.domain.OrderItem;
import cn.itcast.servlet.BaseServlet;

public class EditOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderService();
	private CureService cureSerivce = new CureService();
	
	
	/**
	 * 
	* @Title: addOrderItem 
	* @Description: 增加订单中的理疗 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String addOrderItem(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		String oid = request.getParameter("oid");
		String cure_id = request.getParameter("cure_id");
		System.out.println("oid = " + oid);
		System.out.println("cure_id =  " +cure_id);
		//TODO 增加订单,一直需要增加的cure 和 订单oid
		service.addOrderItem(oid,cure_id);
		Order order = service.findByOid(oid);
		order.setItem_counts( service.getItem_count(oid) );
		request.setAttribute("order", order);
		request.setAttribute("cureList", cureSerivce.findAll());
		return "f:/indent.jsp";
	}
	
	/**
	 * 
	* @Title: deleteOrder 
	* @Description: 删除订单 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		boolean flag = service.deleteOrder(oid);
		if(flag == true){
			request.setAttribute("msg_title", "删除成功");
			request.setAttribute("msg_content", "你删除了一个空订单");
		} else {
			request.setAttribute("msg_title", "删除订单事变");
			request.setAttribute("msg_content", "你删除了错误的订单");
		}
		return "f:/msg.jsp";
	}

	/**
	 * 
	* @Title: deleteitem 
	* @Description: 删除某项订单 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String deleteitem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String item_id = request.getParameter("item_id");
		System.out.println("item_id="+item_id);
		OrderItem item = service.deleteItem(item_id);
		if( item == null){
			request.setAttribute("msg_title", "修改失败");
			request.setAttribute("msg_content", "你给的item_id : "+item_id+" 修改订单失败!");
			return "f:/msg.jsp";
		}	
		Order order = service.findByOid(""+item.getOid());
		order.setItem_counts( service.getItem_count(""+item.getOid()) );
		request.setAttribute("order", order);
		request.setAttribute("cureList", cureSerivce.findAll());
		return "f:/indent.jsp";
	}
	
	/**
	 * 
	* @Title: Edit 
	* @Description: 编辑订单 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String oid = request.getParameter("oid");
		Order order = service.findByOid(oid);
		if(order == null){
			request.setAttribute("msg_title", "查看子项目失败");
			request.setAttribute("msg_content", "你给的oid : "+oid+" 查找的订单不存在!");
			return "f:/msg.jsp";
		}
		order.setItem_counts( service.getItem_count(""+order.getOid()) );
		request.setAttribute("cureList", cureSerivce.findAll());
		request.setAttribute("order", order);
		return "f:/indent.jsp";
	}
	
	
}
