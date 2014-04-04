package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.databaze.Databaze;

import servlety.role.AVlastniServlet;

public abstract class AServletZamestnanec extends AVlastniServlet{

	private static final long serialVersionUID = -7749730765832324193L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if(!response.isCommitted()){
			HttpSession session = request.getSession();
			String role = (String) session.getAttribute("role");
			if(role == null){
				response.sendRedirect("log");
			//overeni spravne role
			}else if(!role.equals("zamestnanec")){
				response.sendRedirect("uvodni");
			//ulozeni udaju o prihlasenem
			}else{
				nastavUdajeOPrihlasenem(request, session, role);
			}
		}
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		pripojeni = new Databaze(context.getInitParameter("db-machine"), context.getInitParameter("db-db"), context.getInitParameter("db-user"), context.getInitParameter("db-pass"));
	}
}
