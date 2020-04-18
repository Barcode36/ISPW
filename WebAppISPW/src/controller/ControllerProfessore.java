package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.AulaBean;
import bean.PrenotazioneEsameBean;
import entity.Aula;
import entity.PrenotazioneEsame;
import entity.PrenotazioneEvento;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import persistence.DaoPrenotazioni;

public class ControllerProfessore {

	
	private static ControllerProfessore instance = null;

	private ControllerProfessore() {
	}

	//pattern singleton	
	public static ControllerProfessore getInstance() {
		if(instance == null) {
			instance = new ControllerProfessore();
		}
		return instance;
	}

/*
 *	metodo per prendere tutte le PrentoazioniAttive dando in input la data attuale e l'identificativo dell user, il titolare.
 *	Il valore di ritorno restituisce un array di prenotazioni Esame che prese come oggetti model diventano bean prima di passare al view
 *
 */
	public static ArrayList<PrenotazioneEsameBean> visualizzaPrenotazioniAttiveExams(java.util.Date  dataOdierna,String userid,int ore) throws SQLException {
	
		ArrayList<PrenotazioneEsame> value = new ArrayList<PrenotazioneEsame>();
		
		value=DaoPrenotazioni.findPrenotazioniEsame(dataOdierna,userid);		
		
		ArrayList<PrenotazioneEsame> valueAttivo = new ArrayList<PrenotazioneEsame>();
		valueAttivo=verificaOrario(value,dataOdierna,ore);
		
		ArrayList<PrenotazioneEsameBean> valoriBean = new ArrayList<PrenotazioneEsameBean>();
		
		
		System.out.println("Ricerca avvenuta con successo: Attendi i risultati");
		for(PrenotazioneEsame f : valueAttivo)
		{
			PrenotazioneEsameBean bean=new PrenotazioneEsameBean();
			bean.setSessione(f.getSessione());
			bean.setAppello(f.getAppello());
			bean.setAula(f.getAula());
			bean.setOrario(f.getOrario());
			bean.setMacroarea(f.getMacroarea());
			bean.setNomeCorso(f.getNomeCorso());
			bean.setEvento(f.getEvento());
			bean.setData((Date) f.getData());
			bean.setSeduta(f.getSeduta());
			bean.setTitolarePrenotazione(f.getTitolarePrenotazione());
		
			valoriBean.add(bean);
		}
	
		return valoriBean;
	}

	/*
	 *	metodo per prendere tutte le Prenotazioni dell'anno accademico selezionato dando in input la data d'inizio e di fine e l'identificativo dell user, il titolare.
	 *	Il valore di ritorno restituisce un array di prenotazioni Esame che prese come oggetti model diventano bean prima di passare al view
	 *
	 */

