

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AulaBean;
import bean.PrenotazioneEventoBean;

import controller.ControllerSegreteria;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Servlet implementation class PrenotaEvento
 */
@WebServlet("/PrenotaEvento")
public class PrenotaEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotaEvento() {
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
		

		PrenotazioneEventoBean prenotazioneEvento= new PrenotazioneEventoBean();
		
		AulaBean  aulabean = new AulaBean();
		aulabean.setNumeroPosti(Integer.valueOf(request.getParameter("posti")));
		aulabean.setProiettore(Integer.valueOf(request.getParameter("proiettori")));
		aulabean.setLavagna((request.getParameter("lav").equals("true")));
		aulabean.setLavInterattiva((request.getParameter("lavInt").equals("true")));
		aulabean.setPrese((request.getParameter("prese").equals("true")));
		aulabean.setMicrofono((request.getParameter("mic").equals("true")));
		aulabean.setEthernet((request.getParameter("ethernet").equals("true")));
		aulabean.setLaboratorio((request.getParameter("Laboratorio")));

	
		aulabean.setTipo((request.getParameter("TipoAula")));
	
		PrenotazioneEventoBean prenotazionebean = new PrenotazioneEventoBean();
		prenotazionebean.setTitolarePrenotazione(LoginCheck.userBean.getUserid());
		prenotazionebean.setEvento(((request.getParameter("Evento"))));
		prenotazionebean.setOrario(((request.getParameter("Orario"))));
		
		prenotazionebean.setMacroarea(((request.getParameter("Macroarea"))));
		
		prenotazionebean.setTitoloEvento((request.getParameter("titolo")));
		
		String datastringa=request.getParameter("date");
		java.util.Date Dataprenotazione =  new java.util.Date();
		
		Dataprenotazione.setMonth((Integer.valueOf(datastringa.substring(3, 5))));
	
		Dataprenotazione.setYear(Integer.valueOf(datastringa.substring(6)));;
				Dataprenotazione.setDate(Integer.valueOf(datastringa.substring(0, 2)));
		
		Dataprenotazione.setYear(Dataprenotazione.getYear()-1900);
	
		
		
		java.sql.Date attuale= new java.sql.Date(Dataprenotazione.getTime());
		
		prenotazionebean.setData(attuale);	
		prenotazionebean.setCorsoStudi((request.getParameter("corsoStudi")));
	
		try {
		prenotazioneEvento=ControllerSegreteria.getInstance().RichiestaPrenotazioneEvento(aulabean,prenotazionebean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		//	PrintWriter out = response.getWriter();
			
			if(ControllerSegreteria.getInstance().verificaEventoController(prenotazioneEvento)) {
				   response.sendRedirect("InsuccessoPrenotazione.jsp");
			}	
			else {
			ControllerSegreteria.getInstance().InvioEventoPrenotazione(prenotazioneEvento);		
			   response.sendRedirect("SuccessoPrenotazione.jsp");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
