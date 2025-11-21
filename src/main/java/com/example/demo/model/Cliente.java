package com.example.demo.model;

public class Cliente {

    private String nome;
    private String cognome;
    private String luogoNascita;
    private int annoNascita;
    private String indirizzo;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getLuogoNascita() { return luogoNascita; }

    public void setLuogoNascita(String luogoNascita) { this.luogoNascita = luogoNascita; }

    public int getAnnoNascita() { return annoNascita; }

    public void setAnnoNascita(int annoNascita) { this.annoNascita = annoNascita; }

    public String getIndirizzo() { return indirizzo; }

    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
}
