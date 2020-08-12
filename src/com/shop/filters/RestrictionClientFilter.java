package com.shop.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionClientFilter implements Filter {
	
	//public static final String PUBLIC_ACCESS = "/client/client.jsp" ;
	public static final String PUBLIC_ACCESS = "/signin" ;
	public static final String ATT_SESSION_CLIENT ="sessionClient" ;
		

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		/* Cast des objets request et response*/
		HttpServletRequest request = (HttpServletRequest) req ;
		HttpServletResponse response = (HttpServletResponse) resp ;
		
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		
		/* Si l'utilisateur n'existe pas dans la session en cours
		 * alors l'utilisateur n'est pas connecté*/
		if(session.getAttribute(ATT_SESSION_CLIENT) == null) {
			// Redirection vers la page publique 
			response.sendRedirect(request.getContextPath()+PUBLIC_ACCESS);
		}else {
			//Affichage de la page restreinte 
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
