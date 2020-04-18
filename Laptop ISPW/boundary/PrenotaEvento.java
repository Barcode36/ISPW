
package boundary;

import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import application.Main;
import bean.AulaBean;
import bean.PrenotazioneEventoBean;
import bean.UtenteBean;
import controller.ControllerSegreteria;
import entity.Evento;
import entity.FasciaOraria;
import entity.Laboratorio;
import entity.Macroarea;
import entity.TipoAula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrenotaEvento {
	
	public static PrenotazioneEventoBean prenotazioneEvento= new PrenotazioneEventoBean();
	@FXML
	private Button confermaButtons;
	@FXML
	private Button closeButtons;
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
	private TextField posti;
	@FXML
	private TextField proiettore;
	@FXML
	private TextField titoloEvento;
	@FXML
	private TextField CorsoStudi;
	
	@SuppressWarnings("unused")
	private Stage stage;
	@FXML
	private Main main;
	protected String laboratorioString,fasceOrarieString,macroareeString,eventiString,tipiAuleString;
	
	@FXML
	public void initialize() {
		
		ObservableList<Laboratorio> lab = FXCollections.observableArrayList(Laboratorio.values());
		this.lab.setItems(lab);
		this.lab.valueProperty().addListener((observable, oldValue, newValue) -> 
		laboratorioString= (newValue.toString()));
		tipiAule.setOnAction((event) -> {
		    TipoAula selectedAula = tipiAule.getSelectionModel().getSelectedItem();
		    
		    if (selectedAula == TipoAula.Convegni) {
		    	this.lab.setDisable(true);
		    } else if (selectedAula == TipoAula.Didattica){
		    	this.lab.setDisable(false);
		    }
		});
		
		ObservableList<FasciaOraria> orari = FXCollections.observableArrayList(FasciaOraria.values());
		this.fasceOrarie.setItems(orari);
		this.fasceOrarie.valueProperty().addListener((observable, oldValue, newValue) -> 
		fasceOrarieString= (newValue.toString()));
		
		ObservableList<Macroarea> macro = FXCollections.observableArrayList(Macroarea.values());
		this.macroaree.setItems(macro);
		this.macroaree.valueProperty().addListener((observable, oldValue, newValue) -> 
		macroareeString= (newValue.toString()));
		
		ObservableList<Evento> valori = FXCollections.observableArrayList(Evento.SedutaLaurea, Evento.TestIngresso);
		eventi.setItems(valori);
		this.eventi.valueProperty().addListener((observable, oldValue, newValue) -> 
		eventiString= (newValue.toString()));
		
		ObservableList<TipoAula> aule = FXCollections.observableArrayList(TipoAula.values());
		this.tipiAule.setItems(aule);
		this.tipiAule.valueProperty().addListener((observable, oldValue, newValue) -> 
		tipiAuleString= (newValue.toString()));
	}
	
	
	
	@FXML
	public void PrenotaEvento2() throws  IOException, SQLException {
		UtenteBean bean=new UtenteBean();
		bean=application.Main.userBean;
		try {
		if(posti.getText().isEmpty() || proiettore.getText().isEmpty()|| CorsoStudi.getText().isEmpty()|| titoloEvento.getText().isEmpty() || Integer.valueOf(posti.getText())<=0 || Integer.valueOf(posti.getText())>750 ) 
			{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Prenotazione non possibile ");
			alert.setHeaderText("Prenotazione non andata a buon fine");
			alert.setContentText("Inserire i valori nei campi nella maniera adeguata");
			alert.showAndWait();
			alert.close();
			return;
		}
		
		AulaBean  aulabean = new AulaBean();
		aulabean.setNumeroPosti(Integer.valueOf((this.posti.getText())));
		aulabean.setProiettore(Integer.valueOf(this.proiettore.getText()));
		
		aulabean.setLavagna(this.lav.isSelected());
		aulabean.setLavInterattiva(this.lavInt.isSelected());
		aulabean.setPrese(this.prese.isSelected());
		aulabean.setMicrofono(this.mic.isSelected());
		aulabean.setEthernet(this.ethernet.isSelected());
		aulabean.setLaboratorio(laboratorioString);

		//aulabean.setLaboratorio(this.lab.getSelectionModel().getSelectedItem().toString());
		aulabean.setTipo((tipiAuleString));
		//aulabean.setTipo(this.tipiAule.getSelectionModel().getSelectedItem().toString());
		
		
		
		//PrenotazioneEventoBean PrenotazionePossibile=new PrenotazioneEventoBean();
		
		PrenotazioneEventoBean prenotazionebean = new PrenotazioneEventoBean();
		prenotazionebean.setTitolarePrenotazione(bean.getUserid());
		prenotazionebean.setEvento((eventiString));
		//prenotazionebean.setEvento(this.eventi.getSelectionModel().getSelectedItem().toString());
		
		prenotazionebean.setOrario((fasceOrarieString));
		//prenotazionebean.setOrario(this.fasceOrarie.getSelectionModel().getSelectedItem().toString());
		
		prenotazionebean.setMacroarea((macroareeString));
		//prenotazionebean.setMacroarea(this.macroaree.getSelectionModel().getSelectedItem().toString());
		
		prenotazionebean.setTitoloEvento(this.titoloEvento.getText());
		java.util.Date Dataprenotazione = (java.util.Date) java.sql.Date.valueOf(this.data.getValue());

		prenotazionebean.setCorsoStudi(this.CorsoStudi.getText());
		prenotazionebean.setData((Date) Dataprenotazione);
	
		prenotazioneEvento=ControllerSegreteria.getInstance().RichiestaPrenotazioneEvento(aulabean,prenotazionebean);
		}catch(NumberFormatException e) {
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
				root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/RiepilogoEvento.fxml"));
				Scene scene = new Scene(root);
				Stage stage1 = new Stage();
				stage1.setScene(scene);
				stage1.setTitle("Riepilogo Prenotazione Evento");
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
	public static PrenotazioneEventoBean getEventoBean() {
		return prenotazioneEvento;
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
	
