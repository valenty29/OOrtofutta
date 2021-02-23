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

    public List<Prodotto> getProducts(Integer codProdotto, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, CatProdotto tipo)
    {
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = "WHERE ";
            int filterCount = 0;
            if (codProdotto != null) {
                query += "CodProdotto = " + codProdotto;
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
            stm.close();
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }

    }


    public List<Bibita> getBibita(Integer codProdotto, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Float gradazioneAlcolicaFloor, Float gradazioneAlcolicaCeil, Boolean frizzante, TipoBibita tipoBibita)  {

        List<Prodotto> prodottiBase = getProducts(codProdotto, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Bibita);



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

    public List<Uovo> getUovo(Integer codProdotto, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, Integer tipoAllevamento, String codAllevamento, CatPeso catPeso)
    {
        List<Prodotto> prodottiBase = getProducts(codProdotto, nome, prezzoFloor, prezzoCeil, sfuso, CatProdotto.Uovo);



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
