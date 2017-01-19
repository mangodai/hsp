package web.store.orderitem.domain;

public class OrderItem {
	private int item_id;
	private int item_count;
	private double item_total;
	private int oid;
	private int cure_id;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}
	public double getItem_total() {
		return item_total;
	}
	public void setItem_total(double item_total) {
		this.item_total = item_total;
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
	@Override
	public String toString() {
		return "OrderItem [item_id=" + item_id + ", item_count=" + item_count
				+ ", item_total=" + item_total + ", oid=" + oid + ", cure_id="
				+ cure_id + "]";
	}
	
	
}
