package it.unina.studenti.oortof.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
    private String CF;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String luogoNascita;
    private Genere sesso;
    private String email;
    private int totalePunti;

    public Cliente(String CF, String nome, String cognome, Date dataNascita, String luogoNascita, Genere sesso, String email, int totalePunti) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.sesso = sesso;
        this.email = email;
        this.totalePunti = totalePunti;
    }

    public Cliente(ResultSet rSet)
    {
        try {
            CF = rSet.getString(0);
            nome = rSet.getString(1);
            cognome = rSet.getString(2);
            dataNascita = rSet.getDate(3);
            luogoNascita = rSet.getString(4);
            sesso = Genere.valueOf(rSet.getString(5));
            email = rSet.getString(6);
            totalePunti = rSet.getInt(7);
        } catch (SQLException e)
        {

        }
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public Genere getSesso() {
        return sesso;
    }

    public void setSesso(Genere sesso) {
        this.sesso = sesso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalePunti() {
        return totalePunti;
    }

    public void setTotalePunti(int totalePunti) {
        this.totalePunti = totalePunti;
    }
}
