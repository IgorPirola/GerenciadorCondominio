/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.DAO;

import com.mycompany.gerenciadorcondominio.Infra.DatabaseConnection;
import com.mycompany.gerenciadorcondominio.Model.Proprietario;
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
public class ProprietarioDAO {
    private Connection con;

    public ProprietarioDAO(){
        if (con == null) con = DatabaseConnection.getConnection();
    }
    
    public void closeBd(){
        DatabaseConnection.closeConnection(con);
    }
    
    public boolean bdInsert(Proprietario prop){
        if (con != null){
            try {
                String sql = "INSERT INTO Proprietario(nome, idade, cpf, rg) VALUES (?,?,?,?)";
            
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setString(1, prop.getNome());
                comando.setInt(2, prop.getIdade());
                comando.setString(3, prop.getCpf());
                comando.setString(4, prop.getRg());
                comando.executeUpdate();
                return true;
            } catch(SQLException e){
                return false;
            }
        }
        return false;
    }
    
    public Proprietario bdSelect(int idProp){
        if(con!=null){
            try {
                String sql="SELECT nome, idade, cpf, rg"+ " FROM Proprietario WHERE id = ?";
                PreparedStatement comando = con.prepareStatement(sql);
                comando.setInt(1, idProp);

                ResultSet resultado = comando.executeQuery();
                if(resultado.next()){
                    Proprietario prop = new Proprietario();
                    prop.setId(idProp);
                    prop.setNome(resultado.getString("nome"));
                    prop.setIdade(resultado.getInt("idade"));
                    prop.setCpf(resultado.getString("cpf"));
                    prop.setRg(resultado.getString("rg"));

                    return prop;
                }
            } catch(SQLException e){
                return null;
            }
        }
        return null;
    }
    
    public List<Proprietario> bdSelectAll(){
        List<Proprietario> props = new ArrayList<>();
        if(con!=null){
            try{
                
                String sql="SELECT id, nome, idade, cpf, rg"+ " FROM Proprietario";
                PreparedStatement comando = con.prepareStatement(sql);            
                ResultSet resultado = comando.executeQuery();

                while(resultado.next()){
                    Proprietario prop = new Proprietario();
                    prop.setId(resultado.getInt("id"));
                    prop.setNome(resultado.getString("nome"));
                    prop.setIdade(resultado.getInt("idade"));
                    prop.setCpf(resultado.getString("cpf"));
                    prop.setRg(resultado.getString("rg"));

                    props.add(prop);
                }
                return props;
            } catch (SQLException e){
                return null;
            }
        }
        return null;
    }
    
    public boolean bdRemove(int id){
        if(con!=null){
            try {
                String sql="DELETE FROM Proprietario WHERE id = ?";
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
