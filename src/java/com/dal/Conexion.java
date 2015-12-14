/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import java.sql.*;

/**
 *
 * @author Julen
 */
public class Conexion {

    private Connection conn;
    private Statement statement;

    public Conexion()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/GestionClubes2","root","root");
            statement = conn.createStatement();
        }
        catch(Exception e)
        {
             System.out.println("No se ha podido cargar el Driver JDBC-ODBC");
        }
    }

    public void cerrar()
    {
         try
         {
            if (getStatement()!=null)
                getStatement().close();
            if (getConn()!=null)
                getConn().close();
         }
         catch(Exception e)
         {
            System.out.println("No se ha podido cerrar la conexión.");
         }
    }

    public Connection getConn()
    {
        return conn;
    }

    public Statement getStatement()
    {
        return statement;
    }

}
