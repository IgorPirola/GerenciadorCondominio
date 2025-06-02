/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.DAO;

import com.mycompany.gerenciadorcondominio.Infra.DatabaseConnection;
import com.mycompany.gerenciadorcondominio.Model.Residencia;
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
public class ResidenciaDAO {
    private Connection con;

    public ResidenciaDAO(){
        if (con == null) con = DatabaseConnection.getConnection();
    }

    public void closeBd(){
        DatabaseConnection.closeConnection(con);
    }

    public boolean bdInsert(Residencia res){
        if (con != null){
            try{
                String sql = "INSERT INTO Residencia (id_prop, rua, numero, cep, em_dia) VALUES (?,?,?,?,?)";
            
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, res.getProp().getId());
                comando.setString(2, res.getRua());
                comando.setInt(3, res.getNumero());
                comando.setString(4, res.getCep());
                comando.setBoolean(5, res.isEm_dia());
                comando.executeUpdate();
                return true;
            } catch (SQLException e){
                return false;
            }
        }
        return false;
    }
    
    public boolean bdUpdate(int idRes, boolean comando){
        if(con!=null){
            try {
                String sql="UPDATE Residencia SET em_dia = ? WHERE id = ?";
                PreparedStatement comandoSql = con.prepareStatement(sql);
                comandoSql.setBoolean(1, comando);
                comandoSql.setInt(2, idRes);
                comandoSql.executeUpdate();
                return true;
            } catch (SQLException e){ 
                System.out.println(e);
                return false;
            }
        }
        return false;
    }
    
    public Residencia bdSelect(int idRes){
        if(con!=null){
            try{
                String sql="SELECT id_prop, rua, numero, cep, em_dia"+ " FROM Residencia WHERE id = ?";
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, idRes);

                ResultSet resultado = comando.executeQuery();
                
                if(resultado.next()){
                    Residencia res = new Residencia();
                    res.setId(idRes);
                    res.setProp(new ProprietarioDAO().bdSelect(resultado.getInt("id_prop")));
                    res.setRua(resultado.getString("rua"));
                    res.setNumero(resultado.getInt("numero"));
                    res.setCep(resultado.getString("cep"));
                    res.setEm_dia(resultado.getBoolean("em_dia"));
                    return res;
                }
            } catch(SQLException e){
                return null;
            }
            
        }
        return null;
    }

    public List<Residencia> bdSelectAll(){
        List<Residencia> resL = new ArrayList<>();
        if(con!=null){
            try{
                String sql="SELECT id, id_prop, rua, numero, cep, em_dia FROM Residencia";
                PreparedStatement comando = con.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery();
                
                while(resultado.next()){
                    Residencia res = new Residencia();
                    
                    res.setId(resultado.getInt("id"));
                    res.setProp(new ProprietarioDAO().bdSelect(resultado.getInt("id_prop")));
                    res.setRua(resultado.getString("rua"));
                    res.setNumero(resultado.getInt("numero"));
                    res.setCep(resultado.getString("cep"));
                    res.setEm_dia(resultado.getBoolean("em_dia"));
                    resL.add(res);
                }
                return resL;
            } catch(SQLException e){
                return null;
            }
            
        }
        return null;
    }
    
    public boolean bdRemove(int id){
        if(con!=null){
            try {
                String sql="DELETE FROM Residencia WHERE id = ?";
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, id);

                comando.executeUpdate();
                return true;
            } catch (SQLException e){
                return false;
            }
        }
        return false;
    }

}
