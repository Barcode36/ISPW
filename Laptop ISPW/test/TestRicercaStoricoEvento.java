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


import bean.PrenotazioneEventoBean;
import controller.ControllerSegreteria;
import entity.PrenotazioneEvento;
import persistence.DaoPrenotazioni;

@RunWith(value = Parameterized.class )


public class TestRicercaStoricoEvento {


		private String titolarePrenotazione; 
		java.util.Date  dInizio=new  java.util.Date ();
		java.util.Date  dFine=new java.util.Date ();
		

			@Parameters
			public static Collection<Object[]> getParameters(){
		 
				return Arrays.asList(new Object[][] {
						
					{"Segretario",Date.valueOf("2017-10-01"),Date.valueOf("2019-03-01")}, 
					{"Segretario",Date.valueOf("2018-10-01"),Date.valueOf("2020-03-01")}
					
				});
			}
			
			public TestRicercaStoricoEvento(String TitolarePrenotazione,java.util.Date Inizio,java.util.Date Fine) {
				super();
				// TODO Auto-generated constructor stu
				this.dInizio=Inizio;
				this.dFine=Fine;
				this.titolarePrenotazione=TitolarePrenotazione;
				
			};
				
			
			@Test
			public  void verifica() throws Exception {
						 
			 
				ArrayList<PrenotazioneEventoBean> risultato=new ArrayList<PrenotazioneEventoBean>();
				ControllerSegreteria instance=ControllerSegreteria.getInstance();
				risultato=instance.visualizzaStoricoPrenotazioniEvento(dInizio,dFine,titolarePrenotazione); 
				System.out.println("Il controller ha ricercato i valori in maniera corretta");

				ArrayList<PrenotazioneEvento> value = new ArrayList<PrenotazioneEvento>();
				value=DaoPrenotazioni.findStoricoPrenotazioniEvento(dInizio,dFine,titolarePrenotazione);
				System.out.println("Il Dao ha ricercato i valori in maniera corretta");		
				
				for (int i=0; i<value.size();i++) {

						assertEquals(risultato.get(i).getAula().getNome(),value.get(i).getAula().getNome());
						assertEquals(risultato.get(i).getCorsoStudi(),value.get(i).getCorsoStudi());
						assertEquals(risultato.get(i).getData(),value.get(i).getData());
						assertEquals(risultato.get(i).getEvento(),value.get(i).getEvento());

						assertEquals(risultato.get(i).getTitoloEvento(),value.get(i).getTitoloEvento());
						assertEquals(risultato.get(i).getTitolarePrenotazione(),value.get(i).getTitolarePrenotazione());
						assertEquals(risultato.get(i).getOrario(),value.get(i).getOrario());
						assertEquals(risultato.get(i).getMacroarea(),value.get(i).getMacroarea());
							
						System.out.println("Abbiamo controllato i valori della "+" "+i+" "+" PrenotazioniEvento tra Dao e controller");
				 
				 }
				System.out.println("Test eseguito in maniera corretta!");

			
			}
	}

