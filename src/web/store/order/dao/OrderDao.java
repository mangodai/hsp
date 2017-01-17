package web.store.order.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import web.store.order.domain.Order;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Order> findAll(){
		String sql = "select * from Order";
		try {
			return qr.query(sql, new BeanListHandler<Order>(Order.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 增加
	 * @param c
	 */
	public void addOrder(Order c){
		String sql = "insert into Order(Order_cost,Order_extra,cid,Order_name,image) values(?, ?, ?, ?, ?)";
/*		try {
			qr.update(sql, c.getOrdertime(), c.getOrder_extra(), c.getCid(), c.getOrder_name(), c.getImage());
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * 编辑Order
	 * @param Order
	 */
	public void editOrder(Order c){
		String sql = "update Order set Order_cost=?,Order_extra=?,cid=?,Order_name=? where Order_id=?";
/*		try {
			qr.update(sql, c.getOrdertime(), c.getOrdertime(), c.getOrder_extra(), c.getCid(),c.getOrder_name(),c.getOrder_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
