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

import nl.hu.v1ipass.model.Inschrijving;

public class InschrijfDAO extends BaseDAO {
	private List<Inschrijving> selectInschrijvingen(String query) {
		List<Inschrijving> results = new ArrayList<Inschrijving>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			//System.out.println(query);
			ResultSet dbResultSet = stmt.executeQuery(query);//query uitvoeren en resultaat opslaan in dbResultSet

			while (dbResultSet.next()) {
				int inschrijfid = dbResultSet.getInt("inschrijfid");
				int persoonid = dbResultSet.getInt("persoonid");
				Date datum = dbResultSet.getDate("datum");
				String status = dbResultSet.getString("status");
				Inschrijving newInschrijving = new Inschrijving(inschrijfid, persoonid, datum, status);
				results.add(newInschrijving);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public boolean save(Inschrijving inschrijving) {//inschrijving opslaan in de database
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");//datum omzetten
		String datum = formatter.format(inschrijving.getDatum());
		String query = "INSERT INTO INSCHRIJVING(inschrijfid, persoonid, datum, status) VALUES('" + inschrijving.getId() + "', '" + inschrijving.getPersoonId() + "', TO_DATE('" + datum + "', 'YYYY-MM-DD'), '" + inschrijving.getStatus() + "');";
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
	
	public boolean update(String status, int persoonid) {//inschrijving status updaten in de database
		String query = "UPDATE INSCHRIJVING SET status = '" + status + "' WHERE persoonid = " + persoonid + ";";
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

	public List<Inschrijving> findAll() {//alle inschrijvingen selecteren
		return selectInschrijvingen(
				"SELECT * FROM INSCHRIJVING;");
	}
}
