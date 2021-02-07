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
 * Classe d'acc�s aux donn�es contenues dans la table produit
 * @version 1.02
 * */
public class ProduitDAO {
	
	/**
	 * Param�tres de connexion � la base de donn�es oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	final static String URL = "jdbc:mysql://localhost:3306/gestionvaccin";
	final static String LOGIN="root";
	final static String PASS="";
	
	/** 
	 * liste des produits connue en temps r�el pour �viter de faire des requetes sans changements
	 */
	private List<Produit> liste;
	
	/**
	 * Constructeur de la classe - initialise la liste
	 * 
	 */
	public ProduitDAO()
	{
		// chargement du pilote de bases de donn�es
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
	 * Le mode est auto-commit par d�faut : chaque insertion est valid�e
	 * @param nouveauProduit produit � ajouter
	 * @return le nombre de ligne ajout�es dans la table
	 */
	public int ajouter(Produit nouveauProduit)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion � la base de donn�es
		try {

			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("INSERT INTO produits (nom, prix, type,stock,fournisseurNb) VALUES (?, ?,?,?,?)");
			ps.setString(1,nouveauProduit.getNom());
			ps.setFloat(2,nouveauProduit.getPrix());
			ps.setString(3,nouveauProduit.getType());
			ps.setLong(4,nouveauProduit.getStock());
			ps.setInt(5,nouveauProduit.getIdFournisseur());

			//Ex�cution de la requ�te
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
	 * Permet de r�cup�rer un produit � partir de son identifiant
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

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM produits WHERE id = ?");
			ps.setInt(1,identifiant);

			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e 
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
	 * Permet de remplacer un produit par un autre dans la table � l'aide de l'identifiant, sert notamment pour la modification d'une ligne
	 * @param p le produit qui comporte les modifications par rapport a l'ancienn produit
	 */
	public void replaceProduit(Produit p)
	{

		Connection con = null;
		PreparedStatement ps = null;
		
		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE produits SET nom = ? , prix = ? , type = ? , stock = ? , fournisseurNB = ? WHERE id = ? ");
			ps.setInt(6,p.getIdentifiant());
			ps.setString(1,p.getNom());
			ps.setFloat(2,p.getPrix());
			ps.setString(3,p.getType());
			ps.setLong(4,p.getStock());
			ps.setInt(5,p.getIdFournisseur());
			//on ex�cute la mise a jour
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
	 * Permet de supprimer un produit � partir de son identifiant
	 * @param identifiant identifiant de la commande
	 */
	public void deleteProduit(int identifiant)
	{

		Connection con = null;
		PreparedStatement ps = null;

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM produits WHERE id = ?");
			ps.setInt(1,identifiant);

			//on ex�cute la mise a jour
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
	 * Permet de r�cup�rer tous les produits stock�es dans la liste
	 * @return une ArrayList de produits
	 */
	public List<Produit> getListeProduits()
	{

		return liste;

	}
	
	/**
	 * Permet de mettre a jour la liste des produits avec tous les produits stock�es dans la table produit
	 */
	public void update() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		liste=new ArrayList<Produit>();

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM produits");

			//on ex�cute la requ�te
			rs=ps.executeQuery();
			//on parcourt les lignes du r�sultat
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
		 
		 System.out.println(retour+ " lignes ajout�es");
		 
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
