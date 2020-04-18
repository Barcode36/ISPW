package bean;

import java.sql.Date;

import entity.Aula;


public class PrenotazioneBean {

	private String titolarePrenotazione;
	private Aula aula;
	private String macroarea;
	private String evento;
	private Date  data;
	private String orario;
	
	
	
	public String getTitolarePrenotazione() {
		return titolarePrenotazione;
	}
	public void setTitolarePrenotazione(String titolarePrenotazione) {
		this.titolarePrenotazione = titolarePrenotazione;
	}
	public Aula getAula() {
		return aula;
	}
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	public String getMacroarea() {
		return macroarea;
	}
	public void setMacroarea(String macroarea) {
		this.macroarea = macroarea;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getOrario() {
		return orario;
	}
	public void setOrario(String orario) {
		this.orario = orario;
	}

	
}
