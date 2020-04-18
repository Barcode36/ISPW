
package boundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomepageProfessore {

	@FXML
	private Button logoutButton;
	
	@FXML
	private Button closeButton;
	
	@FXML
	private Stage stage;
	
	
	@FXML
	public void Esci() {
		System.out.println("Uscito dal sistema con successo");
		System.exit(0);
	}

	@FXML
	public void visualizzaStorico() {
			try {
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/StoricoPrenotazioni.fxml"));
			
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Visualizza Storico Prenotazioni");
				stage.show();

			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	
	@FXML
	public void PrenotaEsame() {
			try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/PrenotaEsame.fxml"));
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Prenota un Esame");
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void visualizzaPrenotazioniAttive() {
		try {	
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/PrenotazioniAttive.fxml"));
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Visualizza  prenotazioni ancora attive");
			stage.show();

			} catch(Exception e) {
				e.printStackTrace();
			}
	}
}
