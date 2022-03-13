package com.example.washeasier;

public class Est_ServModel {
    private int estacaoID;
    private int servicosID;
    private String preco;

    //construtores
    public Est_ServModel(int estacaoID, int servicosID, String preco) {
        this.estacaoID = estacaoID;
        this.servicosID = servicosID;
        this.preco = preco;
    }

    public Est_ServModel() {
    }

    //getters e setters
    public int getEstacaoID() {
        return estacaoID;
    }

    public void setEstacaoID(int estacaoID) {
        this.estacaoID = estacaoID;
    }

    public int getServicosID() {
        return servicosID;
    }

    public void setServicosID(int servicosID) {
        this.servicosID = servicosID;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
