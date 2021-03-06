package service;

import java.sql.SQLException;
import java.util.List;

/* 使用するDAO、Domain、Beanをインポート */
import db.CategoryDao;
import domain.Category;
import service.bean.Bean;

public class CategoryService extends Service {
public	CategoryService(){
	}
	private CategoryDao cdao;

	//使用するDaoをnewする
	@Override
	void prePare() {
		this.cdao = new CategoryDao( this.con);
	}


//public  CategoryBean getCategoryBean(){
//
//	List<Category> category = new ArrayList<Category>();
//
//	CategoryBean bean = null;
//	try {
//		category = cdao.getCategories();
//
//		bean = new CategoryBean();
//		this.getCategories(bean);	// 検索バーのカテゴリ追加分
//	}
//	catch (SQLException e)
//	{
//		throw new RuntimeException(e);
//	} finally{
//		end();
//	}
//
//	bean.setCategory(category);
//	return bean;
//}

	public void getCategories(Bean bean){
		List<Category> list = bean.getCategories();
		CategoryDao cdao = new CategoryDao(con);
		try {
			cdao.getCategories(list);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Bean createBean(){
		Bean bean = new Bean();
		this.getCategories(bean);
		return bean;
	}


}


