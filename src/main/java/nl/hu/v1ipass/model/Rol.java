package nl.hu.v1ipass.model;

public class Rol {
	private String naam;
	private String beschrijving;
	
	public Rol(String naam, String beschrijving) {
		this.naam = naam;
		this.beschrijving = beschrijving;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public String toString() {
		return "Rol naam: " + naam + ", beschrijving: " + beschrijving;
	}
}
