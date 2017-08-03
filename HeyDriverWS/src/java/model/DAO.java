/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author LAPGrock
 */
public class DAO {


    private static Connection conn = null;

    private static Connection conInstance;
    /*private Connection _conn = getBdConnect();
    private Statement _st;
    private ResultSet _rs;
    /**
     * Metodo para devolver una unica instancia de la conexion
     * @return instancia de la conexion
     */
    public static Connection getConInstance() throws Exception {

        try {
            conInstance = getBdConnect();
        }catch (Exception e){
            throw e;
        }

        return conInstance;
    }

    /**
     * Metodo que realiza la conexion con la base de datos
     * @return Conexion hecha a la base de datos
     * @throws ClassNotFoundException Si la clase no es encontrada
     * @throws SQLException Problemas con sql
     * @throws Exception
     * @see Connection
     * @see Statement
     */
    public static Connection getBdConnect() throws Exception
    {

        try
        {
            Class.forName( Registry.BD_CLASS_FOR_NAME );
            conn = DriverManager.getConnection( Registry.BD_URL, Registry.BD_USER, Registry.BD_PASSWORD );
        }
        catch ( ClassNotFoundException e )
        {
            throw e;
        }
        catch ( SQLException e )
        {
            throw e;
        }
        return conn;
    }

    /**
     *
     */
    protected static void closeConnection() throws SQLException
    {
        try
        {
            conn.close();
        }
        catch ( SQLException e )
        {
            throw e;
        }
    }

}