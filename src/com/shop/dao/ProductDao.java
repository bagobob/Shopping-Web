package com.shop.dao;

import java.util.List;

import com.shop.beans.Product;



public interface ProductDao {
	
	public void insert(Product P) throws DaoException;
	public void  delete(Product P) throws DaoException;
	public void  update(Product P) throws DaoException;
	public Product findbyName(String name);
	public List<Product> allProduct();
	public List<Product> Lister() ;

}
