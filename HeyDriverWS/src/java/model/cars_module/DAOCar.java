/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cars_module;

import common.entities.Car;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import model.DAO;
import model.Registry;

/**
 *
 * @author LAPGrock
 */
public class DAOCar extends DAO{
    private Connection _bdCon;
    private static String _sqlAddVehicle = "{?=call ADD_VEHICLE(?,?,?,?,?)}";
    //private static String _sqlUserLogin = "{?=call USER_LOGIN(?,?)}";
    //private static String _sqlUserInfo = "{call USER_INFORMATION(?)}";
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
}
