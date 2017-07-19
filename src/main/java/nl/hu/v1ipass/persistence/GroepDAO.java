package nl.hu.v1ipass.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.model.Groep;

public class GroepDAO extends BaseDAO {
	private List<Groep> selectGroepen(String query) {
		List<Groep> results = new ArrayList<Groep>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			//System.out.println(query);
			ResultSet dbResultSet = stmt.executeQuery(query);//query uitvoeren en resultaat opslaan in dbResultSet

			while (dbResultSet.next()) {
				String naam = dbResultSet.getString("naam");//De leden van de groep staan in de groep database, maar in personen
				Groep newGroep = new Groep(naam);
				results.add(newGroep);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public boolean save(Groep groep) {//Groep opslaan in de database
		String query = "INSERT INTO GROEP(naam) VALUES('" + groep.getNaam() + "');";
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

	public List<Groep> findAll() {//alle groepen selecteren
		return selectGroepen(
				"SELECT * FROM GROEP;");
	}
}
