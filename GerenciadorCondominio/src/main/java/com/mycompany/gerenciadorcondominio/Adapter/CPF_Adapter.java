/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Adapter;

import br.com.caelum.stella.validation.CPFValidator;

/**
 *
 * @author Igor
 */
public class CPF_Adapter implements ValidacaoCPF {

    @Override
    public boolean isAValideCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try{
            cpfValidator.assertValid(cpf);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
}
