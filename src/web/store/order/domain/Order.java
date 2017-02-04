package web.store.order.domain;

import java.util.Date;
import java.util.List;

import web.store.orderitem.domain.OrderItem;


public class Order {
	private int oid;
	private Date ordertime;
	private double total;
	private int state;
	private int user_id;
	private String tel;
	private String order_uid;
	private Date order_reserve;
	private int item_counts;
	private String user_name;
	private List<OrderItem> orderItemList;//当前订单下所有条目
	private List<ItemDetail> itemDetailList;//当前订单下所有条目
	
	
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getItem_counts() {
		return item_counts;
	}

	public void setItem_counts(int item_counts) {
		this.item_counts = item_counts;
	}

	public List<ItemDetail> getItemDetailList() {
		return itemDetailList;
	}

	public void setItemDetailList(List<ItemDetail> itemDetailList) {
		this.itemDetailList = itemDetailList;
	}

	public List<OrderItem> getOrderItemList() {
		
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOrder_uid() {
		return order_uid;
	}
	public void setOrder_uid(String order_uid) {
		this.order_uid = order_uid;
	}
	public Date getOrder_reserve() {
		return order_reserve;
	}
	public void setOrder_reserve(Date order_reserve) {
		this.order_reserve = order_reserve;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total="
				+ total + ", state=" + state + ", user_id=" + user_id
				+ ", tel=" + tel + ", order_uid=" + order_uid
				+ ", order_reserve=" + order_reserve + "]";
	}
	

	
	
}
