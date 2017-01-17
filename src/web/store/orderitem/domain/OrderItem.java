package web.store.orderitem.domain;

public class OrderItem {
	private int item_id;
	private int count;
	private String total;
	private int oid;
	private int cure_id;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getCure_id() {
		return cure_id;
	}
	public void setCure_id(int cure_id) {
		this.cure_id = cure_id;
	}
	
}
