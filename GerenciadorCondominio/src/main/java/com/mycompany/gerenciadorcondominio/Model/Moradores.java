/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Model;

import com.mycompany.gerenciadorcondominio.Observer.Observer;
import java.util.Objects;

/**
 *
 * @author Igor
 */
public class Moradores extends Pessoa implements Observer {
    private int id;
    private Residencia res;
    
    public Moradores(String nome, int idade, String cpf, String rg, Residencia res) {
        super(nome, idade, cpf, rg);
        this.res = res;
    }

    public Moradores() {
        
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Residencia getRes() {
        return res;
    }

    public void setRes(Residencia res) {
        this.res = res;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.res);
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
        final Moradores other = (Moradores) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Res: " + res + "\n" +
                super.toString();
    }

    @Override
    public void update(int idFatura) {
        
    }
}
