
package boundary;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;

import bean.PrenotazioneEsameBean;
import bean.PrenotazioneEventoBean;
import bean.UtenteBean;
import controller.ControllerProfessore;
import controller.ControllerSegreteria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class PrenotazioniAttive {

	public static ArrayList<PrenotazioneEventoBean> Eventi= new ArrayList<PrenotazioneEventoBean>();
	public static ArrayList<PrenotazioneEsameBean> Esami= new ArrayList<PrenotazioneEsameBean>();

	@FXML
	private Main main;
	@FXML
	private Stage stage;
	@FXML
	private Button closeButton;	
	
	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage loginStage) {
		this.stage = loginStage;
	}
	
	@FXML
	public void chiudi(ActionEvent event) {
		// implementazione del pulsante per chiudere la finestra attuale
		 ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		 System.out.println("Pagina chiusa con successo");
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	public void visualizzaPrenotazioniAttive(ActionEvent event) throws SQLException {
			

			java.util.Date attuale= new java.util.Date();
			//java.sql.Date  today =  new java.sql.Date( calendar.getTime());
			 
				 
		
			
			int  ore=attuale.getHours();
			
			int mese=attuale.getMonth();
			java.util.Date data = new Date(Integer.valueOf(attuale.toLocaleString().substring(6,10)),mese,attuale.getDate());
			
			UtenteBean bean=new UtenteBean();
			bean=application.Main.userBean;
						
			String Type= bean.getType();
			

			switch(Type) {
				case "Professore":
					ControllerProfessore istance=ControllerProfessore.getInstance();
					Esami=istance.visualizzaPrenotazioniAttiveExams(data,bean.getUserid(),ore);
			 
					Stage stage = (Stage) closeButton.getScene().getWindow();
					stage.close();
				
				try {
				AnchorPane	root1 = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/SchermataPrenotazioniAttiveEsami.fxml"));
					Scene scene1 = new Scene(root1);
					Stage stage2 = new Stage();
					stage2.setScene(scene1);
					stage2.setTitle("Schermata Prenotazioni Attive Esami");
					stage2.show(); 
				} catch (IOException e) {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("");
					alert.setHeaderText("Attenzione!");
					alert.setContentText("Inserire i campi nella maniera adeguata.");
					alert.showAndWait();
					System.out.println("Gli elementi non sono stati inseriti nel modo corretto");
					

				}	
					break;
					
					
				case "Segreteria":
					ControllerSegreteria istance2=	ControllerSegreteria.getInstance();
				Eventi=istance2.visualizzaPrenotazioniAttiveEvents(data,bean.getUserid(),ore); 
				Stage stage1 = (Stage) closeButton.getScene().getWindow();
				stage1.close();
				AnchorPane root;
				try {
					root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/SchermataPrenotazioniAttiveEventi.fxml"));
					Scene scene = new Scene(root);
					Stage stage10 = new Stage();
					stage10.setScene(scene);
					stage10.setTitle("Schermata Prenotazioni Attive Eventi");
					stage10.show(); 
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("");
					alert.setHeaderText("Attenzione!");
					alert.setContentText("Inserire i campi nella maniera adeguata.");
					alert.showAndWait();
					System.out.println("Gli elementi non sono stati inseriti nel modo corretto");
					
				}
				break;
				
			 }//chiusura switch
			
	}
			 
		
			
		
	    
	public ArrayList<PrenotazioneEsameBean> getPrenotazioneAttiveEsame(){
		return Esami;
	}
	public ArrayList<PrenotazioneEventoBean> getPrenotazioneAttiveEvento(){
		return Eventi;
	}


}
