package it.unina.studenti.oortof.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SQLProductDAO implements ProductDAO {

    private DBContext context;

    public SQLProductDAO(DBContext context)
    {
        this.context = context;
    }

    @Override
    public List<Prodotto> getAllProducts() {

        String sql = "SELECT * FROM PRODOTTO";
        try {
            Connection conn = context.OpenConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            List<Prodotto> list = new ArrayList();
            while(rs.next())
            {
                Prodotto prod = new Prodotto(rs);
                list.add(prod);
            }
            rs.close();
            stat.close();
            conn.close();
            return list;
        } catch (SQLException ex)
        {
            return null;
        }
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
                Prodotto prod = new Prodotto(rs);
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

    @Override
    public void deleteProducts(List<Prodotto> prodotti) {
        int[] ids = new int[prodotti.size()];
        int count = 0;
        for(Prodotto prod: prodotti)
        {
            ids[count] = prod.getCodProdotto();
            count++;
        }
        String inSql = "";
        for (int x = 0; x < count; x++)
        {
            inSql += ids[x] + ",";
        }
        inSql = inSql.subSequence(0, inSql.length() - 1).toString();
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
}
