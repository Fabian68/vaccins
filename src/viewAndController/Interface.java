package viewAndController;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.CommandeDAO;
import dao.FournisseurDAO;
import dao.ProduitDAO;

public class Interface extends JFrame implements ActionListener{
	
	// Les données
	private ProduitDAO produits;
	private CommandeDAO commandes;
	private FournisseurDAO fournisseurs;
	
	// Les 3 onglets et leur conteneur
	private JTabbedPane onglets;
	private JPanel produit, commande, fournisseur;
	
	// les attributs graphique de l'onglet produit
	private JLabel nomProduitLabel, prixProduitLabel, nomFournisseurProduitLabel;
	private JTextField nomProduitField, prixProduitField, nomFournisseurProduitField;
	private JTable tableProduit;
	private DefaultTableModel modelTableProduit;
	private JPanel panelTableProduit;
	private JScrollPane scrollProduit;
	
	// les attributs graphique de l'onglet fournisseur
	private JLabel nomFournisseurLabel, adresseLabel, codePostalLabel, villeLabel, telLabel;
	private JTextField nomFournisseurField, adresseField, codePostalField, villeField, telField;
	private JTable tableFournisseur;
	private DefaultTableModel modelTableFournisseur;
	private JPanel panelTableFournisseur;
	private JScrollPane scrollFournisseur;
	
	// les attributs graphique de l'onglet Commande
	private JLabel nomProduitCommandeLabel, quantiteLabel;
	private JTextField nomProduitCommandeField, quantiteField;
	private JTable tableCommande;
	private DefaultTableModel modelTableCommande;
	private JPanel panelTableCommande;
	private JScrollPane scrollCommande;
	
	public Interface() {
		produits = new ProduitDAO();
		commandes = new CommandeDAO();
		fournisseurs = new FournisseurDAO();
		
		setSize(710, 405);
		setLayout( null);
		
		//Initialisation onglets
		onglets = new JTabbedPane();
		onglets.setBounds( 10, 5, 700, 360);
		produit = new JPanel();
		produit.setLayout(null);
		commande = new JPanel();
		fournisseur = new JPanel();
		
		//Initialisation onglet produit
		nomProduitLabel = new JLabel("Nom du produit: ");
		nomProduitLabel.setBounds( 30, 50, 60, 30);
		prixProduitLabel = new JLabel("Prix: ");
		prixProduitLabel.setBounds( 30, 85, 60, 30);
		nomFournisseurProduitLabel = new JLabel("Nom du Fournisseur: ");
		nomFournisseurProduitLabel.setBounds( 30, 120, 60, 30);
		
		nomProduitField = new JTextField();
		nomProduitField.setBounds( 95, 50, 100, 30);
		prixProduitField = new JTextField();
		prixProduitField.setBounds( 95, 85, 100, 30);
		nomFournisseurProduitField = new JTextField();
		nomFournisseurProduitField.setBounds( 95, 120, 100, 30);
		
		panelTableProduit = new JPanel();
		panelTableProduit.setLayout( new GridLayout());
		panelTableProduit.setBounds( 250, 10, 400, 300);
		panelTableProduit.setBorder(BorderFactory.createDashedBorder( Color.blue));
		
		modelTableProduit = new DefaultTableModel();
		tableProduit = new JTable( modelTableProduit);
		tableProduit.setEnabled(false);
		
		modelTableProduit.addColumn("Nom du Produit");
		modelTableProduit.addColumn("Prix");
		modelTableProduit.addColumn("Fournisseur");
		modelTableProduit.addColumn("Stock");
		
		scrollProduit = new JScrollPane( tableProduit, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelTableProduit.add(scrollProduit);
		
		//Ajout de tous les éléments dans l'onglet produit
		produit.add( nomProduitLabel);
		produit.add( prixProduitLabel);
		produit.add( nomFournisseurProduitLabel);
		produit.add( nomProduitField);
		produit.add( prixProduitField);
		produit.add( nomFournisseurProduitField);
		produit.add( panelTableProduit);
		
		//Initialisation onglet fournisseur
		
		//Ajout de tous les éléments dans l'onglet fournisseur
		
		//Initialisation onglet commande
		
		//Ajout de tous les éléments dans l'onglet commande
		
		
		//Ajout des pannels en tant qu'onglets
		onglets.add( "Produits", produit);
		onglets.add( "Fournisseurs", fournisseur);
		onglets.add( "Commandes", commande);
		
		//ajout des onglets dans la fenêtre
		add( onglets);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) { 
		new Interface(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
