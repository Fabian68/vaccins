package vaccins2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table commande
 * @version 1.01
 * */
public class CommandeDAO {
	
	/**
	 * Paramètres de connexion à la base de données oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	final static String URL = "jdbc:mysql://localhost:3306/gestionvaccin";
	final static String LOGIN="root";
	final static String PASS="";
	
	/** 
	 * liste des commandes connue en temps réel pour éviter de faire des requetes sans changements
	 */
	private List<Commande> liste;
	
	/**
	 * Constructeur de la classe - initialise la liste
	 * 
	 */
	public CommandeDAO()
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
	 * Permet d'ajouter une commande dans la table commande
	 * Le mode est auto-commit par défaut : chaque insertion est validée
	 * @param nouvelleCommande commande à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int ajouter(Commande nouvelleCommande)
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
			ps = con.prepareStatement("INSERT INTO commandes (dateheure, nombre, produitNb) VALUES (?, ?,?)");
		
			ps.setDate(1,(Date) nouvelleCommande.getDateHeure());
			ps.setInt(2,nouvelleCommande.getNombre());
			ps.setInt(3,nouvelleCommande.getIdVaccin());

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
	 * Permet de récupérer une commande à partir de son identifiant
	 * @param identifiant identifiant de la commande
	 * @return une commande
	 * @return null si aucune commande ne correspond a cet identifiant
	 */
	public Commande getCommande(int identifiant)
	{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Commande retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM commandes WHERE id = ?");
			ps.setInt(1,identifiant);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())
				retour=new Commande(rs.getInt("id"),rs.getDate("dateheure"),rs.getInt("nombre"),rs.getInt("produitNb"));


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
	 * Permet de remplacer une commande par une autre dans la table à l'aide de l'identifiant, sert notamment pour la modification d'une ligne
	 * @param c la commande qui comporte les modifications par rapport a l'ancienne commande
	 */
	public void replaceCommande( Commande c)
	{

		Connection con = null;
		PreparedStatement ps = null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE commandes SET dateheure = ? , nombre = ? , produitNb = ?  WHERE id = ?");
			ps.setInt(4,c.getIdentifiant());
			ps.setDate(1,(Date) c.getDateHeure());
			ps.setInt(2,c.getNombre());
			ps.setInt(3,c.getIdVaccin());
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
	 * Permet de supprimer une commande à partir de son identifiant
	 * @param identifiant identifiant de la commande
	 */
	public void deleteCommande(int identifiant)
	{

		Connection con = null;
		PreparedStatement ps = null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM commandes WHERE id = ?");
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
	 * Permet de récupérer toutes les commandes stockées dans la liste des commandes
	 * @return une ArrayList de commandes
	 */
	public List<Commande> getListeCommandes()
	{
		return liste;
	}

	/**
	 * Permet de mettre a jour la liste des commandes avec ceux stocker dans la table commande
	 */
	public void update() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		liste=new ArrayList<Commande>();

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM commandes");

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next())
				liste.add(new Commande(rs.getInt("id"),rs.getDate("dateheure"),rs.getInt("nombre"),rs.getInt("produitNb")));


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

		 CommandeDAO commandeDAO=new CommandeDAO();
		
		 Date date = new Date(0);
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 System.out.println(formatter.format(date));
		 
		 Commande F = new Commande(date,100,1);
		 
		 int retour = commandeDAO.ajouter(F);
		 
		 System.out.println(retour+ " lignes ajoutées");
		 
		 Commande F1 = commandeDAO.getCommande(1);
		 System.out.println(F1);
		 System.out.println("");
	
		 commandeDAO.deleteCommande(1);
		 commandeDAO.getListeCommandes().get(1).setNombre(555);
		 commandeDAO.replaceCommande( commandeDAO.getListeCommandes().get(1));
		 
		 List<Commande> liste = commandeDAO.getListeCommandes();
		 
		 for(Commande FL : liste) {
			 System.out.println(FL);
		 }

	}
}

