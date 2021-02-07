package viewAndController;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.CommandeDAO;
import dao.FournisseurDAO;
import dao.ProduitDAO;
import model.Fournisseur;
import model.Produit;

public class Interface extends JFrame implements ActionListener{
	
	// Les données
	private ProduitDAO produits;
	private CommandeDAO commandes;
	private FournisseurDAO fournisseurs;
	
	//Les attributs des données
	private int nextIdProduit, idSelectedProduit;
	private int nextIdFournisseur, idSelectedFournisseur;
	private int nextIdCommande, idSelectedCommande;
	
	// Les 3 onglets et leur conteneur
	private JTabbedPane onglets;
	private JPanel produit, commande, fournisseur;
	
	// les attributs graphique de l'onglet produit
	private JLabel nomProduitLabel, prixProduitLabel, nomFournisseurProduitLabel, typeProduitLabel;
	private JTextField nomProduitField, prixProduitField, nomFournisseurProduitField, typeProduitField;
	private JButton modifierProduit, ajouterProduit, supprimerProduit;
	private JTable tableProduit;
	private DefaultTableModel modelTableProduit;
	private JPanel panelTableProduit;
	private JScrollPane scrollProduit;
	
	// les attributs graphique de l'onglet fournisseur
	private JLabel nomFournisseurLabel, adresseLabel, codePostalLabel, villeLabel, telLabel;
	private JTextField nomFournisseurField, adresseField, codePostalField, villeField, telField;
	private JButton modifierFournisseur, ajouterFournisseur, supprimerFournisseur;
	private JTable tableFournisseur;
	private DefaultTableModel modelTableFournisseur;
	private JPanel panelTableFournisseur;
	private JScrollPane scrollFournisseur;
	
	// les attributs graphique de l'onglet Commande
	private JLabel nomProduitCommandeLabel, quantiteLabel;
	private JTextField nomProduitCommandeField, quantiteField;
	private JButton modifierCommande, ajouterCommande, supprimerCommande;
	private JTable tableCommande;
	private DefaultTableModel modelTableCommande;
	private JPanel panelTableCommande;
	private JScrollPane scrollCommande;
	
	// Le label qui affiche une erreur si besoin
	private JLabel labelErreur;
	
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
		labelErreur = new JLabel("");
		labelErreur.setBounds( 20, 20, 300, 30);
		labelErreur.setForeground(new Color(255,0,0));
		
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
		panelTableProduit.setBorder(BorderFactory.createDashedBorder( Color.blue));
		
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
		produit.add(labelErreur);
		
		//Initialisation onglet fournisseur
		
		//Labels
		nomFournisseurLabel = new JLabel("Nom du fournisseur: ");
		nomFournisseurLabel.setBounds( 20, 50, 100, 30);
		adresseLabel = new JLabel("Adresse: ");
		adresseLabel.setBounds( 20, 85, 100, 30);
		codePostalLabel = new JLabel("Code Postal: ");
		codePostalLabel.setBounds( 20, 120, 120, 30);
		villeLabel = new JLabel("Ville : ");
		villeLabel.setBounds( 20, 155, 120, 30);
		telLabel = new JLabel("Téléphone : ");
		telLabel.setBounds( 20, 190, 120, 30);
				
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
		panelTableFournisseur.setBorder(BorderFactory.createDashedBorder( Color.blue));
				
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
		fournisseur.add(labelErreur);
		
		//Initialisation onglet commande
		
		
		
		
		//Ajout des pannels en tant qu'onglets
		onglets.add( "Produits", produit);
		onglets.add( "Fournisseurs", fournisseur);
		onglets.add( "Commandes", commande);
		
