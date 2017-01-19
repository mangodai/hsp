package web.store.order.service;

import java.sql.SQLException;
import java.util.List;

import web.store.order.dao.OrderDao;
import web.store.order.domain.Order;
import web.store.order.domain.PageBean;
import web.store.orderitem.domain.OrderItem;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao dao = new OrderDao();
	private PageBean<Order> pageBean = new PageBean<Order>();
	
	private void caculatePage(PageBean<Order> pageBean){
		if(pageBean.getPageLength() > pageBean.getAllPage()){  //总页码 不足默认时候
			pageBean.setBeginPage(1);
			pageBean.setEndPage(pageBean.getAllPage());
		} else {
			//计算开头
			if( (pageBean.getNowPage()-5) < 1){  //现在页码在前五页的时候
				pageBean.setBeginPage(1);
				pageBean.setEndPage(10);
			} else {
				//计算结尾
				pageBean.setBeginPage(pageBean.getNowPage()-5);
				if( (pageBean.getNowPage()+4) > pageBean.getAllPage() ){//当在最后几页的时候
					pageBean.setEndPage(pageBean.getAllPage());
					pageBean.setBeginPage(pageBean.getAllPage()-9);
				} else{
					pageBean.setEndPage(pageBean.getNowPage()+4);
				}
			}
		}
	}
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 利用页码查找 
	* @param @param next
	* @param @return    设定文件 
	* @return PageBean<Order>    返回类型 
	* @throws
	 */
	public PageBean<Order> findAll(String next) {
		int begin = Integer.parseInt(next);
		pageBean.setNowPage(begin);//更新当前页
		begin = (begin-1)*pageBean.getPageSize(); //如果next为0 那么begin为 0   
		int end = pageBean.getPageSize();
		pageBean.setList(dao.findAll( begin , end ));
		caculatePage(pageBean);
		return pageBean;
	}
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 开启首页 
	* @param @return    设定文件 
	* @return PageBean<Order>    返回类型 
	* @throws
	 */
	public PageBean<Order> findAll(){
		pageBean.setBeanTotal(dao.findCount());
		pageBean.setPageSize(8);//自定义每页数据
		pageBean.setNowPage(1);//查询全部时候默认为1
		pageBean.setAllPage(pageBean.getBeanTotal()/pageBean.getPageSize());
		if(pageBean.getBeanTotal()%pageBean.getPageSize() != 0){ //剩余的数据
			pageBean.setAllPage(pageBean.getAllPage()+1);
		}
		int begin = (pageBean.getNowPage()-1)*pageBean.getPageSize();
		int end = pageBean.getPageSize();
		pageBean.setList(dao.findAll( begin , end ));
		caculatePage(pageBean);
		return pageBean;
	}
	
	/**
	 * 
	* @Title: checkout 
	* @Description: 下单业务层实现
	* @param @param order
	* @param @param item    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void checkout(Order order , List<OrderItem> item){
		try {//开启事务
			JdbcUtils.beginTransaction();
			dao.addOrder(order);
			//处理没有设置的订单时间
//			System.out.println(order.getOrdertime());
//			System.out.println(order.getOrder_uid());
			Order tmp = dao.findByUUID(order.getOrder_uid());
			System.out.println(tmp);
			for(OrderItem i : item){
				i.setOid(tmp.getOid());
				System.out.println(i.toString());
			}
			
//			System.out.println(item);
			
			dao.addOrderItem(item);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}


}
