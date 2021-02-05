package vaccins2;

import java.sql.*;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table fournisseur
 * @version 1.1
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
	 * Constructeur de la classe
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

	}
	
	//main permettant de tester la classe
	public static void main(String[] args)  throws SQLException {

		 FournisseurDAO fournisseurDAO=new FournisseurDAO();
		
		 //test de la méthode ajouter
		/*Article a = new Article("Set de 2 raquettes de ping-pong",149.9,10);
		int retour=articleDAO.ajouter(a);

		System.out.println(retour+ " lignes ajoutées");
*/
		//test de la méthode getArticle
	/*	Article a2 = articleDAO.getArticle(1);
		System.out.println(a2);
*/
		 //test de la méthode getListeArticles
	//	List<Article> liste=articleDAO.getListeArticles();
		//System.out.println(liste);
	/*	for(Article art : liste)
		{
			System.out.println(art.toString());
		}*/

	}
}
