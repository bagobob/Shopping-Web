package com.shop.forms;

import com.shop.beans.*;
import com.shop.dao.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class InscriptionClientForm {
	
	public static final String CHAMP_NAME="name";
	public static final String CHAMP_FIRSTNAME="firstName";
	public static final String CHAMP_STREET="street";
	public static final String CHAMP_TOWN="town";
	public static final String CHAMP_POSTAL="postal";
	public static final String CHAMP_TEL="num_tel";
	public static final String CHAMP_EMAIL="email";
	public static final String CHAMP_PASS="password";
	public static final String CHAMP_CONF="confirmPassword";
	private static final String ALGO_CHIFFREMENT = "SHA-256";
	
	private ClientDao clientDao;
	private Map<String,String> erreurs = new HashMap<String, String>() ;
	private String resultat ;
	
	public InscriptionClientForm(ClientDao clientDao) {
		this.clientDao = clientDao ;
	}
	public Map<String,String> getErreurs() {
		return erreurs ;
	}
	
	public String getResultat() {
		return resultat;
	}
	
	public Client saveClient(HttpServletRequest request) throws DaoException {
		/* Récupération des éléments des champs du formulaire et vérification avec 
		 * les éléments présent en base de donnée*/ 
		String name=getValeurChamp(request, CHAMP_NAME);
		String firstName=getValeurChamp(request, CHAMP_FIRSTNAME);
		String street=getValeurChamp(request, CHAMP_STREET);
		String town=getValeurChamp(request, CHAMP_TOWN);
		String postal=getValeurChamp(request, CHAMP_POSTAL);
		String num_tel=getValeurChamp(request, CHAMP_TEL);
		String email=getValeurChamp(request,CHAMP_EMAIL);	
		String password=getValeurChamp(request,CHAMP_PASS);	
		String confirmPassword=getValeurChamp(request,CHAMP_CONF);
		
		Client client = new Client() ;
		traiterNom(name, client) ;
		traiterPrenom(firstName,client);
		traiterStreet(street, client);
		traiterVille(town, client);
		traiterPostalCode(postal,client) ;
		traiterTelephone(num_tel,client);
		traiterEmail(email,password,client);
		traiterPassword(password,confirmPassword,client);
		
		if(!(erreurs.isEmpty())) {
			resultat="Echec de l'inscription";
		}
			clientDao.insert(client);
			resultat="Succès de l'inscription";
    						
		return client;
		
	}
	/* Declaration des différentes méthodes dans des bloc try/catch */
	
	private void traiterNom(String name,Client client) {
		try {
			validationNom(name);
		}catch(FormValidationException e) {
			setErreurs(CHAMP_NAME,e.getMessage()) ;
		}
		client.setName(name);
	}
	
	private void traiterPrenom(String firstName,Client client) {
		try {
			validationPrenom(firstName);
		}catch(FormValidationException e) {
			setErreurs(CHAMP_FIRSTNAME,e.getMessage()) ;
		}
		client.setFirstName(firstName);
	}
	
	private void traiterStreet(String street,Client client) {
		try {
			validationStreet(street);
		}catch(FormValidationException e) {
			setErreurs(CHAMP_STREET,e.getMessage()) ;
		}
		client.setStreet(street);
	}
	
	private void traiterVille(String town,Client client) {
		try {
			validationTown(town);
		}catch(FormValidationException e) {
			setErreurs(CHAMP_TOWN,e.getMessage()) ;
		}
		client.setTown(town);
	}
	
	private void traiterPostalCode(String postal, Client client) {
		try {
			validationPostal(postal);
		}catch(FormValidationException e) {
			setErreurs(CHAMP_POSTAL, e.getMessage());
		}
		int code = Integer.parseInt(postal) ;
		client.setPostal(code);
	}
	
	private void traiterTelephone(String num_tel,Client client) {
		try {
				validationTelephone(num_tel);
		}catch(FormValidationException e) {
				setErreurs(CHAMP_TEL,e.getMessage());
			}client.setNum_tel(num_tel);
	}
	
	private void traiterEmail(String email,String password,Client client) {
		try {
			validationEmail( email ,password);
			} catch (FormValidationException e ) {
			setErreurs( CHAMP_EMAIL, e.getMessage() );
			}client.setEmail(email);
	}
	
	private void traiterPassword(String password,String confirmPassword,Client client) {
		try {
				validationMotsDePasse(password,confirmPassword);
			}catch(FormValidationException e) {
					setErreurs(CHAMP_PASS,e.getMessage());
					setErreurs(CHAMP_CONF,null); }
					
					
					/* Utilisation de la bibliothèque Jasypt pour chiffrer le mot de
					passe
					* efficacement.
					**
					L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
					* aléatoire et un grand nombre d'itérations de la fonction de
					hashage.
					**
					La String retournée est de longueur 56 et contient le hash en
					Base64.
					*/
					ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
					passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
					passwordEncryptor.setPlainDigest( false );
					String passwordCrypt = passwordEncryptor.encryptPassword(password );  
					client.setPassword(passwordCrypt);
	}
	
	
	//Méthode permettant de vérifier que le nom est non vide
	private void validationNom(String name) throws FormValidationException{
		
		if(name == null) {	
			throw new FormValidationException("Merci d'entrez un votre nom!") ;
		}
	}
	
	//Méthode permettant de vérifier que le prénom est non vide
	private void validationPrenom(String firstName) throws FormValidationException{
		
		if(firstName == null) {	
			throw new FormValidationException("Merci d'entrez un votre prénom!") ;
		}
	}
	
	
	//Méthode permettant de vérifier que le champ street ne sois pas vide
	private void validationStreet(String street) throws FormValidationException{
		
		if(street == null) {	
			throw new FormValidationException("Merci d'entrez le nom de votre rue!") ;
		}
	}
	
	//Méthode permettant de vérifier que le champ town ne sois pas vide
	private void validationTown(String town) throws FormValidationException{
		
		if(town == null) {	
			throw new FormValidationException("Merci d'entrez le nom de votre ville") ;
		}
	}
	
	
	// Méthode permettant de vérifier que le code postal est bien un chiffre
	
	private void validationPostal(String postal) throws FormValidationException{
		
		if(postal != null) {
			if(postal.length() != 5) 
				throw new FormValidationException("Vous devez entrez un code postal correct de 5 chiffres") ;	
		}
		else {
			throw new FormValidationException("Merci d'entrez un code postal") ;
		}
	}
	

	/*Methode permettant de verifier que le numéro de téléphone ne contienne que des chiffres*/
	private void validationTelephone( String num_tel ) throws FormValidationException
	{
			if ( num_tel != null ) {
					if ( !num_tel.matches( "^\\d+$" ) ) {
							throw new FormValidationException( "Le numéro de téléphone doit uniquement contenir des chiffres." );
					} else if ( num_tel.length() < 4 ) {
							throw new FormValidationException( "Le numéro de téléphone doit contenir au moins 4 chiffres." );
					}
			} else {
						throw new FormValidationException( "Merci d'entrer un numéro de téléphone." );
					}
	}
	
	/* Validation de l'adresse email */
    private void validationEmail( String email ,String pass) throws FormValidationException {
    	if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( clientDao.findClient( email,pass ) != null ) {
                throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }
    
    /* Methode permettant de verifier si les mots de passe sont identiques */
	private void validationMotsDePasse( String password, String confirmPassword ) throws FormValidationException {
			if ( password != null && confirmPassword != null ) {
						if ( !password.equals( confirmPassword ) ) {
								throw new FormValidationException( "Les mots de passe entrés sont 	différents, merci de les saisir à nouveau." );
						} else if ( password.length() < 4 ) {
								throw new FormValidationException( "Les mots de passe doivent contenir 	au moins 4 caractères." );
						}
			} else {
				throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
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
