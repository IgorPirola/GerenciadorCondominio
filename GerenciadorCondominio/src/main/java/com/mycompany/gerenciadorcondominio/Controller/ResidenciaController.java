/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Controller;

import com.mycompany.gerenciadorcondominio.DAO.ResidenciaDAO;
import com.mycompany.gerenciadorcondominio.Model.Proprietario;
import com.mycompany.gerenciadorcondominio.Model.Residencia;
import java.sql.SQLException;

/**
 *
 * @author Igor
 */
public class ResidenciaController {
    
    public boolean insertResidencia(Proprietario prop, String rua, int numero, String cep, boolean em_dia) throws SQLException{
        Residencia residencia = new Residencia(prop, rua, numero, cep, em_dia);
        return new ResidenciaDAO().bdInsert(residencia);
    }
}
