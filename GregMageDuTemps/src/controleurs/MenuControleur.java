package controleurs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuControleur extends Pane {
	@FXML
	private Label btnJouer;
	@FXML
	private Label btnCommandes;
	@FXML
	private Label btnHistoire;
	@FXML
	private Label btnQuitter;
	@FXML
	private Group groupMenu;
	
	@FXML
	private Label labelCommande;
	@FXML
	private Label labelHistoire;
	
	EventHandler<MouseEvent> cliqueQuitter;
	EventHandler<MouseEvent> cliqueJouer;
	EventHandler<MouseEvent> cliqueHistoireCommande;
	EventHandler<MouseEvent> cliqueReprendre;
	EventHandler<KeyEvent> retourArriere;


	public MenuControleur() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Menu.fxml"));
		loader.setRoot(this);
		loader.setController(this);
	    try {
	        loader.load();
	    } catch (Exception exception) {
	        throw new RuntimeException(exception);
	    }	
	    initReprendre();
	    initJouer();
	    initQuitter();
	    initHistoireCommande();
	    initRetourArriereDansMenu();
	    initSurvoler();
	}
	
	private void initQuitter() {
		cliqueQuitter = c -> {
			System.exit(0);;
		};
		btnQuitter.addEventHandler(MouseEvent.MOUSE_CLICKED,cliqueQuitter);
	}
	
	private void initJouer() {
		cliqueJouer = c -> {
			try {
				Jeu.getInstanceUnique().lancerJeu();
				btnJouer.setText("Reprendre");
				btnJouer.removeEventHandler(MouseEvent.MOUSE_CLICKED, cliqueJouer);
				btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED, cliqueReprendre);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		btnJouer.addEventHandler(MouseEvent.MOUSE_CLICKED,cliqueJouer);
	}
	
	private void initReprendre() {
		cliqueReprendre = c -> {
			Jeu.getInstanceUnique().reprendre();
		};
	}
	
	private void initHistoireCommande() {
		
		cliqueHistoireCommande = c -> {
			visibiliteEnfant(groupMenu);
			
			if(c.getSource().equals(btnHistoire))
				visibiliteEnfant(labelHistoire);
			else	
				visibiliteEnfant(labelCommande);
			
			
			this.getScene().addEventHandler(KeyEvent.KEY_PRESSED,retourArriere);
		};
		btnHistoire.addEventHandler(MouseEvent.MOUSE_CLICKED,cliqueHistoireCommande);
		btnCommandes.addEventHandler(MouseEvent.MOUSE_CLICKED,cliqueHistoireCommande);
	}
	
	private void visibiliteEnfant(Node enfant) {
		enfant.setVisible(!enfant.isVisible());
		enfant.setDisable(!enfant.isDisabled());
	}
	
	private void initRetourArriereDansMenu() {
		retourArriere = k -> {
			KeyCode kc = k.getCode();
			switch(kc) {
				case ESCAPE :
					if(labelHistoire.isVisible())
						visibiliteEnfant(labelHistoire);
					else
						visibiliteEnfant(labelCommande);
					visibiliteEnfant(groupMenu);
					break;
			}
			this.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, retourArriere);
		};
		
	}
	
	private void initSurvoler() {
		EventHandler<MouseEvent> entrer = s -> {
			Node element = ((Node)s.getSource());
			element.setStyle(element.getStyle() + "-fx-opacity:0.5;");
			
		};
		
		EventHandler<MouseEvent> sortir = s -> {
			((Node)s.getSource()).setStyle("-fx-background-color:gray;");
		};
		
		groupMenu.getChildren().subList(1, groupMenu.getChildren().size()).forEach(n -> {
			n.setOnMouseEntered(entrer);
			n.setOnMouseExited(sortir);	
		});
		
	}
}
