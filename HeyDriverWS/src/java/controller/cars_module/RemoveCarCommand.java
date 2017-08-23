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
public class RemoveCarCommand extends Command{
    
    private String serial;
    private String username;
    private int code;
    
    public RemoveCarCommand(String _serial, String _username){
        this.serial = _serial;
        this.username = _username;
    }
    
    public int getResponseCode(){
        return this.code;
    }

    @Override
    public void execute() throws Exception{
        try{
            DAOCar dao = new DAOCar();
            code = dao.delete(serial, username);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}