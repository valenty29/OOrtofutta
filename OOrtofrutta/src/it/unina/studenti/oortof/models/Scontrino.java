package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Scontrino {
    private int codScontrino;
    private String cfCliente;
    private Timestamp dataOrario;
    private float prezzoTotale;

    public Scontrino(int codScontrino, String cfCliente, Timestamp dataOrario, float prezzoTotale)
    {
        this.codScontrino = codScontrino;
        this.cfCliente = cfCliente;
        this.dataOrario = dataOrario;
        this.prezzoTotale = prezzoTotale;
    }
    public Scontrino(ResultSet rSet)
    {
        try {
            codScontrino = rSet.getInt(0);
            cfCliente = rSet.getString(1);
            dataOrario = rSet.getTimestamp(2);
            prezzoTotale = rSet.getFloat(3);
        } catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
    }

    public int getCodScontrino() {
        return codScontrino;
    }

    public void setCodScontrino(int codScontrino) {
        this.codScontrino = codScontrino;
    }

    public String getCfCliente() {
        return cfCliente;
    }

    public void setCfCliente(String cfCliente) {
        this.cfCliente = cfCliente;
    }

    public Timestamp getDataOrario() {
        return dataOrario;
    }

    public void setDataOrario(Timestamp dataOrario) {
        this.dataOrario = dataOrario;
    }

    public float getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(float prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }
}
