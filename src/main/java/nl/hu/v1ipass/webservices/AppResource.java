package nl.hu.v1ipass.webservices;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.v1ipass.model.AppService;
import nl.hu.v1ipass.model.Persoon;
import nl.hu.v1ipass.model.Inschrijving;
import nl.hu.v1ipass.model.ServiceProvider;

@Path("/personen")
public class AppResource {
	AppService service = ServiceProvider.getAppService();

	@GET
	@Produces("application/json")
	public String getPersonen() {//De gegevens vanuit service.getAllPersonen doorsturen naar de front-end in json formaat
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Persoon p : service.getAllPersonen()) {//per persoon
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");//datum omzetten
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("persoonid", p.getPersoonid());
			job.add("naam", p.getNaam());
			job.add("groep", p.getGroep());
			job.add("rol", p.getRol());
			job.add("email", p.getEmail());
			job.add("woonplaats", p.getWoonplaats());
			job.add("postcode", p.getPostcode());
			job.add("telefoonnummer", p.getTelefoonnummer());
			job.add("huisarts", p.getHuisarts());
			job.add("geboortedatum", formatter.format(p.getGeboortedatum()));//De datum omzetten naar een String
			job.add("bijzonderheden", p.getBijzonderheden());
			job.add("betaaltermijn", p.getBetaaltermijn());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();//gehele json als String doorsturen naar de front-end
	}
	
	@GET
	@Path("/inschrijvingen")
	@Produces("application/json")
	public String getInschrijvingen() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Persoon p : service.getAllIngeschreven()) {//per persoon
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");//datum omzetten
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("persoonid", p.getPersoonid());
			job.add("naam", p.getNaam());
			job.add("groep", p.getGroep());
			job.add("rol", p.getRol());
			job.add("email", p.getEmail());
			job.add("woonplaats", p.getWoonplaats());
			job.add("postcode", p.getPostcode());
			job.add("telefoonnummer", p.getTelefoonnummer());
			job.add("huisarts", p.getHuisarts());
			job.add("geboortedatum", formatter.format(p.getGeboortedatum()));//De datum omzetten naar een String
			job.add("bijzonderheden", p.getBijzonderheden());
			job.add("betaaltermijn", p.getBetaaltermijn());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();//gehele json als String doorsturen naar de front-end
	}

	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getPersoonById(@PathParam("code") int id) {//specifiek iemand opzoeken
		//System.out.println(id);
		Persoon p = service.getPersoonById(id);
		//System.out.println(p);
		JsonArrayBuilder jab = Json.createArrayBuilder();

		Format formatter = new SimpleDateFormat("yyyy-MM-dd");//datum omzetten
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("persoonid", p.getPersoonid());
		job.add("naam", p.getNaam());
		job.add("groep", p.getGroep());
		job.add("rol", p.getRol());
		job.add("email", p.getEmail());
		job.add("woonplaats", p.getWoonplaats());
		job.add("postcode", p.getPostcode());
		job.add("telefoonnummer", p.getTelefoonnummer());
		job.add("huisarts", p.getHuisarts());
		job.add("geboortedatum", formatter.format(p.getGeboortedatum()));//De datum omzetten naar een String
		job.add("bijzonderheden", p.getBijzonderheden());
		job.add("betaaltermijn", p.getBetaaltermijn());
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();//gehele json als String doorsturen naar de front-end
	}
	
	@POST
	@Path("/accepteer/{code}")
	@Produces("application/json")
	//Alle informatie uit de request van de client halen met @FormParam()
	public String beoordeelPersoon(@PathParam("code") String persoonid, @FormParam("groep") String groep) {
		String status = "Geaccepteerd";//De nieuwe status van de inschrijving
		service.updateInschrijving(status, Integer.parseInt(persoonid));
		service.updatePersoon(groep, Integer.parseInt(persoonid));
		return "geaccepteerd";
	}
	
	@POST
	@Path("/weiger/{code}")
	@Produces("application/json")
	//Alle informatie uit de request van de client halen met @FormParam()
	public String weigerPersoon(@PathParam("code") String persoonid, @FormParam("groep") String groep) {
		String status = "Geweigerd";//De nieuwe status van de inschrijving
		service.updateInschrijving(status, Integer.parseInt(persoonid));
		return "geweigerd";
	}

	@POST
	@Path("/toevoegen")
	@Produces("application/json")
	//Alle informatie uit de request van de client halen met @FormParam()
	public String createPersoon(@FormParam("naam") String naam, @FormParam("email") String email,
			@FormParam("woonplaats") String woonplaats, @FormParam("telefoonnummer") String telefoonnummer,
			@FormParam("postcode") String postcode, @FormParam("geboortedatum") String geboortedatumString,
			@FormParam("bijzonderheden") String bijzonderheden, @FormParam("huisarts") String huisarts,
			@FormParam("betaaltermijn") String betaaltermijn) {
		int persoonid = service.getAllPersonen().size() + 1;//id ophogen met 1
		String groep = "Geen groep";//bij een inschrijving heeft een persoon nog niet gelijk een rol en groep
		String rol = "LID";//na het inschrijven zal iemand worden geregistreerd als lid
	    Date geboortedatum = null;
		try {
			geboortedatum = new SimpleDateFormat("yyyy-MM-dd").parse(geboortedatumString);
		} catch (ParseException e) {
			System.out.println("Error bij het omzetten van de datum");
		}
		Persoon newP = new Persoon(persoonid, naam, groep, rol, email, woonplaats, postcode, telefoonnummer, huisarts, geboortedatum, bijzonderheden, betaaltermijn);
		service.addPersoon(newP);//toevoegen aan de service
		//Een inschrijving maken bij het persoon dat zich heeft geregistreerd
		Inschrijving in = new Inschrijving(service.getAllInschrijvingen().size() + 1, newP.getPersoonid(), new Date(), "In behandeling");
		service.addInschrijving(in);
		
		return persoonToJson(newP).build().toString();//doorsturen als String
	}

	private JsonObjectBuilder persoonToJson(Persoon p) {//gegevens van een Persoon omzetten naar json formaat om door te sturen
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");//datum omzetten
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("persoonid", p.getPersoonid());
		job.add("naam", p.getNaam());
		job.add("groep", p.getGroep());
		job.add("rol", p.getRol());
		job.add("email", p.getEmail());
		job.add("woonplaats", p.getWoonplaats());
		job.add("postcode", p.getPostcode());
		job.add("telefoonnummer", p.getTelefoonnummer());
		job.add("huisarts", p.getHuisarts());
		job.add("geboortedatum", formatter.format(p.getGeboortedatum()));//De datum omzetten naar een String
		job.add("bijzonderheden", p.getBijzonderheden());
		job.add("betaaltermijn", p.getBetaaltermijn());
		return job;
	}

	@DELETE
	@Path("/delete/{code}")
	public Response deletePersoon(@PathParam("id") int id) {//op basis van persoonid de persoon verwijderen
		Persoon found = null;
		AppService service = ServiceProvider.getAppService();
		for (Persoon p : service.getAllPersonen()) {
			if (p.getPersoonid() == id) {
				found = p;
				break;
			}
		}
		if (found == null) {//als de persoon niet gevonden is laat dat dan weten
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			service.deletePersoon(found);//persoon daadwerkelijk verwijderen
			return Response.ok().build();
		}
	}

}
