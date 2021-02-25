package it.unina.studenti.oortof.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SQLProductDAO implements ProductDAO {

    private DBContext context;

    public SQLProductDAO(DBContext context)
    {
        this.context = context;
    }

    public List<Prodotto> getProducts(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, CatProdotto tipo)
    {
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
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
            String sql = "SELECT * FROM PRODOTTO" + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
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
                list.add(prod);
            }
            rs.close();

            List<Lotto> lotti;
            for(Prodotto prod: list)
            {
                lotti = new ArrayList<>();
                sql = "SELECT * FROM LOTTO WHERE CodProdotto = " + prod.getId() + ";";
                rs = stm.executeQuery(sql);
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
                prod.setLotti(lotti);
            }
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }

    }


    public List<Bibita> getBibita(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Float gradazioneAlcolicaFloor, Float gradazioneAlcolicaCeil, Boolean frizzante, TipoBibita tipoBibita)  {

        List<Prodotto> prodottiBase = getProducts(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Bibita);



        try {
        Connection conn = context.OpenConnection();
        Statement stm = conn.createStatement();
        String query = "WHERE ";
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
            query += "Tipo = '" + tipoBibita.name() + "'";
        }


        String sql = "SELECT * FROM BIBITA " + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
        System.out.println(sql);
        ResultSet rs = stm.executeQuery(sql);
        List<Bibita> list = new ArrayList();
        while(rs.next())
        {
            int rsId = rs.getInt("IdProdotto");
            float rsGradazione = rs.getFloat("GradazioneAlcolica");
            boolean rsFrizzante = rs.getBoolean("Frizzante");
            TipoBibita rsTipoBibita = TipoBibita.valueOf(rs.getString("Tipo"));

            Optional<Prodotto> prodBase = prodottiBase.stream().filter(prodotto -> prodotto.getId() == rsId).findFirst();

            if (prodBase.isPresent())
            {
                list.add(new Bibita(prodBase.get(), rsGradazione, rsFrizzante, rsTipoBibita));
            }
        }
        rs.close();
        stm.close();
        conn.close();
        return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<Uovo> getUovo(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Integer tipoAllevamento, String codAllevamento, CatPeso catPeso)
    {
        List<Prodotto> prodottiBase = getProducts(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Uovo);



        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
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

            String sql = "SELECT * FROM UOVO " + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Uovo> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("IdProdotto");
                int rsTipoAllevamento = rs.getInt("TipoAllevamento");
                String rsCodAllevamento = rs.getString("CodAllevamento");
                CatPeso rsCatPeso = CatPeso.valueOf(rs.getString("CatPeso"));
                Optional<Prodotto> prodBase = prodottiBase.stream().filter(prodotto -> prodotto.getId() == rsId).findFirst();

                if (prodBase.isPresent())
                {
                    list.add(new Uovo(prodBase.get(), rsTipoAllevamento, rsCodAllevamento, rsCatPeso));
                }
            }
            rs.close();
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<CarnePesce> getCarnepesce(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, TipoCarnePesce tipoCP, Boolean daAllevamento, String daAnimale, Boolean confezionato)
    {
        List<Prodotto> prodottiBase = getProducts(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Carnepesce);



        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
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

            String sql = "SELECT * FROM CARNEPESCE " + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<CarnePesce> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("IdProdotto");
                TipoCarnePesce rsTipoCP = TipoCarnePesce.valueOf(rs.getString("Tipo"));
                Boolean rsDaAllevamento = rs.getBoolean("DaAllevamento");
                String rsAnimale = rs.getString("Animale");
                Boolean rsConfezionato = rs.getBoolean("Confezionato");
                Optional<Prodotto> prodBase = prodottiBase.stream().filter(prodotto -> prodotto.getId() == rsId).findFirst();

                if (prodBase.isPresent())
                {
                    list.add(new CarnePesce(prodBase.get(), rsTipoCP, rsDaAllevamento, rsAnimale, rsConfezionato));
                }
            }
            rs.close();
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }


    public List<Farinaceo> getFarinaceo(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Boolean glutine, String tipoFarina, Boolean fresco, Boolean surgelato)
    {
        List<Prodotto> prodottiBase = getProducts(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Farinaceo);



        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
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

            String sql = "SELECT * FROM FARINACEO " + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Farinaceo> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("IdProdotto");
                Boolean rsGlutine = rs.getBoolean("Glutine");
                String rsTipoFarina = rs.getString("TipoFarina");
                Boolean rsFresco = rs.getBoolean("Fresco");
                Boolean rsSurgelato = rs.getBoolean("Surgelato");
                Optional<Prodotto> prodBase = prodottiBase.stream().filter(prodotto -> prodotto.getId() == rsId).findFirst();

                if (prodBase.isPresent())
                {
                    list.add(new Farinaceo(prodBase.get(), rsGlutine, rsTipoFarina, rsFresco, rsSurgelato));
                }
            }
            rs.close();
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<FruttaVerdura> getFruttaVerdura(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, TipoFruttaVerdura tipoFV, Boolean bio, Boolean surgelato)
    {
        List<Prodotto> prodottiBase = getProducts(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Fruttaverdura);

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
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

            String sql = "SELECT * FROM FRUTTAVERDURA " + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<FruttaVerdura> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("IdProdotto");
                TipoFruttaVerdura rsTipoFV = TipoFruttaVerdura.valueOf(rs.getString("Tipo"));
                Boolean rsBio = rs.getBoolean("Bio");
                Boolean rsSurgelato = rs.getBoolean("Surgelato");
                Optional<Prodotto> prodBase = prodottiBase.stream().filter(prodotto -> prodotto.getId() == rsId).findFirst();

                if (prodBase.isPresent())
                {
                    list.add(new FruttaVerdura(prodBase.get(), rsTipoFV, rsBio, rsSurgelato));
                }
            }
            rs.close();
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<Conserva> getConserva(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, TipoConservazione tipoC)
    {
        List<Prodotto> prodottiBase = getProducts(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Conserva);

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
            int filterCount = 0;
            if(tipoC != null)
            {
                query += "TipoConservazione = '" + tipoC + "'";
                filterCount++;
            }


            String sql = "SELECT * FROM CONSERVA " + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Conserva> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("IdProdotto");
                TipoConservazione rsTipoC= TipoConservazione.valueOf(rs.getString("TipoConservazione"));

                Optional<Prodotto> prodBase = prodottiBase.stream().filter(prodotto -> prodotto.getId() == rsId).findFirst();

                if (prodBase.isPresent())
                {
                    list.add(new Conserva(prodBase.get(), rsTipoC));
                }
            }
            rs.close();
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<ProdottoCaseareo> getProdottoCaseario(Integer id, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, String tipolatte, String stabilimento, Integer stagionatura)
    {
        List<Prodotto> prodottiBase = getProducts(id, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Prodottocaseareo);

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
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

            String sql = "SELECT * FROM PRODOTTOCASEAREO " + (query.equals("WHERE ") ? ";" : " " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<ProdottoCaseareo> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("IdProdotto");
                String rsTipoLatte = rs.getString("TipoLatte");
                String rsStabilimento = rs.getString("Stabilimento");
                int rsStagionatura = rs.getInt("Stagionatura");

                Optional<Prodotto> prodBase = prodottiBase.stream().filter(prodotto -> prodotto.getId() == rsId).findFirst();

                if (prodBase.isPresent())
                {
                    list.add(new ProdottoCaseareo(prodBase.get(), rsTipoLatte, rsStabilimento, rsStagionatura));
                }
            }
            rs.close();
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }



    @Override
    public void deleteProducts(List<Prodotto> prodotti) {
        String inSql = arrayToString(prodotti);
        String sql = "DELETE FROM PRODOTTO WHERE CodProdotto IN (" +inSql + ");";

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            stm.execute(sql);
            stm.close();
            conn.close();
        } catch (SQLException se)
        {

        }
    }

    

    @Override
    public void updateProducts(List<Prodotto> prodotti) {

    }

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
