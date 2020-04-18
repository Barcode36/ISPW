
package boundary;

import java.sql.SQLException;

import application.Main;
import bean.PrenotazioneEventoBean;
import controller.ControllerSegreteria;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RiepilogoEvento {
	
	@FXML
	private Label aula;
	@FXML
	private Label evento;
	@FXML
	private Label data;
	@FXML
	private Label fasciaOraria;
	@FXML
	private Label macroarea;
	@FXML
	private Label corsoStudi;
	@FXML
	private Label titoloEvento; 
	@FXML
	private Label titolarePrenotazione; 
	@FXML
	private Button returnButton;		
	@FXML
	private Button ConfermaButton;
	@FXML
	private Main main;
	@SuppressWarnings("unused")
	private Stage stage;
	
	public static PrenotazioneEventoBean valori = new PrenotazioneEventoBean();
	@FXML
	public void initialize() {
		valori= PrenotaEvento.getEventoBean();

		
		aula.setText(valori.getAula().getNome());
		data.setText(valori.getData().toString());
		fasciaOraria.setText((valori.getOrario().intern()));
		evento.setText(valori.getEvento().toString());
		titoloEvento.setText(valori.getTitoloEvento());
		titolarePrenotazione.setText(valori.getTitolarePrenotazione());
		macroarea.setText(String.valueOf(valori.getMacroarea()));
		corsoStudi.setText(valori.getCorsoStudi());
	
	}
	@FXML
	public void Conferma() throws SQLException {
		valori= PrenotaEvento.prenotazioneEvento;
		ControllerSegreteria istance=ControllerSegreteria.getInstance();
		if(ControllerSegreteria.getInstance().verificaEventoController(valori)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Tentativo di Prenotazione fallito");
			alert.setHeaderText("Prenotazione Evento!");
			alert.setContentText("Prenotazione non andata a buon fine perche gia esistente per quella data fascia oraria e aula");
			alert.showAndWait();
			alert.close();
		}
		else {
		istance.InvioEventoPrenotazione(valori);		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Prenotazione ");
		alert.setHeaderText("Prenotazione Evento!");
		alert.setContentText("Prenotazione andata a buon fine");
		alert.showAndWait();
		alert.close();
		}
	}
	@FXML
	public void Indietro() {
		Stage stage = (Stage) returnButton.getScene().getWindow();
	    stage.close();
	
	}
	public void setMain(Main main) {
		this.main = main;
		
	}
	public void setStage(Stage formStage) {
		this.stage = formStage;
		
	}
	
	
}
