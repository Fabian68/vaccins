package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produit;

/**
 * Classe d'accès aux données contenues dans la table produit
 * @version 1.02
 * */
public class ProduitDAO {
	
	/**
	 * Paramètres de connexion à la base de données oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	final static String URL = "jdbc:mysql://localhost:3306/gestionvaccin";
	final static String LOGIN="root";
	final static String PASS="";
	
	/** 
	 * liste des produits connue en temps réel pour éviter de faire des requetes sans changements
	 */
	private List<Produit> liste;
	
	/**
	 * Constructeur de la classe - initialise la liste
	 * 
	 */
	public ProduitDAO()
	{
		// chargement du pilote de bases de données
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Pilote bien charger");
		} catch (ClassNotFoundException e2) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
		update();
	}
	
	/**
	 * Permet d'ajouter un produit dans la table produit
	 * Le mode est auto-commit par défaut : chaque insertion est validée
	 * @param nouveauProduit produit à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int ajouter(Produit nouveauProduit)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {

			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("INSERT INTO produits (nom, prix, type,stock,fournisseurNb) VALUES (?, ?,?,?,?)");
			ps.setString(1,nouveauProduit.getNom());
			ps.setFloat(2,nouveauProduit.getPrix());
			ps.setString(3,nouveauProduit.getType());
			ps.setLong(4,nouveauProduit.getStock());
			ps.setInt(5,nouveauProduit.getIdFournisseur());

			//Exécution de la requête
			retour=ps.executeUpdate();


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		update();
		return retour;

	}
	
	/**
	 * Permet de récupérer un produit à partir de son identifiant
	 * @param identifiant identifiant du produit
	 * @return un produit
	 * @return null si aucun produit ne correspond a cet identifiant
	 */
	public Produit getProduit(int identifiant)
	{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Produit retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM produits WHERE id = ?");
			ps.setInt(1,identifiant);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())
				retour=new Produit(rs.getInt("id"),rs.getString("nom"),rs.getFloat("prix"),rs.getString("type"),rs.getLong("stock"),rs.getInt("fournisseurNb"));


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;

	}
	
	/**
	 * Permet de remplacer un produit par un autre dans la table à l'aide de l'identifiant, sert notamment pour la modification d'une ligne
	 * @param p le produit qui comporte les modifications par rapport a l'ancienn produit
	 */
	public void replaceProduit(Produit p)
	{

		Connection con = null;
		PreparedStatement ps = null;
		
		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE produits SET nom = ? , prix = ? , type = ? , stock = ? , fournisseurNB = ? WHERE id = ? ");
			ps.setInt(6,p.getIdentifiant());
			ps.setString(1,p.getNom());
			ps.setFloat(2,p.getPrix());
			ps.setString(3,p.getType());
			ps.setLong(4,p.getStock());
			ps.setInt(5,p.getIdFournisseur());
			//on exécute la mise a jour
			ps.executeUpdate();
		

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		update();
	}
	/**
	 * Permet de supprimer un produit à partir de son identifiant
	 * @param identifiant identifiant de la commande
	 */
	public void deleteProduit(int identifiant)
	{

		Connection con = null;
		PreparedStatement ps = null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM produits WHERE id = ?");
			ps.setInt(1,identifiant);

			//on exécute la mise a jour
			ps.executeUpdate();
			

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		update();
	}
	
	/**
	 * Permet de récupérer tous les produits stockées dans la liste
	 * @return une ArrayList de produits
	 */
	public List<Produit> getListeProduits()
	{

		return liste;

	}
	
	/**
	 * Permet de mettre a jour la liste des produits avec tous les produits stockées dans la table produit
	 */
	public void update() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		liste=new ArrayList<Produit>();

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM produits");

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next())
				liste.add(new Produit(rs.getInt("id"),rs.getString("nom"),rs.getFloat("prix"),rs.getString("type"),rs.getLong("stock"),rs.getInt("fournisseurNb")));


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du rs, du preparedStatement et de la connexion
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
	}

	//main permettant de tester la classe
	public static void main(String[] args)  throws SQLException {

		 ProduitDAO produitDAO=new ProduitDAO();
		
		 Produit F = new Produit("Comirnaty",2.5f,"ANRm",500000,1);
		 
		 int retour = produitDAO.ajouter(F);
		 
		 System.out.println(retour+ " lignes ajoutées");
		 
		 Produit F1 = produitDAO.getProduit(1);
		 System.out.println(F1);
		 System.out.println("");
		 produitDAO.deleteProduit(1);
		 produitDAO.getListeProduits().get(1).setNom("Nouveau Nom");
		 produitDAO.replaceProduit( produitDAO.getListeProduits().get(1));
	
		 List<Produit> liste = produitDAO.getListeProduits();
		 
		 for(Produit FL : liste) {
			 System.out.println(FL);
		 }

	}
}
