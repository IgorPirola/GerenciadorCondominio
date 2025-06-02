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
    private ResidenciaDAO resDAO = new ResidenciaDAO();
    
    public boolean insertResidencia(int propID, String rua, int numero, String cep, boolean em_dia){
        Proprietario prop = new ProprietarioDAO().bdSelect(propID);
        
        Residencia residencia = new Residencia(prop, rua, numero, cep, em_dia);
        return new ResidenciaDAO().bdInsert(residencia);
    }

    public void preencherTabela(JTable jtabela){
        DefaultTableModel dtm = (DefaultTableModel) jtabela.getModel();
        int tamTabela;
        int posicaoLinha = 0;
        
        try{
            tamTabela = resDAO.bdSelectAll().size();
            
        }catch (NullPointerException e){
            tamTabela = 0;
        }
        
        dtm.setRowCount(tamTabela);
        jtabela.setModel(dtm);
        
        if(tamTabela != 0){
            for(int i=0; i<tamTabela; i++){
                Residencia res = resDAO.bdSelectAll().get(i);
                jtabela.setValueAt(res.getId(), posicaoLinha, 0);
                jtabela.setValueAt(res.getRua(), posicaoLinha, 1);
                jtabela.setValueAt(res.getNumero(), posicaoLinha, 2);
                jtabela.setValueAt(res.getCep(), posicaoLinha, 3);
                jtabela.setValueAt(res.getEm_dia(), posicaoLinha, 4);
                jtabela.setValueAt(res.getProp().getId(), posicaoLinha, 5);

                posicaoLinha += 1;
            }
        }
    }
    
    public void excluir(JTable jtabela){
        if(jtabela.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione uma Residencia da tabela", "Aviso", 0);
        } else {
            int resposta = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja excluir a residencia?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
            );
            
            if(resposta == JOptionPane.YES_OPTION){
                resDAO.bdRemove(Integer.parseInt(jtabela.getValueAt(jtabela.getSelectedRow(), 0).toString()));
                
                JOptionPane.showMessageDialog(null, "Residencia excluida com sucesso", "Sucesso", 1);
                preencherTabela(jtabela);
            }
        }
    }
}
