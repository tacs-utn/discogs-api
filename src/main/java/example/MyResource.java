package example;

import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
//@Path("myresource")
@Path("/")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    //@GET, @PUT, @POST, @DELETE and @HEAD
    //@Consumes("text/plain")
//    public Response smooth(
//            @DefaultValue("2") @QueryParam("step") int step,
//            @DefaultValue("true") @QueryParam("min-m") boolean hasMin,
//            @DefaultValue("blue") @QueryParam("min-color") ColorParam minColor)
    @GET
    @Path("myresource")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("myresource2")
    @JSONP
    @Produces(MediaType.APPLICATION_JSON)
    public Person getIt2() {
        Person p = new Person();
        p.setName("asd");
        //return OK
        return p;
    }

    @GET
    @Path("myresource3")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt3() {
        Person p = new Person();
        p.setName("asd");
        return Response.status(Response.Status.CREATED).entity(p).build();
    }



//    @GET
//    @Path("myresource2/{version}")
//    public void put(@PathParam("version") int version,
//                    @Context HttpHeaders headers,
//                    byte[] in) {
//        ...
//    }

}
