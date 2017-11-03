/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cars_module;

import common.entities.Car;
import common.entities.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import model.DAO;
import model.Registry;

/**
 *
 * @author LAPGrock
 */
public class DAOCar extends DAO{
    private Connection _bdCon;
    private static String _sqlAddVehicle = "{?=call ADD_VEHICLE(?,?,?,?,?)}";
    private static String _sqlCarsUserInformation = "{call CARS_USER_INFORMATION(?)}";
    private static String _sqlRemoveVehicle = "{?=call REMOVE_VEHICLE(?,?)}";
    private static String _sqlCarBrandsInformation = "{call CAR_BRANDS_INFORMATION()}";
    private static String _sqlCarModelsByBrandInformation = "{call CAR_MODELS_BY_BRAND_INFORMATION(?)}";
    private ResultSet rs;
    
    public int create(String username, Car _car) throws Exception {

        CallableStatement cstmt;

        int response = 0;


        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlAddVehicle);
            //Parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //Parametros de entrada
            cstmt.setString(2, username);
            cstmt.setString(3, _car.get_serial());
            cstmt.setString(4, _car.get_brand());
            cstmt.setString(5, _car.get_model());
            cstmt.setInt(6, _car.get_year());
            cstmt.execute();

            response = cstmt.getInt(1);
            return response;


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
    
    public ArrayList<Car> getCars(String username) throws Exception {

        User _userFail = null;
        ArrayList<Car> carsList = new ArrayList<Car>();
        
        CallableStatement cstmt;

        int response = 0;

        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlCarsUserInformation);
            cstmt.setString(1, username);
            rs = cstmt.executeQuery();
            while(rs.next()){
                Car car = new Car(rs.getString("carserial"), 
                                  rs.getString("carbrand"),
                                  rs.getString("carmodel"), 
                                  rs.getInt("caryear"));
                car.set_error(0);
                carsList.add(car);
            }
            return carsList;


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
    
    public int delete(String serial, String username) throws Exception {

        CallableStatement cstmt;

        int response = 0;


        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlRemoveVehicle);
            //Parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //Parametros de entrada
            cstmt.setString(2, serial);
            cstmt.setString(3, username);
            cstmt.execute();

            response = cstmt.getInt(1);
            return response;


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
    
    public ArrayList<String> getBrands() throws Exception {

        User _userFail = null;
        ArrayList<String> brandsList = new ArrayList<String>();
        
        CallableStatement cstmt;

        int response = 0;

        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlCarBrandsInformation);
            rs = cstmt.executeQuery();
            while(rs.next()){
                brandsList.add(rs.getString("brand"));
            }
            return brandsList;


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
    
    public ArrayList<String> getModelsByBrand(String brandname) throws Exception {

        User _userFail = null;
        ArrayList<String> modelsByBrand = new ArrayList<String>();
        
        CallableStatement cstmt;

        int response = 0;

        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlCarModelsByBrandInformation);
            cstmt.setString(1, brandname);
            rs = cstmt.executeQuery();
            while(rs.next()){
                modelsByBrand.add(rs.getString("model"));
            }
            return modelsByBrand;


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
    
    
}
