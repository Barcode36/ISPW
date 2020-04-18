

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuProfessore
 */
@WebServlet("/MenuProfessore")
public class MenuProfessore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuProfessore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(request.getParameter("logout")!=null)
			response.sendRedirect("index.jsp");
		if(request.getParameter("esame")!=null)
		response.sendRedirect("PrenotaEsame.jsp");
		if(request.getParameter("prenotazioniAttive")!=null)
			response.sendRedirect("TabellaAttivaProfessore.jsp");
		if(request.getParameter("prenotazioniStorico")!=null)
			response.sendRedirect("SchermataStoricoEsame.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
