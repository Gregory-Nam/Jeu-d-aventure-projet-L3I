package test;


import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Cette classe permet de créer un environnement de test. 
 * </br>
 * Nous avions deux solutions :
 * </br>
 * - TestFX (mais projet devait être sous maven) 
 * </br>
 * - Creer une fausse application 
 * </br>
 * 
 * Le code de cette classe a été pris sur StackOverflow (pour le setUpClass) : 
 * </br>
 * https://stackoverflow.com/questions/11385604/how-do-you-unit-test-a-javafx-controller-with-junit?fbclid=IwAR2BU_y3Fa68FdKuvbXSWlASQAWOTHG-G-ywg5xyIgooZsR8oyr5UAS_5rc 
 * </br>
 * 
 * @author StackOverflow
 * @author Grégory NAM
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
	
	public static boolean compareImages(Image image1, Image image2) {
		for (int i = 0; i < image1.getWidth(); i++) {
		  for (int j = 0; j < image2.getHeight(); j++)
			  if (image1.getPixelReader().getArgb(i, j) != image2.getPixelReader().getArgb(i, j)) return false;
		}
		return true;
	}
}
