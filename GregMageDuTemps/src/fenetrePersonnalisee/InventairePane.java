package fenetrePersonnalisee;

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
		
	
	
	public InventairePane() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Enigme.fxml"));
		loader.setRoot(this);
        try {
            loader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }	
        initEnfant();
		
	}
	
	public void initEnfant() {
		paneParent = (Pane)this.getChildren().get(0);
		//Initialisation des Pane enfants
		paneHG = (Pane)paneParent.getChildren().get(0);
		paneHD = (Pane)paneParent.getChildren().get(1);
		paneBG = (Pane)paneParent.getChildren().get(2);
		paneBD = (Pane)paneParent.getChildren().get(3);
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
	
	public void ajouterItem(Item o, int i) {
		switch(i) {
		case 0 : this.imgHG.setImage(o.getImageView().getImage());
				 this.lblHG.setText(o.getNom());
		break;
		case 1 : this.imgHD.setImage(o.getImageView().getImage());
				 this.lblHD.setText(o.getNom());
		break;
		case 2 : this.imgBG.setImage(o.getImageView().getImage());
				 this.lblBG.setText(o.getNom());
		break;
		case 3 : this.imgBD.setImage(o.getImageView().getImage());
				 this.lblBD.setText(o.getNom());
		break;
		}
	}
	
	
	
	
	
}
