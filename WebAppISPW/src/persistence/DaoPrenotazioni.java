
package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Aula;
import entity.Prenotazione;
import entity.PrenotazioneEsame;
import entity.PrenotazioneEvento;

public class DaoPrenotazioni {
	
	
	/*
	 * 1) Caricare il Driver
	 * 2) Aprire una Connessione al Database
	 * 3) Creare un Oggetto Statement
	 * 4) Interagire con il Database
	 * 5) Gestire e visualizzare i risultati ottenuti dalle ResultSet.
	 */
	private final  static String driverName="org.postgresql.Driver"; 	//classe del driver
	private final static String  databaseName="daoISPW";
	private final  static String id="postgres";
	private final  static String password="postgres";
	private final  static String url="jdbc:postgresql://localhost:5432/"+databaseName+"";
	
	/*
	 * Apre la connessione
	 */
	public static Connection getConn() {
		
		Connection conn=null;
		try {
			Class.forName(driverName);
		    	  
			try {
				conn=DriverManager.getConnection(url,id,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
								
				        
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return conn;
	}
		/*
		 * Chiude la connessione      
		 */
		      
	public static void closeConn(Connection conn){
		try {
			if (conn != null)
				conn.close();
		}catch (SQLException se) {
			se.printStackTrace();
		}
	}
	/*
	 * Prende tutte le prenotazioni di esami che ci sono da oggi, data odierna ,in poi all'interno del database senza fare alcun tipo di controllo sull'orario
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<PrenotazioneEsame> findPrenotazioniEsame(java.util.Date dataOdierna,String userid) throws SQLException {
		PreparedStatement stmt = null;
		ArrayList<PrenotazioneEsame> risultato = new ArrayList<PrenotazioneEsame>();
		Connection conn=getConn();
		java.sql.Date attuale= new java.sql.Date(dataOdierna.getTime());
		attuale.setYear(attuale.getYear()-1900);
		
	    	
		String sql="select * from prenotazioniesame join utenti on prenotazioniesame.titolare=utenti.userid where prenotazioniesame.datascadenza>=? and prenotazioniesame.titolare=?";
		stmt = conn.prepareStatement(sql);
		stmt.setDate(1,  attuale);
		stmt.setString(2,userid);
		
		        
		ResultSet rs=stmt.executeQuery();
		while (rs.next()) {
		        	

		       		Aula aulaPren= new Aula(rs.getString("aula"));
		 // String evento, java.util.Date data, String orario,String seduta,int appello,String NomeCorso,String sessione) {

		        	PrenotazioneEsame arg0= new PrenotazioneEsame(rs.getString("titolare"),aulaPren,rs.getString("macroarea"),rs.getString("evento"),rs.getDate("datascadenza"),rs.getString("orario"),rs.getString("seduta"),rs.getInt("appello"),rs.getString("nomecorso"),rs.getString("sessione")	
		    		        );
		        	
		        
		        	
		        
		        		risultato.add(arg0);
		        	
		}
		rs.close();
		closeConn(conn);
		stmt.close();
		return risultato;     
		
	}
		    
	


	/*
	 * Prende tutte le prenotazioni di eventi inclusi nell'anno accademico incluse nel database
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<PrenotazioneEvento> findStoricoPrenotazioniEvento(java.util.Date i,java.util.Date f,String userid) throws SQLException {
		
		PreparedStatement stmt = null;
		ArrayList<PrenotazioneEvento> risultato = new ArrayList<PrenotazioneEvento>();
		Connection conn=getConn();
		
		java.sql.Date inizio= new java.sql.Date(i.getTime());
		inizio.setYear(inizio.getYear()-1900);
		    
		java.sql.Date fine= new java.sql.Date(f.getTime());
		fine.setYear(fine.getYear()-1900);
		    
		String sql="select * from prenotazionievento join utenti on prenotazionievento.titolare=utenti.userid where prenotazionievento.datascadenza<=? and prenotazionievento.datascadenza>=? and prenotazionievento.titolare=?";	
		    
			 
		stmt = conn.prepareStatement(sql);
		stmt.setDate(1,  fine);
		stmt.setDate(2, inizio);
		stmt.setString(3,userid);
		ResultSet rs=stmt.executeQuery();
		while (rs.next()) {
		        	
		        	Aula aulaPren= new Aula(rs.getString("aula"));
		        	PrenotazioneEvento arg0= new PrenotazioneEvento(rs.getString("titolare"),rs.getString("titolo"),aulaPren,rs.getString("macroarea"),rs.getString("evento"),rs.getDate("datascadenza"),rs.getString("orario"),rs.getString("corsostudi")); 
			    		        
			        	
		        	
		        	risultato.add(arg0);
		     
		        	}
		rs.close();
		closeConn(conn);
		stmt.close();
		
		return risultato;     
	}
	/*
	 * Attraverso questo metodo prendiamo prendiamo tutte le prenotazioni di un intero anno accademico
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<PrenotazioneEsame> findStoricoPrenotazioniEsame(java.util.Date i,java.util.Date f,String userid) throws SQLException {
		
		PreparedStatement stmt = null;
		ArrayList<PrenotazioneEsame> risultato = new ArrayList<PrenotazioneEsame>();
	    Connection conn=getConn();
	    //passo importante.. perchè dobbiamo passare per effettuare bene la query le informazioni ad un oggetto di tipo java.sql.Date
	    
	    java.sql.Date inizio= new java.sql.Date(i.getTime());
	    //tale oggetto tuttavia modifica il valore dell'anno settando in automatico un anno incrementato di 1900 rispetto al precedente
	    inizio.setYear(inizio.getYear()-1900);
	    
	    java.sql.Date fine= new java.sql.Date(f.getTime());
	    fine.setYear(fine.getYear()-1900);
	    
	    String sql="select * from prenotazioniesame join utenti on prenotazioniesame.titolare=utenti.userid where prenotazioniesame.datascadenza<=? and prenotazioniesame.datascadenza>=? and prenotazioniesame.titolare=?";	
	    
		 
	    stmt = conn.prepareStatement(sql);
	    stmt.setDate(1,  fine);
	    stmt.setDate(2, inizio);
	    stmt.setString(3,userid);
	    ResultSet rs=stmt.executeQuery();
	    while (rs.next()) {
	        	
	        	Aula aulaPren= new Aula(rs.getString("aula"));
	        	PrenotazioneEsame arg0= new PrenotazioneEsame(rs.getString("titolare"),aulaPren,rs.getString("macroarea"),rs.getString("evento"),rs.getDate("datascadenza"),rs.getString("orario"),rs.getString("seduta"),rs.getInt("appello"),rs.getString("nomecorso"),rs.getString("sessione")	
	    		        );
	        	
	        	risultato.add(arg0);
	     
	        }
	        rs.close();
	        closeConn(conn);
	        stmt.close();

	        return risultato;     
	}
	

	/*
	 * Attraverso questo metodo prendiamo prendiamo tutte le prenotazioni che vanno dalla data corrente in poi senza alcun tipo di controllo sull'ora
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<PrenotazioneEvento> findPrenotazioniAttiveEvento(java.util.Date  dataOdierna,String userid) throws SQLException {
			
		PreparedStatement stmt = null;
		ArrayList<PrenotazioneEvento> risultato = new ArrayList<PrenotazioneEvento>();
		Connection conn=getConn();
		java.sql.Date attuale= new java.sql.Date(dataOdierna.getTime());
		attuale.setYear(attuale.getYear()-1900);
		
	    	
		String sql="select * from prenotazionievento join utenti on prenotazionievento.titolare=utenti.userid where prenotazionievento.datascadenza>=? and prenotazionievento.titolare=?";
		stmt = conn.prepareStatement(sql);
		stmt.setDate(1,  attuale);
		stmt.setString(2, userid);
		ResultSet rs=stmt.executeQuery();
		while (rs.next()) {
	        	
					Aula aulaPren= new Aula(rs.getString("aula"));
					
					
					PrenotazioneEvento arg0= new PrenotazioneEvento(rs.getString("titolare"),rs.getString("titolo"),aulaPren,rs.getString("macroarea"),rs.getString("evento"),rs.getDate("datascadenza"),rs.getString("orario"),rs.getString("corsostudi")); 
				    
	risultato.add(arg0);
		        	
		}
		rs.close();
		closeConn(conn);
		stmt.close();
		return risultato;     
	}


	/*
	 * Controlla quali sono le aule con tali caratterisiche precedentemente definite e passate in un oggetto Aula dato per input
	 */
	public static ArrayList<String> findAuleEvento(Aula aula) throws SQLException {
		
		PreparedStatement stmt = null;
		ArrayList<String> risultato = new ArrayList<String>();
		

		if(aula.getLaboratorio()==null) {
			aula.setLaboratorio("");
		}
		Connection conn=getConn();

		String sql="select * from aula where tipo=? and numeroposti>=? or laboratorio=?  or proiettore>=? or microfono=? or "
						+ "lavagna=? or lavinterattiva=? or prese=? or ethernet=?";
				

				
		stmt = conn.prepareStatement(sql);
				

		stmt.setString(1, aula.getTipo().toString());
		stmt.setString(3,aula.getLaboratorio().toString());
		stmt.setInt(2, aula.getNumeroPosti());
		stmt.setInt(4, aula.getProiettore());
		stmt.setBoolean(5, aula.isLavagna());
		stmt.setBoolean(6, aula.isMicrofono());
		stmt.setBoolean(7, aula.isLavInterattiva());
		stmt.setBoolean(8, aula.isPrese());
		stmt.setBoolean(9, aula.isEthernet());
				
		ResultSet rs=stmt.executeQuery();
		while (rs.next()) {
	        	   	
					String nome= (rs.getString("nome"));					
					risultato.add(nome);

				}
		rs.close();
	        
		closeConn(conn);
		stmt.close();
		return risultato;     
	}


	/*
	 * Controlla quali sono le aule con tali caratterisiche precedentemente definite e passate in un oggetto Aula dato per input
	 */
	public static ArrayList<String> findAuleEsame(Aula aula) throws SQLException {
		
		PreparedStatement stmt = null;
		ArrayList<String> risultato = new ArrayList<String>();
		Connection conn=getConn();
		String sql="select * from aula where tipo=?  and numeroposti>=? or laboratorio=? or proiettore>=? or microfono=? or "
						+ "lavagna=? or lavinterattiva=? or prese=? or ethernet=?";
				

				
		stmt = conn.prepareStatement(sql);
				

		stmt.setString(1, aula.getTipo().toString());
		stmt.setString(3,aula.getLaboratorio().toString());
		stmt.setInt(2, aula.getNumeroPosti());
		stmt.setInt(4, aula.getProiettore());
		stmt.setBoolean(6, aula.isLavagna());
		stmt.setBoolean(5, aula.isMicrofono());
		stmt.setBoolean(7, aula.isLavInterattiva());
		stmt.setBoolean(8, aula.isPrese());
		stmt.setBoolean(9, aula.isEthernet());
		
				   
		ResultSet rs=stmt.executeQuery();
		while (rs.next()) {  	
			
					String nome= (rs.getString("nome"));
					risultato.add(nome);
				
		}
		closeConn(conn);
		stmt.close();
		return risultato;     
	}
	/*
	 * Controlla se esiste gia quella prenotazione nel database
	 */

	public static PrenotazioneEsame findPrenotaEsame(String aula,String orario,java.sql.Date data) throws SQLException {
	
		PreparedStatement stmt = null;
		Connection conn=getConn();
		PrenotazioneEsame ritorno= new PrenotazioneEsame() ;
		String sql="select * from prenotazioniesame where prenotazioniesame.aula=? and prenotazioniesame.orario=? and prenotazioniesame.datascadenza=?;";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, aula);
		stmt.setString(2,orario);
		stmt.setDate(3, data);
		ResultSet rs=stmt.executeQuery();
		if (rs.next()) {
			rs.close();
			return null;
		}
		else {
			rs.close();
			
			return  ritorno;
			}
	}

	/*
	 * Controlla se esiste gia quella prenotazione nel database
	 */
	public static PrenotazioneEvento findPrenotazioniEventi(String aula,String orario,java.sql.Date data) throws  SQLException {
	
		PreparedStatement stmt = null;
	    Connection conn=getConn();
	    PrenotazioneEvento ritorno= new PrenotazioneEvento();
		//"and prenotazionievento.datascadenza="+data+
	    String sql="select * from prenotazionievento where prenotazionievento.aula=? and prenotazionievento.orario=? and prenotazionievento.datascadenza=?;";
	    stmt = conn.prepareStatement(sql);
	    stmt.setString(1, aula);
	    stmt.setString(2,orario);
	    stmt.setDate(3, data);
	    ResultSet rs=stmt.executeQuery();
	    if (rs.next()) {
	    	rs.close();
	    	return null;
	    }
	    else {
				
	    	rs.close();
	    	return ritorno;
        
	    }
			
	}

	/*
	 * Esegue una prenotazione di un oggetto di tipo PrenotazioneEsame
	 */
	public static boolean PrenotaEsameUfficiale(PrenotazioneEsame value) {
		
		PreparedStatement stmt = null;
	    Connection conn=getConn();
	    boolean action=false;
		try {
			String sql="INSERT INTO prenotazioniesame (aula,datascadenza,orario,titolare,evento,macroarea,sessione,seduta,appello,nomecorso) values(?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,value.getAula().getNome());
			stmt.setDate(2, (Date) value.getData());
			stmt.setString(3,value.getOrario());
			stmt.setString(7,value.getSessione());
			stmt.setString(4,value.getTitolarePrenotazione());
			stmt.setString(5,value.getEvento());
			stmt.setString(6,value.getMacroarea());
			stmt.setString(8,value.getSeduta());
			stmt.setInt(9,value.getAppello());
			stmt.setString(10, value.getNomeCorso());
						
			action=stmt.execute();    	   	    	           
			stmt.close();
			conn.close();
			return action;
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return action;
		
	}


	/*
	 * Esegue una prenotazione di un oggetto di tipo PrenotazioneEvento
	 */
	
	public static boolean PrenotaEventoUfficiale(PrenotazioneEvento value) {
		
		PreparedStatement stmt = null;
	    Connection conn=getConn();
	    boolean action = false;
		try {
			
			String sql="INSERT INTO prenotazionievento (aula,datascadenza,orario,titolo,titolare,evento,macroarea,corsostudi) values(?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,value.getAula().getNome());
			stmt.setDate(2, (Date) value.getData());
			stmt.setString(3,value.getOrario());
			stmt.setString(4,value.getTitoloEvento());
			stmt.setString(5,value.getTitolarePrenotazione());
			stmt.setString(6,value.getEvento());
			stmt.setString(7,value.getMacroarea());
			stmt.setString(8,value.getCorsoStudi());
			action=stmt.execute();       	   	
			stmt.close();
			conn.close();
			return action;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return action;
	
	}





/*
 * Usato per verificare se è possibile per quella data ,datascadenza e orario una prenotazione di un certo evento. 
 */
	

	public static boolean verificaEvento(PrenotazioneEvento value) throws SQLException {
	
		Connection conn=getConn();
		PreparedStatement stmt = null;
		boolean risultato = false;
    


    	
        stmt = conn.prepareStatement("SELECT * FROM prenotazionievento where aula=? and datascadenza=? and orario=? ");
        
        stmt.setString(1,value.getAula().getNome());
		stmt.setDate(2, (Date) value.getData());
		stmt.setString(3,value.getOrario());
		
        ResultSet rs=stmt.executeQuery();
        
        risultato= (rs.next());
     
        
        rs.close();
        
        closeConn(conn);

        return risultato;
	}



/*
 * Usato per verificare se è possibile per quella data ,datascadenza e orario una prenotazione di un certo esame. 
 */

	public static boolean verificaEsame(PrenotazioneEsame value) throws SQLException {
	
		Connection conn=getConn();
		PreparedStatement stmt = null;
		boolean risultato = false;
    


    	
        stmt = conn.prepareStatement("SELECT * FROM prenotazioniesame where aula=? and datascadenza=? and orario=? ");
        
        stmt.setString(1,value.getAula().getNome());
		stmt.setDate(2, (Date) value.getData());
		stmt.setString(3,value.getOrario());
		
        ResultSet rs=stmt.executeQuery();
        
        risultato= (rs.next());
     
        
        rs.close();
        
        closeConn(conn);

        return risultato;
	}


}
