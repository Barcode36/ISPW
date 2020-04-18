
package boundary;

import java.util.ArrayList;

import bean.PrenotazioneEsameBean;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SchermataStoricoPrenotazioniEsami {
	@FXML
	private Button closeButton;
	@FXML 
	private TableView<PrenotazioneEsameBean> table=new TableView<PrenotazioneEsameBean>();
	@FXML
	private TableColumn<PrenotazioneEsameBean, String> sessione=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean, String> seduta=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean,String> appello=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean, String> titolare=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean, String> aula=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean,String> orario=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean,String> NomeCorso=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean,String>  data=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean,String> evento=new TableColumn<PrenotazioneEsameBean, String>();
	@FXML
	private TableColumn<PrenotazioneEsameBean,String> macroarea=new TableColumn<PrenotazioneEsameBean, String>();
	public static  ObservableList<PrenotazioneEsameBean> listaPrenotazioni =FXCollections.observableArrayList();
	public static ArrayList<PrenotazioneEsameBean> valori= new ArrayList<PrenotazioneEsameBean>();
	StoricoPrenotazioni Prenotazione= new StoricoPrenotazioni();
	@FXML
	public void initialize(){
	  	try {
	  		
	   		valori=StoricoPrenotazioni.getPrenotazioneStoricoEsame();
			if(valori.size()==0) {
				
	    		Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("");
				alert.setHeaderText("Attenzione!");
				alert.setContentText("NON CI SONO PRENOTAZIONI PER QUEST'UTENTE NEL SEGUENTE ANNO ACCADEMICO");
				alert.showAndWait();
			
			}
			
			listaPrenotazioni=FXCollections.observableArrayList(valori);
			table.setItems(listaPrenotazioni);
			
			sessione.setCellValueFactory(cellData -> cellData.getValue().getSessioneProperty());
			
			appello.setCellValueFactory(cellData -> cellData.getValue().getAppelloProperty());
			
			titolare.setCellValueFactory(cellData -> cellData.getValue().getTitolareProperty());
			
			seduta.setCellValueFactory(cellData -> cellData.getValue().getSedutaProperty());
			
			aula.setCellValueFactory(cellData -> cellData.getValue().getAulaProperty());

			orario.setCellValueFactory(cellData -> cellData.getValue().getFasciaOrariaProperty());

			NomeCorso.setCellValueFactory(cellData -> cellData.getValue().getNomeCorsoProperty());

			data.setCellValueFactory(cellData -> cellData.getValue().getDataProperty());

			evento.setCellValueFactory(cellData -> cellData.getValue().getEventoProperty());
			
			macroarea.setCellValueFactory(cellData -> cellData.getValue().getMacroareaProperty());
	    	
	   	}catch(Exception e) {
	    		Alert alert = new Alert(AlertType.ERROR);	
	    		alert.setTitle("");
				alert.setHeaderText("Attenzione!");
				alert.setContentText("IL VALORE INSERITO HA 0 RISULTATI");
				alert.showAndWait();
	    	}
	    }
	    
		@FXML
		public void chiudi() {
			Stage stage = (Stage) closeButton.getScene().getWindow();
			System.out.println("Chiusa pagina con successo");
			stage.close();
		}
		
		
}