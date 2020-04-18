
package entity;

import java.util.Date;

import entity.Aula;
import entity.Prenotazione;

public class PrenotazioneEsame extends Prenotazione{
	
	private String sessione;
	private int appello;
	private String seduta;
	private String nomeCorso;
	
	public String getSessione() {
		return sessione;
	}
	public void setSessione(String sessione) {
		this.sessione = sessione;
	}
	public int getAppello() {
		return appello;
	}
	public void setAppello(int appello) {
		this.appello = appello;
	}
	public String getSeduta() {
		return seduta;
	}
	public void setSeduta(String seduta) {
		this.seduta = seduta;
	}
	public String getNomeCorso() {
		return nomeCorso;
	}
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}
	
	public PrenotazioneEsame(String TitolarePrenotazione,Aula aula, String macroarea, String evento, java.util.Date data, String orario,String seduta,int appello,String NomeCorso,String sessione) {

		super(TitolarePrenotazione, aula, macroarea, evento, data, orario);
		/*super.aula=aula;
		super.titolarePrenotazione=TitolarePrenotazione;
		super.orario=orario;
		super.evento=evento;
		super.macroarea=macroarea;
		*/
		this.seduta = seduta;
		this.appello=appello;
		this.nomeCorso=NomeCorso;
		this.sessione=sessione;
	}
	public PrenotazioneEsame( Aula aula, Date data,
			String orario) {
		super( aula,  (java.sql.Date) data, orario);
		// TODO Auto-generated constructor stub
	}
	
	public PrenotazioneEsame(String titolarePrenotazione, Aula aula, String macroarea, String evento, Date data,
			String orario) {
		super(titolarePrenotazione, aula, macroarea, evento, data, orario);
		// TODO Auto-generated constructor stub
	}
	
		
		// TODO Auto-generated constructor stub
	
	public PrenotazioneEsame() {}}
