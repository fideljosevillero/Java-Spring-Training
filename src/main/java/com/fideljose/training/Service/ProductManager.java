package com.fideljose.training.Service;

import com.fideljose.training.model.*;
import java.util.*;



public interface ProductManager {
	public List<Product> getProductsList(Product product);
	public Product getProductById(Integer idProduct);
	public void saveProduct(Product product);
	public void deleteProduct(Integer idProduct);
	public List<Category> getCategoriesTypesList();
	public boolean isRepeatDescription(String name, Integer idProduct);
}
