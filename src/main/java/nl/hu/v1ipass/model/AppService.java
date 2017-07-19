package nl.hu.v1ipass.model;

import java.util.List;

import nl.hu.v1ipass.persistence.PersoonDAO;
import nl.hu.v1ipass.persistence.GroepDAO;
import nl.hu.v1ipass.persistence.InschrijfDAO;

public class AppService {
	private PersoonDAO persoonDAO = new PersoonDAO();
	private GroepDAO groepDAO = new GroepDAO();
	private InschrijfDAO inschrijfDAO = new InschrijfDAO();

	public List<Persoon> getAllPersonen() {
		return persoonDAO.findAll();//alle personen ophalen!
	}

	public boolean addPersoon(Persoon p) {
		boolean result = false;
		if (persoonDAO.save(p)) {
			result = true;
		}
		return result;
	}

	public boolean deletePersoon(Persoon p) {
		boolean result = false;
		if (persoonDAO.delete(p)) {
			result = true;
		}
		return result;
	}

	public Persoon getPersoonById(int id) {
		//System.out.println(id);

		for (Persoon p : getAllPersonen()) {//loopen door alle personen en kijken naar id
			//System.out.println(p.getPersoonid());
			if (p.getPersoonid() == id) {
				return p;
			}
		}
		return null;
	}
	
	public List<Groep> getAllGroepen() {
		return groepDAO.findAll();//alle groepen ophalen!
	}

	public boolean addGroep(Groep g) {
		boolean result = false;
		if (groepDAO.save(g)) {
			result = true;
		}
		return result;
	}
	
	public List<Inschrijving> getAllInschrijvingen() {
		return inschrijfDAO.findAll();//alle inschrijvingen ophalen!
	}
	
	public List<Persoon> getAllIngeschreven() {
		return persoonDAO.findAllIngeschreven();//alle inschrijvingen ophalen!
	}

	public boolean addInschrijving(Inschrijving i) {
		boolean result = false;
		if (inschrijfDAO.save(i)) {
			result = true;
		}
		return result;
	}
	
	public boolean updateInschrijving(String status, int persoonid) {
		boolean result = false;
		if (inschrijfDAO.update(status, persoonid)) {
			result = true;
		}
		return result;
	}
	
	public boolean updatePersoon(String groep, int persoonid) {
		boolean result = false;
		if (persoonDAO.update(groep, persoonid)) {
			result = true;
		}
		return result;
	}
}
