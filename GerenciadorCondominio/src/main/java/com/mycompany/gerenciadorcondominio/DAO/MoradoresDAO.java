/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.DAO;

import com.mycompany.gerenciadorcondominio.Infra.DatabaseConnection;
import com.mycompany.gerenciadorcondominio.Model.Moradores;
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
public class MoradoresDAO {
    private Connection con;

    public MoradoresDAO() {
        if (con == null) con = DatabaseConnection.getConnection();
    }
    
    public boolean bdInsert(Moradores morador){
        if (con != null){
            try {
                String sql = "INSERT INTO Moradores (id_res, nome, idade, cpf, rg) VALUES (?,?,?,?,?)";
        
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, morador.getRes().getId());
                comando.setString(2, morador.getNome());
                comando.setInt(3, morador.getIdade());
                comando.setString(4, morador.getCpf());
                comando.setString(5, morador.getRg());
                comando.executeUpdate();
                return true;
            } catch(SQLException e){
                return false;
            } finally {
                DatabaseConnection.closeConnection(con);
            }
            
        }
        return false;
    }
    
    public Moradores bdSelect(int idMorador) throws SQLException{
        if(con!=null){
            String sql="SELECT id_res, nome, idade, cpf, rg"+ " FROM Moradores WHERE id = ?";
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setInt(1, idMorador);
            
            ResultSet resultado = comando.executeQuery();
            
            if(resultado.next()){
                Moradores morador = new Moradores();
                morador.setId(idMorador);
                morador.setRes(new ResidenciaDAO().bdSelect(resultado.getInt("id_res")));
                morador.setNome(resultado.getString("nome"));
                morador.setIdade(resultado.getInt("idade"));
                morador.setCpf(resultado.getString("cpf"));
                morador.setRg(resultado.getString("rg"));
                
                return morador;
            }
        }
        return null;
    }
    
    public List<Moradores> bdSelectAll(){
        List<Moradores> moradores = new ArrayList<>();
        
        if(con!=null){
            try {
                String sql="SELECT id, id_res, nome, idade, cpf, rg"+ " FROM Moradores";
                PreparedStatement comando = con.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery();

                while (resultado.next()){
                    Moradores morador = new Moradores();
                    morador.setId(resultado.getInt("id"));
                    morador.setRes(new ResidenciaDAO().bdSelect(resultado.getInt("id_res")));
                    morador.setNome(resultado.getString("nome"));
                    morador.setIdade(resultado.getInt("idade"));
                    morador.setCpf(resultado.getString("cpf"));
                    morador.setRg(resultado.getString("rg"));

                    moradores.add(morador);
                }
                return moradores;
            } catch(SQLException e){
                return null;
            }
        }
        return null;
    }
    
    public boolean bdRemove(int id){
        if(con!=null){
            try {
                String sql="DELETE FROM Moradores WHERE id = ?";
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
