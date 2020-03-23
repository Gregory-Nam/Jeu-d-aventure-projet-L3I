package controleurs;

import java.awt.List;
import java.util.ArrayList;

import elements.Inventaire;
import elements.Item;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Cette classe est le <b>controleur</b> de la vue Inventaire.fxml
 * Elle contient des méthodes permettant de gerer les differents
 * éléments de la vue selon l'inventair du <b>PersonnageJoueur</b>
 * @author Grégory NAM
 * @author Hugo CHALIK
 * @see    Pane
 *
 */
public class InventaireControleur extends Pane {

	@FXML
	private ImageView imgHG;
	@FXML
	private Label lblHG;
	@FXML
	private ImageView imgHD;
	@FXML
	private Label lblHD;
	@FXML
	private ImageView imgBG;
	@FXML
	private Label lblBG;
	@FXML
	private ImageView imgBD;
	@FXML
	private Label lblBD;
	
	/**
	 * ArrayList d'ImageView contenant les ImagesView de la vue (image des items)
	 */
	private ArrayList<ImageView> images;
	/**
	 * ArrayList de label contenant les labels de la vue (nom des items)
	 */
	private ArrayList<Label> labels;
	/**
	 * ArrayList des items ayant été ajoutés dans l'inventaire
	 */
	private ArrayList<Item> itemsAjoute;
	/**
	 * ImageView de l'item qui a été selectionné dans l'inventaire
	 */
	private ImageView imgItemSelectionne;
		
	
	/**
	 * Constructeur sans paramètre
	 * 
	 */
	public InventaireControleur() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Inventaire.fxml"));
		loader.setRoot(this);
		loader.setController(this);
        try {
            loader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }	
        initListe();
        creerEvenement();
	}
	
	/**
	 * Initialise les listes <b>images</b>, <b>labels</b>, <b>itemsAjoute </b>
	 */
	private void initListe() {
		this.images = new ArrayList<ImageView>();
		this.labels = new ArrayList<Label>();
		this.itemsAjoute = new ArrayList<Item>();
		
		images.add(imgHG);
		images.add(imgHD);
		images.add(imgBG);
		images.add(imgBD);
		
		labels.add(lblHG);
		labels.add(lblHD);
		labels.add(lblBG);
		labels.add(lblBD);
	}
	
	/**
	 * Ajoute l'item passé en parametre
	 * dans la liste des items ajoutés et qui rempli la case dans la vue
	 * @param o Item que l'on veut ajouter
	 * @see remplir
	 */
	public void ajouterItem(Item o) {
		itemsAjoute.add(o);
		int i = itemsAjoute.indexOf(o);
		remplir(i, o);
	}
	
	/**
	 * Suppression de l'item passé en parametre
	 * dans la liste des items ajoutés, désélectionne la case
	 * où était l'item et fait un raffraichissement de l'inventaire
	 * @param o item que l'on veut supprimer
	 */
	public void supprimerItem(Item o) {
		int i = itemsAjoute.indexOf(o);
		itemsAjoute.remove(o);
		/* ON VIDE LA CASE */
		vider(i);
		/* DESELECTIONNE LA CASE */
		deselection();
		/* ON DEPLACE LES ELEMENTS DANS L'INVENTAIRE */
		raffraichissement(i);
	}
	
	/**
	 * Mise à jour de l'inventaire après une suppression.
	 * pour ne pas avoir une case vide entre deux items.
	 * Elle décale tous les items suivant l'item supprimé sur la case précedente.
	 * @param aPartirDe correspond à la position de l'item qui a été supprimé
	 */
	private void raffraichissement(int aPartirDe) {
		/* ON DECALE TOUS LES ITEMS VERS LA CASE PRECENDENTE */
		for(int i = aPartirDe; i < itemsAjoute.size(); ++i) {
			Item itemCourant = itemsAjoute.get(i);
			remplir(i, itemCourant);
		}
		
		/* ON VIDE LA DERNIERE CASE REMPLI */
		vider(itemsAjoute.size());
	}
	
	/**
	 * Vide la case à l'indice passé en parametre
	 * L'image de l'ImageView est mise à null
	 * Le text du Label prend pour valeur "Vide"
	 * @param indice indice de la case à vider
	 */
	private void vider(int indice) {
		images.get(itemsAjoute.size()).setImage(null);
		labels.get(itemsAjoute.size()).setText("Vide");
	}
	
	/**
	 * Remplir la case à l'indice passé en parametre par l'item passé en parametre
	 * L'image de l'ImageView prend pour valeur celle de l'Item
	 * Le texte du Label prend pour valeur le nom de l'Item
	 * @param indice
	 * @param item
	 */
	private void remplir(int indice, Item item) {
		images.get(indice).setImage(item.getImageViewPourInventaire().getImage());
		labels.get(indice).setText(item.getNom());
	}
	
	/**
	 * Ajoute un evenement sur chacun des ImagesView pour permettre
	 * de selectionner/deselectionner un item.
	 * Un item selectionné aura un cadre rouge, cela engendre la déselection
	 * de l'ancien item selectionné s'il existe.
	 * Deux items ne peuvent pas être selectionné en même temps.
	 * @see selection
	 * @see deselection
	 */
	private void creerEvenement() {	
		EventHandler<MouseEvent> ev = e -> {
			/* ELEMENT SUR LEQUEL ON A CLIQUE */
			ImageView elementCourant = (ImageView)e.getSource();
			Pane parentDuCourant = (Pane)elementCourant.getParent();
			/* PAS D'ITEM A SUR CETTE PARTIE */
			if(elementCourant.getImage() == null) return;
			
			/* SELECTION BASIQUE */
			if(!existeUnItemSelectionne()) 
				selection(parentDuCourant, elementCourant);
			/* DESELECTION BASIQUE*/
			else if(existeUnItemSelectionne() && elementCourant.getId().equals("selectionne"))
				deselection();
			/* DESELECTION DE L'ANCIEN ITEM SELECTION ET SELECTION DU NOUVEAU */
			else {
				deselection();
				selection(parentDuCourant, elementCourant);
			}
		};
		/* APPLIQUE L'EVENEMENT POUR CHAQUE IMAGEVIEW DE L'INVENTAIRE */
		for(ImageView img : images)
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, ev);
	}
	
	/**
	 * Selectionne un item sur lequel on a cliqué.
	 * Un cadre rouge est ajouté sur le panneau et un id de valeur
	 * "selectionne" est ajouté à l'ImageView.
	 * @param panneau panneau correspondant a la case de l'item selectionné
	 * @param img ImageView selectionné
	 */
	private void selection(Pane panneau, ImageView img) {
		img.setId("selectionne");
		panneau.setStyle(panneau.getStyle() + "-fx-border-color:red;-fx-border-width:5;");
		imgItemSelectionne = img;
	}
	
	/**
	 * Deselectionne un item.
	 * Le cadre rouge et l'id de valeur "selectionne" sont enlevés.
	 */
	private void deselection(){
		ImageView selectionne = (ImageView)this.lookup("#selectionne");
		if(selectionne == null) return;
		selectionne.setId("");
		selectionne.getParent().setStyle("-fx-background-color:grey;");
		imgItemSelectionne = null;
	}
	
	/**
	 * @return Renvoie l'item qui est selectionné dans l'inventaire
	 * 		   La valeur de retour est null si aucun item a été selectionné
	 */
	public Item getItemSelectionne() {
		return imgItemSelectionne == null ? null : itemsAjoute.get(images.indexOf(imgItemSelectionne));
	}
	
	/**
	 * @return vrai si il existe un item a ete selectionne
	 * 		   faux aucun item a été selectionne
	 */
	private boolean existeUnItemSelectionne() {
		return this.lookup("#selectionne") == null ? false : true;
	}
	
	/**
	 * Permet de créer un lien entre l'inventaire graphique et l'inventaire "modèle". 
	 * <br>
	 * On ajouter un écouteur sur la liste observable de l'inventaire qui permet d'effectuer
	 * les changements graphiques lorsqu'une modification est ralisée sur la liste des items
	 * de l'inventaire.
	 * @param inventaire inventaire avec lequel on veut créer un lien.
	 */
	public void creerListener(Inventaire inventaire) {
		ObservableList<Item> listeInventaire = inventaire.getInventaire();
		listeInventaire.addListener((Change<? extends Item> changement) -> {
			while(changement.next()) {
				if(changement.wasAdded()) {
					this.ajouterItem(listeInventaire.get(changement.getFrom()));
				}
				else if(changement.wasRemoved()) {
					this.supprimerItem(changement.getRemoved().get(0));
				}

			}
		});
	}
	
	
	
}
