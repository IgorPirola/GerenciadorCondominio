/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciadorcondominio.Model;


/**
 *
 * @author Igor
 */
public class Residencia {
    private int id;
    private Proprietario prop;
    private String rua;
    private int numero;
    private String cep;
    private boolean em_dia;

    public Residencia(Proprietario prop, String rua, int numero, String cep, boolean em_dia) {
        this.prop = prop;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.em_dia = em_dia;
    }

    public Residencia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proprietario getProp() {
        return prop;
    }

    public void setProp(Proprietario prop) {
        this.prop = prop;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isEm_dia() {
        return em_dia;
    }

    public void setEm_dia(boolean em_dia) {
        this.em_dia = em_dia;
    }
}
