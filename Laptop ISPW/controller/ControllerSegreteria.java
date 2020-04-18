
package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.AulaBean;
import bean.PrenotazioneEventoBean;
import entity.Aula;
import entity.PrenotazioneEsame;
import entity.PrenotazioneEvento;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import persistence.DaoPrenotazioni;

public class ControllerSegreteria {


	private static ControllerSegreteria instance = null;
	public static PrenotazioneEvento prenotazioneevent;
	private ControllerSegreteria() {
	}
	//pattern singleton	
	public static ControllerSegreteria getInstance() {
		if(instance == null) {
			instance = new ControllerSegreteria();
		}
		return instance;
	}

/*
 *	metodo per prendere tutte le PrentoazioniAttive dando in input la data attuale e l'identificativo dell user, il titolare.
 *	Il valore di ritorno restituisce un array di prenotazioni Esame che prese come oggetti model diventano bean prima di passare al view
 *
 */

	public ArrayList<PrenotazioneEventoBean> visualizzaPrenotazioniAttiveEvents(java.util.Date today,String userid,int ore) throws SQLException {
	
	
		ArrayList<PrenotazioneEvento> value = new ArrayList<PrenotazioneEvento>();
		value=DaoPrenotazioni.findPrenotazioniAttiveEvento(today,userid);

		ArrayList<PrenotazioneEvento> valueAttivo = new ArrayList<PrenotazioneEvento>();
		valueAttivo=verificaOrario(value,today,ore);
	
		ArrayList<PrenotazioneEventoBean> valoriBean = new ArrayList<PrenotazioneEventoBean>();
		
		System.out.println("Ricerca avvenuta con successo: Attendi i risultati");

		
		for(PrenotazioneEvento f : valueAttivo)
	
		{
			PrenotazioneEventoBean bean=new PrenotazioneEventoBean();
		
			bean.setAula(f.getAula());
			bean.setOrario(f.getOrario());
			bean.setMacroarea(f.getMacroarea());
			bean.setEvento(f.getEvento());
			bean.setData((Date) f.getData());
			bean.setTitoloEvento(f.getTitoloEvento());
			bean.setTitolarePrenotazione(f.getTitolarePrenotazione());
			bean.setCorsoStudi(f.getCorsoStudi());
		
			valoriBean.add(bean);
		}
	
		return valoriBean;
	}


