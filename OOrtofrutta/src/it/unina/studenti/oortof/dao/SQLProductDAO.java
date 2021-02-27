package it.unina.studenti.oortof.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unina.studenti.oortof.models.Prodotto;

public class SQLProductDAO implements ProductDAO {

    private DBContext context;

    public SQLProductDAO(DBContext context)
    {
        this.context = context;
    }

    //region CREATE
    //lasciare come parametro la classe o inserire gli attributi di prodotto come parametro?
    public void createProduct(Prodotto prodotto){
        String sql = "INSERT INTO PRODOTTO (Nome, Prezzo, Sfuso, Tipo) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createProds = conn.prepareStatement(sql);
            createProds.setString(1, prodotto.getNome());
            createProds.setFloat(2, prodotto.getPrezzo());
            createProds.setBoolean(3, prodotto.isSfuso());
            createProds.setObject(4, prodotto.getTipo().name(), Types.OTHER);

            createProds.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }
    //endregion

    //region READ
    public List<Prodotto> getProducts(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, CatProdotto tipo)
    {
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, tipo);

            String sql = "SELECT * FROM PRODOTTO" + (query.equals("") ? ";" : " WHERE " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Prodotto> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("id");
                String rsNome = rs.getString("Nome");
                float rsPrezzo = rs.getFloat("Prezzo");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                CatProdotto rsCatProdotto = CatProdotto.valueOf(rs.getString("Tipo"));
                Prodotto prod = new Prodotto(rsId, rsNome, rsPrezzo, rsSfuso, rsCatProdotto);
                List<Lotto> lotti = getLotti(rsId, conn);
                prod.setLotti(lotti);
                list.add(prod);
            }


