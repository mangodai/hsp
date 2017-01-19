package web.store.cure.service;

import java.util.List;

import web.store.cure.dao.CureDao;
import web.store.cure.domain.Cure;

public class CureService {
	private CureDao dao = new CureDao();
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 返回所有类 
	* @param @return    设定文件 
	* @return List<Cure>    返回类型 
	* @throws
	 */
	public List<Cure> findAll(){
		return dao.findAll();
	}

	public Cure findByID(String id) {
		return dao.findByID(id);
	}
}
