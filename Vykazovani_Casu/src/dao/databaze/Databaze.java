package dao.databaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.entity.Uzivatel;

public class Databaze {
	
	protected final String dbUri;
	
	public Databaze(String machine, String db, String user, String pass) {
		this.dbUri = "jdbc:mysql://" + machine + "/" + db + "?user=" + user + "&password=" + pass + "&useUnicode=true&characterEncoding=utf8";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDbUri() {
		return dbUri;
	}
	
	public Uzivatel nactiUzivatele(String hodnota, String atribut, String role) {
		Uzivatel uzivatel = null;
		try {
			Connection con = DriverManager.getConnection(dbUri);
			Statement s = con.createStatement();

			String sql = "SELECT * FROM uzivatel WHERE " + atribut + "='" + hodnota + "';";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				uzivatel = new Uzivatel(rs.getInt("id_uzivatel"), rs.getString("login"), rs.getString("jmeno"), rs.getString("prijmeni"), rs.getString("titul_pred"), rs.getString("titul_za"));
			}
			
			rs.close();
			s.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uzivatel;
	}

	public Object ziskejSystemoveInformace() {
		// TODO Auto-generated method stub
		return null;
	}

}
