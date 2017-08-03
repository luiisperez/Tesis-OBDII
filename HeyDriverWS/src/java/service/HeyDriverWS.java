package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.entities.User;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author HeyDriver
 */

//PARA ACCEDER A LOS METODOS DEL WS SE USA LA SIGUIENTE DIRECCION http://<IP DEL SERVIDOR>:8084/HeyDriverWS/webresources/heydriverws/<METODO A PROBAR>

@Path("heydriverws")
public class HeyDriverWS {
    Gson gson = new Gson();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HeyDriverWS
     */
    public HeyDriverWS() {
    }

    /**
     * Retrieves representation of an instance of service.HeyDriverWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of HeyDriverWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    @GET
    @Path("prueba")
    @Produces("application/json")
    public String ejemplo (){
        User result = new User();//nuevo
        result.set_email("Prueba exitosa");
        return gson.toJson( result );//nuevo
    }
    
    @GET
    @Path("signUpUser")
    @Produces("application/json")
    public String signUpUser (@QueryParam("user") String _user){
        Gson gson = new GsonBuilder().create();
        User userToInsert = gson.fromJson(_user, User.class);
        return gson.toJson( userToInsert );//nuevo
    }
    
    
}
