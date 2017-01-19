package web.store.cart.domain;

import java.math.BigDecimal;

import web.store.cure.domain.Cure;

public class CartItem {
	private Cure c = new Cure();
	private int count ;
	
	/**
	 * 
	* @Title: getSubtotal 
	* @Description: TODO 去精度 
	* @param @return    设定文件 
	* @return double    返回类型 
	* @throws
	 */
	public double getSubtotal() {//小计方法，但它没有对应的成员！
		BigDecimal d1 = new BigDecimal(c.getCure_cost() + "");
		BigDecimal d2 = new BigDecimal(count + "");
		return d1.multiply(d2).doubleValue();
	}
	
	
	
	@Override
	public String toString() {
		return "CartItem [c=" + c + ", count=" + count + "]";
	}



	public Cure getCure() {
		return c;
	}
	public void setCure(Cure c) {
		this.c = c;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