	@SuppressWarnings("deprecation")
	public	static ArrayList<PrenotazioneEvento> verificaOrario(ArrayList<PrenotazioneEvento> value,java.util.Date today, int ore) {
		ArrayList<PrenotazioneEvento> valueAttivo = new ArrayList<PrenotazioneEvento>();
	
		for(PrenotazioneEvento f : value)	
		{
			if((f.getData().toLocaleString().substring(0,11) ) == (today.toLocaleString().substring(0,11)))
			{
				if(f.getOrario().substring(5,7).contains("_"))
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
	/*
 *	metodo per prendere tutte le Prenotazioni dell'anno accademico selezionato dando in input la data d'inizio e di fine e l'identificativo dell user, il titolare.
 *	Il valore di ritorno restituisce un array di prenotazioni evento che prese come oggetti model diventano bean prima di passare al view
 *
 */
	public ArrayList<PrenotazioneEventoBean> visualizzaStoricoPrenotazioniEvento(java.util.Date inizio,java.util.Date fine,String userid) throws SQLException {
		
		ArrayList<PrenotazioneEvento> value = new ArrayList<PrenotazioneEvento>();
		value=DaoPrenotazioni.findStoricoPrenotazioniEvento(inizio,fine,userid);
	
		ArrayList<PrenotazioneEventoBean> valoriBean = new ArrayList<PrenotazioneEventoBean>();
	
		System.out.println("Ricerca avvenuta con successo: Attendi i risultati");
		
		for(PrenotazioneEvento f : value)
				
		{
			PrenotazioneEventoBean bean=new PrenotazioneEventoBean();
			
			bean.setAula(f.getAula());
			bean.setOrario(f.getOrario());
			bean.setMacroarea(f.getMacroarea());
			bean.setEvento(f.getEvento());
			bean.setData((Date) f.getData());
			bean.setTitoloEvento(f.getTitoloEvento());
			bean.setTitolarePrenotazione(f.getTitolarePrenotazione());
			bean.setCorsoStudi(f.getCorsoStudi());


			valoriBean.add(bean);
		}
	
		return valoriBean;
	}

/*
 *	metodo per fare richiesta di una prenotazione di un evento , la quale verrà confermata e salvata nel db attraverso un altro metodo :passiamo per in input l'aula e la prenotazione di un certo esame 
 *	L'aula verrà inserita come model e da qui attraverso il Dao possiamo vedere quale tra queste aule è libera in quella data e ora.
 *	la prenotazione dell'evento avviene selezionanado un'aula tra l'array di aule disponibili .
 *
 */
	public PrenotazioneEventoBean RichiestaPrenotazioneEvento(AulaBean aulabean, PrenotazioneEventoBean prenotazionebean) throws SQLException {
		ArrayList<String> value=new ArrayList<String>();
		ArrayList<PrenotazioneEvento> value1=new ArrayList<PrenotazioneEvento>();
		PrenotazioneEvento model= new PrenotazioneEvento();
		PrenotazioneEventoBean bean=new PrenotazioneEventoBean();	
		
		PrenotazioneEvento prenotazione= new PrenotazioneEvento(prenotazionebean.getTitolarePrenotazione(),prenotazionebean.getTitoloEvento(),prenotazionebean.getAula(),prenotazionebean.getMacroarea(),prenotazionebean.getEvento(),prenotazionebean.getData(),prenotazionebean.getOrario(),prenotazionebean.getCorsoStudi());
			
		Aula aulaperDao=new Aula(aulabean.getTipo(), aulabean.getNumeroPosti(),aulabean.getNome(),aulabean.getProiettore(),aulabean.isMicrofono(),
				aulabean.isLavagna(),aulabean.isLavInterattiva(),aulabean.isPrese(),aulabean.isEthernet(),aulabean.getLaboratorio());
		
			value=DaoPrenotazioni.findAuleEvento(aulaperDao);
			
			for(int i=0;i<value.size();i++) {
				Aula a= new Aula(value.get(i));
				prenotazione.setAula(a);
				value1.add(prenotazione);

				model=DaoPrenotazioni.findPrenotazioniEventi(value1.get(i).getAula().getNome(),value1.get(i).getOrario(),(java.sql.Date)value1.get(i).getData());
				if(model!=null) {
				bean.setAula(prenotazione.getAula());
				bean.setOrario(prenotazione.getOrario());
				bean.setMacroarea(prenotazione.getMacroarea());
				bean.setEvento(prenotazione.getEvento());
				bean.setData((Date) prenotazione.getData());
				bean.setTitoloEvento(prenotazione.getTitoloEvento());
				bean.setTitolarePrenotazione(prenotazione.getTitolarePrenotazione());
				bean.setCorsoStudi(prenotazione.getCorsoStudi());

				return bean;
						
						
			}
			
			}
		return bean;
	}
		
	/*
	 * Dal form selezionato avviene la conferma della prenotazione!
	 */
	public void InvioEventoPrenotazione(PrenotazioneEventoBean valori) {
	
		boolean action=false;
		
		PrenotazioneEvento prenotazione= new PrenotazioneEvento(valori.getTitolarePrenotazione(),valori.getTitoloEvento(),valori.getAula(),valori.getMacroarea(),valori.getEvento(),valori.getData(),valori.getOrario(),valori.getCorsoStudi());
		
		action=DaoPrenotazioni.PrenotaEventoUfficiale(prenotazione);
		
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
	public boolean verificaEventoController(PrenotazioneEventoBean valori) throws SQLException {
		
		PrenotazioneEvento prenotazione= new PrenotazioneEvento(valori.getTitolarePrenotazione(),valori.getTitoloEvento(),valori.getAula(),valori.getMacroarea(),valori.getEvento(),valori.getData(),valori.getOrario(),valori.getCorsoStudi());
		PrenotazioneEsame prenotazione2= new PrenotazioneEsame(valori.getAula(),valori.getData(),valori.getOrario());
		
		
		
		
		if(DaoPrenotazioni.verificaEvento(prenotazione)|| DaoPrenotazioni.verificaEsame(prenotazione2))
		return true;
		return false;
	}
	
	
}






