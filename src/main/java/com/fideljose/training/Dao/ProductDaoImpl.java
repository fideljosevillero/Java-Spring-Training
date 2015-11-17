package com.fideljose.training.Dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.fideljose.training.model.Category;
import com.fideljose.training.model.Product;

import org.springframework.orm.hibernate3.SessionFactoryUtils;

@Repository
public class ProductDaoImpl implements ProductDao {

	private HibernateTemplate hibernateTemplate;
	private JdbcTemplate jdbcTemplate;
//	@Override
//	public List<Product> getProductsList(Product product) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Product getProductById(Integer idProduct) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public void saveProduct(Product product) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void deleteProduct(Integer idProduct) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public List<Category> getCategoriesTypesList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public boolean isRepeatDescription(String name, Integer idProduct) {
//		// TODO Auto-generated method stub
//		return false;
//	}
			
	@Autowired
	public ProductDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	public void deleteProduct(Integer idProduct) {
		Product product = getProductById(idProduct);
		hibernateTemplate.delete(product);
	}

	public Product getProductById(Integer idProduct) {
		Product product = (Product) hibernateTemplate.get(Product.class, idProduct);
		return product;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsList(Product product) {
		
		StringBuffer query = new StringBuffer(" from Product ");
		
		if(product != null && product.getName() != null 
				&& product.getName().length() > 0){
			
			query.append(" where upper(name) like '%").append(product.getName().
					toUpperCase()).append("%' ");
		}
		
		List<Product> list = (List<Product>)
				  hibernateTemplate.find(query.toString());
		return list;
	}

	public void saveProduct(Product product) {

		hibernateTemplate.saveOrUpdate(product);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategoriesTypesList() {
		
		List<Category> list = (List<Category>) hibernateTemplate.
				find("from Category");
		return list;
	}	
	
	public boolean isRepeatDescription(String name, Integer idProduct){
		
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from product ");
		query.append(" where upper(trim(name)) = '").append(name.trim().
				toUpperCase()).append("' ");
		
		if(idProduct != null){
			query.append(" and id_product != ").append(idProduct);
		}		

//		int count = jdbcTemplate.queryForInt(query.toString());
		int count = 1;
		if(count > 0){
			return true;
		}
		return false;		
	}
}