		//ajout des onglets dans la fenêtre
		add( onglets);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void resetTableProduit(){
		tableProduit.clearSelection();
		idSelectedProduit = 0;
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
	
	public void resetTableFournisseur(){
		tableFournisseur.clearSelection();
		idSelectedFournisseur = 0;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// Boutons produit
		if( e.getSource() == ajouterProduit) {
			if( nomProduitField.getText().equals("") || prixProduitField.getText().equals("") || nomFournisseurProduitField.getText().equals("") ) {
				labelErreur.setText("Aucun champ ne doit être vide");
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
						labelErreur.setText( "Fournisseur non trouvé");
					}else {
						Produit produitAjoute = new Produit(nextIdProduit, nomProduitField.getText(), Float.parseFloat( prixProduitField.getText()), typeProduitField.getText(), 0, idfournisseur);
						produits.ajouter(produitAjoute);
						resetTableProduit();
						labelErreur.setText("");
					}
				}catch(NumberFormatException exception) {
					labelErreur.setText(" le prix doit être: nombre(+point si besoin) Ex: 1.2");
				}
			}
		}else if (e.getSource() == modifierProduit) {
			if( idSelectedProduit == -1) {
				labelErreur.setText("Veuillez d'abord selectionner un produit");
			}else {
				try {
					Produit produitSelected = produits.getProduit(idSelectedProduit);
					Fournisseur fournisseurSelected = fournisseurs.getFournisseur(produitSelected.getIdFournisseur());
					
					//si aucun text field n'a été changé
					if( nomProduitField.getText().equals( produitSelected.getNom()) && Float.parseFloat( prixProduitField.getText()) == produitSelected.getPrix() && produitSelected.getType().equals( typeProduitField.getText()) && fournisseurSelected.getNom().equals( nomFournisseurProduitField.getText())) {
						labelErreur.setText("Veuillez modifier un des champs");
					}else {
						Produit produitModifie = new Produit( produitSelected.getIdentifiant(), nomProduitField.getText(), Float.parseFloat( prixProduitField.getText()), typeProduitField.getText(), produitSelected.getStock(), fournisseurSelected.getIdentifiant());
						produits.replaceProduit(produitModifie);
						resetTableProduit();
						labelErreur.setText("");
					}
				}catch(NumberFormatException exception) {
					labelErreur.setText("le prix doit être: nombre(+point si besoin) Ex: 1.2");
				}
			}
		}else if (e.getSource() == supprimerProduit) {
			if( idSelectedProduit == -1) {
				labelErreur.setText("Veuillez d'abord selectionner un produit");
			}else {
				produits.deleteProduit(idSelectedProduit);
				resetTableProduit();
				labelErreur.setText("");
			}
		}
		
		//Boutons Fournisseur
		else if( e.getSource() == ajouterFournisseur) {
			if( nomFournisseurField.getText().equals("") || adresseField.getText().equals("") || codePostalField.getText().equals("") ||  villeField.getText().equals("") || telField.getText().equals("")) {
				labelErreur.setText("Aucun champ ne doit être vide");
			}
			else {
				Fournisseur fournisseurAjoute = new Fournisseur( nextIdFournisseur, nomFournisseurField.getText(), "France", adresseField.getText(), villeField.getText(), codePostalField.getText(), telField.getText());
				fournisseurs.ajouter(fournisseurAjoute);
				resetTableFournisseur();
				labelErreur.setText("");
			}
		}else if (e.getSource() == modifierFournisseur) {
			if( idSelectedFournisseur == -1) {
				labelErreur.setText("Veuillez d'abord selectionner un fournisseur");
			}else {
				Fournisseur fournisseurSelected = fournisseurs.getFournisseur(idSelectedFournisseur);
				
				//si aucun text field n'a été changé
				if( nomFournisseurField.getText().equals( fournisseurSelected.getNom()) && adresseField.getText().equals( fournisseurSelected.getAdresse()) && codePostalField.getText().equals( fournisseurSelected.getCode_postal()) && villeField.getText().equals( fournisseurSelected.getVille()) && telField.getText().equals( fournisseurSelected.getTelephone())) {
					labelErreur.setText("Veuillez modifier un des champs");
				}else {
					Fournisseur fournisseurAjoute = new Fournisseur( idSelectedFournisseur, nomFournisseurField.getText(), "France", adresseField.getText(), villeField.getText(), codePostalField.getText(), telField.getText());
					fournisseurs.replaceFournisseur(fournisseurAjoute);
					resetTableFournisseur();
					labelErreur.setText("");
				}
			}
		}else if (e.getSource() == supprimerFournisseur) {
			if( idSelectedFournisseur == -1) {
				labelErreur.setText("Veuillez d'abord selectionner un produit");
			}else {
				fournisseurs.deleteFournisseur(idSelectedFournisseur);
				resetTableFournisseur();
				labelErreur.setText("");
			}
		}
	}
	
	public static void main(String[] args) { 
		new Interface(); 
	}

}
