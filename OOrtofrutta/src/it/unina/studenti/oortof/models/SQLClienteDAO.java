package it.unina.studenti.oortof.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLClienteDAO {
    private DBContext context;

    public SQLClienteDAO(DBContext context)
    {
        this.context = context;
    }

    public List<Cliente> getClienti(Integer id, String cf, String nome, String cognome, Date dataNascita, String luogoNascita, Genere genere, String email, Integer totalePunti)
    {
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();

            String query = "";
            int filterCount = 0;
            if (id != null) {
                query += "idC = " + id;
                filterCount++;
            }
            if (cf != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "CF = " + cf;
                filterCount++;
            }
            if (nome != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "Nome = " + nome;
                filterCount++;
            }
            if(cognome != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "Cognome = " + cognome;
                filterCount++;
            }
            if(dataNascita != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "DataNascita = " + dataNascita;
                filterCount++;
            }
            if (luogoNascita != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "LuogoNascita = " + luogoNascita;
                filterCount++;
            }
            if (genere != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "Genere = '" + genere.name() + "'";
                filterCount++;
            }
            if (email != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "Email = " + email;
                filterCount++;
            }
            if (totalePunti != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "TotalePunti = " + totalePunti;
                filterCount++;
            }

            String sql = "SELECT C.id AS idC, R.id as idR, * FROM CLIENTE C INNER JOIN RACCOLTAPUNTI R ON R.idCliente = C.id" + (query.equals("") ? ";" : " WHERE " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Cliente> list = new ArrayList();
            while(rs.next())
            {
                int rsIdC = rs.getInt("idC");
                String rsCf = rs.getString("cf");
                String rsNome = rs.getString("Nome");
                String rsCognome = rs.getString("Cognome");
                Date rsData = rs.getDate("DataNascita");
                String rsLuogo = rs.getString("LuogoNascita");
                Genere rsGenere = Genere.valueOf(rs.getString("Sesso"));
                String rsEmail = rs.getString("Email");
                int rsTotalePunti = rs.getInt("TotalePunti");

                int rsidR = rs.getInt("idR");
                int rsFruttaVerdura = rs.getInt("puntifruttaVerdura");
                int rsProdottoCaseario = rs.getInt("puntiprodottoCaseario");
                int rsFarinaceo = rs.getInt("puntifarinaceo");
                int rsUovo = rs.getInt("puntiuovo");
                int rsCarnePesce = rs.getInt("punticarnePesce");
                int rsBibita = rs.getInt("puntibibita");
                int rsConserva = rs.getInt("punticonserva");
                int rsAltro = rs.getInt("puntialtro");

                RaccoltaPunti raccoltaPunti = new RaccoltaPunti(rsidR, rsFruttaVerdura, rsProdottoCaseario, rsFarinaceo, rsUovo, rsCarnePesce, rsBibita, rsConserva, rsAltro);
                Cliente cliente = new Cliente(rsIdC, rsCf, rsNome, rsCognome, rsData, rsLuogo, rsGenere, rsEmail, rsTotalePunti, raccoltaPunti);

                list.add(cliente);
            }


            conn.close();
            return list;
        } catch (SQLException se)
        {
            //TODO
            return null;
        }
    }

    public void updateCliente(Cliente cliente)
    {

    }

    public void deleteCliente(Cliente cliente)
    {

    }

    public void createCliente(Cliente cliente)
    {

    }

}
