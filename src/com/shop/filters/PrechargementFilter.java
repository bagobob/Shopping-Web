package com.shop.filters;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shop.dao.*;
import com.shop.beans.*;

public class PrechargementFilter implements Filter {

	 public static final String CONF_DAO_FACTORY      = "daofactory";
	   public static final String ATT_SESSION_PRODUCTS   = "products";
	   
	   private ProductDao productDao;
	   
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		/* Cast de l'objet request */
        HttpServletRequest request = (HttpServletRequest) req;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
        /*
         * Si la map des produits n'existe pas en session, alors l'utilisateur se
         * connecte pour la première fois et nous devons précharger en session
         * les infos contenues dans la BDD.
         */
		
        if ( session.getAttribute( ATT_SESSION_PRODUCTS ) == null ) {
            /*
             * Récupération de la liste des clients existants, et enregistrement
             * en session
             */
            List<Product> listeProducts = null;
		
				listeProducts = productDao.Lister();
			
            Map<Long, Product> mapProducts = new HashMap<Long, Product>();
            for ( Product product : listeProducts ) {
            	System.out.println(product) ;
                mapProducts.put( product.getId_product(), product );
            }
            session.setAttribute( ATT_SESSION_PRODUCTS, mapProducts );
        }
        /* Pour terminer, poursuite de la requête en cours */
        chain.doFilter( request, res );
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		 /* Récupération d'une instance de notre DAO productDao */
		this.productDao = ((DAOFactory)config.getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();
		
	}

}
