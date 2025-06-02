/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Model;

import com.mycompany.gerenciadorcondominio.Observer.Observer;

/**
 *
 * @author Igor
 */
public class Proprietario extends Pessoa implements Observer {
    private int id;
    
    public Proprietario(String nome, int idade, String cpf, String rg) {
        super(nome, idade, cpf, rg);
    }

    public Proprietario() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proprietario other = (Proprietario) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                super.toString();
    }

    @Override
    public void update(int idFatura) {
        
    }
}
