package bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class PrenotazioneEventoBean extends PrenotazioneBean{
	
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
	public StringProperty getCorsoStudiProperty(){
		StringProperty corsoStudi =new SimpleStringProperty((this.corsoStudi));
		return corsoStudi;}

	public StringProperty getAulaProperty() {
		StringProperty aula=new SimpleStringProperty(String.valueOf(super.getAula().getNome()));
		return aula;
		}
	public StringProperty getTitoloEventoProperty(){
		StringProperty titolo =new SimpleStringProperty((this.titoloEvento));
		return titolo;
		}
	public StringProperty getFasciaOrariaProperty(){
		StringProperty fasciaOraria=new SimpleStringProperty(String.valueOf(super.getOrario()));
		return fasciaOraria ;
		}
	public StringProperty getDataProperty(){
		StringProperty Data=new SimpleStringProperty(String.valueOf(super.getData()));
		return Data;
		}
	public StringProperty getTitolareProperty(){
		StringProperty titolare=new SimpleStringProperty((super.getTitolarePrenotazione()));
		return titolare;
		}
	public StringProperty getMacroareaProperty(){
		StringProperty macroarea=new SimpleStringProperty(String.valueOf(super.getMacroarea()));
		return macroarea ;
		}
	public StringProperty getEventoProperty(){
		StringProperty Evento=new SimpleStringProperty(String.valueOf(super.getEvento()));
		return Evento;
		}
}
