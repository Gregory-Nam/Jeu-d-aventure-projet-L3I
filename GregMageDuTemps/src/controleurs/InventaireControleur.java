package controleurs;

import java.util.ArrayList;

import elements.Item;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import personnages.PersonnageJoueur;

public class InventaireControleur extends Pane{

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
	
	private ArrayList<ImageView> images;
	private ArrayList<Label> labels;
	private ArrayList<Item> itemsAjoute;
	
	private ImageView imgItemSelectionne;
		
	
	
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
	
	public void ajouterItem(Item o) {
		itemsAjoute.add(o);
		int i = itemsAjoute.indexOf(o);
		remplir(i, o);
	}
	
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
	
	private void raffraichissement(int aPartirDe) {
		/* ON DECALE TOUS LES ITEMS VERS LA CASE PRECENDENTE */
		for(int i = aPartirDe; i < itemsAjoute.size(); ++i) {
			Item itemCourant = itemsAjoute.get(i);
			remplir(i, itemCourant);
		}
		
		/* ON VIDE LA DERNIERE CASE REMPLI */
		vider(itemsAjoute.size());
	}
	
	private void vider(int indice) {
		images.get(itemsAjoute.size()).setImage(null);
		labels.get(itemsAjoute.size()).setText("Vide");
	}
	
	private void remplir(int indice, Item item) {
		images.get(indice).setImage(item.getImageViewPourInventaire().getImage());
		labels.get(indice).setText(item.getNom());
	}
	
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
	
	
	private void selection(Pane panneau, ImageView img) {
		img.setId("selectionne");
		panneau.setStyle(panneau.getStyle() + "-fx-border-color:red;-fx-border-width:5;");
		imgItemSelectionne = img;
	}
	
	private void deselection(){
		ImageView selectionne = (ImageView)this.lookup("#selectionne");
		if(selectionne == null) return;
		selectionne.setId("");
		selectionne.getParent().setStyle("-fx-background-color:grey;");
		imgItemSelectionne = null;
	}
	
	
	public Item getItemSelectionne() {
		return imgItemSelectionne == null ? null : itemsAjoute.get(images.indexOf(imgItemSelectionne));
	}
	
	private boolean existeUnItemSelectionne() {
		return this.lookup("#selectionne") == null ? false : true;
	}
	
	
	
}
