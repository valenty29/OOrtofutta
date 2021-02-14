package Models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoCaseareo extends Lotto {
    private Date dataMungitura;


    public LottoCaseareo(String codLottoProdotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine, Date dataMungitura) {
        super(codLottoProdotto, scadenza, disponibilita, dataProduzione, codPaeseOrigine);
        this.dataMungitura = dataMungitura;
    }

    public LottoCaseareo(ResultSet rSet) {
        super(rSet);
        try {
            dataMungitura = rSet.getDate(7);
        } catch (SQLException e)
        {

        }
    }
}
