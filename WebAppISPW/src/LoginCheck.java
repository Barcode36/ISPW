

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UtenteBean;
import controller.Controller;
//import persistence.DaoUtente;
/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static UtenteBean userBean ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
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
		try {
	String id=request.getParameter("id");
	String pass=request.getParameter("password");

	Controller controllerFisico = Controller.getInstance();
	
	UtenteBean bean=controllerFisico.login(id, pass);
		userBean=bean;
		String tipo=bean.getType();
	
	switch(tipo) {
	case "Professore":
	{
		response.sendRedirect("HomepageProfessore.jsp");
	}	
	break;
	case "Segreteria":
	{
		response.sendRedirect("HomepageSegretario.jsp");
		break;
	}
	default:
		response.sendRedirect("ErroreAccesso.jsp");
		break;
		
	}
		}catch(NullPointerException e) {
			response.sendRedirect("ErroreAccesso.jsp");
			
		}
}
public UtenteBean getUtenteBean() {
	return userBean;
}
}