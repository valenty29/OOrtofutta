package it.unina.studenti.oortof.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoCaseareo {
    private Date dataMungitura;


    public LottoCaseareo(String codLottoProdotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine, Date dataMungitura) {
        //super(codLottoProdotto, scadenza, disponibilita, dataProduzione, codPaeseOrigine);
        this.dataMungitura = dataMungitura;
    }
}
