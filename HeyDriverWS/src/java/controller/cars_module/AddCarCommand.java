/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cars_module;

import common.entities.Car;
import controller.Command;
import model.cars_module.DAOCar;

/**
 *
 * @author LAPGrock
 */
public class AddCarCommand extends Command{
    
    private Car carToAdd;
    private String username;
    private int code;
    
    public AddCarCommand(String _username, Car _carToAdd){
        this.carToAdd = _carToAdd;
        this.username = _username;
    }
    
    public int getResponseCode(){
        return this.code;
    }

    @Override
    public void execute() throws Exception{
        try{
            DAOCar dao = new DAOCar();
            code = dao.create(username, carToAdd);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}

