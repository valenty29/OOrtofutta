package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Uovo extends Prodotto {
    private int TipoAllevamento;
    private String DaAnimale;
    private String CodAllevamento;
    private CatQualita CatQualita;
    private CatPeso CatPeso;

    public Uovo(int codProdotto, String nome, float prezzo, boolean sfuso, CatProdotto tipo, int tipoAllevamento, String daAnimale, String codAllevamento, Models.CatQualita catQualita, Models.CatPeso catPeso) {
        super(codProdotto, nome, prezzo, sfuso, tipo);
        TipoAllevamento = tipoAllevamento;
        DaAnimale = daAnimale;
        CodAllevamento = codAllevamento;
        CatQualita = catQualita;
        CatPeso = catPeso;
    }


    public Uovo(ResultSet rSet) {
        super(rSet);
        try {
            TipoAllevamento = rSet.getInt(5);
            DaAnimale = rSet.getString(6);
            CodAllevamento = rSet.getString(7);
            CatQualita = CatQualita.valueOf(rSet.getString(8));
            CatPeso = CatPeso.valueOf(rSet.getString(9));

        } catch (SQLException e)
        {

        }
    }


    public int getTipoAllevamento() {
        return TipoAllevamento;
    }

    public void setTipoAllevamento(int tipoAllevamento) {
        TipoAllevamento = tipoAllevamento;
    }

    public String getDaAnimale() {
        return DaAnimale;
    }

    public void setDaAnimale(String daAnimale) {
        DaAnimale = daAnimale;
    }

    public String getCodAllevamento() {
        return CodAllevamento;
    }

    public void setCodAllevamento(String codAllevamento) {
        CodAllevamento = codAllevamento;
    }

    public Models.CatQualita getCatQualita() {
        return CatQualita;
    }

    public void setCatQualita(Models.CatQualita catQualita) {
        CatQualita = catQualita;
    }

    public Models.CatPeso getCatPeso() {
        return CatPeso;
    }

    public void setCatPeso(Models.CatPeso catPeso) {
        CatPeso = catPeso;
    }
}
