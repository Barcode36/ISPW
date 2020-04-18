package bean;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class PrenotazioneEsameBean extends PrenotazioneBean{
	
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
	public StringProperty getAulaProperty() {
		StringProperty aula=new SimpleStringProperty(String.valueOf(super.getAula().getNome()));
		return aula;
		}
	public StringProperty getAppelloProperty(){
		StringProperty appello=new SimpleStringProperty(String.valueOf(this.appello));
		return appello;
		}
	public StringProperty getSessioneProperty(){
		StringProperty sessione=new SimpleStringProperty(String.valueOf(this.sessione));
		return sessione;
		}
	public StringProperty getNomeCorsoProperty(){
		StringProperty NomeCorso=new SimpleStringProperty((this.nomeCorso));
		return NomeCorso ;
		}
	public StringProperty getSedutaProperty(){
		StringProperty seduta =new SimpleStringProperty(String.valueOf(this.seduta));
		return seduta;
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
