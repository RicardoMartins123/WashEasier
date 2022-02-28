package com.example.washeasier;

public class EstacaoModel {

    private int estacaoID;
    private String nomeEmpresa;
    private String horarioServico;
    private float estLat;
    private float estLong;

    //construtores
    public EstacaoModel(int estacaoID, String nomeEmpresa, String horarioServico, float estLat, float estLong) {
        this.estacaoID = estacaoID;
        this.nomeEmpresa = nomeEmpresa;
        this.horarioServico = horarioServico;
        this.estLat = estLat;
        this.estLong = estLong;
    }

    public EstacaoModel() {
    }

    //toString
    @Override
    public String toString() {
        return "EstacaoModel{" +
                "estacaoID=" + estacaoID +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", horarioServico='" + horarioServico + '\'' +
                ", estLat=" + estLat +
                ", estLong=" + estLong +
                '}';
    }


    //getters and setters
    public int getEstacaoID() {
        return estacaoID;
    }

    public void setEstacaoID(int estacaoID) {
        this.estacaoID = estacaoID;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getHorarioServico() {
        return horarioServico;
    }

    public void setHorarioServico(String horarioServico) {
        this.horarioServico = horarioServico;
    }

    public float getEstLat() {
        return estLat;
    }

    public void setEstLat(float estLat) {
        this.estLat = estLat;
    }

    public float getEstLong() {
        return estLong;
    }

    public void setEstLong(float estLong) {
        this.estLong = estLong;
    }
}
