package fenetrePersonnalisee;

import java.util.ArrayList;

import elements.Item;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import personnages.PersonnageJoueur;

public class InventairePane extends Pane{

	private Pane paneParent;
	private Pane paneHG;
	private ImageView imgHG;
	private Label lblHG;
	private Pane paneHD;
	private ImageView imgHD;
	private Label lblHD;
	private Pane paneBG;
	private ImageView imgBG;
	private Label lblBG;
	private Pane paneBD;
	private ImageView imgBD;
	private Label lblBD;
	
	private ArrayList<ImageView> images;
	private ArrayList<Label> labels;
	
	private ArrayList<Item> itemsAjoute;
	private ImageView itemSelectionne;
		
	
	
	public InventairePane() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Inventaire.fxml"));
		loader.setRoot(this);
        try {
            loader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }	
        initEnfant();	
        initListe();
        creerEvenement();
	}
	
	private void initEnfant() {
		//Initialisation des Pane enfants
		paneHG = (Pane)this.getChildren().get(1);
		paneHD = (Pane)this.getChildren().get(2);
		paneBG = (Pane)this.getChildren().get(3);
		paneBD = (Pane)this.getChildren().get(4);
		//Initialisation des ImageView
		imgHG = (ImageView)paneHG.getChildren().get(0);
		imgHD = (ImageView)paneHD.getChildren().get(0);
		imgBG = (ImageView)paneBG.getChildren().get(0);
		imgBD = (ImageView)paneBD.getChildren().get(0);
		//Initialisation des Labels
		lblHG = (Label)paneHG.getChildren().get(1);
		lblHD = (Label)paneHD.getChildren().get(1);
		lblBG = (Label)paneBG.getChildren().get(1);
		lblBD = (Label)paneBD.getChildren().get(1);
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
		images.get(i).setImage(o.getImageViewPourInventaire().getImage());
		labels.get(i).setText(o.getNom());
		PersonnageJoueur.getInstanceUnique().prendreItemEnMain(o);
	}
	
	public void supprimerItem(Item o) {
		int i = itemsAjoute.indexOf(o);
		itemsAjoute.remove(o);
		images.get(i).setImage(null);
		labels.get(i).setText("Vide");
		raffraichissement(i);
	}
	
	private void raffraichissement(int aPartirDe) {
		for(int i = aPartirDe; i < itemsAjoute.size(); ++i) {
			Item itemCourant = itemsAjoute.get(i);
			images.get(i).setImage(itemCourant.getImageView().getImage());
			labels.get(i).setText(itemCourant.getNom());
		}
	}
	
	private void creerEvenement() {
		
		EventHandler<MouseEvent> ev = e -> {
			ImageView elementCourant = (ImageView)e.getSource();
			Pane parentDuCourant = (Pane)elementCourant.getParent();
			
			if(elementCourant.getImage() == null) return;
			
			if(!existeUnItemSelectionne()) 
				selection(parentDuCourant, elementCourant);
			
			else if(existeUnItemSelectionne() && elementCourant.getId().equals("selectionne"))
				deselection(parentDuCourant, elementCourant);
			
			else {
				deselection((Pane)itemSelectionne.getParent(), itemSelectionne);
				selection(parentDuCourant, elementCourant);
			}
			
		};
		
		for(ImageView img : images)
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, ev);

	}
	
	
	private void selection(Pane panneau, ImageView img) {
		img.setId("selectionne");
		panneau.setStyle(panneau.getStyle() + "-fx-border-color:red;-fx-border-width:5;");
		itemSelectionne = img;
	}
	
	private void deselection(Pane panneau, ImageView img){
		img.setId("");
		panneau.setStyle("-fx-background-color:grey;");
		itemSelectionne = null;
	}
	
	
	private boolean existeUnItemSelectionne() {
		return this.lookup("#selectionne") == null ? false : true;
	}
	
	
	
}
