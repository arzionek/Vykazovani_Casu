package servlety.role;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.beany.Akce;
import dao.beany.Cas;
import dao.beany.Chyby;
import dao.databaze.Databaze;

public abstract class AServlet extends HttpServlet{
	
	private static final long serialVersionUID = 276641826155495602L;
	
	protected Databaze pripojeni;
	protected Akce akce;
	protected Chyby chyby;
	protected String volanaAkce;
	protected String adresa;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		
		//overeni prihlaseni
		if (session == null || session.getAttribute("loggedUser") == null){
			response.sendRedirect("log");
		//overeni registrace
		}/*else if(session.getAttribute("role").equals("zadna")){
			String stagUserTicket = request.getParameter("stagUserTicket");
			if(stagUserTicket != null || session.getAttribute("ticket") != null){
				session.setAttribute("ticket", stagUserTicket);
				response.sendRedirect("registrace");
			}else{
				String cesta = request.getRequestURL().toString();
				response.sendRedirect("https://stag-ws.zcu.cz/webauth/ws/login?originalURL=" + cesta);
			}
		}*/
		
		//nastaveni akce
		volanaAkce = request.getParameter("akce");
		akce = (Akce) request.getAttribute("akce");
		chyby = (Chyby) request.getAttribute("chyby");
		
		//akce odhlaseni
		if(!response.isCommitted() && akce.getOdhlasit().equals(volanaAkce)) odhlasit(session, response);
		
		//nastaveni adres
		if(session.getAttribute("role") == "zamestnanec") adresa = "/zamestnanec";
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		doGet(request, response);
	}
	
	private void odhlasit(HttpSession session, HttpServletResponse response) throws IOException {
		session.setAttribute("id_uzivatel", null);
		session.setAttribute("loggedUser", null);
		session.setAttribute("role", null);
		System.out.println(new Cas().ziskejDatum() + " - odhlaseni: " + session.getAttribute("login"));
		session.setAttribute("login", null);
		session.setAttribute("viceInfo", null);
		session.setAttribute("uciteleId", null);
		session.setAttribute("zastup", null);
		session.setAttribute("ticket", null);
		response.sendRedirect("log");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//ServletContext context = config.getServletContext();
		//context.getInitParameter("db-machine"), context.getInitParameter("db-db"), context.getInitParameter("db-user"), context.getInitParameter("db-pass")
		pripojeni = new Databaze();
	}
	
	@Override
	public void destroy() {
	  super.destroy();
	  pripojeni.uzavritSpojeni(true);
	}

}
