

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
import bean.UtenteBean;
import controller.ControllerProfessore;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Servlet implementation class TabellaAnnoAccademicoEsame
 */
@WebServlet("/TabellaAnnoAccademicoEsame")
public class TabellaAnnoAccademicoEsame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  ArrayList<PrenotazioneEsameBean> Esami= new ArrayList<PrenotazioneEsameBean>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TabellaAnnoAccademicoEsame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String AnnoAccademico=request.getParameter("annoAccademico");
		int InizioAnnoAccademico=Integer.valueOf(AnnoAccademico.substring(0,4));
		
		int FineAnnoAccademico=Integer.valueOf(AnnoAccademico.substring(5));
		
			
			java.util.Date  dInizio=new  java.util.Date (InizioAnnoAccademico, 10,1 );
		
			java.util.Date  dFine=new java.util.Date (FineAnnoAccademico,03,1 );
			
			UtenteBean bean=new UtenteBean();
			bean=LoginCheck.userBean;				
		
		try {
				
				ControllerProfessore.getInstance();
				Esami=ControllerProfessore.visualizzaStoricoPrenotazioniEsame(dInizio,dFine,bean.getUserid());
				
		
				response.setContentType("text/html");
				PrintWriter out =response.getWriter();
				
				String str="<html> <head>" + 
						"      <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\r\n" + 
						"      <link type=\"text/css\" rel=\"stylesheet\" href=\"css/materialize.min.css\"  media=\"screen,projection\"/>\r\n" + 
						"      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n" + 
						"    </head>" + 
						
						"    <body>" + 
						"      <script type=\"text/javascript\" src=\"js/materialize.min.js\"></script> "
						+ "<div class=card-panel  brown align=center><h3> VISUALIZZA STORICO PRENOTAZIONI ESAME : </h3></div>\r\n" + 							
						 " <div class=container>";
				str+=("<table >   "
					+ "     <thead>"
					+ "          <tr>" 
					+"              <th> Titolare</th>"  
					+"              <th>Nome Corso</th>"  
					+"              <th>Sessione</th>" 
					+"              <th>Seduta</th>"  
					+"              <th>Aula</th>"  
					+"              <th>Appello</th>"
					+"              <th> Orario</th>"  
					+"              <th>Data</th>"  
					+"              <th>Evento</th>"
					+"              <th>Macroarea</th>"					
					+"          </tr>" 
					+"        </thead>"
					+"<tbody>"  );
				for(PrenotazioneEsameBean f : Esami) {
					
					PrenotazioneEsameBean bean1=new PrenotazioneEsameBean();
					bean1.setSessione(f.getSessione());
					bean1.setAppello(f.getAppello());
					bean1.setAula(f.getAula());
					bean1.setOrario(f.getOrario());
					bean1.setMacroarea(f.getMacroarea());
					bean1.setNomeCorso(f.getNomeCorso());
					bean1.setEvento(f.getEvento());
					bean1.setData((Date) f.getData());
					bean1.setSeduta(f.getSeduta());
					bean1.setTitolarePrenotazione(f.getTitolarePrenotazione());
					str+=
						 "            <tr>" + 
						 "            <td>"+ bean1.getTitolarePrenotazione()+"</td>" + 
						 "            <td>"+bean1.getNomeCorso() +"</td>" + 
						 "            <td>"+ bean1.getSessione()+"</td>" + 
						 "            <td>"+bean1.getSeduta() +"</td>" + 
						 "            <td>"+bean1.getAula().getNome() +"</td>" + 
						 "            <td>"+ bean1.getAppello()+"</td>" + 
						 "            <td>"+bean1.getOrario() +"</td>" + 
						 "            <td>"+bean1.getData() +"</td>" + 
						 "            <td>"+ bean1.getEvento()+"</td>" + 
						 "			  <td>"+bean1.getMacroarea() +"</td>"+
						 "            </tr>" ;

				}
				str+=("  </tbody></table> ");
				str+=(" </button>  \r\n" + 
						"     \r\n" + 
						"        <button class=\" large btn waves-effect  green darken-4\" onClick=\"location.href='HomepageProfessore.jsp'\r\n" + 
						"        \"type=\"submit\"  value=\"homepageP\" name=\"homepageP\">HomePage Professore\r\n" + 
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
			
		
		
	}

	}}
