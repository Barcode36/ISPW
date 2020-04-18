
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Utente;
import exception.UtenteEsistenteException;

public class DaoUtente {
	
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
	 * Verificare l'utente se esiste all'interno del database e in caso affermativo settare tutte le 
	 * informazioni ad un nuovo oggetto di tipo Utente che ritornerà al controller
	 */
    public static Utente findUtente(String inputid,String inputpassword) {
    	Utente u=null;
    	Connection con=null;
	    Statement stm=null;
	
	    try {
	    	Class.forName(driverName);
	    }
		catch (ClassNotFoundException e) 
		{
		    // gestione dell'eccezione 
			e.printStackTrace();
		}
	    try {
	    	con=DriverManager.getConnection(url,id,password);
	    	stm=con.createStatement();
	    	String sql="SELECT * FROM \"utenti\" WHERE \"userid\"='" +inputid+ "' AND \"password\" ='"+inputpassword+"';";
	    	ResultSet rs=stm.executeQuery(sql);
	    	if(rs.next()) {
	    		String Type=rs.getString("type");
		
	    		u=new Utente(rs.getString("userid"),rs.getString("password"),Type);
	    	}
	    	if(con != null)
	    		con.close();
	    	if(stm != null)
	    		stm.close();
 	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
			return u;	
    }
    /*
     * metodo non usato nella parte di codice ma utile nel caso di qualche test per aggiungere un utente al database
     */
    
    public static void aggiungiUtente(String Password,String UserId,String Type) throws  SQLException {
    	try{
    		Utente u=new Utente(UserId,Password, Type);
    		if (!UtenteDisponibile(u.getUserid(),u.getPassword())) {
    			throw new UtenteEsistenteException();
    		}	
    		else {
    			Connection con=null;
    		    Statement stm=null;
    		    try {
    				Class.forName(driverName);
    			}
    				catch (ClassNotFoundException e) 
    				{
    				    // gestione dell'eccezione 
    					e.printStackTrace();
    				}
    		    try {
    				con=DriverManager.getConnection(url,id,password);
    				stm=con.createStatement();
    				
    				String sql="INSERT INTO utenti (userid, password, type)" + "VALUES ('"+u.getUserid()+"', '"+u.getPassword()+"','"+u.getType()+"')"; 
       				
    				stm.executeUpdate(sql);       
                	stm.close();
                	con.close();
            	} catch (SQLException e) {
            		e.printStackTrace();
            	}
            }
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /*
     *metodo non usato nella parte di codice ma utile nel caso di qualche test per aggiungere un utente al database
     */
    public static void deleteUtente(String Password,String UserId,String Type) throws UtenteEsistenteException, SQLException {
    	try{
    		Utente u=new Utente(UserId,Password,Type); 	    
    		if (!UtenteDisponibile(u.getUserid(),u.getPassword())) {
    			throw new UtenteEsistenteException();
    		}
    		else {
    			Connection con=null;

    			PreparedStatement stm = null;
    		    try {
    				Class.forName(driverName);
    			}
    				catch (ClassNotFoundException e) 
    				{
    				    // gestione dell'eccezione 
    					e.printStackTrace();
    				}
    		    try {
    				con=DriverManager.getConnection(url,id,password);
    				
    				String sql="delete from utenti  where userid=? and  password=? and type=?"; 
    				stm=con.prepareStatement(sql);
    				stm.setString(1,u.getUserid());
    				stm.setString(2, u.getPassword());
    				stm.setString(3, u.getType());
    	    		
    				stm.execute();
                	stm.close();
                	con.close();
            	} catch (SQLException e) {
            		e.printStackTrace();
            	}
            }
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /*
    *metodo  usato nell'aggiungiUtente per verificare se esiste gia un utente con quell'id o quella password on cui eventualmente loggarsi
    *   */
    public static boolean UtenteDisponibile(String inputid,String inputpassword) throws exception.UtenteEsistenteException, SQLException {
    	Utente u=null;
    	Connection con=null;
	    Statement stm=null;
	
	    try {
	    	Class.forName(driverName);
	    }
	    catch (ClassNotFoundException e) 
		{
		    // gestione dell'eccezione 
			e.printStackTrace();
		}
	    try {
	    	con=DriverManager.getConnection(url,id,password);
	    	stm=con.createStatement();
	    	
	    	String sql="SELECT * FROM \"utenti\" WHERE \"userid\"='" +inputid+"';";
	    	ResultSet rs=stm.executeQuery(sql);
	    	while(rs.next()) {
	    		u=new Utente(rs.getString("userid"),rs.getString("password"));
	    		if( inputid==u.getUserid())
	    			return false;
	    	
	    	}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    if(con != null)
		 con.close();
		if(stm != null)
		 stm.close();
		
		
		return true;
    
    }
 
   
    
}
