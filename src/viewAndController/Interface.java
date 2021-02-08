package viewAndController;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.CommandeDAO;
import dao.FournisseurDAO;
import dao.ProduitDAO;
import model.Commande;
import model.Fournisseur;
import model.Produit;


/**
 * 
 * @author Alban Spindler
 * Classe main qui crééz toute l'interface
 */
public class Interface extends JFrame implements ActionListener{
	
	/**
	 * 	Le stockage de donnée: ProduitDAO, CommandeDAO et FournisseurDAO
	 */
	private ProduitDAO produits;
	private CommandeDAO commandes;
	private FournisseurDAO fournisseurs;
	
	/**
	 * Les ids de certaines données dont on a besoin dans le code:
	 * nextIdProduit, idSelectedProduit
	 * nextIdFournisseur, idSelectedFournisseur
	 * nextIdCommande, idSelectedCommande
	 */
	private int nextIdProduit, idSelectedProduit;
	private int nextIdFournisseur, idSelectedFournisseur;
	private int nextIdCommande, idSelectedCommande;
	
	/**
	 * Le conteneur d'onglet: onglet
	 * Les onglets: produit, commande et fournisseur
	 */
	private JTabbedPane onglets;
	private JPanel produit, commande, fournisseur;
	
	/**
	 * Tous les attributs graphiques de l'onglet produit:
	 * nomProduitLabel, prixProduitLabel, nomFournisseurProduitLabel, typeProduitLabel
	 * nomProduitField, prixProduitField, nomFournisseurProduitField, typeProduitField
	 * modifierProduit, ajouterProduit, supprimerProduit
	 * tableProduit
	 * modelTableProduit
	 * panelTableProduit
	 * scrollProduit
	 * labelErreurProduit
	 */
	private JLabel nomProduitLabel, prixProduitLabel, nomFournisseurProduitLabel, typeProduitLabel;
	private JTextField nomProduitField, prixProduitField, nomFournisseurProduitField, typeProduitField;
	private JButton modifierProduit, ajouterProduit, supprimerProduit;
	private JTable tableProduit;
	private DefaultTableModel modelTableProduit;
	private JPanel panelTableProduit;
	private JScrollPane scrollProduit;
	private JLabel labelErreurProduit;
	
	/**
	 * les attributs graphique de l'onglet fournisseur:
	 * nomFournisseurLabel, adresseLabel, codePostalLabel, villeLabel, telLabel
	 * nomFournisseurField, adresseField, codePostalField, villeField, telField
	 * modifierFournisseur, ajouterFournisseur, supprimerFournisseur
	 * tableFournisseur
	 * modelTableFournisseur
	 * panelTableFournisseur
	 * scrollFournisseur
	 * labelErreurFournisseur
	 */
	private JLabel nomFournisseurLabel, adresseLabel, codePostalLabel, villeLabel, telLabel;
	private JTextField nomFournisseurField, adresseField, codePostalField, villeField, telField;
	private JButton modifierFournisseur, ajouterFournisseur, supprimerFournisseur;
	private JTable tableFournisseur;
	private DefaultTableModel modelTableFournisseur;
	private JPanel panelTableFournisseur;
	private JScrollPane scrollFournisseur;
	private JLabel labelErreurFournisseur;
	
	/**
	 * les attributs graphique de l'onglet Commande:
	 * nomProduitCommandeLabel, quantiteLabel
	 * nomProduitCommandeField, quantiteField
	 * modifierCommande, ajouterCommande, supprimerCommande
	 * tableCommande
	 * modelTableCommande
	 * panelTableCommande
	 * scrollCommande
	 * labelErreurCommande
	 */
	private JLabel nomProduitCommandeLabel, quantiteLabel;
	private JTextField nomProduitCommandeField, quantiteField;
	private JButton modifierCommande, ajouterCommande, supprimerCommande;
	private JTable tableCommande;
	private DefaultTableModel modelTableCommande;
	private JPanel panelTableCommande;
	private JScrollPane scrollCommande;
	private JLabel labelErreurCommande;
	
