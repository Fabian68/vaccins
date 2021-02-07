package vaccins2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table fournisseur
 * @version 1.0
 * */
public class FournisseurDAO {

	/**
	 * Paramètres de connexion à la base de données oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	final static String URL = "jdbc:mysql://localhost:3306/gestionvaccin";
	final static String LOGIN="root";
	final static String PASS="";
	
	/** 
	 * liste des fournisseurs connue en temps réel pour éviter de faire des requetes sans changements
	 */
	private List<Fournisseur> liste;
	
	/**
	 * Constructeur de la classe - initialise la liste
	 * 
	 */
	public FournisseurDAO()
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
	 * Permet d'ajouter un fournisseur dans la table fournisseur
	 * Le mode est auto-commit par défaut : chaque insertion est validée
	 * @param nouveauFournisseur le fournisseur à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int ajouter(Fournisseur nouveauFournisseur)
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
			ps = con.prepareStatement("INSERT INTO fournisseurs (nom, pays, adresse,ville,code_postal,telephone) VALUES ( ?, ?, ?,?,?,?)");
			ps.setString(1,nouveauFournisseur.getNom());
			ps.setString(2,nouveauFournisseur.getPays());
			ps.setString(3,nouveauFournisseur.getAdresse());
			ps.setString(4,nouveauFournisseur.getVille());
			ps.setString(5,nouveauFournisseur.getCode_postal());
			ps.setString(6,nouveauFournisseur.getTelephone());

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
	 * Permet de récupérer un fournisseur à partir de son identifiant
	 * @param identifiant identifiant du fournisseur
	 * @return un fournisseur
	 * @return null si aucun fournisseur ne correspond a cet identifiant
	 */
	public Fournisseur getFournisseur(int identifiant)
	{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Fournisseur retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM fournisseurs WHERE id = ?");
			ps.setInt(1,identifiant);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())
				retour=new Fournisseur(rs.getInt("id"),rs.getString("nom"),rs.getString("pays"),rs.getString("adresse"),rs.getString("ville"),rs.getString("code_postal"),rs.getString("telephone"));


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
	 * Permet de remplacer un fournisseur par un autre dans la table à l'aide de l'identifiant, sert notamment pour la modification d'une ligne
	 * @param f le fournisseur qui comporte les modifications par rapport a l'ancienn fournisseur
	 */
	public void replaceFournisseur(Fournisseur f)
	{

		Connection con = null;
		PreparedStatement ps = null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE fournisseurs SET nom = ? , pays = ? , adresse = ? , ville = ? , code_postal = ? , telephone = ? WHERE id = ? ");
			ps.setInt(7,f.getIdentifiant());
			ps.setString(1,f.getNom());
			ps.setString(2,f.getPays());
			ps.setString(3,f.getAdresse());
			ps.setString(4,f.getVille());
			ps.setString(5,f.getCode_postal());
			ps.setString(6,f.getTelephone());
			//on met a jour 
		
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
	 * Permet de supprimer un fournisseur à partir de son identifiant
	 * @param identifiant identifiant de la commande
	 */
	public void deleteFournisseur(int identifiant)
	{

		Connection con = null;
		PreparedStatement ps = null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM fournisseurs WHERE id = ?");
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
	 * Permet de récupérer tous les fournisseurs stockées dans la liste de fournisseurs
	 * @return une ArrayList de fournisseur
	 */
	public List<Fournisseur> getListeFournisseurs()
	{
		return liste;
	
	}

	/**
	 * Permet de mettre a jour la liste des fournisseurs avec ceux de la table fournisseur
	 */
	public void update() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		liste=new ArrayList<Fournisseur>();

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM fournisseurs");

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next())
				liste.add(new Fournisseur(rs.getInt("id"),rs.getString("nom"),rs.getString("pays"),rs.getString("adresse"),rs.getString("ville"),rs.getString("code_postal"),rs.getString("telephone")));


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

		 FournisseurDAO fournisseurDAO=new FournisseurDAO();
		
		 Fournisseur F = new Fournisseur("Moderna","Suisse","adresse de Moderna","Bonfol","3333","+41 71 11");
		 
		 int retour = fournisseurDAO.ajouter(F);
		 
		 System.out.println(retour+ " lignes ajoutées");
		 
		 Fournisseur F1 = fournisseurDAO.getFournisseur(1);
		 System.out.println(F1);
		 System.out.println("");
		 fournisseurDAO.deleteFournisseur(1);
		 fournisseurDAO.getListeFournisseurs().get(1).setNom("Nouveau Nom");
		 fournisseurDAO.replaceFournisseur( fournisseurDAO.getListeFournisseurs().get(1));
		 List<Fournisseur> liste = fournisseurDAO.getListeFournisseurs();
		 
		 for(Fournisseur FL : liste) {
			 System.out.println(FL);
		 }

	}
}
