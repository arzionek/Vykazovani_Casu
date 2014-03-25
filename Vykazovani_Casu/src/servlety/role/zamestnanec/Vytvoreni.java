package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Vytvoreni extends AServletZamestnanec{

	private static final long serialVersionUID = 5600900190713821993L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
			vytvoreni(request, response);
		}
	}

	private void vytvoreni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
