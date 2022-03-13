package com.example.washeasier;

public class ServicosModel {

    private int servicosID;
    private String descricao;

    //construtores
    public ServicosModel(int servicosID, String descricao) {
        this.servicosID = servicosID;
        this.descricao = descricao;
    }

    public ServicosModel() {
    }

    //getters e setters

    public int getServicosID() {
        return servicosID;
    }

    public void setServicosID(int servicosID) {
        this.servicosID = servicosID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