	/**
	 * Constructeur: 
	 * créé toute la partie graphique.
	 * Divisé en trois partie majeure: création de l'onglet produit, création de l'onglet fournisseur et enfin création de l'onglet commande
	 */
	public Interface() {
		produits = new ProduitDAO();
		commandes = new CommandeDAO();
		fournisseurs = new FournisseurDAO();
		
		nextIdProduit = 0;
		idSelectedProduit = -1;
		nextIdFournisseur = 0;
		idSelectedFournisseur = -1;
		nextIdCommande = 0;
		idSelectedCommande = -1;
		
		setSize(1000, 605);
		setLayout( null);
		
		//Initialisation onglets
		onglets = new JTabbedPane();
		onglets.setBounds( 10, 5, 950, 560);
		produit = new JPanel();
		produit.setLayout(null);
		commande = new JPanel();
		commande.setLayout(null);
		fournisseur = new JPanel();
		fournisseur.setLayout(null);
		
		//Initialisation onglet produit
		
		//Labels
		nomProduitLabel = new JLabel("Nom du produit: ");
		nomProduitLabel.setBounds( 20, 50, 100, 30);
		prixProduitLabel = new JLabel("Prix: ");
		prixProduitLabel.setBounds( 20, 85, 100, 30);
		nomFournisseurProduitLabel = new JLabel("Nom du Fournisseur: ");
		nomFournisseurProduitLabel.setBounds( 20, 120, 120, 30);
		typeProduitLabel = new JLabel("Type : ");
		typeProduitLabel.setBounds( 20, 155, 120, 30);
		labelErreurProduit = new JLabel("");
		labelErreurProduit.setBounds( 10, 20, 400, 30);
		labelErreurProduit.setForeground(new Color(255,0,0));
		
		//Fields
		nomProduitField = new JTextField();
		nomProduitField.setBounds( 150, 50, 150, 30);
		prixProduitField = new JTextField();
		prixProduitField.setBounds( 150, 85, 150, 30);
		nomFournisseurProduitField = new JTextField();
		nomFournisseurProduitField.setBounds( 150, 120, 150, 30);
		typeProduitField = new JTextField();
		typeProduitField.setBounds( 150, 155, 150, 30);
		
		//Boutons
		modifierProduit = new JButton("Modifier");
		modifierProduit.setBounds( 10, 220, 100, 30);
		modifierProduit.addActionListener(this);
		ajouterProduit = new JButton("Ajouter");
		ajouterProduit.setBounds( 115, 220, 100, 30);
		ajouterProduit.addActionListener(this);
		supprimerProduit = new JButton("Supprimer");
		supprimerProduit.setBounds( 220, 220, 100, 30);
		supprimerProduit.addActionListener(this);
		
		//table
		panelTableProduit = new JPanel();
		panelTableProduit.setLayout( new GridLayout());
		panelTableProduit.setBounds( 350, 10, 600, 500);
		
		modelTableProduit = new DefaultTableModel();
		tableProduit = new JTable( modelTableProduit);
		tableProduit.setEnabled(true);
		JTextField tf = new JTextField();
		tf.setEditable(false);
		DefaultCellEditor editor = new DefaultCellEditor(tf);
		tableProduit.setDefaultEditor(Object.class, editor);
		
		modelTableProduit.addColumn("Id");
		modelTableProduit.addColumn("Nom du Produit");
		modelTableProduit.addColumn("Type");
		modelTableProduit.addColumn("Prix");
		modelTableProduit.addColumn("Fournisseur");
		modelTableProduit.addColumn("Stock");
		
		scrollProduit = new JScrollPane( tableProduit, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelTableProduit.add(scrollProduit);
		
		//Listener pour le click d'une ligne
		tableProduit.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if( tableProduit.getSelectedRow() != -1) {
		            idSelectedProduit = Integer.valueOf( (String)tableProduit.getValueAt(tableProduit.getSelectedRow(), 0));
		            nomProduitField.setText((String)tableProduit.getValueAt(tableProduit.getSelectedRow(), 1));
		            prixProduitField.setText((String)tableProduit.getValueAt(tableProduit.getSelectedRow(), 3));
		            nomFournisseurProduitField.setText((String)tableProduit.getValueAt(tableProduit.getSelectedRow(), 4));
		            typeProduitField.setText((String)tableProduit.getValueAt(tableProduit.getSelectedRow(), 2));
	        	}
	        }
	    });
		
		//Ajoute les lignes des produits
		resetTableProduit();
		
		//Ajout de tous les éléments dans l'onglet produit
		produit.add( nomProduitLabel);
		produit.add( prixProduitLabel);
		produit.add( nomFournisseurProduitLabel);
		produit.add( typeProduitLabel);
		produit.add( nomProduitField);
		produit.add( prixProduitField);
		produit.add( nomFournisseurProduitField);
		produit.add( typeProduitField);
		produit.add( modifierProduit);
		produit.add( ajouterProduit);
		produit.add( supprimerProduit);
		produit.add( panelTableProduit);
		produit.add(labelErreurProduit);
		
		//Initialisation onglet fournisseur
		
		//Labels
		nomFournisseurLabel = new JLabel("Nom du fournisseur: ");
		nomFournisseurLabel.setBounds( 20, 50, 200, 30);
		adresseLabel = new JLabel("Adresse: ");
		adresseLabel.setBounds( 20, 85, 100, 30);
		codePostalLabel = new JLabel("Code Postal: ");
		codePostalLabel.setBounds( 20, 120, 120, 30);
		villeLabel = new JLabel("Ville : ");
		villeLabel.setBounds( 20, 155, 120, 30);
		telLabel = new JLabel("Téléphone : ");
		telLabel.setBounds( 20, 190, 120, 30);
		labelErreurFournisseur = new JLabel("");
		labelErreurFournisseur.setBounds( 10, 20, 400, 30);
		labelErreurFournisseur.setForeground(new Color(255,0,0));
				
		//Fields
		nomFournisseurField = new JTextField();
		nomFournisseurField.setBounds( 150, 50, 150, 30);
		adresseField = new JTextField();
		adresseField.setBounds( 150, 85, 150, 30);
		codePostalField = new JTextField();
		codePostalField.setBounds( 150, 120, 150, 30);
		villeField = new JTextField();
		villeField.setBounds( 150, 155, 150, 30);
		telField = new JTextField();
		telField.setBounds(150, 190, 150, 30);
				
		//Boutons
		modifierFournisseur = new JButton("Modifier");
		modifierFournisseur.setBounds( 10, 300, 100, 30);
		modifierFournisseur.addActionListener(this);
		ajouterFournisseur = new JButton("Ajouter");
		ajouterFournisseur.setBounds( 115, 300, 100, 30);
		ajouterFournisseur.addActionListener(this);
		supprimerFournisseur = new JButton("Supprimer");
		supprimerFournisseur.setBounds( 220, 300, 100, 30);
		supprimerFournisseur.addActionListener(this);
				
		//table
		panelTableFournisseur = new JPanel();
		panelTableFournisseur.setLayout( new GridLayout());
		panelTableFournisseur.setBounds( 350, 10, 600, 500);
				
		modelTableFournisseur = new DefaultTableModel();
		tableFournisseur = new JTable( modelTableFournisseur);
		tableFournisseur.setEnabled(true);
		tf = new JTextField();
		tf.setEditable(false);
		editor = new DefaultCellEditor(tf);
		tableFournisseur.setDefaultEditor(Object.class, editor);
				
		modelTableFournisseur.addColumn("Id");
		modelTableFournisseur.addColumn("Nom du Fournisseur");
		modelTableFournisseur.addColumn("Adresse");
		modelTableFournisseur.addColumn("Code Postal");
		modelTableFournisseur.addColumn("Ville");
		modelTableFournisseur.addColumn("Téléphone");
				
		scrollFournisseur = new JScrollPane( tableFournisseur, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelTableFournisseur.add(scrollFournisseur);
				
		//Listener pour le click d'une ligne
		tableFournisseur.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
			    	if( tableFournisseur.getSelectedRow() != -1) {
				           	idSelectedFournisseur = Integer.valueOf( (String)tableFournisseur.getValueAt(tableFournisseur.getSelectedRow(), 0));
				            nomFournisseurField.setText((String)tableFournisseur.getValueAt(tableFournisseur.getSelectedRow(), 1));
				            codePostalField.setText((String)tableFournisseur.getValueAt(tableFournisseur.getSelectedRow(), 3));
				            villeField.setText((String)tableFournisseur.getValueAt(tableFournisseur.getSelectedRow(), 4));
				            adresseField.setText((String)tableFournisseur.getValueAt(tableFournisseur.getSelectedRow(), 2));
				            telField.setText((String)tableFournisseur.getValueAt(tableFournisseur.getSelectedRow(), 5));
			        	}
			 }
		});
				
		//Ajoute les lignes des produits
		resetTableFournisseur();
				
		//Ajout de tous les éléments dans l'onglet produit
		fournisseur.add( nomFournisseurLabel);
		fournisseur.add( adresseLabel);
		fournisseur.add( codePostalLabel);
		fournisseur.add( villeLabel);
		fournisseur.add( telLabel);
		fournisseur.add( nomFournisseurField);
		fournisseur.add( adresseField);
		fournisseur.add( codePostalField);
		fournisseur.add( villeField);
		fournisseur.add( telField);
		fournisseur.add( modifierFournisseur);
		fournisseur.add( ajouterFournisseur);
		fournisseur.add( supprimerFournisseur);
		fournisseur.add( panelTableFournisseur);
		fournisseur.add(labelErreurFournisseur);
		
		//Initialisation onglet commande
		
		//Labels
		nomProduitCommandeLabel = new JLabel("Nom du produit: ");
		nomProduitCommandeLabel.setBounds( 20, 50, 100, 30);
		quantiteLabel = new JLabel("Quantite: ");
		quantiteLabel.setBounds( 20, 85, 100, 30);
		labelErreurCommande = new JLabel("");
		labelErreurCommande.setBounds( 10, 20, 400, 30);
		labelErreurCommande.setForeground(new Color(255,0,0));
				
		//Fields
		nomProduitCommandeField = new JTextField();
		nomProduitCommandeField.setBounds( 150, 50, 150, 30);
		quantiteField = new JTextField();
		quantiteField.setBounds( 150, 85, 150, 30);
				
		//Boutons
		modifierCommande = new JButton("Modifier");
		modifierCommande.setBounds( 10, 150, 100, 30);
		modifierCommande.addActionListener(this);
		ajouterCommande = new JButton("Ajouter");
		ajouterCommande.setBounds( 115, 150, 100, 30);
		ajouterCommande.addActionListener(this);
		supprimerCommande = new JButton("Supprimer");
		supprimerCommande.setBounds( 220, 150, 100, 30);
		supprimerCommande.addActionListener(this);
				
		//table
		panelTableCommande = new JPanel();
		panelTableCommande.setLayout( new GridLayout());
		panelTableCommande.setBounds( 350, 10, 600, 500);
				
		modelTableCommande = new DefaultTableModel();
		tableCommande = new JTable( modelTableCommande);
		tableCommande.setEnabled(true);
		tf = new JTextField();
		tf.setEditable(false);
		editor = new DefaultCellEditor(tf);
		tableCommande.setDefaultEditor(Object.class, editor);
				
		modelTableCommande.addColumn("Id");
		modelTableCommande.addColumn("Nom du Produit");
		modelTableCommande.addColumn("Date");
		modelTableCommande.addColumn("Quantite");
		modelTableCommande.addColumn("Prix à l'unité");
				
		scrollCommande = new JScrollPane( tableCommande, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelTableCommande.add(scrollCommande);
				
		//Listener pour le click d'une ligne
		tableCommande.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
			       if( tableCommande.getSelectedRow() != -1) {
				          idSelectedCommande = Integer.valueOf( (String)tableCommande.getValueAt(tableCommande.getSelectedRow(), 0));
				          nomProduitCommandeField.setText((String)tableCommande.getValueAt(tableCommande.getSelectedRow(), 1));
				          quantiteField.setText((String)tableCommande.getValueAt(tableCommande.getSelectedRow(), 3));
			       }
			 }
	    });
				
		//Ajoute les lignes des produits
		resetTableCommande();
				
		//Ajout de tous les éléments dans l'onglet produit
		commande.add( nomProduitCommandeLabel);
		commande.add( quantiteLabel);
		commande.add( nomProduitCommandeField);
		commande.add( quantiteField);
		commande.add( modifierCommande);
		commande.add( ajouterCommande);
		commande.add( supprimerCommande);
		commande.add( panelTableCommande);
		commande.add(labelErreurCommande);
		
		//Ajout des pannels en tant qu'onglets
		onglets.add( "Produits", produit);
		onglets.add( "Fournisseurs", fournisseur);
		onglets.add( "Commandes", commande);
		
		//Ajout des onglets dans la fenêtre
		add( onglets);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	/**
	 * Permet de réafficher le tableau des produits
	 */
	public void resetTableProduit(){
		tableProduit.clearSelection();
		idSelectedProduit = -1;
		modelTableProduit.setRowCount(0);
		Object[] row = null;
		java.util.List<Produit> prods = produits.getListeProduits();
		for ( Produit p : prods) {
			if( p.getIdentifiant() >= nextIdProduit)
				nextIdProduit = p.getIdentifiant() + 1;
			String ligne = p.getIdentifiant() + "," + p.getNom()  + "," + p.getType() + "," + p.getPrix() + "," + fournisseurs.getFournisseur( p.getIdFournisseur()).getNom() + "," + p.getStock();
			row = ligne.split(",");
			modelTableProduit.addRow(row);
		}
		panelTableProduit.revalidate();
	}
	
	/**
	 * Permet de réafficher le tableau des fournisseurs
	 */
	public void resetTableFournisseur(){
		tableFournisseur.clearSelection();
		idSelectedFournisseur = -1;
		modelTableFournisseur.setRowCount(0);
		Object[] row = null;
		java.util.List<Fournisseur> fourniss = fournisseurs.getListeFournisseurs();
		for ( Fournisseur f : fourniss) {
			if( f.getIdentifiant() >= nextIdFournisseur)
				nextIdFournisseur = f.getIdentifiant() + 1;
			String ligne = f.getIdentifiant() + "," + f.getNom()  + "," + f.getAdresse() + "," + f.getCode_postal() + "," + f.getVille() + "," + f.getTelephone();
			row = ligne.split(",");
			modelTableFournisseur.addRow(row);
		}
		panelTableFournisseur.revalidate();
	}
	
	/**
	 * Permet de réafficher le tableau des commandes
	 */
	public void resetTableCommande(){
		tableCommande.clearSelection();
		idSelectedCommande = -1;
		modelTableCommande.setRowCount(0);
		Object[] row = null;
		java.util.List<Commande> comm = commandes.getListeCommandes();
		for ( Commande c : comm) {
			if( c.getIdentifiant() >= nextIdCommande)
				nextIdCommande = c.getIdentifiant() + 1;
			String ligne = c.getIdentifiant() + "," + produits.getProduit( c.getIdVaccin()).getNom()  + "," + c.getDateHeure().toString() + "," + c.getNombre() + "," + produits.getProduit( c.getIdVaccin()).getPrix();
			row = ligne.split(",");
			modelTableCommande.addRow(row);
		}
		panelTableCommande.revalidate();
	}

	
	/**
	 * Permet de gérer l'appui des boutons.
	 * Cette fonction est appelée quand l'interface détecte une action de l'utilisateur.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Boutons produit
		if( e.getSource() == ajouterProduit) {
			if( nomProduitField.getText().equals("") || prixProduitField.getText().equals("") || nomFournisseurProduitField.getText().equals("") ) {
				labelErreurProduit.setText("Aucun champ ne doit être vide");
			}
			else {
				try {
					int idfournisseur = -1, i = 0;
					boolean test = false;
					while(!test) {
						if( fournisseurs.getListeFournisseurs().get(i).getNom().equals( nomFournisseurProduitField.getText())) {
							idfournisseur = fournisseurs.getListeFournisseurs().get(i).getIdentifiant();
							test = true;
						}else if( i == fournisseurs.getListeFournisseurs().size()-1) {
							test = true;
						}
						i++;
					}
					if(idfournisseur == -1) {
						labelErreurProduit.setText( "Fournisseur non trouvé");
					}else {
						Produit produitAjoute = new Produit(nextIdProduit, nomProduitField.getText(), Float.parseFloat( prixProduitField.getText()), typeProduitField.getText(), 0, idfournisseur);
						produits.ajouter(produitAjoute);
						resetTableProduit();
						labelErreurProduit.setText("");
					}
				}catch(NumberFormatException exception) {
					labelErreurProduit.setText(" le prix doit être: nombre(+point si besoin) Ex: 1.2");
				}
			}
		}else if (e.getSource() == modifierProduit) {
			if( idSelectedProduit == -1) {
				labelErreurProduit.setText("Veuillez d'abord selectionner un produit");
			}else {
				try {
					Produit produitSelected = produits.getProduit(idSelectedProduit);
					Fournisseur fournisseurSelected = fournisseurs.getFournisseur(produitSelected.getIdFournisseur());
					
					//si aucun text field n'a été changé
					if( nomProduitField.getText().equals( produitSelected.getNom()) && Float.parseFloat( prixProduitField.getText()) == produitSelected.getPrix() && produitSelected.getType().equals( typeProduitField.getText()) && fournisseurSelected.getNom().equals( nomFournisseurProduitField.getText())) {
						labelErreurProduit.setText("Veuillez modifier un des champs");
					}else if( !fournisseurSelected.getNom().equals( nomFournisseurProduitField.getText())){
						labelErreurProduit.setText("Supprimez le produit et recréez un nouveau produit avec ce fournisseur");
					}else {
						Produit produitModifie = new Produit( produitSelected.getIdentifiant(), nomProduitField.getText(), Float.parseFloat( prixProduitField.getText()), typeProduitField.getText(), produitSelected.getStock(), fournisseurSelected.getIdentifiant());
						produits.replaceProduit(produitModifie);
						resetTableProduit();
						labelErreurProduit.setText("");
					}
				}catch(NumberFormatException exception) {
					labelErreurProduit.setText("le prix doit être: nombre(+point si besoin) Ex: 1.2");
				}
			}
		}else if (e.getSource() == supprimerProduit) {
			if( idSelectedProduit == -1) {
				labelErreurProduit.setText("Veuillez d'abord selectionner un produit");
			}else {
				produits.deleteProduit(idSelectedProduit);
				resetTableProduit();
				labelErreurProduit.setText("");
			}
		}
		
		//Boutons Fournisseur
		else if( e.getSource() == ajouterFournisseur) {
			if( nomFournisseurField.getText().equals("") || adresseField.getText().equals("") || codePostalField.getText().equals("") ||  villeField.getText().equals("") || telField.getText().equals("")) {
				labelErreurFournisseur.setText("Aucun champ ne doit être vide");
			}
			else {
				Fournisseur fournisseurAjoute = new Fournisseur( nextIdFournisseur, nomFournisseurField.getText(), "France", adresseField.getText(), villeField.getText(), codePostalField.getText(), telField.getText());
				fournisseurs.ajouter(fournisseurAjoute);
				resetTableFournisseur();
				labelErreurFournisseur.setText("");
			}
		}else if (e.getSource() == modifierFournisseur) {
			if( idSelectedFournisseur == -1) {
				labelErreurFournisseur.setText("Veuillez d'abord selectionner un fournisseur");
			}else {
				Fournisseur fournisseurSelected = fournisseurs.getFournisseur(idSelectedFournisseur);
				
				//si aucun text field n'a été changé
				if( nomFournisseurField.getText().equals( fournisseurSelected.getNom()) && adresseField.getText().equals( fournisseurSelected.getAdresse()) && codePostalField.getText().equals( fournisseurSelected.getCode_postal()) && villeField.getText().equals( fournisseurSelected.getVille()) && telField.getText().equals( fournisseurSelected.getTelephone())) {
					labelErreurFournisseur.setText("Veuillez modifier un des champs");
				}else {
					Fournisseur fournisseurAjoute = new Fournisseur( idSelectedFournisseur, nomFournisseurField.getText(), "France", adresseField.getText(), villeField.getText(), codePostalField.getText(), telField.getText());
					fournisseurs.replaceFournisseur(fournisseurAjoute);
					resetTableFournisseur();
					labelErreurFournisseur.setText("");
				}
			}
		}else if (e.getSource() == supprimerFournisseur) {
			if( idSelectedFournisseur == -1) {
				labelErreurFournisseur.setText("Veuillez d'abord selectionner un produit");
			}else {
				fournisseurs.deleteFournisseur(idSelectedFournisseur);
				resetTableFournisseur();
				labelErreurFournisseur.setText("");
			}
		}
		
		// Boutons commande
		if( e.getSource() == ajouterCommande) {
			if( nomProduitCommandeField.getText().equals("") || quantiteField.getText().equals("") ) {
				labelErreurFournisseur.setText("Aucun champ ne doit être vide");
			}
			else {
				try {
					int idProduit = -1, i = 0;
					boolean test = false;
					while(!test) {
						if( produits.getListeProduits().get(i).getNom().equals( nomProduitCommandeField.getText())) {
							idProduit = produits.getListeProduits().get(i).getIdentifiant();
							test = true;
						}else if( i == produits.getListeProduits().size()-1) {
							test = true;
						}
						i++;
					}
					if(idProduit == -1) {
						labelErreurFournisseur.setText( "Produit non trouvé");
					}else {
						Commande commandeAjoute = new Commande( nextIdCommande, new Date( System.currentTimeMillis()), Integer.valueOf( quantiteField.getText()), idProduit);
						commandes.ajouter(commandeAjoute);
						produits.update();
						resetTableCommande();
						resetTableProduit();
						labelErreurFournisseur.setText("");
					}
				}catch(NumberFormatException exception) {
					labelErreurFournisseur.setText("la quantite doit être un nombre sans virgule");
				}
			}
		}else if (e.getSource() == modifierCommande) {
			if( idSelectedCommande == -1 ) {
				labelErreurCommande.setText("Veuillez d'abord selectionner une commande");
			}else {
				try {
					Commande commandeSelected = commandes.getCommande( idSelectedCommande);
					Produit produitSelected = produits.getProduit( commandeSelected.getIdVaccin());
					
					//si aucun text field n'a été changé
					if( nomProduitCommandeField.getText().equals( produitSelected.getNom()) && Integer.valueOf( prixProduitField.getText()) == commandeSelected.getNombre() ) {
						labelErreurCommande.setText("Veuillez modifier un des champs");
					}else if( !produitSelected.getNom().equals( nomProduitCommandeField.getText())){
						labelErreurCommande.setText("Supprimez la commande et recréez une nouvelle commande avec ce produit");
					}else {
						Commande commandeModifie = new Commande( idSelectedCommande, commandeSelected.getDateHeure(), Integer.valueOf( quantiteField.getText()), produitSelected.getIdentifiant());
						commandes.replaceCommande(commandeModifie);
						produits.update();
						resetTableCommande();
						resetTableProduit();
						labelErreurCommande.setText("");
					}
				}catch(NumberFormatException exception) {
					labelErreurCommande.setText("la quantite doit être un nombre sans virgule");
				}
			}
		}else if (e.getSource() == supprimerCommande) {
			if( idSelectedCommande == -1) {
				labelErreurCommande.setText("Veuillez d'abord selectionner une commande");
			}else {
				commandes.deleteCommande(idSelectedCommande);
				resetTableCommande();
				labelErreurCommande.setText("");
			}
		}
	}
	
	/**
	 * Fonction main. 
	 * Initialise l'interface.
	 * @param args
	 */
	public static void main(String[] args) { 
		new Interface(); 
	}

}
