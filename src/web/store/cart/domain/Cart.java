package web.store.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {
	private Map<String , CartItem> map = new LinkedHashMap<String , CartItem>();
	
	/**
	 * 
	* @Title: getTotal 
	* @Description: 计算合计 
	* @param @return    设定文件 
	* @return double    返回类型 
	* @throws
	 */
	public double getTotal() {
		// 合计=所有条目的小计之和
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : map.values()) {
			BigDecimal subtotal = new BigDecimal("" + cartItem.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Cart [map=" + map + "]";
	}

	public void remove(String id){
		map.remove(id);
	}
	
	/**
	 * 
	* @Title: clear 
	* @Description: 清空 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void clear(){
		map.clear();
	}
	
	/**
	 * 
	* @Title: add 
	* @Description: TODO 增加条目 
	* @param @param cartItem    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void add(CartItem cartItem) {
		// 如果没有
		if(!map.containsKey( cartItem.getCure().getCure_id() ) ){
			map.put(""+cartItem.getCure().getCure_id(), cartItem);
		} else { //如果有 相加取和
			CartItem tmp = map.get(cartItem.getCure().getCure_id());
			tmp.setCount(tmp.getCount()+cartItem.getCount()); 
			map.put(""+cartItem.getCure().getCure_id(), cartItem);
		}
	}

}
