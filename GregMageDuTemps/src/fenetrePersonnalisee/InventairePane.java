package fenetrePersonnalisee;

import java.util.ArrayList;

import elements.Item;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
	
	private ArrayList<ImageView> lstImages;
		
	
	
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
		this.lstImages = new ArrayList<ImageView>();
		
		lstImages.add(imgHG);
		lstImages.add(imgHD);
		lstImages.add(imgBG);
		lstImages.add(imgBD);
	}
	
	public void ajouterItem(Item o) {
		for(ImageView img : lstImages) {
			if(img == null) {
				img.setImage(o.getImageView().getImage());
			}
		}
	}
	
	
	
	
	
}
