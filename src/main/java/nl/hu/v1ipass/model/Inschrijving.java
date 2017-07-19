package nl.hu.v1ipass.model;

import java.util.Date;

public class Inschrijving {
	private int id;
	private int persoonId;
	public int getId() {
		return id;
	}

	public Inschrijving(int id, int persoonId, Date datum, String status) {
		this.id = id;
		this.persoonId = persoonId;
		this.datum = datum;
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersoonId() {
		return persoonId;
	}

	public void setPersoonId(int persoonId) {
		this.persoonId = persoonId;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private Date datum;
	private String status;
	
	public String toString() {
		return "Inschrijving id: " + id + ", persoon id: " + persoonId + ", inschrijfdatum: " + datum + ", status: " + status;
	}
}
