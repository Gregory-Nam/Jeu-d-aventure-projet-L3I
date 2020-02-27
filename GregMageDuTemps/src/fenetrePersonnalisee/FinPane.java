package fenetrePersonnalisee;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class FinPane extends Pane {

	private Label raisonDeLaFin;
	private Label etatDeLaFin;
	
	public FinPane() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/EcranDeFin.fxml"));
		loader.setRoot(this);
        try {
            loader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        
        etatDeLaFin = (Label)this.getChildren().get(0);
        raisonDeLaFin = (Label)this.getChildren().get(1);
	}
	
	public void setRaisonDeLaFin(String message, boolean aGagne) {
		if(aGagne)etatDeLaFin.setText("Bravo !");
		raisonDeLaFin.setText(message);
	}
}
