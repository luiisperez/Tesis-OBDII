package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.entities.Car;
import common.entities.ObdData;
import common.entities.User;
import controller.cars_module.AddCarCommand;
import controller.cars_module.GetUsersCarsCommand;
import controller.cars_module.RemoveCarCommand;
import controller.obdData_module.AddObdDataCommand;
import controller.users_module.LoginCommand;
import controller.users_module.SignUpCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        SignUpCommand cmd = new SignUpCommand(userToInsert);
        try {
            cmd.execute();
            return gson.toJson( cmd.getResponse() );//nuevo
        } catch (Exception ex) {
            User error = new User();
            error.set_error(500);
            return gson.toJson( error );//nuevo
        }
    }
    
    @GET
    @Path("userLogin")
    @Produces("application/json")
    public String userLogin (@QueryParam("user") String _user){
        Gson gson = new GsonBuilder().create();
        User userToLogin = gson.fromJson(_user, User.class);
        LoginCommand cmd = new LoginCommand(userToLogin);
        try {
            cmd.execute();
            return gson.toJson( cmd.getResponse() );//nuevo
        } catch (Exception ex) {
            User error = new User();
            error.set_error(500);
            return gson.toJson( error );//nuevo
        }
    }
    
    @GET
    @Path("addVehicle")
    @Produces("application/json")
    public String addVehicle (@QueryParam("user") String _user, @QueryParam("car") String _car){
        Gson gson = new GsonBuilder().create();
        Car carToAdd = gson.fromJson(_car, Car.class);
        AddCarCommand cmd = new AddCarCommand(_user, carToAdd);
        try {
            cmd.execute();
            return gson.toJson( cmd.getResponseCode());//nuevo
        } catch (Exception ex) {
            return gson.toJson( 500 );//nuevo
        }
    }
    
    @GET
    @Path("removeVehicle")
    @Produces("application/json")
    public String removeVehicle (@QueryParam("user") String _user, @QueryParam("car") String _car){
        RemoveCarCommand cmd = new RemoveCarCommand(_car, _user);
        try {
            cmd.execute();
            return gson.toJson( cmd.getResponseCode());//nuevo
        } catch (Exception ex) {
            return gson.toJson( 500 );//nuevo
        }
    }
    
    @GET
    @Path("getUserVehicles")
    @Produces("application/json")
    public String getUserVehicles (@QueryParam("user") String _user){
        GetUsersCarsCommand cmd = new GetUsersCarsCommand(_user);
        try {
            cmd.execute();
            return gson.toJson( cmd.getResponse());//nuevo
        } catch (Exception ex) {
            return gson.toJson( null );//nuevo
        }
    }
    
    @GET
    @Path("synchronization")
    @Produces("application/json")
    public String synchronization (@QueryParam("obddata") String _readings){
        Gson gson = new GsonBuilder().create();
        ObdData obd_data = gson.fromJson(_readings, ObdData.class);
        AddObdDataCommand cmd = new AddObdDataCommand(obd_data);
        try {
        //List<ObdData> readings_add = gson.fromJson(_readings, new TypeToken<List<ObdData>>(){}.getType());
            cmd.execute();
            if (obd_data != null)
            return "true";
        else
            return "false";
        } catch (Exception ex) {

            return gson.toJson( "error" );//nuevo
        }
    } 
    
    
}
