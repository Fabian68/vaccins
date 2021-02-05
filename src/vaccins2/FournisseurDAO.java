package vaccins2;

import java.sql.*;
import java.util.List;

/**
 * Classe d'acc�s aux donn�es contenues dans la table fournisseur
 * @version 1.1
 * */
public class FournisseurDAO {

	/**
	 * Param�tres de connexion � la base de donn�es oracle
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
		// chargement du pilote de bases de donn�es
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
		
		 //test de la m�thode ajouter
		/*Article a = new Article("Set de 2 raquettes de ping-pong",149.9,10);
		int retour=articleDAO.ajouter(a);

		System.out.println(retour+ " lignes ajout�es");
*/
		//test de la m�thode getArticle
	/*	Article a2 = articleDAO.getArticle(1);
		System.out.println(a2);
*/
		 //test de la m�thode getListeArticles
	//	List<Article> liste=articleDAO.getListeArticles();
		//System.out.println(liste);
	/*	for(Article art : liste)
		{
			System.out.println(art.toString());
		}*/

	}
}
