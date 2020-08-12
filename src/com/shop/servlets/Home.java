package com.shop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.beans.Product;
import com.shop.dao.*;


public class Home extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductDao productDao ;
	public static final String ATT_PRODUCTS = "listes" ;
	public static final String VUE = "/WEB-INF/index.jsp" ;
	  public static final String SESSION_PRODUCTS  = "products";
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance() ;
		this.productDao = daoFactory.getProductDao() ;
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException{
		 
		
				List<Product> listes = new ArrayList<Product>();
					listes = productDao.Lister();
				
		HttpSession session = request.getSession();
         Map<Long, Product> products = (HashMap<Long, Product>) session.getAttribute( SESSION_PRODUCTS );
       
         /* Puis ajout du client courant dans la map */
         for (Product p : listes){
			products.put(p.getId_product(), p) ;	
		}
      
         /* Et enfin (ré)enregistrement de la map en session */
         session.setAttribute( SESSION_PRODUCTS, products );

		request.setAttribute(ATT_PRODUCTS, listes);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	

}
