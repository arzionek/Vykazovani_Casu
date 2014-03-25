package dao.databaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Prihlaseni{
	
	private String dbUri;
	
	public Prihlaseni(Databaze pripojeni){
		this.dbUri = pripojeni.getDbUri();
	}

	public boolean login(String login, String heslo, HttpServletRequest request) {
		boolean prihlaseni = false;
		try {
			Connection con = DriverManager.getConnection(dbUri);
			Statement s = con.createStatement();

			String sql = "SELECT * FROM uzivatel WHERE login='" + login + "'" + " AND heslo='" + heslo + "';";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) prihlaseni = ulozeniDat(request, rs, login);
			
			rs.close();
			s.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prihlaseni;
	}
	
	public boolean login(String login, HttpServletRequest request) {
		boolean prihlaseni = false;
		try {
			Connection con = DriverManager.getConnection(dbUri);
			Statement s = con.createStatement();

			String sql = "SELECT * FROM uzivatel WHERE login='" + login + "';";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) prihlaseni = ulozeniDat(request, rs, login);
			else prihlaseni = novyUzivatel(request, login);
			
			rs.close();
			s.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prihlaseni;
	}

	private boolean ulozeniDat(HttpServletRequest request, ResultSet rs, String login) throws SQLException {
		HttpSession session = request.getSession(true);
		int id = Integer.parseInt(rs.getString("id_uzivatel"));
		String prihlaseny = rs.getString("jmeno") + " " + rs.getString("prijmeni");
		if((rs.getString("titul_pred")).length() > 0) prihlaseny = rs.getString("titul_pred") + " " + prihlaseny;
		if((rs.getString("titul_za")).length() > 0) prihlaseny += ", " + rs.getString("titul_za");
		session.setAttribute("loggedUser", prihlaseny);
		session.setAttribute("id_uzivatel", id);
		session.setAttribute("role", ziskejRoli(id));
		session.setAttribute("login", login);
		return true;
	}
	
	private boolean novyUzivatel(HttpServletRequest request, String login) {
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedUser", login);
		session.setAttribute("role", ziskejRoli(-1));
		session.setAttribute("login", login);
		return true;
	}

	private String ziskejRoli(int id) {
		String role = null;
		if(id != -1) role = "zamestnanec";
		else role = "zadna";
		return role;
	}

}
