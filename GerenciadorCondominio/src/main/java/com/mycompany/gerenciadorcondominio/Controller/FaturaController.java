/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Controller;

import com.mycompany.gerenciadorcondominio.DAO.FaturasDAO;
import com.mycompany.gerenciadorcondominio.Model.Faturas;
import com.mycompany.gerenciadorcondominio.Model.Residencia;
import java.sql.SQLException;

/**
 *
 * @author Igor
 */
public class FaturaController {
    
    public boolean insertFatura(Residencia res, double valor, int mes, int ano) throws SQLException{
        Faturas fat = new Faturas(res, valor, mes, ano);
        return new FaturasDAO().bdInsert(fat);
    }
}