	public static ArrayList<PrenotazioneEsameBean> visualizzaStoricoPrenotazioniEsame(java.util.Date inizio,java.util.Date fine,String userid) throws SQLException {

		ArrayList<PrenotazioneEsame> value = new ArrayList<PrenotazioneEsame>();
		value=DaoPrenotazioni.findStoricoPrenotazioniEsame(inizio,fine,userid);
	
		ArrayList<PrenotazioneEsameBean> valoriBean = new ArrayList<PrenotazioneEsameBean>();
	
		System.out.println("Ricerca avvenuta con successo: Attendi i risultati");
		for(PrenotazioneEsame f : value)
	

		{
			PrenotazioneEsameBean bean=new PrenotazioneEsameBean();
			bean.setSessione(f.getSessione());
			bean.setAppello(f.getAppello());
			bean.setAula(f.getAula());
			bean.setOrario(f.getOrario());
			bean.setMacroarea(f.getMacroarea());
			bean.setNomeCorso(f.getNomeCorso());
			bean.setEvento(f.getEvento());
			bean.setData((Date) f.getData());
			bean.setTitolarePrenotazione(f.getTitolarePrenotazione());
			bean.setSeduta(f.getSeduta());
		
			valoriBean.add(bean);
		}
	
		return valoriBean;	
	}
	/*
	 *	metodo per fare richiesta di una prenotazione di un esame , la quale verrà confermata e salvata nel db attraverso un altro metodo :passiamo per in input l'aula e la prenotazione di un certo esame 
	 *	L'aula verrà inserita come model e da qui attraverso il Dao possiamo vedere quale tra queste aule è libera in quella data e ora.
	 *	la prenotazione dell'esame avviene selezionanado un'aula tra l'array di aule disponibili .
	 *
	 */
	public PrenotazioneEsameBean RichiestaPrenotazioneEsame(AulaBean aulabean, PrenotazioneEsameBean prenotazionebean) throws SQLException {
		
		ArrayList<String> value = new ArrayList<String>();
		ArrayList<PrenotazioneEsame> value1=new ArrayList<PrenotazioneEsame>();
		PrenotazioneEsameBean bean=new PrenotazioneEsameBean();
		PrenotazioneEsame model= new PrenotazioneEsame();
		//PrenotazioneEsame prenotazione= new PrenotazioneEsame();
		PrenotazioneEsame prenotazione= new PrenotazioneEsame(prenotazionebean.getTitolarePrenotazione(),prenotazionebean.getAula(), prenotazionebean.getMacroarea(),prenotazionebean.getEvento(), prenotazionebean.getData(),prenotazionebean.getOrario(),prenotazionebean.getSeduta(),prenotazionebean.getAppello(),prenotazionebean.getNomeCorso(),prenotazionebean.getSessione());
			
		/*prenotazione.setTitolarePrenotazione(prenotazionebean.getTitolarePrenotazione());
		prenotazione.setAppello(prenotazionebean.getAppello());
		prenotazione.setAula(prenotazionebean.getAula());
		prenotazione.setMacroarea(prenotazionebean.getMacroarea());
		prenotazione.setEvento(prenotazionebean.getEvento());
		prenotazione.setData(prenotazionebean.getData());
		prenotazione.setOrario(prenotazionebean.getOrario());
		prenotazione.setNomeCorso(prenotazionebean.getNomeCorso());
		prenotazione.setSessione(prenotazionebean.getSessione());
		prenotazione.setSeduta(prenotazionebean.getSeduta());
		*/
		Aula aulaperDao=new Aula(aulabean.getTipo(), aulabean.getNumeroPosti(),aulabean.getNome(),aulabean.getProiettore(),aulabean.isMicrofono(),
				aulabean.isLavagna(),aulabean.isLavInterattiva(),aulabean.isPrese(),aulabean.isEthernet(),aulabean.getLaboratorio());
	
	
		
			value=DaoPrenotazioni.findAuleEsame(aulaperDao);
			for(int i=0;i<value.size();i++) {
				Aula a= new Aula(value.get(i));
				prenotazione.setAula(a);
				value1.add(prenotazione);
				model=DaoPrenotazioni.findPrenotaEsame(value1.get(i).getAula().getNome(),value1.get(i).getOrario(),(java.sql.Date)value1.get(i).getData());
				if(model!=null) {
					bean.setSessione(prenotazione.getSessione());
					bean.setAppello(prenotazione.getAppello());
					bean.setAula(prenotazione.getAula());
					bean.setOrario(prenotazione.getOrario());
					bean.setMacroarea(prenotazione.getMacroarea());
					bean.setNomeCorso(prenotazione.getNomeCorso());
					bean.setEvento(prenotazione.getEvento());
					bean.setData((Date) prenotazione.getData());
					bean.setTitolarePrenotazione(prenotazione.getTitolarePrenotazione());
					bean.setSeduta(prenotazione.getSeduta());
					
					return bean;
				}
				
				
			}
			
			return bean;
	
	}
	/*
	 * Dal form selezionato avviene la conferma della prenotazione!
	 */
	public void InvioEsamePrenotazione(PrenotazioneEsameBean valori) {
	
		boolean action=false;
		
		
		PrenotazioneEsame prenotazione= new PrenotazioneEsame(valori.getTitolarePrenotazione(),valori.getAula(), valori.getMacroarea(),valori.getEvento(), valori.getData(),valori.getOrario(),valori.getSeduta(),valori.getAppello(),valori.getNomeCorso(),valori.getSessione());
		
		
		action=DaoPrenotazioni.PrenotaEsameUfficiale(prenotazione);
		
		if(action!=true)
			return;
		
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Prenotazione non andata a buon fine");
			alert.setHeaderText("C'è stato un errore nell'inserimento dei valori");
			alert.setContentText("Prenotazione non andata a buon fine");
			alert.showAndWait();
			alert.close();	
		}
	
	}

	/*
	 * Usato per verificare se esiste per una certa ipotetica prenotazione un'altra esistente nel databse con quella data,fasciaoraria,aula
	 */
	public boolean verificaEsameController(PrenotazioneEsameBean valori) throws SQLException {
		

		PrenotazioneEsame prenotazione= new PrenotazioneEsame(valori.getTitolarePrenotazione(),valori.getAula(), valori.getMacroarea(),valori.getEvento(), valori.getData(),valori.getOrario(),valori.getSeduta(),valori.getAppello(),valori.getNomeCorso(),valori.getSessione());
		
		PrenotazioneEvento prenotazione2= new PrenotazioneEvento(valori.getAula(),valori.getData(),valori.getOrario());
		
		
		
		
		if(DaoPrenotazioni.verificaEsame(prenotazione)|| DaoPrenotazioni.verificaEvento(prenotazione2))
			return true;
			return false;
	}
	


	@SuppressWarnings("deprecation")
	public	static ArrayList<PrenotazioneEsame> verificaOrario(ArrayList<PrenotazioneEsame> value,java.util.Date today, int ore) {
		ArrayList<PrenotazioneEsame> valueAttivo = new ArrayList<PrenotazioneEsame>();
	

		for(PrenotazioneEsame f : value)	
		{
			if((f.getData().toLocaleString().substring(0,11) ) == (today.toLocaleString().substring(0,11)))
			{
				if(f.getOrario().substring(5,7).contains("-"))
				{
					int fasciaOrariaInizio=9;
					if(ore>=fasciaOrariaInizio)
					{
				
					}
					else 
					{
						valueAttivo.add(f);
					}
				}
				else 
				{
					int fasciaOrariaInizio=Integer.valueOf(f.getOrario().substring(5,7));
					if(ore>=fasciaOrariaInizio) 
					{
			
					}
					else 
					{
						valueAttivo.add(f);
					}
				}
			}
			else
			{
				valueAttivo.add(f);
			}
		}
	
		return valueAttivo;
	
	
	
		}
		
		
}





