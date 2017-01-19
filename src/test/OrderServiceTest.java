package test;

import org.junit.Test;

import web.store.order.domain.Order;
import web.store.order.domain.PageBean;
import web.store.order.service.OrderService;

public class OrderServiceTest {
	OrderService service = new OrderService();
	
	
	@Test
	public void fun1(){
		PageBean<Order> tmp = service.findAll();
		System.out.println("总页数	"+tmp.getAllPage());
		System.out.println("总数据量	"+tmp.getBeanTotal());
		System.out.println("每页数据量	"+tmp.getPageSize());
		System.out.println("当前页码	"+tmp.getNowPage());
		for(int i = 0 ; i<20;i++){
			System.out.println(tmp.getList().get(i));
		}
	}
	
}
