package nl.hu.v1ipass.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.hu.v1ipass.model.Persoon;

public class PersoonDAO extends BaseDAO {
	private List<Persoon> selectPersonen(String query) {
		List<Persoon> results = new ArrayList<Persoon>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			//System.out.println(query);
			ResultSet dbResultSet = stmt.executeQuery(query);//query uitvoeren en resultaat opslaan in dbResultSet

			while (dbResultSet.next()) {//loop door de resultaten heen en leg per persoon de gegevens vast
				int persoonid = dbResultSet.getInt("persoonid");
				String naam = dbResultSet.getString("naam");
				String groep = dbResultSet.getString("groep");
				String rol = dbResultSet.getString("rol");
				String email = dbResultSet.getString("email");
				String woonplaats = dbResultSet.getString("woonplaats");
				String postcode = dbResultSet.getString("postcode");
				String telefoonnummer = dbResultSet.getString("telefoonnummer");
				String huisarts = dbResultSet.getString("huisarts");
				Date geboortedatum = dbResultSet.getDate("geboortedatum");
				String bijzonderheden = dbResultSet.getString("bijzonderheden");
				String betaaltermijn = dbResultSet.getString("betaaltermijn");
				Persoon newPersoon = new Persoon(persoonid, naam, groep, rol, email, woonplaats, postcode, telefoonnummer, huisarts, geboortedatum, bijzonderheden, betaaltermijn);
				results.add(newPersoon);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public boolean save(Persoon persoon) {//persoon opslaan in de database, alle gegevens voor de query worden vanuit een Persoon object gehaald
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");//datum omzetten
		String geboortedatum = formatter.format(persoon.getGeboortedatum());
		String query = "INSERT INTO PERSOON(persoonid, naam, groep, rol, email, woonplaats, postcode, telefoonnummer, huisarts, geboortedatum, bijzonderheden, betaaltermijn) VALUES("
				+ persoon.getPersoonid() + ", '" + persoon.getNaam() + "', '" + persoon.getGroep() + "', '"
				+ persoon.getRol() + "', '" + persoon.getEmail() + "', '" + persoon.getWoonplaats() + "', '"
				+ persoon.getPostcode() + "', '" + persoon.getTelefoonnummer() + "', '" + persoon.getHuisarts() + "', TO_DATE('"
				+ geboortedatum + "','YYYY-MM-DD'), '" + persoon.getBijzonderheden() + "', '" + persoon.getBetaaltermijn() + "');";
		//System.out.println(query);
		boolean result = false;
		try (Connection con = getConnection()) {

			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);//query uitvoeren
			result = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	public List<Persoon> findAll() {//alle personen selecteren
		return selectPersonen(
				"SELECT * FROM PERSOON;");
	}
	
	public List<Persoon> findAllIngeschreven() {//alle personen selecteren
		return selectPersonen(
				"SELECT * FROM PERSOON WHERE PERSOONID IN (SELECT PERSOONID FROM INSCHRIJVING WHERE STATUS = 'In behandeling');");
	}

	public Persoon findById(int id) {//specifiek iemand opzoeken
		return selectPersonen(
				"SELECT * FROM PERSOON WHERE PERSOONID = "
						+ id + ";").get(0);
	}

	public boolean delete(Persoon persoon) {//persoon verwijderen in database
		boolean result = false;
		boolean persoonExists = findById(persoon.getPersoonid()) != null;

		if (persoonExists) {
			String query = "DELETE FROM PERSOON WHERE ID = '" + persoon.getPersoonid() + "';";

			try (Connection con = getConnection()) {

				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = true;//1 row updated betekend dat de rij verwijderd is, zet resultaat op true
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean update(String groep, int persoonid) {//persoon groep updaten in de database
		String query = "UPDATE PERSOON SET GROEP = '" + groep + "' WHERE persoonid = " + persoonid + ";";
		//System.out.println(query);
		boolean result = false;
		try (Connection con = getConnection()) {

			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);//query uitvoeren
			result = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}
}
