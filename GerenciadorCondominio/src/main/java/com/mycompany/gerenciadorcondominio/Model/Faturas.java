/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Model;

import java.util.Objects;

/**
 *
 * @author Igor
 */
public class Faturas {
   private int id;
   private Residencia res;
   private double valor;
   private int mes;
   private int ano;

    public Faturas(Residencia res, double valor, int mes, int ano) {
        this.res = res;
        this.valor = valor;
        this.mes = mes;
        this.ano = ano;
    }

    public Faturas() {
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.res);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 31 * hash + this.mes;
        hash = 31 * hash + this.ano;
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
        final Faturas other = (Faturas) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.mes != other.mes) {
            return false;
        }
        if (this.ano != other.ano) {
            return false;
        }
        return Objects.equals(this.res, other.res);
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Residencia: " + res + "\n" +
                "Valor: " + valor + "\n" +
                "Mes: " + mes + "\n" +
                "Ano: " + ano;
    }
}
