package com.shop.dao;

@SuppressWarnings("serial")
public class DaoException extends Exception {
	
	public DaoException(String msg) {
		super(msg);
	}

	public DaoException(Throwable cause) {
		super(cause) ;
	}
	
	public DaoException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
