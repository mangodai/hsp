package web.store.order.domain;

public class Order {
	private int oid;
	private String ordertime;
	private String total;
	private int state;
	private int user_id;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getUid() {
		return user_id;
	}
	public void setUid(int uid) {
		this.user_id = uid;
	}
	
}
