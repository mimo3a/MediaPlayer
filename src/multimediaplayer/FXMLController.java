package multimediaplayer;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLController {

//	fur die Buene
private Stage meineStage;

//	fur den Player
private MediaPlayer mediaplayer;

//  fur die Mediaview
@FXML private MediaView mediaview;

// fur die ImageView mit dem Symbol
@FXML private ImageView symbol;

//	fur das Listenfeld
@FXML private ListView<String> liste;

// die Methode setzt die Buehne auf den uebergebenen Wert	
	public void setMeineStage(Stage meineStage) {
		this.meineStage = meineStage;
		System.out.println("ok");
	}
	
//	die Methode zum Laden
	@FXML protected void ladenKlick(ActionEvent event) {
//		eine neue Instans der Klasse FileChooser erzeugen
		FileChooser oeffnendialog = new FileChooser();
//		den Titel fur den Dialog setzen
		oeffnendialog.setTitle("Datei oeffnen");
//		den Filter setzen
		oeffnendialog.getExtensionFilters().add(new ExtensionFilter("Audiodateien", "*.mp3"));
		oeffnendialog.getExtensionFilters().add(new ExtensionFilter("Videodateien", "*.mp4"));
//		den Startordner auf den Benutzerordner setzen
		oeffnendialog.setInitialDirectory(new File(System.getProperty("user.home")));
		
//		den Oeffnendialog anzeigen und das Ergebnis beschaffen
		File datei = oeffnendialog.showOpenDialog(meineStage);
//		wurde eine Datei ausgewaehlt
		if(datei != null)
//			dann uber eine eigene Methode laden
			dateiLaden(datei);		
	}
	
//	die Methode zum Stoppen
	@FXML protected void stoppKlick(ActionEvent event) throws Exception {
//		gibt es uberhaupt einen Mediaplayer?
		if(mediaplayer != null)
//			dann anhalten
			mediaplayer.stop();
		
	}
	
//	die Methode fur die Pause
	@FXML protected void pauseKlick(ActionEvent event) {
//		gibt es uberhaupt einen Mediaplayer?
		if(mediaplayer != null)
//			dann unterbrechen
			mediaplayer.pause();
	}
	
//	die Methode fur die Wiedergabe
	@FXML protected void playKlick(ActionEvent event) {
//		gibt es uberhaupt einen Mediaplayer?
		if(mediaplayer != null)
//			dann widergeben
			mediaplayer.play();
	}
	
//	die Methode fur das Ein- und Ausschalten der Lautstaerke
	@FXML protected void lautsprecherKlick(ActionEvent event) {
		String dateiname;
//		gibt es uberhaupt einen Mediaplayer?
		if(mediaplayer != null) {
//			ist die Lautstaerke 0?
			if(mediaplayer.getVolume() == 0) {
//				dann auf 100 setzen
				mediaplayer.setVolume(100);
//				und das normale Symbol setzen
				dateiname = "icons/mute.gif";
			}
			else {
//				sonst auf 0 setzen
				mediaplayer.setVolume(0);
//				und das durchgestrichene Symbol setzen
				dateiname = "icons/mute_off.gif";
			}
//			das Bild erzeugen und anzeigen
			File bilddatei = new File(dateiname);
			Image bild  = new Image(bilddatei.toURI().toString());
			symbol.setImage(bild);
		}
	}
	
//	die Methode zum Beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
//	die Methode laedt eine Datei
	public void dateiLaden(File datei) {
//		lauft schon eine Wiedergabe?
		if(mediaplayer != null && mediaplayer.getStatus() == MediaPlayer.Status.PLAYING) {
//			dann anhalten
			mediaplayer.stop();
		}
//		das Medium, den Mediaplayer und die MediaView erzeugen
		try {
			Media medium = new Media(datei.toURI().toString());
			mediaplayer = new MediaPlayer(medium);
			mediaview.setMediaPlayer(mediaplayer);
//			die Wiedergabe starten
			mediaplayer.play();
			
//			den Pfad in das Listenfeld eintragen
			liste.getItems().add(datei.toString());
//			und die Titelleiste anpassen
			System.out.println(datei.toString());
			meineStage.setTitle("JavaFX Multimedia-Player" + datei.toString());
			
		}
		catch(Exception ex) {
			Alert meinDialog = new Alert(AlertType.INFORMATION, "Beim Laden hat es ein Problem gegeben.\n"
					+ ex.getMessage());
			meinDialog.setHeaderText("Bitte beachten");
			meinDialog.initOwner(meineStage);
			meinDialog.showAndWait();
		}
	}
}
