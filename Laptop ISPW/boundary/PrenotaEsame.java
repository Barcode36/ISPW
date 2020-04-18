
package boundary;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import bean.AulaBean;
import bean.PrenotazioneEsameBean;

import bean.UtenteBean;
import controller.ControllerProfessore;
import entity.Evento;
import entity.FasciaOraria;
import entity.Laboratorio;
import entity.Macroarea;
import entity.Seduta;
import entity.Sessione;
import entity.TipoAula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrenotaEsame {
	@FXML
	private ComboBox<TipoAula> tipiAule;
	@FXML
	private ComboBox<Laboratorio> lab;
	@FXML
	private ComboBox<Evento> eventi;
	@FXML
	private ComboBox<FasciaOraria> fasceOrarie;
	@FXML
	private ComboBox<Macroarea> macroaree;
	@FXML
	private ComboBox<Sessione> sessione;
	@FXML
	private ComboBox<Seduta> seduta;
	@FXML
	private Button closeButton;
	@FXML
	private DatePicker data;
	@FXML
	private RadioButton lav;
	@FXML
	private RadioButton lavInt;
	@FXML
	private RadioButton ethernet;
	@FXML
	private RadioButton prese;
	@FXML
	private RadioButton mic;
	@FXML
	private TextField Appello;
	@FXML
	private TextField NomeCorso;
	@FXML
	private TextField posti;
	@FXML
	private TextField proiettore;
	
	@SuppressWarnings("unused")
	private Stage stage;
	
	protected String laboratorioString,sedutaString,sessioneString,fasceOrarieString,macroareeString,eventiString,tipiAuleString;
	
	public static  PrenotazioneEsameBean prenotazioneEsame;
	
	
	@FXML
	public void initialize() {
		
		ObservableList<Laboratorio> lab = FXCollections.observableArrayList(Laboratorio.values());
		this.lab.setItems(lab);
		this.lab.valueProperty().addListener((observable, oldValue, newValue) -> 
		laboratorioString= (newValue.toString()));
		
		ObservableList<Seduta> seduta = FXCollections.observableArrayList(Seduta.values());
		this.seduta.setItems(seduta);
		this.seduta.valueProperty().addListener((observable, oldValue, newValue) -> 
		sedutaString= (newValue.toString()));
		
		ObservableList<Sessione> sessione = FXCollections.observableArrayList(Sessione.values());
		this.sessione.setItems(sessione);
		this.sessione.valueProperty().addListener((observable, oldValue, newValue) -> 
		sessioneString= (newValue.toString()));
				
		ObservableList<FasciaOraria> orari = FXCollections.observableArrayList(FasciaOraria.values());
		this.fasceOrarie.setItems(orari);
		this.fasceOrarie.valueProperty().addListener((observable, oldValue, newValue) -> 
		fasceOrarieString= (newValue.toString()));
		
		ObservableList<Macroarea> macro = FXCollections.observableArrayList(Macroarea.values());
		this.macroaree.setItems(macro);
		this.macroaree.valueProperty().addListener((observable, oldValue, newValue) -> 
		macroareeString= (newValue.toString()));
		
		
		ObservableList<Evento> evento = FXCollections.observableArrayList(Evento.Esame);
		eventi.setItems(evento);
		this.eventi.valueProperty().addListener((observable, oldValue, newValue) -> 
		eventiString= (newValue.toString()));
	
		ObservableList<TipoAula> aule = FXCollections.observableArrayList(TipoAula.Didattica);
		this.tipiAule.setItems(aule);
		this.tipiAule.valueProperty().addListener((observable, oldValue, newValue) -> 
		tipiAuleString= (newValue.toString()));
	
	}
	

	
	@FXML
	public void Prenota() throws IOException,SQLException {
		UtenteBean bean=new UtenteBean();
		bean=application.Main.userBean;
		try {
		if(posti.getText().isEmpty() || proiettore.getText().isEmpty()|| NomeCorso.getText().isEmpty()|| Appello.getText().isEmpty() || Integer.valueOf(Appello.getText())>6 || Integer.valueOf(posti.getText())<=0 || Integer.valueOf(Appello.getText())<=0 || Integer.valueOf(posti.getText())>750 ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Prenotazione non possibile ");
			alert.setHeaderText("Prenotazione non andata a buon fine");
			alert.setContentText("Errore nei vincoli inserire i valori nei campi nella maniera adeguata");
			alert.showAndWait();
			alert.close();
			return;
		}
		AulaBean aulabean = new AulaBean();
		aulabean.setNumeroPosti(Integer.valueOf((this.posti.getText())));
		aulabean.setProiettore(Integer.valueOf(this.proiettore.getText()));
		aulabean.setLavagna(this.lav.isSelected());
		aulabean.setLavInterattiva(this.lavInt.isSelected());
		aulabean.setPrese(this.prese.isSelected());
		aulabean.setMicrofono(this.mic.isSelected());
		aulabean.setEthernet(this.ethernet.isSelected());
		aulabean.setTipo(tipiAuleString);
		aulabean.setLaboratorio(laboratorioString);
		
		//aulabean.setTipo(this.tipiAule.getSelectionModel().getSelectedItem());
		
		
		//aulabean.setLaboratorio(laboratorioString);
		//PrenotazioneEsameBean prenotazione=new PrenotazioneEsameBean();
		java.util.Date Dataprenotazione = (java.util.Date) java.sql.Date.valueOf(this.data.getValue());
		PrenotazioneEsameBean prenotazionebean = new PrenotazioneEsameBean();
		prenotazionebean.setEvento((eventiString));
		prenotazionebean.setOrario((fasceOrarieString));
		prenotazionebean.setMacroarea((macroareeString));
		prenotazionebean.setTitolarePrenotazione(bean.getUserid());
		prenotazionebean.setNomeCorso((this.NomeCorso.getText()));
		prenotazionebean.setSessione((sessioneString));
		prenotazionebean.setSeduta((sedutaString));	
		prenotazionebean.setAppello(Integer.valueOf(this.Appello.getText()));		
		prenotazionebean.setData((Date) Dataprenotazione);
		ControllerProfessore istance=ControllerProfessore.getInstance();
		prenotazioneEsame=istance.RichiestaPrenotazioneEsame(aulabean,prenotazionebean);
		}catch(NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Prenotazione non possibile ");
			alert.setHeaderText("Prenotazione non andata a buon fine");
			alert.setContentText("Errore nei vincoli inserire i valori nei campi nella maniera adeguata");
			alert.showAndWait();
			alert.close();
			return;
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Prenotazione non possibile ");
			alert.setHeaderText("Prenotazione non andata a buon fine");
			alert.setContentText("Errore nei vincoli inserire i valori nei campi nella maniera adeguata");
			alert.showAndWait();
			alert.close();
			return;
		}
		AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/RiepilogoEsame.fxml"));
				Scene scene = new Scene(root);
				Stage stage1 = new Stage();
				stage1.setScene(scene);
				stage1.setTitle("Riepilogo Prenotazione Esame");
				stage1.show(); 
			} catch (IOException e) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Tentativo di Prenotazione fallito");
				alert.setHeaderText("Prenotazione Esame!");
				alert.setContentText("Prenotazione non andata a buon fine perche non ci sono più aule disponibili");
				alert.showAndWait();
				alert.close();
			
			}
	}
	
	public PrenotazioneEsameBean getEsameBean() {
		return prenotazioneEsame;
	}
	
	@FXML
	public void chiudi(ActionEvent event) {
		// implementazione del pulsante per chiudere la finestra attuale
		 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	    System.out.println("Pagina chiusa con successo");
	}

	public void setStage(Stage formStage) {
		this.stage = formStage;
	}
	
	
}


