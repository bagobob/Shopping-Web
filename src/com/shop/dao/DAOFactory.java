package com.shop.dao;

import java.sql.*;

public class DAOFactory {

	private  String url ;
	private String username;   			//For Mysql is "root" by default even if you had created one and for Postgre is "postgres"
	private String password;			//By default is "" for mysql and the password define while you installed postgresql
	
	public static final String databaseName = "ecommerce_java" ; 							//You can change the name of the database here
	public static final  String hostName = "localhost" ;  									//Or 127.0.0.1 for postgre 
	public static final String port = "3306";  												//For postgre it's 5432  
	public static final String sgbdName = "mysql" ;  										//Or postgresql
	public static final String urls = "jdbc:"+sgbdName+"://"+hostName+":"+port+"/"+databaseName;
	public static final String driver =  "com.mysql.jdbc.Driver" ;  						//for PosgreSQL : "org.postgresql.Driver"
	
	DAOFactory(String url ,String username,String password) {
		this.url=url;
		this.username=username;
		this.password=password;
	}
	
	public static DAOFactory getInstance() {
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	
			DAOFactory instance=new DAOFactory(urls, "root", "");
			return instance;
	}
	public Connection getConnection()throws SQLException {
		
		return DriverManager.getConnection(url,username,password);
		
	}
	
	//Recupération du Dao
	
		public ClientDao getClientDao() {
		return new ClientDaoImpl( this );
		}
		
		public ProductDao getProductDao() {
			return new ProductDaoImpl(this);
		}
		
}
