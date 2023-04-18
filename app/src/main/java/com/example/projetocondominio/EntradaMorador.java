package com.example.projetocondominio;

public class EntradaMorador {
    private String nome;
    private String numeroCondominio;
    private String horaEntrada;

    public EntradaMorador() {}

    public EntradaMorador(String nome, String numeroCondominio, String horaEntrada) {
        this.nome = nome;
        this.numeroCondominio = numeroCondominio;
        this.horaEntrada = horaEntrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroCondominio() {
        return numeroCondominio;
    }

    public void setNumeroCondominio(String numeroCondominio) {
        this.numeroCondominio = numeroCondominio;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
}
