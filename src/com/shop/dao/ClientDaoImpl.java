package com.shop.dao;

import java.sql.*;
import com.shop.beans.Client;




public class ClientDaoImpl implements ClientDao {

	private DAOFactory daoFactory;
	private static final String SQL_INSERT = "INSERT INTO `client`(`id_client`, `name`, `firstName`, `street`, `town`, `postal`, `num_tel`, `email`, `password`) VALUES (NULL,?,?,?,?,?,?,?,?)";
	private static final String SQL_TROUVER = "SELECT  `email`, `password`,`name` FROM `client` WHERE email =? and password =?" ;
	private static final String SQL_FIND = "SELECT  `email`, `password`,`name` FROM `client` WHERE email =?" ;

	public ClientDaoImpl(DAOFactory daoFactory) {
		// 
		this.daoFactory = daoFactory;
	}

	@Override
	public void insert(Client C) throws DaoException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			conn = daoFactory.getConnection();
			preparedStatement = conn.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.getGeneratedKeys();
			preparedStatement.setString(1, C.getName());
			preparedStatement.setString(2, C.getFirstName());
			preparedStatement.setString(3, C.getStreet());
			preparedStatement.setString(4, C.getTown());
			preparedStatement.setInt(5, C.getPostal());
			preparedStatement.setString(6, C.getNum_tel());
			preparedStatement.setString(7, C.getEmail());
			preparedStatement.setString(8, C.getPassword());
			
			int statut = preparedStatement.executeUpdate();
			  
			if ( statut == 0 ) {
				throw new DaoException( 
						"echec de la cr�ation de l'utilisateur, aucune ligne ajoutée dans la table." );
			}
			rs = preparedStatement.getGeneratedKeys();
			if ( rs.next() ) {
			/* Puis initialisation de la propri�t� id du bean Client avec sa valeur */
				C.setId_client( rs.getInt( 1 ) );
			} else {
				throw new DaoException( "�chec de la cr�ation de l'utilisateur en base, aucun ID auto-g�n�r� retourn�." );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Client findByEmail(String email) {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultat=null;
		Client client=null;
		try {
			connexion=daoFactory.getConnection();
			preparedStatement=connexion.prepareStatement(SQL_FIND);
			
			preparedStatement.setString(1, email);
			resultat=preparedStatement.executeQuery();
			
			if(resultat.next()) {
				client=new Client();
				client.setEmail(resultat.getString("email"));
				client.setPassword(resultat.getString("password"));
				client.setName(resultat.getString("name"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//fermeture de la connexion
			try {
				if(resultat != null)
					resultat.close();
				if(preparedStatement !=null)
					preparedStatement.close();
				if(connexion !=null)
					connexion.close();
			}catch (SQLException ignore) {
			}
		}	
	
		return client;

	}

	@Override
	public Client findClient(String email, String password) {
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultat=null;
		Client client=null;
		try {
			connexion=daoFactory.getConnection();
			preparedStatement=connexion.prepareStatement(SQL_TROUVER);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultat=preparedStatement.executeQuery();
			
			if(resultat.next()) {
				client=new Client();
				client.setEmail(resultat.getString("email"));
				client.setPassword(resultat.getString("password"));
				client.setName(resultat.getString("name"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//fermeture de la connexion
			try {
				if(resultat != null)
					resultat.close();
				if(preparedStatement !=null)
					preparedStatement.close();
				if(connexion !=null)
					connexion.close();
			}catch (SQLException ignore) {
			}
		}	
	
		return client;
	}
	}


