/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cars_module;
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
public class DAOSyncNHTSA extends DAO{
    private Connection _bdCon;
    private static String _sqlAddVehicle = "{?=call ADD_VEHICLE(?,?,?,?,?)}";
    private static String _sqlCarsUserInformation = "{call CARS_USER_INFORMATION(?)}";
    private static String _sqlRemoveVehicle = "{?=call REMOVE_VEHICLE(?,?)}";
    private static String _sqlSyncNHTSAInformationBrands = "{?=call SYNC_NHTSA_INFORMATION_BRANDS(?)}";
    private static String _sqlSyncNHTSAInformationModels = "{?=call SYNC_NHTSA_INFORMATION_MODELS(?,?)}";
    private static String _sqlBrandsInformation = "{?=call BRANDS_INFORMATION()}";
    private static String _sqlModelsByBrandInformation = "{?=call MODELS_BY_BRAND_INFORMATION(?)}";
    private ResultSet rs;
    
    public int SyncBrandsInformation() throws Exception {

        ApiNHTSA info = new ApiNHTSA();
        ArrayList<String> brands = info.getVehicleBrands();
        
        CallableStatement cstmt;

        int response = 0;


            for(String brand : brands){
                
                try {   
                    _bdCon = DAO.getBdConnect();
                    cstmt = _bdCon.prepareCall(_sqlSyncNHTSAInformationBrands);
                    //Parametro de salida
                    cstmt.registerOutParameter(1, Types.INTEGER);

                    //Parametros de entrada
                    cstmt.setString(2, brand);

                    ArrayList<String> models = info.getModelsOfABrand(brand);
                    if (models.size() != 0){
                        cstmt.execute();

                        response = cstmt.getInt(1);
                        _bdCon.close();
                        SyncModelsByInformation(brand);
                    }else{
                        _bdCon.close();
                    }


                } catch (SQLException ex) {


                } catch (Exception ex) {


                } 
            }
        _bdCon.close();
        return response;
    }
    
    public int SyncModelsByInformation(String brand) throws Exception {

        ApiNHTSA info = new ApiNHTSA();
        ArrayList<String> models = info.getModelsOfABrand(brand);
        
        CallableStatement cstmt;

        int response = 0;

        if (models.size() != 0){
                for(String model : models){
                try {
                    _bdCon = DAO.getBdConnect();
                    cstmt = _bdCon.prepareCall(_sqlSyncNHTSAInformationModels);
                    //Parametro de salida
                    cstmt.registerOutParameter(1, Types.INTEGER);

                    //Parametros de entrada
                    cstmt.setString(2, model);
                    cstmt.setString(3, brand);
                    cstmt.execute();

                    response = cstmt.getInt(1);
                    _bdCon.close();
                } catch (SQLException ex) {
                

                } catch (Exception ex) {


                }
            }
        }
        _bdCon.close();
        return response;
    }
}
