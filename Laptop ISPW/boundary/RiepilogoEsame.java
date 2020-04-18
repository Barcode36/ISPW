
package boundary;

import java.sql.SQLException;

import bean.PrenotazioneEsameBean;
import controller.ControllerProfessore;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RiepilogoEsame {
	
	@FXML
	private Label aula;
	@FXML
	private Label data;
	@FXML
	private Label fasciaOraria;
	@FXML
	private Label Seduta;
	@FXML
	private Label Sessione;
	@FXML
	private Label Appello; 
	@FXML
	private Label macroarea;
	@FXML
	private Label titolarePrenotazione;
	@FXML
	private Label nomeEsame; 
	@FXML
	private Button returnButton;	
	@FXML
	private Button ConfermaButton;	
	

	public static PrenotazioneEsameBean valori = new PrenotazioneEsameBean();
	@FXML
	public void initialize() {
	
		valori= PrenotaEsame.prenotazioneEsame;
		
		aula.setText(valori.getAula().getNome());
		data.setText(valori.getData().toString());
		fasciaOraria.setText(valori.getOrario().toString());
		Seduta.setText(valori.getSeduta().toString());
		Sessione.setText(valori.getSessione().toString());
		Appello.setText(String.valueOf(valori.getAppello()));
		macroarea.setText(String.valueOf(valori.getMacroarea()));
		titolarePrenotazione.setText(valori.getTitolarePrenotazione());
		nomeEsame.setText(valori.getNomeCorso());
	
	}
	@FXML
	public void Indietro() {
		Stage stage = (Stage) returnButton.getScene().getWindow();
	    stage.close();
		
	}
	@FXML
	public void Conferma() throws SQLException {
		valori= PrenotaEsame.prenotazioneEsame;

		ControllerProfessore istance=ControllerProfessore.getInstance(); 
		if(istance.verificaEsameController(valori)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Tentativo di Prenotazione fallito");
			alert.setHeaderText("Prenotazione Esame!");
			alert.setContentText("Prenotazione non andata a buon fine perche gia esistente per quella data fascia oraria e aula");
			alert.showAndWait();
			alert.close();
		}
		else {
		istance.InvioEsamePrenotazione(valori);	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Prenotazione ");
		alert.setHeaderText("Prenotazione Esame!");
		alert.setContentText("Prenotazione andata a buon fine");
		alert.showAndWait();
		alert.close();
		}
	}
	
}
