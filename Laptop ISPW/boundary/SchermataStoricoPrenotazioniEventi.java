
package boundary;

import java.util.ArrayList;

import bean.PrenotazioneEventoBean;
import boundary.StoricoPrenotazioni;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
public class SchermataStoricoPrenotazioniEventi {
	@FXML
	private Button closeButton;
	@FXML 
	private TableView<PrenotazioneEventoBean> table=new TableView<PrenotazioneEventoBean>();
	@FXML
	private TableColumn<PrenotazioneEventoBean, String> titolare=new TableColumn<PrenotazioneEventoBean, String>();
	@FXML
	private TableColumn<PrenotazioneEventoBean, String> corsoStudi=new TableColumn<PrenotazioneEventoBean, String>();
	@FXML
	private TableColumn<PrenotazioneEventoBean, String> aula=new TableColumn<PrenotazioneEventoBean, String>();
	@FXML
	private TableColumn<PrenotazioneEventoBean,String> orario=new TableColumn<PrenotazioneEventoBean, String>();
	@FXML
	private TableColumn<PrenotazioneEventoBean,String> titolo=new TableColumn<PrenotazioneEventoBean, String>();
	@FXML
	private TableColumn<PrenotazioneEventoBean,String>  data=new TableColumn<PrenotazioneEventoBean, String>();
	@FXML
	private TableColumn<PrenotazioneEventoBean,String> evento=new TableColumn<PrenotazioneEventoBean, String>();
	@FXML
	private TableColumn<PrenotazioneEventoBean,String> macroarea=new TableColumn<PrenotazioneEventoBean, String>();
	
	public static  ObservableList<PrenotazioneEventoBean> listaPrenotazioni =FXCollections.observableArrayList();
	public static ArrayList<PrenotazioneEventoBean> valori= new ArrayList<PrenotazioneEventoBean>();
	StoricoPrenotazioni Prenotazione= new StoricoPrenotazioni();
	
	@FXML
	public void initialize(){
		try {
			valori=StoricoPrenotazioni.getPrenotazioneStoricoEvento();
			if(valori.size()==0) {
				
	    		Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("");
				alert.setHeaderText("Attenzione!");
				alert.setContentText("NON CI SONO PRENOTAZIONI PER QUEST'UTENTE NEL SEGUENTE ANNO ACCADEMICO");
				alert.showAndWait();
				
			}
			
			listaPrenotazioni=FXCollections.observableArrayList(valori);
			table.setItems(listaPrenotazioni);
			titolare.setCellValueFactory(cellData -> cellData.getValue().getTitolareProperty());
			
			corsoStudi.setCellValueFactory(cellData -> cellData.getValue().getCorsoStudiProperty());
			
			aula.setCellValueFactory(cellData -> cellData.getValue().getAulaProperty());

			orario.setCellValueFactory(cellData -> cellData.getValue().getFasciaOrariaProperty());

			titolo.setCellValueFactory(cellData -> cellData.getValue().getTitoloEventoProperty());

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



