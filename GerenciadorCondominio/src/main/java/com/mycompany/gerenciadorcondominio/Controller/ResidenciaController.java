/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Controller;

import com.mycompany.gerenciadorcondominio.DAO.ProprietarioDAO;
import com.mycompany.gerenciadorcondominio.DAO.ResidenciaDAO;
import com.mycompany.gerenciadorcondominio.Model.Proprietario;
import com.mycompany.gerenciadorcondominio.Model.Residencia;
import com.mycompany.gerenciadorcondominio.Observer.Observer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Igor
 */
public class ResidenciaController implements Observer {
    private final ResidenciaDAO resDAO = new ResidenciaDAO();
    
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
                jtabela.setValueAt(res.getProp().getId(), posicaoLinha, 1);
                jtabela.setValueAt(res.getRua(), posicaoLinha, 2);
                jtabela.setValueAt(res.getNumero(), posicaoLinha, 3);
                jtabela.setValueAt(res.getCep(), posicaoLinha, 4);
                jtabela.setValueAt(res.isEm_dia(), posicaoLinha, 5);
                

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
                if(resDAO.bdRemove(Integer.parseInt(jtabela.getValueAt(jtabela.getSelectedRow(), 0).toString()))){
                    JOptionPane.showMessageDialog(null, "Residencia excluida com sucesso", "Sucesso", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Residencia não excluida\nVerifique se a residencia possui dependencias", "Aviso", 3);
                }
                preencherTabela(jtabela);
            }
        }
    }
    
    @Override
    public void update(int idRes, boolean comando) {
        resDAO.bdUpdate(idRes, comando);
    }
}
