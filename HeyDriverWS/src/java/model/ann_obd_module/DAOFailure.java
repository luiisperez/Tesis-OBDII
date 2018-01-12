/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ann_obd_module;

import common.entities.Car;
import common.entities.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import model.DAO;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;

/**
 *
 * @author LAPGrock
 */
public class DAOFailure  extends DAO{
    private Connection _bdCon;
    private static String _sqlAddFailure = "{?=call ADD_FAILURE(?,?,?)}";
    private static String _sqlGetAllFailures = "{call GET_ALL_FAILURES()}";
    private static String _sqlGetAllFailuresByBrand = "{call GET_FAILURES_BY_BRAND(?)}";
    private static String _sqlGetAllFailuresByModel = "{call GET_FAILURES_BY_MODEL(?,?)}";
    private static String _sqlBrandsByFailure = "{?=call GET_BRANDS_BY_FAILURE(?)}";
    private static String _sqlModelsByFailure = "{?=call GET_MODELS_BY_FAILURE(?)}";
    private ResultSet rs;
    
    public int create(String serial, String brand, String model, String failure) throws Exception {

        CallableStatement cstmt;

        int response = 0;


        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlAddFailure);
            //Parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //Parametros de entrada
            cstmt.setString(2, serial);
            cstmt.setString(3, brand);
            cstmt.setString(4, model);
            cstmt.setString(5, failure);
            cstmt.execute();

            response = cstmt.getInt(1);
            _bdCon.close();
            return 200;


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
}