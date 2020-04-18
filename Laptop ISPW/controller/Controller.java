package controller;



import bean.UtenteBean;
import entity.Utente;
import persistence.DaoUtente;

public class Controller {


private static Controller instance = null;

private Controller() {
}


public UtenteBean login(String username, String password) {
	
	Utente Model= DaoUtente.findUtente(username, password);
	UtenteBean Bean=new UtenteBean();
	Bean.setType(Model.getType());
	Bean.setPassword(Model.getPassword());
	Bean.setUserid(Model.getUserid());
	return Bean;
}

public static Controller getInstance() {
	// TODO Auto-generated method stub
	if(instance == null) {
		instance = new Controller();
	}
	return instance;
}

}


