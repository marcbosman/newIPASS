package nl.hu.v1ipass.model;

import java.util.ArrayList;

public class Groep {
	private String naam;
	private ArrayList<Persoon> personen;
	
	public Groep(String naam) {
		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public ArrayList<Persoon> getPersonen() {
		return personen;
	}

	public void setPersonen(ArrayList<Persoon> personen) {
		this.personen = personen;
	}
	
	public String toString() {
		String s = "Groep naam: " + naam + ", personen:\n";
		for (Persoon p : personen) {
			s += "persoon id: " + p.getPersoonid() + ", naam: " + p.getNaam();
		}
		return s;
	}
}
