package com.shop.dao;



import java.sql.*;
import java.util.*;
import com.shop.beans.Product;


public class ProductDaoImpl implements ProductDao{

	private DAOFactory daoFactory;
	private static final String SQL_INSERT = "INSERT INTO `product`(`id_product`, `name`, `Qte`, `Category`, `price`,`photo`,`description`) VALUES (NULL,?,?,?,?,?,?)";
	private static final String SQL_TROUVER = "SELECT * FROM `product` WHERE 1";
	private static final String SQL= "SELECT `id_product`, `name`, `Qte`, `Category`, `price`, `photo`, `description` FROM `product` WHERE name = ? "; 
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM product WHERE id_product = ?";
	private static final String SQL_UPDATE = "UPDATE FROM product SET name=?,Qte=?,Category=?,price=?, photo=?,description=? WHERE id_product = ?";
	private static final String SQL_ALL = "SELECT `id_product`, `name`, `Qte`, `Category`, `price`, `photo`, `description`  FROM `product";


	public ProductDaoImpl(DAOFactory daoFactory) {
		// 
		this.daoFactory = daoFactory;
	}
	@Override
	public void insert(Product P) throws DaoException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			conn = daoFactory.getConnection();
			preparedStatement = conn.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.getGeneratedKeys();
			preparedStatement.setString(1, P.getName());
			preparedStatement.setInt(2, P.getQte());
			preparedStatement.setString(3, P.getCategory());
			preparedStatement.setInt(4, P.getPrice());
			preparedStatement.setString(5, P.getPhoto());
			preparedStatement.setString(6, P.getDescription());
			
			
			int statut = preparedStatement.executeUpdate();
			  
			if ( statut == 0 ) {
				throw new DaoException( 
						"echec de la cr�ation de l'utilisateur, aucune ligne ajoutée dans la table." );
			}
			rs = preparedStatement.getGeneratedKeys();
			if ( rs.next() ) {
			/* Puis initialisation de la propri�t� id du bean Client avec sa valeur */
				P.setId_product( rs.getInt( 1 ) );
			} else {
				throw new DaoException( "�chec de la cr�ation de l'utilisateur en base, aucun ID auto-g�n�r� retourn�." );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Product P) throws DaoException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = daoFactory.getConnection();
			preparedStatement = conn.prepareStatement(SQL_DELETE_PAR_ID);
			preparedStatement.setLong(1, P.getId_product());
			preparedStatement.setString(2, P.getName());
			preparedStatement.setInt(3, P.getQte());
			preparedStatement.setString(4, P.getCategory());
			preparedStatement.setInt(5, P.getPrice());
			preparedStatement.setString(5, P.getPhoto());
			preparedStatement.setString(6, P.getDescription());
			
			int statut = preparedStatement.executeUpdate();
			  
			if ( statut == 0 ) {
				throw new DaoException( 
						"�chec de la modification de la notification, aucune ligne modifi�e dans la table");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Product P) throws DaoException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = daoFactory.getConnection();
			preparedStatement = conn.prepareStatement(SQL_UPDATE);
			preparedStatement.setLong(1, P.getId_product());
			
			int statut = preparedStatement.executeUpdate();
			  
			if ( statut == 0 ) {
				throw new DaoException( 
						"�chec de la suppression de la notification, aucune ligne supprim�e dans la table.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Product findbyName(String name)  {
		// TODO Auto-generated method stub
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultat=null;
		Product produit=null;
		try {
			connexion=daoFactory.getConnection();
			preparedStatement=connexion.prepareStatement(SQL);
			
			preparedStatement.setString(1, name);
			resultat=preparedStatement.executeQuery();
			
			if(resultat.next()) {
				produit=new Product();
				produit.setId_product(resultat.getInt("id_product"));
				produit.setName(resultat.getString("name"));
				produit.setQte(resultat.getInt("qte"));
				produit.setCategory(resultat.getString("category"));
				produit.setPrice(resultat.getInt("price"));
				produit.setPhoto(resultat.getString("photo"));
				produit.setDescription(resultat.getString("description"));
				
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
				ignore.getMessage();
			}
		}	
	
		return produit;
	}
	
	@Override
	public List<Product> Lister() {
		List <Product>products =new ArrayList<Product>();
		Connection connexion=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultat=null;
		Product produit=null;
		try {
			connexion=daoFactory.getConnection();
			preparedStatement=connexion.prepareStatement(SQL_TROUVER);
			
			//Exécution de la requête
			resultat=preparedStatement.executeQuery();
			while(resultat.next())
					{
						produit=new Product();
						produit.setId_product(resultat.getInt("id_product"));
						produit.setName(resultat.getString("name"));
						produit.setQte(resultat.getInt("qte"));
						produit.setCategory(resultat.getString("category"));
						produit.setPrice(resultat.getInt("price"));
						produit.setPhoto(resultat.getString("photo"));
						produit.setDescription(resultat.getString("description"));
						
						products.add(produit);	
					}	
		}catch(SQLException e) {	
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
					ignore.getMessage();
				}
			}	
		
		return products;
	}

	@Override
	public List<Product> allProduct()  {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Product>produits = new ArrayList<>();
		try {
			/* R�cup�ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement=connexion.prepareStatement(SQL_ALL);

			resultSet = preparedStatement.executeQuery();
			/* Parcours des lignes de donn�es de l'�ventuel ResulSet retourn� */
			while ( resultSet.next() ) {
				produits.add( map( resultSet ) );
			}
		} catch ( SQLException e ) {
			e.getMessage();
		} finally {
			try {
				if(resultSet != null)
					resultSet.close();
				if(preparedStatement !=null)
					preparedStatement.close();
				if(connexion !=null)
					connexion.close();
			}catch (SQLException ignore) {
			}
		} 
		return produits;
		
	}
	private Product map(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		Product produit = new Product();
		produit.setId_product(resultSet.getInt("id_product"));
		produit.setName(resultSet.getString("name"));
		produit.setQte(resultSet.getInt("Qte"));
		produit.setCategory(resultSet.getString("Category"));
		produit.setPrice(resultSet.getInt("price"));
		produit.setPhoto(resultSet.getString("photo"));
		produit.setDescription(resultSet.getString("description"));
		return produit;
	}

}
