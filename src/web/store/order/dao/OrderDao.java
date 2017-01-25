package web.store.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import web.store.order.domain.ItemDetail;
import web.store.order.domain.Order;
import web.store.order.web.exception.DeleteOrderException;
import web.store.order.web.exception.OrderException;
import web.store.orderitem.domain.OrderItem;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<ItemDetail> findItemByOid(String oid){
		String sql = "select * from item_details where oid=?";
		try {
			return qr.query(sql, new BeanListHandler<ItemDetail>(ItemDetail.class), oid);
		} catch (SQLException e) {
			throw new OrderException("通过oid查找子项目失败");
		}
	}
	
	
	/**
	 * 
	* @Title: findOrderByOid 
	* @Description: 通过oid 查找订单 
	* @param @param oid
	* @param @return    设定文件 
	* @return Order    返回类型 
	* @throws
	 */
	public Order findOrderByOid(String oid){
		String sql = "select * from orders where oid=?";
		try {
			return qr.query(sql, new BeanHandler<Order>(Order.class) , oid);
		} catch (SQLException e) {
			throw new OrderException("通过oid 没有找到订单");
		}
	}
	
	
	/**
	 * 
	* @Title: findCount 
	* @Description: 计算数据库总共个数 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
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
		String sql = "SELECT * FROM orders ORDER BY order_reserve DESC LIMIT ?,?";
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


	public OrderItem findItemByItem_id(String item_id) {
		String sql = "select * from order_item where item_id=?";
		try {
			return qr.query(sql, new BeanHandler<OrderItem>(OrderItem.class),item_id);
		} catch (Exception e) {
			throw new OrderException("通过item_id没有找到item");
		}
	}


	public void deleteItem(int item_id) {
		String sql = "delete from order_item where item_id=?";
		try {
			qr.update(sql , item_id);
		} catch (SQLException e) {
			throw new OrderException("通过item_id删除item失败");
		}
	}


	public void updateItem(OrderItem item) {
		String sql = "update order_item set item_count=? , item_total=? where item_id=?";
		try {
			qr.update(sql, item.getItem_count(), item.getItem_total(), item.getItem_id());
		} catch (SQLException e) {
			throw new OrderException("更新item失败");
		}
	}

	public double getOrderItemsTotal(String oid){
		String sql = "select sum(item_total) from order_item where oid=?";
		try {
			Number num = (Number)qr.query(sql,new ScalarHandler(),oid);
			if(num == null){
				return 0;
			} else {
				return num.doubleValue();
			}
		} catch (SQLException e) {
			throw new OrderException("查询所有子项目总金额失败");
		}
	}
	
	public void updateOrderTotal(String oid) {
		double total = getOrderItemsTotal(oid);
//		if(total ==  )
		String updateTotal = "update orders set total=? where oid=?";
		try {
			qr.update(updateTotal , total, oid);
		} catch (SQLException e) {
			throw new OrderException("更新父项目总金额失败");
		}
	}


	public void deleteOrder(int oid) throws DeleteOrderException {
		String sql = "delete from orders where oid = ?";
		try {
			qr.update(sql, oid);
		} catch (SQLException e) {
			throw new DeleteOrderException("删除项目失败");
		}
		
	}


	public int findOrderItemCount(String oid) {
		String sql = "select count(item_id) from order_item where oid=?";
		try {
			Number num = (Number) qr.query(sql, new ScalarHandler(),oid);
			return num.intValue();
		} catch (SQLException e) {
			throw new OrderException("查找子项目数量失败");
		}
		
	}


	public OrderItem queryOrderItemByOidANDCure_id(String oid, String cure_id) {
		String sql = "SELECT * FROM order_item WHERE oid=? AND cure_id=?";
		try {
			return qr.query(sql, new BeanHandler<OrderItem>(OrderItem.class), oid, cure_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("通过oid,cure_id查询项目失败");
		}
	}


	public void addOrderItem(OrderItem item) {
		String sql = "insert into order_item(item_count, item_total,oid,cure_id) value(?,?,?,?)";
		try {
			qr.update(sql,item.getItem_count(), item.getItem_total(), item.getOid(), item.getCure_id());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("增加订单中的理疗失败");
		}
	}


}
