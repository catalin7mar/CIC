import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/")
public class Service {
	@POST
	@Path("/service")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response servREST(InputStream incomData) {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomData));
			String line = null;
			while ((line = in.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Parsing Fehler");
		}
		System.out.println("Data Received: " + builder.toString());
 
		return Response.status(200).entity(builder.toString()).build();
	}
 
	@GET
	@Path("/check")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomData) {
		String result = "Service wurde erfolgreich gestartet "; 
		return Response.status(200).entity(result).build();
	}
 
}