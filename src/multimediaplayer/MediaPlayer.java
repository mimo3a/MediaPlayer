package multimediaplayer;


import java.io.File;

import javax.swing.ImageIcon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MediaPlayer extends Application{
	
	@Override
	public void start(Stage meineStage) throws Exception {
		
		
		
//		eine Instatz von FXMLLoader erzeugen
		FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("sb_mediaplayer.fxml"));
		System.out.println("0");
//		die Datei laden
		Parent root = meinLoader.load();
		System.out.println("1");
//		den Controller beschaffen
	    FXMLController meinController = meinLoader.getController();
	    System.out.println("2");
//		und die Buene uebergeben
	    meinController.setMeineStage(meineStage);
		
//		die Scene erzeugen
//		an den Konstruktor werden der oberste Knoten und die Groesse uebergeben
		Scene meineScene = new Scene(root, 800,600);
//		den Titel ueber stage setzen
		meineStage.setTitle("Java-FX Multimedia Player");
//		die Scene setzen
		meineStage.setScene(meineScene);
//		im Vollbild darstellen
		meineStage.setMaximized(true);
//		ein icon zun Titel setzen
		File bilddatei = new File("icons/icon.gif");
		Image bild = new Image(bilddatei.toURI().toString()); 
 		
		meineStage.getIcons().addAll(bild);
//		und anzeigen
		meineStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
