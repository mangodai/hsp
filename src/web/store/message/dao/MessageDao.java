package web.store.message.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import web.store.message.domain.Message;

/**
 * 
 * @author Deri
 * 2017-01-16 09:16:29
 */
public class MessageDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 增加信息
	 * @param m
	 */
	public void addMessage(Message m){
		String sql = "INSERT INTO message values(?,?,?,?,?)";
		System.out.println(m.toString());
		try {
			qr.update(sql, m.getMid(), m.getMname(), m.getEmail(), m.getSubject(), m.getText());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回所有信息
	 * @return list
	 */
	public List<Message> findAll(){
		String sql = "select * from Messsage";
		try {
			return qr.query(sql, new BeanListHandler<Message>(Message.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void deleteMsg(Message m){
		String sql = "delete Message where mid=?";
		try {
			qr.update(sql, m.getMid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
