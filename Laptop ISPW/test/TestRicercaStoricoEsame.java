package test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import bean.PrenotazioneEsameBean;
import controller.ControllerProfessore;
import entity.PrenotazioneEsame;
import persistence.DaoPrenotazioni;

@RunWith(value = Parameterized.class )



public class TestRicercaStoricoEsame {



		private String titolarePrenotazione; 
		java.util.Date  dInizio=new  java.util.Date ();
		java.util.Date  dFine=new java.util.Date ();
		

			@Parameters
			public static Collection<Object[]> getParameters(){
		 
				return Arrays.asList(new Object[][] {
						
					{"Professore",Date.valueOf("2017-10-01"),Date.valueOf("2019-03-01")}, 
					{"Professore",Date.valueOf("2018-10-01"),Date.valueOf("2020-03-01")}
					
				});
			}
			
			public TestRicercaStoricoEsame(String TitolarePrenotazione,java.util.Date Inizio,java.util.Date Fine) {
				super();
				// TODO Auto-generated constructor stu
				this.dInizio=Inizio;
				this.dFine=Fine;
				this.titolarePrenotazione=TitolarePrenotazione;
				
			};
				
			
			@Test
			public  void verifica() throws Exception {
				 ArrayList<PrenotazioneEsameBean> risultato=new ArrayList<PrenotazioneEsameBean>();
				 ControllerProfessore istance=ControllerProfessore.getInstance();
					risultato=istance.visualizzaStoricoPrenotazioniEsame(dInizio,dFine,titolarePrenotazione); 
					System.out.println("Il controller ha ricercato i valori in maniera corretta");
					ArrayList<PrenotazioneEsame> value = new ArrayList<PrenotazioneEsame>();
					value=DaoPrenotazioni.findStoricoPrenotazioniEsame(dInizio,dFine,titolarePrenotazione);		
					System.out.println("Il Dao ha ricercato i valori in maniera corretta ");

					
					
					 
					 for (int i=0; i<value.size();i++) {
							assertEquals(risultato.get(i).getAppello(),value.get(i).getAppello());
							assertEquals(risultato.get(i).getAula().getNome(),value.get(i).getAula().getNome());
							assertEquals(risultato.get(i).getNomeCorso(),value.get(i).getNomeCorso());
							assertEquals(risultato.get(i).getData(),value.get(i).getData());
							assertEquals(risultato.get(i).getEvento(),value.get(i).getEvento());
							assertEquals(risultato.get(i).getSessione(),value.get(i).getSessione());
							assertEquals(risultato.get(i).getSeduta(),value.get(i).getSeduta());
							assertEquals(risultato.get(i).getTitolarePrenotazione(),value.get(i).getTitolarePrenotazione());
							assertEquals(risultato.get(i).getOrario(),value.get(i).getOrario());
							assertEquals(risultato.get(i).getMacroarea(),value.get(i).getMacroarea());
								
					 System.out.println("Abbiamo controllato i valori della "+" "+i+" "+" Prenotazione Esame tra Dao e controller");
					 
					 
					 
					 

					 }
					 System.out.println("Test eseguito in maniera corretta !");

				
				}

}
