package web.store.order.domain;

public class ItemDetail {
	private int item_id;
	private int item_count;
	private double item_total;
	private int oid;
	private String cure_name;
	@Override
	public String toString() {
		return "ItemDetail [item_id=" + item_id + ", item_count=" + item_count
				+ ", item_total=" + item_total + ", oid=" + oid
				+ ", cure_name=" + cure_name + "]";
	}
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
	public String getCure_name() {
		return cure_name;
	}
	public void setCure_name(String cure_name) {
		this.cure_name = cure_name;
	}
	
}
