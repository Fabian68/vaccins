package vaccins2;

public class Produit {

	/** 
	 * identifiant du produit
	 */
	private int identifiant;
	
	/** 
	 * nome du produit
	 */
	private String nom;
	
	/** 
	 * prix du produit
	 */
	private float prix;
	
	/** 
	 * type du produit
	 */
	private String type;
	
	/** 
	 * stock du produit
	 */
	private long  stock;
	
	/** 
	 * idantifiant du fournisseur
	 */
	private int idFournisseur;

	/**
	 * Constructeur
	 * @param identifiant identifiant du produit
	 * @param nom nom du produit
	 * @param prix prix du produit
	 * @param type type du produit
	 * @param stock stock du produit
	 * @param idFournisseur idantifiant du fournisseur servant a lier les vaccins a leurs fournisseurs
	 */
	public Produit(int identifiant, String nom, float prix, String type, long stock, int idFournisseur) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.prix = prix;
		this.type = type;
		this.stock = stock;
		this.idFournisseur = idFournisseur;
	}

	/**
	 * getter pour l'attribut identifiant
	 * @return valeur de l'identifiant du produit
	 */
	public int getIdentifiant() {
		return identifiant;
	}

	
	/**
	 * getter pour l'attribut nom
	 * @return valeur du nom du produit
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter  pour l'attribut nom
	 * @param nom :  nouvelle valeur du nom du produit
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * getter pour l'attribut prix
	 * @return valeur du prix du produit
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * setter  pour l'attribut prix
	 * @param prix :  nouvelle valeur du prix du produit
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	/**
	 * getter pour l'attribut type
	 * @return valeur du type du produit
	 */
	public String getType() {
		return type;
	}

	/**
	 * setter  pour l'attribut type
	 * @param type :  nouvelle valeur du type du produit
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * getter pour l'attribut stock
	 * @return valeur du stock du produit
	 */
	public long getStock() {
		return stock;
	}

	/**
	 * setter  pour l'attribut stock
	 * @param stock :  nouvelle valeur du stock du fournisseur
	 */
	public void setStock(long stock) {
		this.stock = stock;
	}

	/**
	 * getter pour l'attribut idFournisseur
	 * @return valeur de l'identifiant du fournisseur auxquel le produit appartient
	 */
	public int getIdFournisseur() {
		return idFournisseur;
	}

	/**
	 * setter  pour l'attribut idFournisseur
	 * @param idFournisseur :  nouvelle valeur de l'identifient du fournisseur auxquel le produit appartient
	 */
	public void setIdFournisseur(int idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	@Override
	public String toString() {
		return "Produit [identifiant=" + identifiant + ", nom=" + nom + ", prix=" + prix + ", type=" + type + ", stock="
				+ stock + ", idFournisseur=" + idFournisseur + "]";
	}
	
	
}
