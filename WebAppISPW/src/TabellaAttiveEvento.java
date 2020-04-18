

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PrenotazioneEsameBean;
import bean.PrenotazioneEventoBean;
import bean.UtenteBean;
import controller.ControllerProfessore;
import controller.ControllerSegreteria;

/**
 * Servlet implementation class TabellaAttiveEvento
 */
@WebServlet("/TabellaAttiveEvento")
public class TabellaAttiveEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public  ArrayList<PrenotazioneEventoBean> Eventi= new ArrayList<PrenotazioneEventoBean>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TabellaAttiveEvento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.util.Date attuale= new java.util.Date();
		//java.sql.Date  today =  new java.sql.Date( calendar.getTime());
		 
			 
	
		
		int  ore=attuale.getHours();
		
		int mese=attuale.getMonth();
		java.util.Date data = new Date(Integer.valueOf(attuale.toLocaleString().substring(6,10)),mese,attuale.getDate());
		
		UtenteBean bean=new UtenteBean();
		bean=LoginCheck.userBean;
					
		if(request.getParameter("send")!=null) {		
				ControllerProfessore.getInstance();
				try {
				
					Eventi=ControllerSegreteria.visualizzaPrenotazioniAttiveEvents(data,bean.getUserid(),ore); 
				
					response.setContentType("text/html");
					PrintWriter out =response.getWriter();
					
					String str="<html> <head>" + 
							"      <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\r\n" + 
							"      <link type=\"text/css\" rel=\"stylesheet\" href=\"css/materialize.min.css\"  media=\"screen,projection\"/>\r\n" + 
							"      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n" + 
							"    </head>" + 
							
							"    <body>" + 
							"      <script type=\"text/javascript\" src=\"js/materialize.min.js\"></script> "
							+ "<div class=card-panel  brown align=center><h3> VISUALIZZA  PRENOTAZIONI ATTIVE EVENTO : </h3></div>\r\n" + 							
							 " <div class=container>";
					str+=("<table>   "
						+ "     <thead>"
						+ "          <tr>" 
						+"              <th> Titolare</th>"  
						+"              <th>Aula</th>"  
						+"              <th>Orario</th>" 
						+"              <th>Macroarea</th>"  
						+"              <th>Data</th>"  
						+"              <th>Evento</th>"
						+"              <th> Titolo Evento</th>"  
						+"              <th>Corso Studi</th>"  				
						+"          </tr>" 
						+"        </thead>"
						+"<tbody>"  );
					for(PrenotazioneEventoBean f : Eventi) {
						
						PrenotazioneEventoBean bean1=new PrenotazioneEventoBean();
					
						bean1.setTitoloEvento(f.getTitoloEvento());
						bean1.setAula(f.getAula());
						bean1.setOrario(f.getOrario());
						bean1.setMacroarea(f.getMacroarea());
						bean1.setCorsoStudi(f.getCorsoStudi());
						bean1.setEvento(f.getEvento());
						bean1.setData((Date) f.getData());
						bean1.setTitolarePrenotazione(f.getTitolarePrenotazione());
						str+=
							 "            <tr>" + 
							 "            <td>"+ bean1.getTitolarePrenotazione()+"</td>" + 	
							 "            <td>"+bean1.getAula().getNome()+"</td>" + 
							 "            <td>"+bean1.getOrario() +"</td>" + 
							 "            <td>"+ bean1.getMacroarea()+"</td>" + 
							 "            <td>"+ bean1.getData()+"</td>" + 
							 "            <td>"+ bean1.getEvento()+"</td>" + 
							 "            <td>"+ bean1.getTitoloEvento()+"</td>" + 
							 "            <td>"+bean1.getCorsoStudi() +"</td>" + 
							 "            </tr>" ;

					}
					str+=("  </tbody></table> ");
					str+=(" </button>  \r\n" + 
							"     \r\n" + 
							"        <button class=\" large btn waves-effect  green darken-4\" onClick=\"location.href='HomepageSegretario.jsp'\r\n" + 
							"        \"type=\"submit\"  value=\"homepageS\" name=\"homepageS\">HomePage Segretario\r\n" + 
							"    <i class=\"material-icons right \">menu</i>\r\n" + 
							"    \r\n" + 
							"  </button>\r\n" + 
							"            \r\n" + 
							"          <button class=\" large btn waves-effect  green darken-4\" onClick=\"location.href='index.jsp'\r\n" + 
							"          \"type=\"submit\"  value=\"logout\" name=\"logout\">Logout\r\n" + 
							"    <i class=\"material-icons right arrow_back\">logout</i>\r\n" + 
							"  </button>");
					str+=("</div></body></html>");
					out.println(str);
					out.close();
		
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
