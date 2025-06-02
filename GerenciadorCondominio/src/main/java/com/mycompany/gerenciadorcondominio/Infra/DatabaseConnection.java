/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Igor
 */
public class DatabaseConnection {
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/condominio",
            "root",
            "1234"
            );
        }catch (SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    public static void closeConnection(Connection con){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
