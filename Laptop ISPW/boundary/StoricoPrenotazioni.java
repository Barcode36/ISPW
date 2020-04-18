
package boundary;

import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StoricoPrenotazioni {
	
	public static ArrayList<PrenotazioneEventoBean> Eventi= new ArrayList<PrenotazioneEventoBean>();
	public static ArrayList<PrenotazioneEsameBean> Esami= new ArrayList<PrenotazioneEsameBean>();

	@FXML
	private TextField AnnoAccademico;
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
	public void chiusura(ActionEvent event) {
		// implementazione del pulsante per chiudere la finestra attuale
		 Stage stage = (Stage) closeButton.getScene().getWindow();
		 stage.close();
			
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	public void visualizzaStoricoPrenotazioni(ActionEvent event) throws SQLException {
		try {
			
			int InizioAnnoAccademico=Integer.valueOf(AnnoAccademico.getText().substring(0,4));
			
			int FineAnnoAccademico=Integer.valueOf(AnnoAccademico.getText().substring(5));
			
			if( AnnoAccademico.getText()=="") {
			

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("");
				alert.setHeaderText("Attenzione!");
				alert.setContentText("campi incompleti... inserire tutti i campi.");
				alert.showAndWait();
			}
			else {
				
				java.util.Date  dInizio=new  java.util.Date (InizioAnnoAccademico, 10,1 );
			
				java.util.Date  dFine=new java.util.Date (FineAnnoAccademico,03,1 );
				
							
				UtenteBean bean=new UtenteBean();
				bean=application.Main.userBean;
				String Type=bean.getType();
				
				switch(Type) {
				case "Professore":
				
					
					ControllerProfessore istance=ControllerProfessore.getInstance();
					Esami=istance.visualizzaStoricoPrenotazioniEsame(dInizio,dFine,bean.getUserid());
					
					Stage stage = (Stage) closeButton.getScene().getWindow(); 
					stage.close();
					try {
						AnchorPane root1 = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/SchermataStoricoPrenotazioniEsami.fxml"));
						Scene scene1 = new Scene(root1);
						Stage stage2 = new Stage();
						stage2.setScene(scene1);
						stage2.setTitle("Schermata Storico Prenotazioni Di Esami");
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
					ControllerSegreteria istance2=ControllerSegreteria.getInstance();
					Eventi=istance2.visualizzaStoricoPrenotazioniEvento(dInizio,dFine,bean.getUserid()); 
					Stage stage4 = (Stage) closeButton.getScene().getWindow();
					stage4.close();
					try {
						AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("boundary/SchermataStoricoPrenotazioniEventi.fxml"));
						Scene scene = new Scene(root);
						Stage stage1 = new Stage();
						stage1.setScene(scene);
						stage1.setTitle("Schermata Prenotazioni Storico Di Eventi");
						stage1.show(); 
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
			}}catch(Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Errore nell'inserimento dei dati ");
				alert.setHeaderText("Non hai inserito un numero giusto");
				alert.setContentText("Prova ad esempio un numero ad esempio (2018-2020)");
				alert.showAndWait();
				alert.close();
				return;
			}}//chiusura else usato per controlli
		//chiusura classe


	public  static ArrayList<PrenotazioneEsameBean> getPrenotazioneStoricoEsame(){
		return Esami;
	}
	public static ArrayList<PrenotazioneEventoBean> getPrenotazioneStoricoEvento(){
		return Eventi;
	}   


}


