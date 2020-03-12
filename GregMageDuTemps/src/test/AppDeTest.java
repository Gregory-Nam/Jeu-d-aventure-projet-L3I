package test;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Cette classe permet de cr�er un environnement de test. </br>
 * Nous avions deux solutions : <br/>
 * - TestFX (mais projet devait �tre sous maven) </br>
 * - Creer une fausse application </br>
 * 
 * Le code de cette clase a �t� pris sur StackOverflow : </br>
 * https://stackoverflow.com/questions/11385604/how-do-you-unit-test-a-javafx-controller-with-junit?fbclid=IwAR2BU_y3Fa68FdKuvbXSWlASQAWOTHG-G-ywg5xyIgooZsR8oyr5UAS_5rc </br>
 * 
 * @author StackOverflow
 *
 */
public class AppDeTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static void setUpClass() throws InterruptedException {
	    System.out.printf("About to launch FX App\n");
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(AppDeTest.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	    System.out.printf("FX App thread started\n");
	    Thread.sleep(500);
	}
}
