
package boundary;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import application.Main;
import bean.UtenteBean;
import controller.Controller;
import exception.UtenteNonTrovatoException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Login {
	
	String tipo;
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Main main;
	@FXML
	private Stage stage;
	@FXML
	public void Esci() {
		System.out.println("Uscito dal sistema con successo");
		System.exit(0);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage loginStage) {
		this.stage = loginStage;
	}
		
	@FXML
	 public void cancella() {
		// utile per pulire i campi
		 user.setText("");
		 password.setText("");
		 System.out.println("Reset campi avvenuto con successo");
	 }
	@FXML
	 public void accedi(ActionEvent event) throws UtenteNonTrovatoException  {
			//collegare i bottoni e altre strutture fxml
		String user = this.user.getText();
	    String pass = this.password.getText();
		if( user.isEmpty() || pass.isEmpty()) {
	
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("");
			alert.setHeaderText("Attenzione!");
			alert.setContentText("Per effettuare il login inserire username e password.");
			alert.showAndWait();
				
		}
		else {
			UtenteBean loginBean=new UtenteBean();
			loginBean.setUserid(user);
			loginBean.setPassword(pass);
			System.out.println("Tentativo di accedere con i seguenti dati:");
			System.out.println("ID:"+user+"     "+"password:"+pass);
			try {
				Controller controllerFisico = Controller.getInstance();
				synchronized(controllerFisico) {
					UtenteBean bean = controllerFisico.login(loginBean.getUserid(), loginBean.getPassword());
					this.stage.close();
					tipo=bean.getType();
					if (tipo.equals("Professore")) {
						this.main.showHomePageProfessore(bean);
						System.out.println("Loggato come Professore");
					}
					else if (tipo.equals("Segreteria")) {
						this.main.showHomePageSegreteria(bean);
						System.out.println("Loggato come Segreteria");
					} 

				
				}}catch(NullPointerException e ) {
					this.main.apriSchermataErrore();
					System.out.println("Errore nell'accesso ");
			}
			
		}
		 
	}		
	
}