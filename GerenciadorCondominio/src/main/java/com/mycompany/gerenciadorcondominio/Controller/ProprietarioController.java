/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Controller;

import com.mycompany.gerenciadorcondominio.Adapter.CPF_Adapter;
import com.mycompany.gerenciadorcondominio.Adapter.ValidacaoCPF;
import com.mycompany.gerenciadorcondominio.DAO.ProprietarioDAO;
import com.mycompany.gerenciadorcondominio.Model.Proprietario;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Igor
 */
public class ProprietarioController {
    private final ValidacaoCPF validador = new CPF_Adapter();
    private ProprietarioDAO propDAO = new ProprietarioDAO();
    
    public boolean insertProprietario(String nome, int idade, String cpf, String rg){        
        if(!validador.isAValideCpf(cpf)){
            return false;
        }
        
        Proprietario prop = new Proprietario(nome, idade, cpf, rg);
        return new ProprietarioDAO().bdInsert(prop);
    }
    
    public void preencherTabela(JTable jtabela){
        DefaultTableModel dtm = (DefaultTableModel) jtabela.getModel();
        int tamTabela;
        int posicaoLinha = 0;
        
        try{
            tamTabela = propDAO.bdSelectAll().size();
            
        }catch (NullPointerException e){
            tamTabela = 0;
        }
        
        dtm.setRowCount(tamTabela);
        jtabela.setModel(dtm);
        
        if(tamTabela != 0){
            for(int i=0; i<tamTabela; i++){
                Proprietario prop = propDAO.bdSelectAll().get(i);
                jtabela.setValueAt(prop.getId(), posicaoLinha, 0);
                jtabela.setValueAt(prop.getNome(), posicaoLinha, 1);
                jtabela.setValueAt(prop.getIdade(), posicaoLinha, 2);
                jtabela.setValueAt(prop.getCpf(), posicaoLinha, 3);
                jtabela.setValueAt(prop.getRg(), posicaoLinha, 4);

                posicaoLinha += 1;
            }
        }
    }
    
    public void excluir(JTable jtabela){
        if(jtabela.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione um produto da tabela", "Aviso", 0);
        } else {
            int resposta = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja excluir o proprietario?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
            );
            
            if(resposta == JOptionPane.YES_OPTION){
                propDAO.bdRemove(Integer.parseInt(jtabela.getValueAt(jtabela.getSelectedRow(), 0).toString()));
                
                JOptionPane.showMessageDialog(null, "Proprietario excluido com sucesso", "Sucesso", 1);
                preencherTabela(jtabela);
            }
        }
    }
}
