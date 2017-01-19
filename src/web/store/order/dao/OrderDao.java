package web.store.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import web.store.order.domain.Order;
import web.store.orderitem.domain.OrderItem;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public int findCount(){
		String sql = "select count(*) from orders";
		try {
			Number num = (Number) qr.query(sql, new ScalarHandler());
			return  num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException("订单总数查找失败");
		}
	}
	
	/**
	 * 
	* @Title: findByUUID 
	* @Description: 通过唯一uuid来寻找订单 
	* @param @param uuid
	* @param @return    设定文件 
	* @return Order    返回类型 
	* @throws
	 */
	public Order findByUUID(String uuid){
		String sql = "select * from orders where order_uid=?";
		try {
			return qr.query(sql, new BeanHandler<Order>(Order.class), uuid);
		} catch (SQLException e) {
			throw new RuntimeException("通过uuid 查找失败");
		}
	}
	
	/**
	 * 
	* @Title: findAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List<Order>    返回类型 
	* @throws
	 */
	public List<Order> findAll(int begin, int end){
		String sql = "SELECT * FROM orders ORDER BY oid DESC LIMIT ?,?";
		try {
			return qr.query(sql, new BeanListHandler<Order>(Order.class), begin, end);
		} catch (SQLException e) {
			throw new RuntimeException("全体订单查找失败");
		}
	}
	/**
	 * 增加
	 * @param order
	 * @return 
	 */
	public void addOrder(Order order){
		Timestamp re = new Timestamp(order.getOrder_reserve().getTime());
		String sql = "insert into orders(ordertime, total, state, user_id, tel, order_uid, order_reserve) values(?, ?, ?, ?, ?, ?, ?)";
		try {
		Timestamp time = new Timestamp(order.getOrdertime().getTime());
			qr.update(sql, time, order.getTotal(), 
					order.getState(), order.getUser_id(),
					order.getTel(),	order.getOrder_uid(),
					re);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 编辑Order
	 * @param Order
	 */
	public void editOrder(Order c){
//		String sql = "update orders set Order_cost=?,Order_extra=?,cid=?,Order_name=? where Order_id=?";
/*		try {
			qr.update(sql, c.getOrdertime(), c.getOrdertime(), c.getOrder_extra(), c.getCid(),c.getOrder_name(),c.getOrder_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public void addOrderItem(List<OrderItem> item) {
		String sql = "insert into order_item(item_count, item_total,oid,cure_id) value(?,?,?,?)";
		/*
		 * 把orderItemList转换成两维数组
		 *   把一个OrderItem对象转换成一个一维数组
		 */
		Object[][] params = new Object[item.size()][];
		// 循环遍历orderItemList，使用每个orderItem对象为params中每个一维数组赋值
		for(int i = 0 ; i < item.size() ; i++){
			params[i] = new Object[]{
				item.get(i).getItem_count(), item.get(i).getItem_total(), 
				item.get(i).getOid(), item.get(i).getCure_id()
			};
		}
		
		try {
			qr.batch(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("批处理插入失败");
		}
	}
	

	
	/**
	 * 
	* @Title: findByTime 
	* @Description: 由于时间下单时间是唯一的,所以我们用时间来查找 
	* @param @param ordertime
	* @param @return    设定文件 
	* @return Order    返回类型 
	* @throws
	 */
	public Order findByTime(Date ordertime) {
		String sql = "select * from orders where ordertime=?";
		try {
			return qr.query(sql, new BeanHandler<Order>(Order.class), new Timestamp(ordertime.getTime()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
