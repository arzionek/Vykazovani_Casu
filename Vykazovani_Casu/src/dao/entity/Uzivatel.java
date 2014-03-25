package dao.entity;

public class Uzivatel extends Objekt{

	private String login;
	private String jmeno;
	private String prijmeni;
	private String titulPred;
	private String titulZa;
	private String celeJmeno;
	
	public Uzivatel(int id, String login, String jmeno, String prijmeni, String titul_pred, String titul_za) {
		super(id);
		this.login = login;
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.titulPred = titul_pred;
		this.titulZa = titul_za;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public String getPrijmeni() {
		return prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}

	public String getTitulPred() {
		return titulPred;
	}

	public void setTitulPred(String titulPred) {
		this.titulPred = titulPred;
	}

	public String getTitulZa() {
		return titulZa;
	}

	public void setTitulZa(String titulZa) {
		this.titulZa = titulZa;
	}

	public String getCeleJmeno() {
		celeJmeno = titulPred + " " + jmeno + " " + prijmeni;
		if(titulZa.length() > 0) celeJmeno += ", " + titulZa;
		return celeJmeno;
	}

	public void setCeleJmeno(String celeJmeno) {
		this.celeJmeno = celeJmeno;
	}

}
