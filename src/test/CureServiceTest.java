package test;

import java.util.List;

import org.junit.Test;

import web.store.cure.domain.Cure;
import web.store.cure.service.CureService;

public class CureServiceTest {
	CureService service = new CureService();
	
	@Test
	public void fun1(){
		List<Cure> list = service.findAll();
		System.out.println(list);
	}
	
	@Test
	public void fun2(){
		Cure c = service.findByID(""+1);
		System.out.println(c);
	}
}
