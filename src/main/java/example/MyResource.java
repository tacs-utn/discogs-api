package example;

import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Root resource (exposed at "myresource" path)
 */
//@Path("myresource")
@Path("/")
public class MyResource {

    //@GET, @PUT, @POST, @DELETE and @HEAD
    //@Consumes("text/plain")
//    public Response smooth(
//            @DefaultValue("2") @QueryParam("step") int step,
//            @DefaultValue("true") @QueryParam("min-m") boolean hasMin,
//            @DefaultValue("blue") @QueryParam("min-color") ColorParam minColor)
//
//    @GET
//    @Path("myresource")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getIt() {
//        return "Got it!";
//    }
//
//    @GET
//    @Path("myresource2")
//    @JSONP
//    @Produces(MediaType.APPLICATION_JSON)
//    public Person getIt2() {
//        Person p = new Person();
//        p.setName("asd");
//        //return OK
//        return p;
//    }
//    @GET
//    @Path("myresource2/{version}")
//    public void put(@PathParam("version") int version,
//                    @Context HttpHeaders headers,
//                    byte[] in) {
//        ...
//    }

    static ArrayList<Song> songs = new ArrayList<Song>();


    static{
        songs.add(new Song(1, "AC/DC", 1980, "Back in Black"));
        songs.add(new Song(2, "Alice Cooper", 1991, "Hurricane Years"));
        songs.add(new Song(3, "Iron Maiden", 1992, "Fear Of The Dark"));
        songs.add(new Song(4, "Red Hot Chili Peppers", 1999, "Californication"));
        songs.add(new Song(5, "Red Hot Chili Peppers", 1999, "Scar Tissue"));
        songs.add(new Song(6, "Limp Bizkit", 1999, "Nookie"));
        songs.add(new Song(7, "Daft Punk", 2000, "One more time"));
        songs.add(new Song(8, "The Black Eyed Peas", 2000, "I Gotta Feeling"));
    }


    //@Context HttpHeaders hh
    //MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
//    Map<String, Cookie> pathParams = hh.getCookies();
    @GET
    @Path("songs/{artist}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongByArtistYear(@PathParam("artist") String artist, @PathParam("year") Integer year) {
        ArrayList<Song> filteredSongs = new ArrayList<Song>();
        for(Song song: songs){
            if( ( artist == null || song.getArtist().equals(artist) ) &&
                    ( year == null   || song.getYear()==year ) ){
                filteredSongs.add(song);
            }
        }
        return Response.ok().entity(filteredSongs).build();
    }

    @GET
    @Path("songs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongByArtistYearQueryString(@QueryParam("artist") String artist,@QueryParam("year") Integer year) {
        ArrayList<Song> filteredSongs = new ArrayList<Song>();
        for(Song song: songs){
                if( ( artist == null || song.getArtist().equals(artist) ) &&
                    ( year == null   || song.getYear()==year ) ){
                    filteredSongs.add(song);
                }
        }
        return Response.ok().entity(filteredSongs).build();
    }


    @POST
    @Path("songs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSong(Song song) {
        song.setId(songs.get(songs.size() - 1).getId() + 1);
        songs.add(song);
        return Response.status(Response.Status.CREATED).entity(song).build();
    }

    @PUT
    @Path("songs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifySong(@PathParam("id") int id, Song song) {
        for(int i=songs.size()-1;i>=0;i--){
            Song tmpSong = songs.get(i);
            if(tmpSong.getId()==id){
                songs.remove(i);
                song.setId(id);
                songs.add(song);
                return Response.status(Response.Status.OK).entity(song).build();
            }
        }
        String msg = String.format("Song with id: %d not found", id);
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMsg(msg)).build();
    }

    @GET
    @Path("songs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSong(@PathParam("id") int id) {
        for(Song song:songs){
            if(song.getId()==id){
                return Response.status(Response.Status.OK).entity(song).build();
            }
        }
        String msg = String.format("Song with id: %d not found", id);
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMsg(msg)).build();
    }

    @DELETE
    @Path("songs/{id}")
    public Response removeSong(@PathParam("id") int id) {
        for(int i=songs.size()-1;i>=0;i--){
            Song tmpSong = songs.get(i);
            if(tmpSong.getId()==id){
                songs.remove(i);
                return Response.status(Response.Status.OK).build();
            }
        }
        String msg = String.format("Song with id: %d not found", id);
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMsg(msg)).build();
    }

}
