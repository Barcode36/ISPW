
package entity;

import java.sql.Date;

import entity.Aula;
import entity.Prenotazione;

public class PrenotazioneEvento extends Prenotazione {
	private String titoloEvento;
	private String corsoStudi;

	public String getCorsoStudi() {
		return corsoStudi;
	}

	public void setCorsoStudi(String corsoStudi) {
		this.corsoStudi = corsoStudi;
	}
	
	public String getTitoloEvento() {
		return titoloEvento;
	}

	public void setTitoloEvento(String titoloEvento) {
		this.titoloEvento = titoloEvento;
	}

	public PrenotazioneEvento(String TitolarePrenotazione,String titoloEvento,Aula aula,String macroarea,String evento,Date data,String orario,String CorsoStudi) {

		super(TitolarePrenotazione, aula, macroarea, evento, data, orario);
		/*super.aula=aula;
		super.titolarePrenotazione=TitolarePrenotazione;
		super.orario=orario;
		super.evento=evento;
		super.macroarea=macroarea;
	*/	this.titoloEvento = titoloEvento;
		this.corsoStudi=CorsoStudi;
	}
	public PrenotazioneEvento(String titolarePrenotazione, Aula aula, String macroarea, String evento, Date data,
			String orario) {
		super(titolarePrenotazione, aula, macroarea, evento, data, orario);
		// TODO Auto-generated constructor stub
	}
	

	public PrenotazioneEvento( Aula aula, Date data,
			String orario) {
		super( aula,  data, orario);
		// TODO Auto-generated constructor stub
	}
	public PrenotazioneEvento() {}
	

	
}
