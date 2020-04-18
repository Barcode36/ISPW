package thread;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import bean.AulaBean;

import bean.PrenotazioneEsameBean;
import bean.PrenotazioneEventoBean;
import bean.UtenteBean;
import controller.Controller;
import controller.ControllerProfessore;
import controller.ControllerSegreteria;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class EsempioThread implements Runnable {

	private int idThread;


	public EsempioThread(int n) {
	    setNumeroThread(n);
	  }
	    
	  public void setNumeroThread(int n) {
	    idThread=n;
	  }
	    
	  public int getNumeroThread() {
	    return idThread;
	  }
	    
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		  try {
			  
			  Random r= new Random();
			  UtenteBean u=UtenteRandomico(r);
			  System.out.println("Il thread n°"+idThread+" sta eseguendo una prenotazione con  "+u.getUserid() );
			  AulaBean aula = AulaRandomica(r);
				
			  if (u.getType().equals("Professore")) {
					System.out.println("Loggato come Professore");
		
					PrenotazioneEsameBean prenotazioneEsame = PrenotaEsameRandomico(r, u.getUserid());			
					try {
						PrenotazioneEsameBean invioPrenotazioneEsame= new PrenotazioneEsameBean();
						invioPrenotazioneEsame=ControllerProfessore.getInstance().RichiestaPrenotazioneEsame(aula, prenotazioneEsame);
						
						if(ControllerProfessore.getInstance().verificaEsameController(invioPrenotazioneEsame)) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Tentativo di Prenotazione fallito");
							alert.setHeaderText("Prenotazione Esame!");
							alert.setContentText("Prenotazione non andata a buon fine perche gia esistente per quella data fascia oraria e aula");
							alert.showAndWait();
							alert.close();
						}
						else {
						ControllerProfessore.getInstance().InvioEsamePrenotazione(invioPrenotazioneEsame);	
						System.out.println("Il thread n°"+idThread+"ha eseguito con successo la prenotazione di un evento");
						System.out.println("INFORMAZIONI PRENOTAZIONE:");
						System.out.println("Fascia Oraria:	"+invioPrenotazioneEsame.getOrario()+"");
						System.out.println("Sessione:	"+invioPrenotazioneEsame.getSessione()+"");
						System.out.println("Appello:	"+invioPrenotazioneEsame.getAppello()+"");
						System.out.println("Seduta:	"+invioPrenotazioneEsame.getSeduta()+"");
						System.out.println("Tipo Evento:	"+invioPrenotazioneEsame.getEvento()+"");
						System.out.println("Nome Corso:	"+invioPrenotazioneEsame.getNomeCorso()+"");
						System.out.println("Titolare Prenotazione:	"+invioPrenotazioneEsame.getTitolarePrenotazione()+"");						
						System.out.println("Macroarea:	"+invioPrenotazioneEsame.getMacroarea()+"");
						System.out.println("Aula:	"+invioPrenotazioneEsame.getAula().getNome()+"");
						System.out.println("Data:	"+invioPrenotazioneEsame.getData()+"");
					
						
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else if (u.getType().equals("Segreteria")) {
					System.out.println("Loggato come Segreteria");
					
					
					
					PrenotazioneEventoBean prenotazioneEvento = PrenotaEventoRandomico(r, u.getUserid());
					try {
						PrenotazioneEventoBean invioPrenotazioneEvento= new PrenotazioneEventoBean();
						invioPrenotazioneEvento=ControllerSegreteria.getInstance().RichiestaPrenotazioneEvento(aula, prenotazioneEvento);
						if(ControllerSegreteria.getInstance().verificaEventoController(invioPrenotazioneEvento)) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Tentativo di Prenotazione fallito");
							alert.setHeaderText("Prenotazione Evento!");
							alert.setContentText("Prenotazione non andata a buon fine perche gia esistente per quella data fascia oraria e aula");
							alert.showAndWait();
							alert.close();
						}
						else {
						ControllerSegreteria.getInstance().InvioEventoPrenotazione(invioPrenotazioneEvento);		
							System.out.println("Il thread n°"+idThread+"ha eseguito con successo la prenotazione di un evento");
							System.out.println("INFORMAZIONI PRENOTAZIONE:");
							System.out.println("Fascia Oraria:	"+invioPrenotazioneEvento.getOrario()+"");
							System.out.println("Corso Studi:	"+invioPrenotazioneEvento.getCorsoStudi()+"");
							System.out.println("Tipo Evento:	"+invioPrenotazioneEvento.getEvento()+"");
							System.out.println("Titolo:	"+invioPrenotazioneEvento.getTitoloEvento()+"");
							System.out.println("Titolare Prenotazione:	"+invioPrenotazioneEvento.getTitolarePrenotazione()+"");						
							System.out.println("Macroarea:	"+invioPrenotazioneEvento.getMacroarea()+"");
							System.out.println("Aula:	"+invioPrenotazioneEvento.getAula().getNome()+"");
							System.out.println("Data:	"+invioPrenotazioneEvento.getData()+"");
						
							
						
						
						}
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				} 

				else {
					
					System.out.println("Errore nel login");

					
				}
			  
		        Thread.sleep(2000);
		      }
		      catch (InterruptedException exc) {
		        System.out.println("Errore");
		      }
	}
	

		
		  private PrenotazioneEventoBean PrenotaEventoRandomico(Random r, String userid) {

				PrenotazioneEventoBean prenotazionebean = new PrenotazioneEventoBean();
				prenotazionebean.setTitolarePrenotazione(userid);
				
				entity.Evento[] e = entity.Evento.values();
				entity.Evento evento = e[r.nextInt(e.length)];
				
				prenotazionebean.setEvento(evento.toString());
				
				entity.FasciaOraria[] f = entity.FasciaOraria.values();
				entity.FasciaOraria fascia= f[r.nextInt(f.length)];
				
				prenotazionebean.setOrario(fascia.toString());
				
				entity.Macroarea [] m =entity.Macroarea.values();
				entity.Macroarea macroarea =m[r.nextInt(m.length)];
				
				prenotazionebean.setMacroarea((macroarea.toString()));
				
				String[] titolo = {"Evento1", "Evento2","Evento3","Evento4"};
				String[] corsoStudi = {"Informatica", "Medica","Gestionale","Meccanica"};
					int i=r.nextInt(titolo.length);
				prenotazionebean.setTitoloEvento(titolo[i]);				
				prenotazionebean.setCorsoStudi(corsoStudi[i]);
			
				
				
				
				int year =randBetween(2018, 2021);
				int month=randBetween(1,12);
				java.util.Date Dataprenotazione =  new java.util.Date();
				
				Dataprenotazione.setMonth(month);
			
				Dataprenotazione.setYear(year);
				if(month==2) {
				int day= randBetween(1,28);
				Dataprenotazione.setDate(day);
				}
				else if(month==4|| month==6|| month==9|| month==11) {
					int day= randBetween(1,30);
					Dataprenotazione.setDate(day);
					}
				else {
					
						int day= randBetween(1,31);
						Dataprenotazione.setDate(day);		
				}
				
				System.out.println(Dataprenotazione.toString());
				Dataprenotazione.setYear(Dataprenotazione.getYear()-1900);
			
				java.sql.Date attuale= new java.sql.Date(Dataprenotazione.getTime());
				attuale.setYear(attuale.getYear()-1900);

				if(attuale.getYear()<=2022   ) {
					if( attuale.getYear()>=2018) {
				
				prenotazionebean.setData(  attuale);
					}
					else {
						attuale.setYear(attuale.getYear()+1900);
						prenotazionebean.setData(  attuale);
					}
				}
				else {
					attuale.setYear(attuale.getYear()-1900);
					prenotazionebean.setData(  attuale);
				}
				return prenotazionebean;
	}


		  public static int randBetween(int start, int end) {
		        return start + (int)Math.round(Math.random() * (end - start));
		    }


		private PrenotazioneEsameBean PrenotaEsameRandomico(Random r, String userid) {
			PrenotazioneEsameBean prenotazionebean = new PrenotazioneEsameBean();
			prenotazionebean.setTitolarePrenotazione(userid);
			
			entity.Evento[] e = entity.Evento.values();
			entity.Evento evento = e[r.nextInt(e.length)];
			
			prenotazionebean.setEvento(evento.toString());
			
			entity.FasciaOraria[] f = entity.FasciaOraria.values();
			entity.FasciaOraria fascia= f[r.nextInt(f.length)];
			
			prenotazionebean.setOrario(fascia.toString());
			
			entity.Macroarea [] m =entity.Macroarea.values();
			entity.Macroarea macroarea =m[r.nextInt(m.length)];
			
			prenotazionebean.setMacroarea((macroarea.toString()));
		
			entity.Seduta[] s1 =entity.Seduta.values();
			entity.Seduta seduta= s1[r.nextInt(s1.length)];
			
			prenotazionebean.setSeduta(seduta.toString());
			
			entity.Sessione[] s2 =entity.Sessione.values();
			entity.Sessione sessione= s2[r.nextInt(s2.length)];
			
			prenotazionebean.setSessione(sessione.toString());
			String[] nomeCorso = {"Sistemi Operativi", "ISPW","IW","LAS"};
			
				int i=r.nextInt(nomeCorso.length);
			
			prenotazionebean.setNomeCorso(nomeCorso[i]);
			
			prenotazionebean.setAppello(randBetween(1,6));
			
			int year =randBetween(2018, 2021);
			int month=randBetween(1,12);
			java.util.Date Dataprenotazione =  new java.util.Date();
			
			Dataprenotazione.setMonth(month);
		
			Dataprenotazione.setYear(year);
			if(month==2) {
			int day= randBetween(1,28);
			Dataprenotazione.setDate(day);
			}
			else if(month==4|| month==6|| month==9|| month==11) {
				int day= randBetween(1,30);
				Dataprenotazione.setDate(day);
				}
			else {
				
					int day= randBetween(1,31);
					Dataprenotazione.setDate(day);		
			}
			System.out.println(Dataprenotazione.toString());
			Dataprenotazione.setYear(Dataprenotazione.getYear()-1900);
		

			java.sql.Date attuale= new java.sql.Date(Dataprenotazione.getTime());
			attuale.setYear(attuale.getYear()-1900);
			
			if(attuale.getYear()<=2022   ) {
				if( attuale.getYear()>=2018) {
			
			prenotazionebean.setData(  attuale);
				}
				else {
					attuale.setYear(attuale.getYear()+1900);
					prenotazionebean.setData(  attuale);
				}
			}
			else {
				attuale.setYear(attuale.getYear()-1900);
				prenotazionebean.setData(  attuale);
			}
			return prenotazionebean;
	}



		private AulaBean AulaRandomica(Random r) {
				AulaBean bean = new AulaBean();
				bean.setNumeroPosti((r.nextInt(750)));
				bean.setProiettore(r.nextInt(2));
				bean.setLavagna(r.nextBoolean());
				bean.setLavInterattiva(r.nextBoolean());
				bean.setPrese(r.nextBoolean());
				bean.setMicrofono(r.nextBoolean());
				bean.setEthernet(r.nextBoolean());
				
				entity.TipoAula[] t = entity.TipoAula.values();
				entity.TipoAula tipo = t[r.nextInt(t.length)];
				
				bean.setTipo(tipo.toString());
				
				entity.Laboratorio lab;
				if (tipo == entity.TipoAula.Convegni) {
					lab = null;
				} else {
					if (r.nextBoolean()) {
						entity.Laboratorio[] l = entity.Laboratorio.values();
						lab = l[r.nextInt(l.length)];
					} else {
						lab = null;
					}
				}
				if(lab!=null)
				bean.setLaboratorio(lab.toString());
				bean.setLaboratorio("");
				return bean;
	}



		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
			public UtenteBean UtenteRandomico(Random rand) {
				String[] id = {"Segretario", "Professore","Cantone","Marrese"};
				String[] p = {"s","p","p","s"};
				String[] type = {"Segreteria","Professore","Professore","Segreteria"};


				UtenteBean u= new UtenteBean();
				
				int i = rand.nextInt(4);
				u.setUserid(id[i]);
				u.setPassword(p[i]);
				u.setType(type[i]);
				try {
					Controller controllerFisico = Controller.getInstance();
					synchronized(controllerFisico) {
						
						UtenteBean bean = controllerFisico.login(u.getUserid(), u.getPassword());				
						return bean;
					}
				}catch(NullPointerException e) {
				
					System.out.println("Errore nell'accesso ");
				}
				return null;
				
			}
		
}
