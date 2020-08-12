package com.shop.forms;

import java.util.HashMap;
import java.util.Map;
import com.shop.dao.*;
import com.shop.beans.*;

import javax.servlet.http.HttpServletRequest;



public class ProductForm {
	public static final String CHAMP_NAME="name";
	public static final String CHAMP_QTE="qte";
	public static final String CHAMP_CATEGORY="category";
	public static final String CHAMP_PRICE="price";

	
	private String resultat;
	private Map<String,String>erreurs=new HashMap<String,String>();
	private ProductDao productDao;
	
			public ProductForm(ProductDao productDao) {
				this.productDao=productDao;
			}
			public String getResultat() {
				return resultat;
			}public Map<String, String> getErreurs() {
				return erreurs;
			}
			
			public Product ajouterCours(HttpServletRequest request) {
					/*Definition des variables locales au programme */
				String name=getValeurChamp(request, CHAMP_NAME);
				String qte=getValeurChamp(request, CHAMP_QTE);
				String category=getValeurChamp(request,CHAMP_CATEGORY);
				String price=getValeurChamp(request,CHAMP_PRICE);
				Product product=new Product();
				
					try {
					validationName( name );
					} catch ( Exception e ) {
					setErreur( CHAMP_NAME, e.getMessage() );
					}product.setName(name);
					try {
						validationQte( qte );
						} catch ( Exception e ) {
						setErreur( CHAMP_QTE, e.getMessage() );
						}
					int quantity = Integer.parseInt(qte);
					product.setQte(quantity);
					try {
							validationCategory( category );
						} catch ( Exception e ) {
							setErreur( CHAMP_CATEGORY, e.getMessage() );
							}product.setCategory(category);
					try {
							validationPrice( price );
							} catch ( Exception e ) {
							setErreur( CHAMP_PRICE, e.getMessage() );
						}
					int prices = Integer.parseInt(price);
					product.setPrice(prices);
			
				
				try {
		    		if(!(erreurs.isEmpty())) {
		    			resultat="Erreur ajout cours";
		    	}	
		    		productDao.insert(product);
		    	resultat="Succès";
			}catch(DaoException e) {
    			setErreur("imprevu", "Erreur non prévue pendant la création");
    			resultat="Echec de création du produit";
    			e.getMessage();
    }
    						return product;
			}
			
			/**
			* Verifie si le champ name est non vide.
			*/
			private void validationName(String name) throws FormValidationException{
				
				if(name == null) {	
					throw new FormValidationException("Merci d'entrez le nom du produit!") ;
				}
			}
			
			/**
			* Verifie si le champ quantité est non vide.
			*/
			private void validationQte(String qte) throws FormValidationException{
				
				if(qte == null) {	
					throw new FormValidationException("Merci d'entrez une quantite de produits!") ;
				}
			}
			
			/**
			* Verifie si le champ quantité est non vide.
			*/
			private void validationCategory(String category) throws FormValidationException{
				
				if(category == null) {	
					throw new FormValidationException("Merci d'entrez une categorie de produits!") ;
				}
			}
			
			/**
			* Verifie si le champ quantité est non vide.
			*/
			private void validationPrice(String price) throws FormValidationException{
				
				if(price == null) {	
					throw new FormValidationException("Merci d'entrez le prix du produit!") ;
				}
			}
			
			
			/*
			* Ajoute un message correspondant au champ spécifié à la map des
			erreurs.
			*/
					private void setErreur( String champ, String message ) {
								erreurs.put( champ, message );
						}
			/*
			* Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.
			*/
				private static String getValeurChamp( HttpServletRequest request,String nomChamp ) 
					 {
						String valeur = request.getParameter( nomChamp );
							if ( valeur == null || valeur.trim().length() == 0 ) {
									return null;
					           }else{
									return valeur.trim();
							   }
								
							}
				
}
