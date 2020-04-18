
package application;
import java.io.IOException;

import bean.UtenteBean;
import boundary.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primaryStage;
	public static UtenteBean userBean;
	
	
	@Override
	public void start(Stage primaryStage) {
	
	
		this.primaryStage=primaryStage;
		initRootLayout();
	
	}
	public void initRootLayout() {
		try {
			
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(getClass().getResource("/boundary/Login.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			
			
			Login controllerLogico =loader.getController();
			controllerLogico.setMain(this);
			controllerLogico.setStage(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void showHomePageProfessore(UtenteBean user){
		try {			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/boundary/HomepageProfessore.fxml"));
			
			AnchorPane anchorPane = (AnchorPane) loader.load();
			userBean = user;
			primaryStage.setTitle("Homepage Professore");				
			primaryStage.setScene(new Scene(anchorPane));
			primaryStage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public UtenteBean getUtenteBean() {
		return userBean;
	}
	
	public void showHomePageSegreteria(UtenteBean user){
		try {		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/boundary/HomePageSegreteria.fxml"));
		
			AnchorPane anchorPane = (AnchorPane) loader.load();
			userBean = user;
			primaryStage.setTitle("Homepage Segreteria");				
			primaryStage.setScene(new Scene(anchorPane));
			primaryStage.show();
		} catch(IOException e) {
			e.printStackTrace();	
		}
	}
	
	public void apriSchermataErrore() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/boundary/ErroreAccesso.fxml"));
			
			AnchorPane anchorPane = (AnchorPane) loader.load();
			primaryStage.setTitle(" Errore nel Login");	
			primaryStage.setScene(new Scene(anchorPane));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