            conn.close();
            return list;
        } catch (SQLException se)
        {
            //TODO
            return null;
        }

    }

    private List<Lotto> getLotti(int id, Connection connection)
    {
        List<Lotto> lotti = new ArrayList<>();
        String sql = "SELECT * FROM LOTTO WHERE CodProdotto = " + id + ";";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next())
            {
                int rsId = rs.getInt("id");
                String rsCodLotto = rs.getString("CodLotto");
                Date rsScadenza = rs.getDate("Scadenza");
                float rsDisponibilita = rs.getFloat("Disponibilita");
                Date rsDataProduzione = rs.getDate("DataProduzione");
                String rsCodPaeseOrigine = rs.getString("CodPaeseOrigine");
                Lotto lotto = new Lotto(rsId, rsCodLotto, rsScadenza, rsDisponibilita, rsDataProduzione, rsCodPaeseOrigine);
                lotti.add(lotto);
            }
            return lotti;
        } catch (SQLException e)
        {
            //TODO
            return null;
        }

    }


    private String getProdottoFilters(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, CatProdotto tipo) {
        String query = "";
        int filterCount = 0;
        if (id != null) {
            query += "CodProdotto = " + id;
            filterCount++;
        }
        if (nome != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Nome = '" + nome + "'";
            filterCount++;
        }
        if (prezzoFloor != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Prezzo > " + prezzoFloor;
            filterCount++;
        }
        if(prezzoCeil != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Prezzo < " + prezzoCeil;
            filterCount++;
        }
        if(sfuso != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Sfuso = " + sfuso;
            filterCount++;
        }
        if (tipo != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Tipo = '" + tipo.name() + "'";
            filterCount++;
        }
        return query;
    }

    public List<Bibita> getBibita(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Float gradazioneAlcolicaFloor, Float gradazioneAlcolicaCeil, Boolean frizzante, TipoBibita tipoBibita)  {

        try {
        Connection conn = context.OpenConnection();
        Statement stm = conn.createStatement();
        String productFilters = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Bibita);
        String query = "";
        int filterCount = 0;
        if (gradazioneAlcolicaFloor != null) {
            query += "GradazioneAlcolica > " + gradazioneAlcolicaFloor;
            filterCount++;
        }
        if(gradazioneAlcolicaCeil != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "GradazioneAlcolica < " + gradazioneAlcolicaCeil;
            filterCount++;
        }
        if(frizzante != null)
        {
            if(filterCount != 0)
                query += " AND ";
            query += "Frizzante = " + frizzante;
        }
        if(tipoBibita != null)
        {
            if(filterCount != 0)
                query += " AND ";
            query += "TipoB = '" + tipoBibita.name() + "'";
        }
        if (!query.equals("") && !productFilters.equals(""))
        {
            query = productFilters + " AND " + query;
        } else {
            query = productFilters + query;
        }

        String sql = "SELECT * FROM PRODOTTO INNER JOIN BIBITA ON PRODOTTO.id = BIBITA.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
        System.out.println(sql);
        ResultSet rs = stm.executeQuery(sql);
        List<Bibita> list = new ArrayList();
        while(rs.next())
        {
            int rsId = rs.getInt("Id");
            String rsNome = rs.getString("Nome");
            boolean rsSfuso = rs.getBoolean("Sfuso");
            float rsPrezzo = rs.getFloat("Prezzo");
            float rsGradazione = rs.getFloat("GradazioneAlcolica");
            boolean rsFrizzante = rs.getBoolean("Frizzante");
            TipoBibita rsTipoBibita = TipoBibita.valueOf(rs.getString("TipoB"));
            Bibita bib = new Bibita(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Bibita, rsGradazione, rsFrizzante, rsTipoBibita );
            bib.setLotti(getLotti(rsId, conn));
            list.add(bib);
        }
        conn.close();
        return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<Uovo> getUovo(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Integer tipoAllevamento, String codAllevamento, CatPeso catPeso)
    {



        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Bibita);
            String query = "";
            int filterCount = 0;
            if (tipoAllevamento != null) {
                query += "TipoAllevamento = " + tipoAllevamento;
                filterCount++;
            }
            if(codAllevamento != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "CodAllevamento = " + codAllevamento;
                filterCount++;
            }
            if(catPeso != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "CatPeso = '" + catPeso.name() + "'";
            }
            if (!query.equals("") && !productFilters.equals(""))
            {
                query = productFilters + " AND " + query;
            } else {
                query = productFilters + query;
            }

            String sql = "SELECT * FROM PRODOTTO INNER JOIN UOVO ON PRODOTTO.id = UOVO.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Uovo> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                int rsTipoAllevamento = rs.getInt("TipoAllevamento");
                String rsCodAllevamento = rs.getString("CodAllevamento");
                CatPeso rsCatPeso = CatPeso.valueOf(rs.getString("CatPeso"));
                Uovo uovo = new Uovo(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Uovo, rsTipoAllevamento, rsCodAllevamento, rsCatPeso);
                uovo.setLotti(getLotti(rsId, conn));
                list.add(uovo);
            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<CarnePesce> getCarnepesce(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, TipoCarnePesce tipoCP, Boolean daAllevamento, String daAnimale, Boolean confezionato)
    {



        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Carnepesce);
            String query = "";
            int filterCount = 0;
            if (tipoCP != null) {
                query += "Tipo = '" + tipoCP.name() + "'";
                filterCount++;
            }
            if(daAllevamento != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "DaAllevamento = " + daAllevamento;
                filterCount++;
            }
            if(daAnimale != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Animale = " + daAnimale;
            }
            if(confezionato != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Confezionato = " + confezionato;
            }

            if (!query.equals("") && !productFilters.equals(""))
            {
                query = productFilters + " AND " + query;
            } else {
                query = productFilters + query;
            }
            String sql = "SELECT * FROM PRODOTTO INNER JOIN CARNEPESCE ON PRODOTTO.id = CARNEPESCE.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<CarnePesce> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");

                TipoCarnePesce rsTipoCP = TipoCarnePesce.valueOf(rs.getString("Tipo"));
                Boolean rsDaAllevamento = rs.getBoolean("DaAllevamento");
                String rsAnimale = rs.getString("Animale");
                Boolean rsConfezionato = rs.getBoolean("Confezionato");
                CarnePesce carnePesce = new CarnePesce(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Carnepesce, rsTipoCP, rsDaAllevamento, rsAnimale, rsConfezionato);
                carnePesce.setLotti(getLotti(rsId, conn));
                list.add(carnePesce);

            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }


    public List<Farinaceo> getFarinaceo(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Boolean glutine, String tipoFarina, Boolean fresco, Boolean surgelato)
    {

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Farinaceo);
            String query = "";
            int filterCount = 0;
            if (glutine != null) {
                query += "Glutine = " + glutine;
                filterCount++;
            }
            if(tipoFarina != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "TipoFarina = " + tipoFarina;
                filterCount++;
            }
            if(fresco != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Fresco = " + fresco;
            }
            if(surgelato != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Surgelato = " + surgelato;
            }

            if (!query.equals("") && !productFilters.equals(""))
            {
                query = productFilters + " AND " + query;
            } else {
                query = productFilters + query;
            }
            String sql = "SELECT * FROM PRODOTTO INNER JOIN FARINACEO ON PRODOTTO.id = FARINACEO.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Farinaceo> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                Boolean rsGlutine = rs.getBoolean("Glutine");
                String rsTipoFarina = rs.getString("TipoFarina");
                Boolean rsFresco = rs.getBoolean("Fresco");
                Boolean rsSurgelato = rs.getBoolean("Surgelato");
                Farinaceo farinaceo = new Farinaceo(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Farinaceo, rsGlutine, rsTipoFarina, rsFresco, rsSurgelato);
                farinaceo.setLotti(getLotti(rsId, conn));
                list.add(farinaceo);
            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<FruttaVerdura> getFruttaVerdura(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, TipoFruttaVerdura tipoFV, Boolean bio, Boolean surgelato)
    {

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Fruttaverdura);
            String query = "";
            int filterCount = 0;
            if(tipoFV != null)
            {
                query += "Tipo = '" + tipoFV + "'";
                filterCount++;
            }
            if(bio != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Bio = " + bio;
            }
            if(surgelato != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Surgelato = " + surgelato;
            }

            if (!query.equals("") && !productFilters.equals(""))
            {
                query = productFilters + " AND " + query;
            } else {
                query = productFilters + query;
            }
            String sql = "SELECT * FROM PRODOTTO INNER JOIN FRUTTAVERDURA ON PRODOTTO.id = FRUTTAVERDURA.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<FruttaVerdura> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                TipoFruttaVerdura rsTipoFV = TipoFruttaVerdura.valueOf(rs.getString("Tipo"));
                Boolean rsBio = rs.getBoolean("Bio");
                Boolean rsSurgelato = rs.getBoolean("Surgelato");
                FruttaVerdura fruttaVerdura = new FruttaVerdura(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Fruttaverdura, rsTipoFV, rsBio, rsSurgelato);
                fruttaVerdura.setLotti(getLotti(rsId, conn));
                list.add(fruttaVerdura);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<Conserva> getConserva(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, TipoConservazione tipoC)
    {


        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Conserva);
            String query = "";
            if(tipoC != null)
            {
                query += "TipoConservazione = '" + tipoC + "'";
            }

            if (!query.equals("") && !productFilters.equals(""))
            {
                query = productFilters + " AND " + query;
            } else {
                query = productFilters + query;
            }

            String sql = "SELECT * FROM PRODOTTO INNER JOIN CONSERVA ON PRODOTTO.id = CONSERVA.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Conserva> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                TipoConservazione rsTipoC= TipoConservazione.valueOf(rs.getString("TipoConservazione"));
                Conserva conserva = new Conserva(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Conserva, rsTipoC);
                conserva.setLotti(getLotti(rsId, conn));
                list.add(conserva);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<ProdottoCaseareo> getProdottoCaseario(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, String tipolatte, String stabilimento, Integer stagionatura)
    {


        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Bibita);
            String query = "";
            int filterCount = 0;
            if(tipolatte != null)
            {
                query += "TipoLatte = " + tipolatte;
                filterCount++;
            }
            if(stabilimento != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Stabilimento = " + stabilimento;
            }
            if(stagionatura != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Stagionatura = " + stagionatura;
            }

            if (!query.equals("") && !productFilters.equals(""))
            {
                query = productFilters + " AND " + query;
            } else {
                query = productFilters + query;
            }
            String sql = "SELECT * FROM PRODOTTO INNER JOIN PRODOTTOCASEAREO ON PRODOTTO.id = PRODOTTOCASEAREO.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<ProdottoCaseareo> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                String rsTipoLatte = rs.getString("TipoLatte");
                String rsStabilimento = rs.getString("Stabilimento");
                int rsStagionatura = rs.getInt("Stagionatura");

                ProdottoCaseareo prodCas = new ProdottoCaseareo(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Prodottocaseario, rsTipoLatte, rsStabilimento, rsStagionatura);
                //List<LottoCaseareo> lottCas = getLottiCaseari()  TODO
                list.add(prodCas);

            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }
    //endregion

    //region DELETE
    //la presenza di lotti impedisce la cancellazione di un prodotto
    @Override
    public void deleteProducts(List<Prodotto> prodotti) {
        String inSql = arrayToString(prodotti);
        String sql = "DELETE FROM PRODOTTO WHERE id IN (" +inSql + ");";

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            stm.execute(sql);
            conn.close();
        } catch (SQLException se)
        {
            System.out.println(se);
            return;
        }
    }
    //endregion

    //region UPDATE
    @Override
    public void updateProducts(int id, String nome, Float prezzo, Boolean sfuso) {
        try {
            Connection conn = context.OpenConnection();
            String updateQuery = "";
            int counter = 0;
            if (nome != null)
            {
                updateQuery += String.format("SET Nome = '%s'", nome);
            }
            if (prezzo != null)
            {
                if (counter != 0)
                {
                    updateQuery += ",";
                }
                updateQuery += String.format("SET Prezzo = %f", prezzo);
            }
            if (sfuso != null)
            {
                if (counter != 0)
                {
                    updateQuery += ",";
                }
                updateQuery += "SET Sfuso = " + sfuso;
            }

            String sql = "UPDATE PRODOTTO " + updateQuery + " WHERE id = " + id;
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            return;
        }
    }
    //endregion

    private String arrayToString(List<Prodotto> prodotti)
    {
        int[] ids = new int[prodotti.size()];
        int count = 0;
        for(Prodotto prod: prodotti)
        {
            ids[count] = prod.getId();
            count++;
        }
        String inSql = "";
        for (int x = 0; x < count; x++)
        {
            inSql += ids[x] + ",";
        }
        inSql = inSql.subSequence(0, inSql.length() - 1).toString();
        return inSql;
    }
}
