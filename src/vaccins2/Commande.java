package vaccins2;

import java.util.Date;

/**
 * Classe Commande
 * @version 1.0
 * */
public class Commande {

	/** 
	 * identifiant de la commande
	 */
	private int identifiant;
	
	/** 
	 * date et heure de la commande
	 */
	private Date dateHeure;
	
	/** 
	 * nombre de vaccins
	 */
	private int nombre;
	
	/** 
	 * identifiant du vaccin auxquel appartient la commande 
	 */
	private int idVaccin;

	
	/**
	 * Constructeur
	 * @param identifiant identifiant de la commande
	 * @param dateHeure date et heure de la commande
	 * @param nombre nombre de vaccin de la commande
	 * @param idVaccin identifiant du vaccin auxquel la commande appartient
	 */
	public Commande(int identifiant, Date dateHeure, int nombre, int idVaccin) {
		super();
		this.identifiant = identifiant;
		this.dateHeure = dateHeure;
		this.nombre = nombre;
		this.idVaccin = idVaccin;
	}


	/**
	 * getter pour l'attribut identifiant
	 * @return valeur de l'identifiant de la commande
	 */
	public int getIdentifiant() {
		return identifiant;
	}


	/**
	 * getter pour l'attribut dateHeure
	 * @return valeur de la date et l'heure de la commande
	 */
	public Date getDateHeure() {
		return dateHeure;
	}


	/**
	 * setter  pour l'attribut dateHeure
	 * @param dateHeure :  nouvelle valeur de la date et heure de la commande
	 */
	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}


	/**
	 * getter pour l'attribut nombre
	 * @return valeur du nombre de vaccin de la commande
	 */
	public int getNombre() {
		return nombre;
	}


	/**
	 * setter  pour l'attribut nombre
	 * @param nombre :  nouvelle valeur du nombre de vaccin de la commande
	 */
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}


	/**
	 * getter pour l'attribut idVaccin
	 * @return valeur de l'identifiant vaccin auxquel la commande appartient
	 */
	public int getIdVaccin() {
		return idVaccin;
	}


	/**
	 * setter  pour l'attribut idVaccin
	 * @param idVaccin :  nouvelle valeur de l'identifiant du vaccin auxquel la commande appartient
	 */
	public void setIdVaccin(int idVaccin) {
		this.idVaccin = idVaccin;
	}

	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	@Override
	public String toString() {
		return "Commande [identifiant=" + identifiant + ", dateHeure=" + dateHeure + ", nombre=" + nombre
				+ ", idVaccin=" + idVaccin + "]";
	}
	
	
	
	
	
	
}
