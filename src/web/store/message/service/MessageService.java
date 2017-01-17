package web.store.message.service;

import org.junit.Test;

import web.store.message.dao.MessageDao;
import web.store.message.domain.Message;

public class MessageService {
	private MessageDao md = new MessageDao();
	public void addMessage(Message m) {
		md.addMessage(m);
	}
	
	@Test
	public void fun1(){
		Message m = new Message();
		m.setMid("qwqweeqw");
		m.setEmail("96555734@qq.com");
		m.setMname("qwe");
		m.setSubject("title");
		m.setText("context");
		new MessageService().addMessage(m);
	}
}
