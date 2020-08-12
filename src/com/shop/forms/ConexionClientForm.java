package com.shop.forms;

import com.shop.dao.*;
import com.shop.beans.*;
import java.util.*;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import javax.servlet.http.HttpServletRequest;

public class ConexionClientForm {
	
	private static final String ALGO_CHIFREMENT = "SHA-256" ;
	private static final  String CHAMP_EMAIL = "email" ;
	private static final String CHAMP_PASS = "password" ;
	private ClientDao clientDao;
	private Map<String,String> erreurs = new HashMap<String, String>() ;
	private String resultat ;
	
	public ConexionClientForm(ClientDao clientDao) {
		
		this.clientDao = clientDao;
	}

	public Map<String,String> getErreurs() {
		return erreurs ;
	}
	
	public String getResultat() {
		return resultat;
	}
	
	public Client connectClient(HttpServletRequest req)
	{
		/* Récupération des éléments des champs du formulaire et vérification avec 
		 * les éléments présent en base de donnée*/ 
		
		String email = getValeurChamp(req,CHAMP_EMAIL) ;
		String password = getValeurChamp(req,CHAMP_PASS) ;
		
		/* Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe efficacement
		 * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage aléatoire et un 
		 * grand nombre d'itérations de la fonction de hashage.
		 * La String retournée est de longueur 56 er contient le hash en Base64.*/
		
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor() ;
		passwordEncryptor.setAlgorithm(ALGO_CHIFREMENT);
		passwordEncryptor.setPlainDigest(false);
		String passwordCrypt = passwordEncryptor.encryptPassword(password) ;
		
		Client client = new Client() ;
		//client = clientDao.findClient(email,passwordCrypt) ;
		client = clientDao.findByEmail(email) ;
		
		/* Validation du champ email*/
		try {
			validationEmail(email) ;
		}catch(Exception e) {
			setErreurs(CHAMP_EMAIL,e.getMessage()) ;
		}
		try{
			client.setEmail(email);
		}catch(NullPointerException exc) {
			exc.getMessage() ;
		}
		
		/* Validation du mot de passe */
		try {
			validationMotDePasse(password) ;
		}catch(Exception e) {
			setErreurs(CHAMP_PASS,e.getMessage()) ;
		}
		try {
			client.setPassword(password) ;
		}catch(NullPointerException exc) {
			exc.getMessage() ;
		}
		
		/* Initialisation du résultat global de la validation. */
		if(erreurs.isEmpty()) 
		{
			try {
				if(client.getEmail().equals(email) && client.getPassword().equals(passwordCrypt)) 
				resultat = "Succès de la connexion!" ;
			}catch(NullPointerException ex) {
				ex.getMessage();
			}
			try {
				if(!client.getEmail().equals(email))
					resultat = "Veuillez entrez un email correct " ;
			}catch(NullPointerException ex) {
				ex.getMessage();
			}
			try {
				if(!client.getPassword().equals(passwordCrypt))
					resultat = "Mot de passe oublié!" ;
			}catch(NullPointerException ex) {
				ex.getMessage();
			}
			
		}
		else {
			resultat = "Echec de la connexion!" ;
		}
		return client;
		
	}
	
	/* Méthode permettant de vérifier l'adresse email saisie.*/
	
	private void validationEmail(String email) throws Exception{
		if(email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) 
		{
			throw new Exception("Merci de saisir une adresse email valide") ;
		}
	}
	
	//Méthode permettant de vérifier le mot de passe saisie
	
	private void validationMotDePasse(String password) throws Exception {
		if(password != null) {
			if(password.length() < 4) 
				throw new Exception("Le mot de passe doit contenir au moins 4 caractères");
		}else {
			throw new Exception("Merci de saisir votre mot de passe ") ;	
			}
	}
	
	//Ajout du message correspondant au champ spécifié à la map des erreurs.
	private void setErreurs(String champ, String message) {
		erreurs.put(champ, message) ;
	}
	
	//Méthode utilitaire qui retourne le null si un champ est vide et son contenu sinon
	private static String getValeurChamp(HttpServletRequest request,String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if(valeur == null || valeur.trim().length() == 0) {
			return null;
		}else {
			return valeur ;
		}
	}
}
