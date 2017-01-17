package web.store.cure.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import web.store.cure.domain.Cure;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * curedao
 * @author Deri
 *
 */
public class CureDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Cure> findAll(){
		String sql = "select * from Cure";
		try {
			return qr.query(sql, new BeanListHandler<Cure>(Cure.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 增加
	 * @param c
	 */
	public void addCure(Cure c){
		String sql = "insert into Cure(cure_cost,cure_extra,cid,cure_name,image) values(?, ?, ?, ?, ?)";
		try {
			qr.update(sql, c.getCure_cost(), c.getCure_extra(), c.getCid(), c.getCure_name(), c.getImage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 编辑cure
	 * @param cure
	 */
	public void editCure(Cure c){
		String sql = "update Cure set cure_cost=?,cure_extra=?,cid=?,cure_name=? where cure_id=?";
		try {
			qr.update(sql, c.getCure_cost(), c.getCure_cost(), c.getCure_extra(), c.getCid(),c.getCure_name(),c.getCure_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
