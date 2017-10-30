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
public class GetCarModelsByBrandCommand extends Command{
    
    private String brandname;
    private ArrayList<String> response;
    
    public GetCarModelsByBrandCommand(String _brandname){
        this.brandname = _brandname;
    }
    
    public ArrayList<String> getResponse(){
        return this.response;
    }

    @Override
    public void execute() throws Exception{
        try{
            DAOCar dao = new DAOCar();
            response = dao.getModelsByBrand(brandname);
        }catch (Exception ex){
            throw ex;
        }
        
    }
    
}