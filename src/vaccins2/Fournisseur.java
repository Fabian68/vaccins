package vaccins2;

public class Fournisseur {
	
	/** 
	 * identifient du fournisseur
	 */
	private int identifient;
	
	/** 
	 * nom du fournisseur
	 */
	private String nom;
	
	/** 
	 * pays du fournisseur
	 */
	private String pays;
	
	/** 
	 * adresse du fournisseur
	 */
	private String adresse;
	
	/** 
	 * ville du fournisseur
	 */
	private String ville;
	
	/** 
	 * code postal du fournisseur
	 */
	private String code_postal;
	
	/** 
	 * telephone du fournisseur
	 */
	private int telephone;

	
	/**
	 * Constructeur
	 * @param identifient identifient du fournisseur
	 * @param nom nom du fournisseur
	 * @param pays pays du fournisseur
	 * @param adresse adresse du fournisseur
	 * @param ville ville du fournisseur
	 * @param code_postal code postal du fournisseur
	 * @param telephone telephone du fournisseur
	 */
	public Fournisseur(int identifient, String nom, String pays, String adresse, String ville, String code_postal,
			int telephone) {
		
		this.identifient = identifient;
		this.nom = nom;
		this.pays = pays;
		this.adresse = adresse;
		this.ville = ville;
		this.code_postal = code_postal;
		this.telephone = telephone;
	}


	/**
	 * getter pour l'attribut identifient
	 * @return valeur de l'identifient du fournisseur
	 */
	public int getIdentifient() {
		return identifient;
	}


	/**
	 * getter pour l'attribut nom
	 * @return valeur du nom du fournisseur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter  pour l'attribut nom
	 * @param nom :  nouvelle valeur du nom du fournisseur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * getter pour l'attribut pays
	 * @return valeur du pays du fournisseur
	 */
	public String getPays() {
		return pays;
	}


	/**
	 * setter  pour l'attribut pays
	 * @param nom :  nouvelle valeur du pays du fournisseur
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}


	/**
	 * getter pour l'attribut adresse
	 * @return valeur de l'adresse du fournisseur
	 */
	public String getAdresse() {
		return adresse;
	}


	/**
	 * setter  pour l'attribut adresse
	 * @param nom :  nouvelle valeur de l'adresse du fournisseur
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	/**
	 * getter pour l'attribut ville
	 * @return valeur de la ville du fournisseur
	 */
	public String getVille() {
		return ville;
	}


	/**
	 * setter  pour l'attribut ville
	 * @param nom :  nouvelle valeur de la ville du fournisseur
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}


	/**
	 * getter pour l'attribut code_postal
	 * @return valeur du code postal du fournisseur
	 */
	public String getCode_postal() {
		return code_postal;
	}


	/**
	 * setter  pour l'attribut code_postal
	 * @param nom :  nouvelle valeur du code postal du fournisseur
	 */
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}


	/**
	 * getter pour l'attribut telephone
	 * @return valeur du numero de telephone du fournisseur
	 */
	public int getTelephone() {
		return telephone;
	}


	/**
	 * setter  pour l'attribut telephone
	 * @param nom :  nouvelle valeur du numero de telephone du fournisseur
	 */
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	@Override
	public String toString() {
		return "Fournisseur [identifient=" + identifient + ", nom=" + nom + ", pays=" + pays + ", adresse=" + adresse
				+ ", ville=" + ville + ", code_postal=" + code_postal + ", telephone=" + telephone + "]";
	}
}
