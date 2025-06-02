/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Controller;

import com.mycompany.gerenciadorcondominio.Adapter.CPF_Adapter;
import com.mycompany.gerenciadorcondominio.Adapter.ValidacaoCPF;
import com.mycompany.gerenciadorcondominio.DAO.MoradoresDAO;
import com.mycompany.gerenciadorcondominio.DAO.ResidenciaDAO;
import com.mycompany.gerenciadorcondominio.Model.Moradores;
import com.mycompany.gerenciadorcondominio.Model.Residencia;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Igor
 */
public class MoradorController {
    private final ValidacaoCPF validador = new CPF_Adapter();
    private MoradoresDAO morDAO = new MoradoresDAO();
    
    public boolean insertMorador(String nome, int idade, String cpf, String rg, int resID){
        if(!validador.isAValideCpf(cpf)){
            return false;
        }
        
        Residencia res = new ResidenciaDAO().bdSelect(resID);
        
        Moradores morador = new Moradores(nome, idade, cpf, rg, res);
        return new MoradoresDAO().bdInsert(morador);
    }
    
    public void preencherTabela(JTable jtabela){
        DefaultTableModel dtm = (DefaultTableModel) jtabela.getModel();
        int tamTabela;
        int posicaoLinha = 0;
        
        try{
            tamTabela = morDAO.bdSelectAll().size();
            
        }catch (NullPointerException e){
            tamTabela = 0;
        }
        
        dtm.setRowCount(tamTabela);
        jtabela.setModel(dtm);
        
        if(tamTabela != 0){
            for(int i=0; i<tamTabela; i++){
                Moradores mor = morDAO.bdSelectAll().get(i);
                jtabela.setValueAt(mor.getId(), posicaoLinha, 0);
                jtabela.setValueAt(mor.getNome(), posicaoLinha, 1);
                jtabela.setValueAt(mor.getIdade(), posicaoLinha, 2);
                jtabela.setValueAt(mor.getCpf(), posicaoLinha, 3);
                jtabela.setValueAt(mor.getRg(), posicaoLinha, 4);
                jtabela.setValueAt(mor.getRes().getId(), posicaoLinha, 5);

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
                "Tem certeza que deseja excluir o morador?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
            );
            
            if(resposta == JOptionPane.YES_OPTION){
                morDAO.bdRemove(Integer.parseInt(jtabela.getValueAt(jtabela.getSelectedRow(), 0).toString()));
                
                JOptionPane.showMessageDialog(null, "Morador excluido com sucesso", "Sucesso", 1);
                preencherTabela(jtabela);
            }
        }
    }
}
