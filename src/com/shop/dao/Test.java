package com.shop.dao;

import java.util.List;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.shop.beans.Client;
import com.shop.beans.Product;

public class Test {
	private static final String ALGO_CHIFREMENT = "SHA-256" ;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DAOFactory daoFactory = DAOFactory.getInstance() ;
		ProductDao productDao = daoFactory.getProductDao() ;
		ClientDao clientDao = daoFactory.getClientDao();
		List<Product> listes =productDao.Lister();
		
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor() ;
		passwordEncryptor.setAlgorithm(ALGO_CHIFREMENT);
		passwordEncryptor.setPlainDigest(false);
		String passwordCrypt = passwordEncryptor.encryptPassword("toto") ;
			
			listes = productDao.allProduct();
		
			System.out.println(productDao.findbyName("montre"));
		//	Client c2 = clientDao.findClient("test1@gmail.com", passwordCrypt) ;
		//	System.out.println(c2.getName());
			Client c1 = clientDao.findByEmail("test1@gmail.com") ;
			System.out.println(c1.getName());
			
		/*for(Product pr: listes) {
			System.out.println(pr);
		}*/
			
	}

}
