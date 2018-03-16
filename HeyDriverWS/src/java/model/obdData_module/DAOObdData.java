/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.obdData_module;

import common.entities.ObdData;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import model.DAO;

/**
 *
 * @author crist
 */
public class DAOObdData extends DAO{
    private Connection _bdCon;
    private static String _sqlAddObdData = "{?=call ADD_OBDDATA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    
    private static String _sqlHistoryInformation = "{call HISTORY_INFORMATION_CAR(?)}";
    private ResultSet rs;
    
    public int create(ObdData _obddata) throws Exception {

        CallableStatement cstmt;

        int response = 0;


        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlAddObdData);
            //Parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);
            
            //Parametros de entrada
            cstmt.setFloat(2, _obddata.getAir_Intake_Temperature());            
            cstmt.setFloat(3, _obddata.getAmbient_Air_Temperature());
            cstmt.setFloat(4, _obddata.getEngine_Coolant_Temperature());
            cstmt.setFloat(5, _obddata.getBarometric_Pressure());
            cstmt.setFloat(6, _obddata.getFuel_Pressure());
            cstmt.setFloat(7, _obddata.getIntake_Manifold_Pressure());
            cstmt.setFloat(8, _obddata.getEngine_Load());
            cstmt.setString(9, _obddata.getEngine_Runtime());
            cstmt.setFloat(10, _obddata.getEngine_RPM());
            cstmt.setFloat(11, _obddata.getVehicle_Speed());
            cstmt.setFloat(12, _obddata.getMass_Air_Flow());
            cstmt.setFloat(13, _obddata.getThrottle_Position());            
            cstmt.setString(14, _obddata.getTrouble_Codes());
            cstmt.setFloat(15, _obddata.getFuel_Level());
            cstmt.setString(16, _obddata.getFuel_type());
            cstmt.setFloat(17, _obddata.getFuel_Consumption_Rate());
            cstmt.setFloat(18, _obddata.getTiming_Advance());
            cstmt.setString(19, _obddata.getDiagnostic_Trouble_Codes());
            cstmt.setFloat(20, _obddata.getCommand_Equivalence_Ratio());
            cstmt.setFloat(21, _obddata.getControl_Module_Power_Supply());
            cstmt.setFloat(22, _obddata.getFuel_Rail_Pressure());
            cstmt.setString(23, _obddata.getVehicle_Identification_Number());
            cstmt.setFloat(24, _obddata.getDistance_traveled_with_MIL_on());
            cstmt.setFloat(25, _obddata.getSTFT2());
            cstmt.setFloat(26, _obddata.getSTFT1());
            cstmt.setFloat(27, _obddata.getLTFT2());
            cstmt.setFloat(28, _obddata.getLTFT1());
            cstmt.setFloat(29, _obddata.getEngine_oil_temperature());
            cstmt.setFloat(30, _obddata.getAirFuel_Ratio());
            cstmt.setFloat(31, _obddata.getWideband_AirFuel_Ratio());                                 
            cstmt.setTimestamp(32,_obddata.getTime_mark());
            //puede cambiar 
            cstmt.setString(33, String.valueOf(_obddata.getLat()));            
            cstmt.setString(34, String.valueOf(_obddata.getLon()));
            cstmt.setString(35, String.valueOf(_obddata.getAlt()));
                       
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
    
        public ArrayList<ObdData> getHistoryBySerial(String serial) throws Exception {

        ObdData _obdDataReg = null;
        ArrayList<ObdData> obdDataList = new ArrayList<ObdData>();
        
        CallableStatement cstmt;

        int response = 0;

        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlHistoryInformation);
            cstmt.setString(1, serial);
            rs = cstmt.executeQuery();
            while(rs.next()){
                              
                ObdData dataReg = new ObdData(rs.getFloat("AIR_FUEL_RATIO"), 
                                  rs.getFloat("TIMING_ADVANCE"),
                                  rs.getFloat("ENGINE_RPM"), 
                                  rs.getFloat("STFT2"),
                                  rs.getFloat("STFT1"),
                                  rs.getFloat("LTFT2"),
                                  rs.getFloat("LTFT1"),
                                  rs.getFloat("MAF"),
                                  rs.getFloat("ENGINE_COOLANT_TEMP"),
                                  rs.getFloat("ENGINE_LOAD"),
                                  rs.getFloat("INTAKE_MANIFOLD_PRESSURE"),
                                  rs.getFloat("AIR_INTAKE_TEMP"),
                                  rs.getDouble("LAT"), // no son float
                                  rs.getDouble("LON")); // no son float
                if(rs.getDouble("LAT")!=0 && rs.getDouble("LON")!=0)
                obdDataList.add(dataReg);
            }
            return obdDataList;


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
    
}
