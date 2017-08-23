/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cars_module;

import common.entities.Car;
import controller.Command;
import java.util.ArrayList;
import model.cars_module.DAOCar;

/**
 *
 * @author LAPGrock
 */
public class GetUsersCarsCommand extends Command{
    
    private String username;
    private ArrayList<Car> response;
    
    public GetUsersCarsCommand(String _username){
        this.username = _username;
    }
    
    public ArrayList<Car> getResponse(){
        return this.response;
    }

    @Override
    public void execute() throws Exception{
        try{
            DAOCar dao = new DAOCar();
            response = dao.getCars(username);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}