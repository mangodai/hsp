package web.store.cure.domain;

public class Cure {
	private int cure_id;
	private String cure_name;
	private String cure_cost;
	private String image;
	private int cid;
	private String cure_extra;
	public int getCure_id() {
		return cure_id;
	}
	public void setCure_id(int cure_id) {
		this.cure_id = cure_id;
	}
	public String getCure_name() {
		return cure_name;
	}
	public void setCure_name(String cure_name) {
		this.cure_name = cure_name;
	}
	public String getCure_cost() {
		return cure_cost;
	}
	public void setCure_cost(String cure_cost) {
		this.cure_cost = cure_cost;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCure_extra() {
		return cure_extra;
	}
	public void setCure_extra(String cure_extra) {
		this.cure_extra = cure_extra;
	}
	
}
