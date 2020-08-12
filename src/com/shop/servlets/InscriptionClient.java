package com.shop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shop.beans.*;
import com.shop.dao.*;
import com.shop.forms.*;


public class InscriptionClient extends HttpServlet {
	
	private ClientDao clientDao ;
	public static final String ATT_FORM = "form" ;
	public static final String VUE = "/WEB-INF/signup.jsp" ;
	public static final String  VUE_SUCCES = "/WEB-INF/signin.jsp" ;
	public static final String ATT_CLIENT ="client" ;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance() ;
		this.clientDao = daoFactory.getClientDao() ;
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException{
		
		/* Préparation de l'objet formulaire*/
		InscriptionClientForm form = new InscriptionClientForm(clientDao) ;
		
		/* Appel au traitement et à la validation  de la requête et recupération du bean en résultant */
		
		try {
			Client client = form.saveClient(request);
			/*Stockage du formulaire et du bean dans l'objet request */
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_CLIENT, client);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
		this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
	}
	
	
}
