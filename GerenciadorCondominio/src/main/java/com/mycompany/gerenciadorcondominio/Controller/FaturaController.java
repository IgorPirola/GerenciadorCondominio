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
    private FaturaDAO fatDAO = new FaturaDAO();

    public boolean insertFatura(int resID, double valor, int mes, int ano){
        Residencia res = new ResidenciaDAO().bdSelect(resID);
        
        Faturas fat = new Faturas(res, valor, mes, ano);
        return new FaturasDAO().bdInsert(fat);
    }

    public void preencherTabela(JTable jtabela){
        DefaultTableModel dtm = (DefaultTableModel) jtabela.getModel();
        int tamTabela;
        int posicaoLinha = 0;
        
        try{
            tamTabela = fatDAO.bdSelectAll().size();
            
        }catch (NullPointerException e){
            tamTabela = 0;
        }
        
        dtm.setRowCount(tamTabela);
        jtabela.setModel(dtm);
        
        if(tamTabela != 0){
            for(int i=0; i<tamTabela; i++){
                Faturas fat = fatDAO.bdSelectAll().get(i);
                jtabela.setValueAt(fat.getId(), posicaoLinha, 0);
                jtabela.setValueAt(fat.valor(), posicaoLinha, 1);
                jtabela.setValueAt(fat.mes(), posicaoLinha, 2);
                jtabela.setValueAt(fat.ano(), posicaoLinha, 3);
                jtabela.setValueAt(fat.getRes().getId(), posicaoLinha, 4);

                posicaoLinha += 1;
            }
        }
    }
    
    public void excluir(JTable jtabela){
        if(jtabela.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione uma fatura da tabela", "Aviso", 0);
        } else {
            int resposta = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja excluir a fatura?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
            );
            
            if(resposta == JOptionPane.YES_OPTION){
                fatDAO.bdRemove(Integer.parseInt(jtabela.getValueAt(jtabela.getSelectedRow(), 0).toString()));
                
                JOptionPane.showMessageDialog(null, "Fatura excluida com sucesso", "Sucesso", 1);
                preencherTabela(jtabela);
            }
        }
    }
}
