package example;

import models.*;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Path("/")
public class MyResource {
    static Client client = ClientBuilder.newClient(new ClientConfig());
    static ArrayList<SearchResponse> favorites = new ArrayList<SearchResponse>();

    @GET
    @Path("favorites")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongByArtistYearQueryString(@QueryParam("artist") String artist) {
        ArrayList<SearchResponse> filteredReleases = new ArrayList<SearchResponse>();
        for(SearchResponse release: favorites){
                if( artist == null || release.getArtist().contains(artist)){
                    filteredReleases.add(release);
                }
        }
        return Response.ok().entity(filteredReleases).build();
    }

    @POST
    @Path("favorites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSong(SearchResponse song) {
        favorites.add(song);
        return Response.status(Response.Status.CREATED).entity(song).build();
    }

   @PUT
   @Path("favorites/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response modifySong(@PathParam("id") Long id, SearchResponse song) {
       for(int i=favorites.size()-1;i>=0;i--){
           SearchResponse tmpSong = favorites.get(i);
           if(tmpSong.getId().equals(id)){
               favorites.remove(i);
               song.setId(id);
               favorites.add(song);
               return Response.status(Response.Status.OK).entity(song).build();
           }
       }
       String msg = String.format("Song with id: %d not found", id);
       return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMsg(msg)).build();
   }

    @GET
    @Path("favorites/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongById(@PathParam("id") Long id) {
        for(SearchResponse song:favorites){
            if(song.getId().equals(id)){
                return Response.status(Response.Status.OK).entity(song).build();
            }
        }
        String msg = String.format("Song with id: %d not found", id);
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMsg(msg)).build();
    }

    @DELETE
    @Path("favorites/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeSong(@PathParam("id") Long id) {
        for(int i=favorites.size()-1;i>=0;i--){
            SearchResponse tmpSong = favorites.get(i);
            if(tmpSong.getId().equals(id)){
                favorites.remove(i);
                return Response.status(Response.Status.OK).build();
            }
        }
        String msg = String.format("Release with id: %d not found", id);
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMsg(msg)).build();
    }

    @GET
    @Path("recommendations/{artistName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recommendation(@PathParam("artistName") String artistName) {
        String discogsUrl = "https://api.discogs.com/";
        String discogsAuth = "Discogs key=wwqFHdRfHMiLNqSZiVlW, secret=MozvVouTUJsdCMovtkGLadUdAyuPvOUZ";

        Search search = client.target(discogsUrl)
                .path("database/search")
                .queryParam("artist", artistName)
                .queryParam("format", "Vinyl")
                .queryParam("type", "release")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header("Authorization", discogsAuth)
                .get(Search.class);

        ArrayList<Release> releases = new ArrayList<Release>(search.getResults().size());

        for(SearchResult sr:search.getResults()){
            Release release = client.target(discogsUrl)
                    .path("releases/" + sr.getId())
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .header("Authorization", discogsAuth)
                    .get(Release.class);
            releases.add(release);
            System.out.print("Release ID: " + release.getId() + ". ");
            System.out.println("Rating : " + release.getCustomRating());
        }

        Collections.sort(releases, new Comparator<Release>() {
            @Override
            public int compare(Release o1, Release o2) {
                return o2.getCustomRating().compareTo(o1.getCustomRating());
            }
        });

        List<Release> topReleases;
        if(releases.size() < 3){
            topReleases = releases.subList(0, releases.size());
        } else {
            topReleases = releases.subList(0, 3);
        }


        ArrayList<SearchResponse> topSearches = new ArrayList<SearchResponse>(3);

        for(Release topRelease :topReleases){
            for(SearchResult sr:search.getResults()){
                if(topRelease.getId().equals(sr.getId())) {
                    topSearches.add(new SearchResponse(sr, topRelease.getCustomRating(), topRelease.getArtistsNames()));
                }
            }
        }

        return Response.status(Response.Status.OK).entity(topSearches).build();
    }
}




















/*
#################################################################
##########################    EXTRA      ########################
#################################################################
 */




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
//@Context HttpHeaders hh
//MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
//    Map<String, Cookie> pathParams = hh.getCookies();
