package web.store.category.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import web.store.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public void addCategory(Category c){
		String sql = "insert into category(cname) values(?)";
		try {
			qr.update(sql,c.getCname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
