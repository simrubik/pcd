package pack;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class HelloWorldResource {

	private String message;
	
	@GET
	@Produces("text/plain")
	public String getMessage() {
		return "Hello World with GET";
	}

	@PUT
	@Consumes("text/plain")
	public void setMessage(@PathParam(value="msg") String msg) {
		this.message = msg;
		System.out.println(message);
	}
}
