package com.shop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.beans.*;
import com.shop.dao.*;
import com.shop.forms.*;


public class Login extends HttpServlet {

	private ClientDao clientDao ;
	
	public static final String ATT_FORM = "form" ;
	public static final String VUE = "/WEB-INF/signin.jsp" ;
	public static final String  VUE_SUCCES = "/client/client.jsp" ;
	public static final String ATT_CLIENT ="client" ;
	public static final String ATT_SESSION_CLIENT ="sessionClient" ;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance() ;
		this.clientDao = daoFactory.getClientDao() ;
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
	} 
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException{
		
		/* Préparation de l'objet formulaire*/
		ConexionClientForm form = new ConexionClientForm(clientDao) ;
		
		/* Traitement de la requête et recupération du bean en résultant */
		Client client = null;
		client = form.connectClient(request) ;
		
		/* Récupération de la session depuis la requête*/
		HttpSession session = request.getSession() ;
		
		/* Si aucune erreur de validation n'a lieu , alors ajout du bean Client à
		 * la session sinon suppression de ce bean de la session */
		if(form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_CLIENT, client);
		}else {
			session.setAttribute(ATT_SESSION_CLIENT, null);
		}
		
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_CLIENT, client);
		
		 /*
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_SESSION_CLIENT ) == null ) {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() + VUE );
        } else {
            /* Affichage de la page restreinte */
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        }
		//this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
	}
}
