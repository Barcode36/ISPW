package test;


import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import bean.UtenteBean;
import controller.Controller;
import entity.Utente;
import exception.UtenteEsistenteException;
import persistence.DaoUtente;

@RunWith(value = Parameterized.class )

public class TestUtente {
	private static String id;
	private static String pass;
	private static String tipo;
	

	
	@Parameters
	public static Collection<String[]> getParameters(){
		
		return Arrays.asList(new String[][] {
			{"DeAngelis", "p", "Professore"}, //String userid,String password,String Type
			{"Segretario2", "s", "Segreteria"}
			
		});
	}
	
	public TestUtente(String userId, String password,String type ) throws UtenteEsistenteException, SQLException {
		id = userId;
		pass = password;
		tipo=type;
		
			
			DaoUtente.aggiungiUtente(pass, id, tipo);
		
	}; 
	
	
	@Test
		public  void verifica() throws Exception {
	
		Controller ControlVerifica=Controller.getInstance();
		UtenteBean uc = ControlVerifica.login(id,pass);
		Utente ud = DaoUtente.findUtente(id, pass);
		assertEquals(uc.getUserid(),ud.getUserid());
		assertEquals(uc.getPassword(),ud.getPassword());
		assertEquals(uc.getType(),ud.getType());
		System.out.println("Test eseguito con successo");
		
	
	}
	@AfterClass
    public static void CleanAllUtenti() {
		Controller ControlVerifica=Controller.getInstance();
		UtenteBean uc = ControlVerifica.login(id,pass);
		try {
			DaoUtente.deleteUtente(uc.getPassword(),uc.getUserid(),uc.getType());
		} catch (UtenteEsistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Il test puo essere rieseguito in quanto son stati cancellati gli oggetti con successo");
    }  
	

}
