
package entity;

import java.util.Date;

import entity.Aula;

public class Prenotazione {

	protected  String titolarePrenotazione; 
	protected  Aula aula;
	protected  String macroarea;
	protected  String evento;
	protected  java.util.Date  data;
	protected  String orario;
	
	

	public String getTitolarePrenotazione() {
		return titolarePrenotazione;
	}
	public void setTitolarePrenotazione(String titolarePrenotazione) {
		this.titolarePrenotazione = titolarePrenotazione;
	}
	public Aula getAula() {
		return aula;
	}
	
	public String getMacroarea() {
		return macroarea;
	}
	
	public String getEvento() {
		return evento;

	}
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public String getOrario() {
		return orario;
	}
	public void setOrario(String orario) {
		this.orario = orario;
	}
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	public void setMacroarea(String macroarea) {
		this.macroarea = macroarea;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public Prenotazione() {}
	public Prenotazione(String titolarePrenotazione, Aula aula, String macroarea, String evento, Date data,
			String orario) {
		super();
		this.titolarePrenotazione = titolarePrenotazione;
		this.aula = aula;
		this.macroarea = macroarea;
		this.evento = evento;
		this.data = data;
		this.orario = orario;
	}
	public Prenotazione(Aula aula2, java.sql.Date data2, String orario2) {
		// TODO Auto-generated constructor stub
		this.aula = aula2;
		this.data = data2;
		this.orario = orario2;
	}
	
	
}