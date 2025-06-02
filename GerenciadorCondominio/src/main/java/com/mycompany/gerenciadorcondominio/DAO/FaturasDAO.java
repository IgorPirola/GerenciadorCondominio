/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.DAO;

import com.mycompany.gerenciadorcondominio.Infra.DatabaseConnection;
import com.mycompany.gerenciadorcondominio.Model.Faturas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Igor
 */
public class FaturasDAO {
    private Connection con;

    public FaturasDAO(){
        if (con == null) con = DatabaseConnection.getConnection();
    }

    public void closeBd(){
        DatabaseConnection.closeConnection(con);
    }
    
    public boolean bdInsert(Faturas fat){
        if (con != null){
            try {
                String sql = "INSERT INTO Debitos (id_res, valor, mes, ano) VALUES (?,?,?,?)";

                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, fat.getRes().getId());
                comando.setDouble(2, fat.getValor());
                comando.setInt(3, fat.getMes());
                comando.setInt(4, fat.getAno());
                comando.executeUpdate();
                return true;
            } catch (SQLException e){
                return false;
            }
        }
        return false;
    }
    
    public Faturas bdSelect(int idFat) throws SQLException{
        if(con!=null){
            try {
                String sql="SELECT id_res, valor, mes, ano FROM Debitos WHERE id = ?";
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, idFat);
                
                ResultSet resultado = comando.executeQuery();
                
                if(resultado.next()){
                    Faturas fatura = new Faturas();
                    fatura.setId(idFat);
                    fatura.setRes(new ResidenciaDAO().bdSelect(resultado.getInt("id_res")));
                    fatura.setValor(resultado.getDouble("valor"));
                    fatura.setMes(resultado.getInt("mes"));
                    fatura.setAno(resultado.getInt("ano"));
                    
                    return fatura;
                }
            } catch (SQLException e){
                return null;
            }
        }
        return null;
    }
    
    public List<Faturas> bdSelectAll(){
        List<Faturas> faturas = new ArrayList<>();
        
        if(con!=null){
            try {
                String sql="SELECT id, id_res, valor, mes, ano FROM Debitos";
                PreparedStatement comando = con.prepareStatement(sql);            
                ResultSet resultado = comando.executeQuery();
                
                if(resultado.next()){
                    Faturas fatura = new Faturas();
                    
                    fatura.setId(resultado.getInt("id"));
                    fatura.setRes(new ResidenciaDAO().bdSelect(resultado.getInt("id_res")));
                    fatura.setValor(resultado.getDouble("valor"));
                    fatura.setMes(resultado.getInt("mes"));
                    fatura.setAno(resultado.getInt("ano"));
                    
                    faturas.add(fatura);
                }
                return faturas;
            } catch (SQLException e){
                return null;
            }
        }
        return null;
    }
    
    public boolean bdRemove(int id){
        if(con!=null){
            try {
                String sql="DELETE FROM Debitos WHERE id = ?";
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, id);

                comando.executeQuery();
                return true;
            } catch (SQLException e){
                return false;
            }
        }
        return false;
    }
    
}
