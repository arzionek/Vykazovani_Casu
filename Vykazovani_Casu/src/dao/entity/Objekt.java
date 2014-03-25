package dao.entity;

public class Objekt {

	private int id;
	private int stavObjektu;
	
	public Objekt(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getStavObjektu() {
		return stavObjektu;
	}

	public void setStavObjektu(int stavObjektu) {
		this.stavObjektu = stavObjektu;
	}
	
}
