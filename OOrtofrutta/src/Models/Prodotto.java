package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Prodotto {
    private int CodProdotto;
    private String Nome;
    private float Prezzo;
    private boolean Sfuso;
    private CatProdotto Tipo;
    private List<Lotto> lotti;

    public Prodotto(int CodProdotto, String Nome, float Prezzo, boolean Sfuso, CatProdotto Tipo) {
        this.CodProdotto = CodProdotto;
        this.Nome = Nome;
        this.Prezzo = Prezzo;
        this.Sfuso = Sfuso;
        this.Tipo = Tipo;
    }

    public Prodotto(ResultSet rSet)
    {
        try{
            this.CodProdotto = rSet.getInt(0);
            this.Nome = rSet.getString(1);
            this.Prezzo = rSet.getFloat(2);
            this.Sfuso = rSet.getBoolean(3);
            this.Tipo = CatProdotto.valueOf(rSet.getString(4));
        } catch (SQLException e)
        {

        }
    }

    public int getCodProdotto() {
        return CodProdotto;
    }

    public void setCodProdotto(int codProdotto) {
        CodProdotto = codProdotto;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public float getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(float prezzo) {
        Prezzo = prezzo;
    }

    public boolean isSfuso() {
        return Sfuso;
    }

    public void setSfuso(boolean sfuso) {
        Sfuso = sfuso;
    }

    public CatProdotto getTipo() {
        return Tipo;
    }

    public void setTipo(CatProdotto tipo) {
        Tipo = tipo;
    }

    public List<Lotto> getLotti() {
        return lotti;
    }

    public void setLotti(List<Lotto> lotti) {
        this.lotti = lotti;
    }
}
