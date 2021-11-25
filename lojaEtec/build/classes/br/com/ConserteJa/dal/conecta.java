package br.com.ConserteJa.dal;
import java.sql.*;
public class conecta {
    public static Connection conector(){
        java.sql.Connection conecta;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/lojaetec";
        String user = "root";
        try {
            Class.forName(driver);
            conecta = DriverManager.getConnection(url, user, "");
            return conecta;
        } catch (Exception e){
            return null;
        }
    }
}
