package nl.hu.v1ipass.model;

import java.util.Date;

public class Persoon {
	private int persoonid;
	private String naam;
	private String groep;
	private String rol;
	private String email;
	private String woonplaats;
	private String postcode;
	private String telefoonnummer;
	private String huisarts;
	private Date geboortedatum;
	private String bijzonderheden;
	private String betaaltermijn;
	
	public Persoon(int persoonid, String naam, String groep, String rol, String email, String woonplaats,
			String postcode, String telefoonnummer, String huisarts, Date geboortedatum, String bijzonderheden, String betaaltermijn) {
		this.persoonid = persoonid;
		this.naam = naam;
		this.groep = groep;
		this.rol = rol;
		this.email = email;
		this.woonplaats = woonplaats;
		this.postcode = postcode;
		this.telefoonnummer = telefoonnummer;
		this.huisarts = huisarts;
		this.geboortedatum = geboortedatum;
		this.bijzonderheden = bijzonderheden;
		this.betaaltermijn = betaaltermijn;
	}

	public String getBetaaltermijn() {
		return betaaltermijn;
	}

	public void setBetaaltermijn(String betaaltermijn) {
		this.betaaltermijn = betaaltermijn;
	}

	public int getPersoonid() {
		return persoonid;
	}

	public void setPersoonid(int persoonid) {
		this.persoonid = persoonid;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getGroep() {
		return groep;
	}

	public void setGroep(String groep) {
		this.groep = groep;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public String getHuisarts() {
		return huisarts;
	}

	public void setHuisarts(String huisarts) {
		this.huisarts = huisarts;
	}

	public Date getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(Date geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String getBijzonderheden() {
		//Als er geen bijzonderheden zijn opgegeven
		if (bijzonderheden != null) {
			return bijzonderheden;
		} else {
			return "Geen bijzonderheden opgegeven";
		}
	}

	public void setBijzonderheden(String bijzonderheden) {
		this.bijzonderheden = bijzonderheden;
	}

	public String toString() {
		return "Persoon: persoonid=" + persoonid + ", naam=" + naam + ", groep=" + groep + ", rol=" + rol + ", email="
				+ email + ", woonplaats=" + woonplaats + ", postcode=" + postcode + ", telefoonnummer=" + telefoonnummer
				+ ", huisarts=" + huisarts + ", geboortedatum=" + geboortedatum + ", bijzonderheden=" + bijzonderheden;
	}
}