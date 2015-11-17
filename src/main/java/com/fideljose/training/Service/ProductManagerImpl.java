package com.fideljose.training.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fideljose.training.Dao.ProductDao;
import com.fideljose.training.model.*;
import java.util.*;

@Service
public class ProductManagerImpl implements ProductManager {
	
	@Autowired
	private ProductDao productDao;
	
	public void deleteProduct(Integer idProduct) {
		productDao.deleteProduct(idProduct);
	}

	public Product getProductById(Integer idProduct) {
		return productDao.getProductById(idProduct);
//		return null;
	}

	public List<Product> getProductsList(Product product) {
		return productDao.getProductsList(product);
//		return null;
	}

	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	public List<Category> getCategoriesTypesList() {
		return productDao.getCategoriesTypesList();
//		return null;
	}

	public boolean isRepeatDescription(String name, Integer idProduct) {
		return productDao.isRepeatDescription(name, idProduct);
//		return false;
	}
}
