package fenetrePersonnalisee;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MortPane extends Pane {

	private Label raisonDeLaMort;
	
	public MortPane() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/EcranDeFin.fxml"));
		loader.setRoot(this);
        try {
            loader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        
        raisonDeLaMort = (Label)this.getChildren().get(1);
	}
	
	public void setRaisonDeLaMort(String message) {
		raisonDeLaMort.setText(message);
	}
}
