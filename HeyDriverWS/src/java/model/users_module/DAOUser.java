/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.users_module;

import common.entities.User;
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
public class DAOUser extends DAO{
    private Connection _bdCon;
    private static String _sqlUserLogin = "{?=call USER_LOGIN(?,?)}";
    private static String _sqlCreateUser = "{?=call CREATE_USER(?,?,?,?,?)}";
    private static String _sqlUserInfo = "{call USER_INFORMATION(?)}";
    private ResultSet rs;
    
    public User create(User _user) throws Exception {

        User _userFail = null;

        CallableStatement cstmt;

        int response = 0;


        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlCreateUser);
            //Parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //Parametros de entrada
            cstmt.setString(2, _user.get_username());
            cstmt.setString(3, _user.get_firstname());
            cstmt.setString(4, _user.get_lastname());
            cstmt.setString(5, _user.get_email());
            cstmt.setString(6, _user.get_password());
            cstmt.execute();

            response = cstmt.getInt(1);

            if (response == Registry.RESULT_CODE_OK) {
                
                return _user;
                
            }
            else {

                return _userFail;
                
            }


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
    
    public User login(User _user) throws Exception {

        User _userFail = null;

        User _userOK = null;

        CallableStatement cstmt;

        int response = 0;


        try {
            _bdCon = DAO.getBdConnect();
            cstmt = _bdCon.prepareCall(_sqlUserLogin);
            //Parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //Parametros de entrada
            cstmt.setString(2, _user.get_email());
            cstmt.setString(3, _user.get_password());
            cstmt.execute();

            response = cstmt.getInt(1);

            if (response == Registry.RESULT_CODE_OK) {
                cstmt = null;
                cstmt = _bdCon.prepareCall(_sqlUserInfo);
                cstmt.setString(1, _user.get_email());
                rs = cstmt.executeQuery();
                while(rs.next()){
                    _userOK = new User(rs.getString("personusername"), 
                                       rs.getString("personpassword"),
                                       rs.getString("personmail"), 
                                       rs.getString("personfirstname"), 
                                       rs.getString("personlastname"));
                }
                return _userOK;
                
            }
            else {

                return _userFail;
                
            }


        } catch (SQLException ex) {

            throw ex;

        } catch (Exception ex) {
            
            throw ex;

        } finally {
            _bdCon.close();
        }
    }
}
