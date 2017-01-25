package web.store.order.service;

import java.sql.SQLException;
import java.util.List;

import web.store.cure.dao.CureDao;
import web.store.cure.domain.Cure;
import web.store.order.dao.OrderDao;
import web.store.order.domain.Order;
import web.store.order.domain.PageBean;
import web.store.order.web.exception.DeleteOrderException;
import web.store.order.web.exception.OrderException;
import web.store.orderitem.domain.OrderItem;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao dao = new OrderDao();
	private PageBean<Order> pageBean = new PageBean<Order>();
	private CureDao cureDao = new CureDao();
	public boolean deleteOrder(String oid){
		
		try {
			dao.deleteOrder(Integer.parseInt(oid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (DeleteOrderException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public int findOrderItemCount(String oid){
		return dao.findOrderItemCount(oid);
	}
	
	public Order findOrderById(String oid){
		return dao.findOrderByOid(oid);
	}
	
	public OrderItem findItemByItem_id(String item_id){
		return dao.findItemByItem_id(item_id);
	}
	/**
	 * 
	* @Title: deleteItem 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param item_id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public OrderItem deleteItem(String item_id){
		//子项目是否存在
		OrderItem item = null;
		try {
			JdbcUtils.beginTransaction();
			item = dao.findItemByItem_id(item_id);
			if(item == null){  //如果不存在,就要跑出异常没有找到子项目
				return null;
			} else {//更改子项目时候,需要修改order 的价格total
				if(item.getItem_count() == 1){ //如果只有一个就全部删掉
					dao.deleteItem(item.getItem_id());
				} else { // 记得修改单价
					int count = item.getItem_count();
					double avg = item.getItem_total()/ (double) count;
					item.setItem_total(avg * (double)(--count) );
					item.setItem_count(count);
					dao.updateItem(item);
				}
			}
			
			//更新完理疗项目后,需要更新订单价格
			dao.updateOrderTotal(""+item.getOid());
			//之后就是判断金额了,如果子项目 都没有了 就删除订单;
			//TODO 继续下去
//			if()
//			dao.deleteOrder(item.getOid());
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new OrderException("更新某项订单失败:" + item_id);
			}
		}
		return item;
	}
	
	/**
	 * 
	* @Title: findByOid 
	* @Description: 通过oid查询,并且插入视图子项目 
	* @param @param oid
	* @param @return    设定文件 
	* @return Order    返回类型 
	* @throws
	 */
	public Order findByOid(String oid){
		Order order = null;
		try {
			JdbcUtils.beginTransaction();
			order = dao.findOrderByOid(oid);
			order.setItemDetailList(dao.findItemByOid(oid));
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		
		
		
		return order;
	}
	
	/**
	 * 
	* @Title: caculatePage 
	* @Description: 计算页码私有方法 
	* @param @param pageBean    设定文件 
	* @return void    返回类型 
	* @throws
	 */
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
	
	/**
	 * 
	* @Title: getItem_count 
	* @Description: 的到订单中项目的个数 
	* @param @param oid    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public int getItem_count(String oid) {
		return dao.findOrderItemCount(oid);
	}

	/**
	 * 
	* @Title: addOrderItem 
	* @Description: 增加订单 
	* @param @param oid
	* @param @param cure_id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addOrderItem(String oid, String cure_id) {
		OrderItem item = dao.queryOrderItemByOidANDCure_id(oid, cure_id);
		Cure cure = cureDao.findByID(cure_id);
		if(item == null){ //子订单不纯在该项目
			item = new OrderItem();
			item.setCure_id(cure.getCure_id());
			item.setItem_count(1);
			item.setOid(Integer.parseInt(oid));
			item.setItem_total(cure.getCure_cost());
			dao.addOrderItem(item);
		} else {
			int count = item.getItem_count();
			double avg = item.getItem_total() / (double) count;
			item.setItem_count( ++count );
			item.setItem_total( avg*(double)count );
			dao.updateItem(item);
		}
		dao.updateOrderTotal(oid);
	}


}
