package it.unina.studenti.oortof.dao;

import it.unina.studenti.oortof.models.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SQLClienteDAO {
    private DBContext context;

    public SQLClienteDAO(DBContext context)
    {
        this.context = context;
    }

    private String getRaccoltaPuntiFilters(RaccoltaPunti raccoltaPunti){
        String query = "";
        if(raccoltaPunti == null) {
            return query;
        }

        int filterCount = 0;

        if (raccoltaPunti.getString(RaccoltaPunti.FRUTTA_VERDURA) != null)
        {
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.FRUTTA_VERDURA);
            query += String.format("PuntiFruttaVerdura %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        if (raccoltaPunti.getString(RaccoltaPunti.PRODOTTO_CASEARIO) != null)
        {
            if (filterCount != 0){
                query += " AND ";
            }
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.PRODOTTO_CASEARIO);
            query += String.format("PuntiProdottoCaseario %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        if (raccoltaPunti.getString(RaccoltaPunti.FARINACEO) != null)
        {
            if (filterCount != 0){
                query += " AND ";
            }
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.FARINACEO);
            query += String.format("PuntiFarinaceo %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        if (raccoltaPunti.getString(RaccoltaPunti.UOVO) != null)
        {
            if (filterCount != 0){
                query += " AND ";
            }
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.UOVO);
            query += String.format("PuntiUovo %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        if (raccoltaPunti.getString(RaccoltaPunti.CARNE_PESCE) != null)
        {
            if (filterCount != 0){
                query += " AND ";
            }
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.CARNE_PESCE);
            query += String.format("PuntiCarnePesce %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        if (raccoltaPunti.getString(RaccoltaPunti.BIBITA) != null)
        {
            if (filterCount != 0){
                query += " AND ";
            }
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.BIBITA);
            query += String.format("PuntiBibita %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        if (raccoltaPunti.getString(RaccoltaPunti.CONSERVA) != null)
        {
            if (filterCount != 0){
                query += " AND ";
            }
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.CONSERVA);
            query += String.format("PuntiConserva %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        if (raccoltaPunti.getString(RaccoltaPunti.ALTRO) != null)
        {
            if (filterCount != 0){
                query += " AND ";
            }
            String puntiFilter = raccoltaPunti.getString(RaccoltaPunti.ALTRO);
            query += String.format("PuntiAltro %s", DAOHelpers.getFloatQueryField(puntiFilter));
            filterCount++;
        }

        return query;
    }

    public ObservedList<Cliente> getClienti(Cliente clienteTemplate)
    {

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String raccoltaPuntiFilters = getRaccoltaPuntiFilters(clienteTemplate.getRaccoltaPunti());

            String query = "";
            int filterCount = 0;
            if (clienteTemplate.getString(Cliente.ID) != null) {
                query += String.format("C.id = %d", clienteTemplate.getId());
                filterCount++;
            }
            if (clienteTemplate.getString(Cliente.CF) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("CF = '%s'", clienteTemplate.getCF());
                filterCount++;
            }
            if (clienteTemplate.getString(Cliente.NOME) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("Nome = '%s'", clienteTemplate.getNome());
                filterCount++;
            }
            if(clienteTemplate.getString(Cliente.COGNOME) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("Cognome = '%s'", clienteTemplate.getCognome());
                filterCount++;
            }
            if(clienteTemplate.getString(Cliente.DATA_NASCITA) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("DataNascita = '%s'", clienteTemplate.getDataNascita());
                filterCount++;
            }
            if (clienteTemplate.getString(Cliente.LUOGO_NASCITA) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("LuogoNascita = '%s'", clienteTemplate.getLuogoNascita());
                filterCount++;
            }
            if (clienteTemplate.getString(Cliente.GENERE) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("Genere = '%s'", clienteTemplate.getGenere());
                filterCount++;
            }
            if (clienteTemplate.getString(Cliente.EMAIL) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("Email = '%s'", clienteTemplate.getEmail());
                filterCount++;
            }

            if (!query.equals("") && !raccoltaPuntiFilters.equals(""))
            {
                query = raccoltaPuntiFilters + " AND " + query;
            } else {
                query = raccoltaPuntiFilters + query;
            }

            String sql = "SELECT C.id AS idC, R.id as idR, * FROM CLIENTE C INNER JOIN RACCOLTAPUNTI R ON R.idCliente = C.id" + (query.equals("") ? ";" : " WHERE " + query + ";") ;
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            ObservedList<Cliente> list = new ObservedList<Cliente>("cliente");
            while(rs.next())
            {
                int rsIdC = rs.getInt("idC");
                String rsCf = rs.getString("cf");
                String rsNome = rs.getString("Nome");
                String rsCognome = rs.getString("Cognome");
                Date rsData = new Date(rs.getDate("DataNascita").getTime());
                String rsLuogo = rs.getString("LuogoNascita");
                Genere rsGenere = Genere.valueOf(rs.getString("Genere"));
                String rsEmail = rs.getString("Email");

                int rsidR = rs.getInt("idR");
                float rsFruttaVerdura = rs.getFloat("puntifruttaVerdura");
                float rsProdottoCaseario = rs.getFloat("puntiprodottoCaseario");
                float rsFarinaceo = rs.getInt("puntifarinaceo");
                float rsUovo = rs.getInt("puntiuovo");
                float rsCarnePesce = rs.getInt("punticarnePesce");
                float rsBibita = rs.getInt("puntibibita");
                float rsConserva = rs.getInt("punticonserva");
                float rsAltro = rs.getInt("puntialtro");

                RaccoltaPunti raccoltaPunti = new RaccoltaPunti(rsidR, rsFruttaVerdura, rsProdottoCaseario, rsFarinaceo, rsUovo, rsCarnePesce, rsBibita, rsConserva, rsAltro);
                Cliente cliente = new Cliente(rsIdC, rsCf, rsNome, rsCognome, rsData, rsLuogo, rsGenere, rsEmail, raccoltaPunti);
                cliente.setScontrini(getScontrini(cliente));
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
    
    private ObservedList<Scontrino> getScontrini(Cliente cliente) {
    	try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM SCONTRINO WHERE IdCliente = " + cliente.getId() +";";
            ResultSet rs = stm.executeQuery(sql);
            ObservedList<Scontrino> list = new ObservedList<Scontrino>("scontrino");
            while(rs.next())
            {
            	int rsId = rs.getInt("id");
                Date rsDataOrario =  new Date(rs.getTimestamp("DataOrario").getTime());
                float rsPrezzoTotale = rs.getFloat("PrezzoTotale");
                //TODO
                ObservedList<Acquisto> acquisti = new ObservedList<Acquisto>("acquisti");
                Scontrino scontrino = new Scontrino(rsId, rsDataOrario, rsPrezzoTotale, acquisti);
                
                list.add(scontrino);
            }


            conn.close();
            return list;
        } catch (SQLException se)
        {
            //TODO
            return null;
        }
    }

    public void updateCliente(Cliente oldCliente, Cliente newCliente)
    {
        if (oldCliente.getId() != newCliente.getId())
        {
            throw new RuntimeException("Gli id sono diversi");
        }

        String updateQuery = "";
        int counter = 0;
        if (oldCliente.getNome() != newCliente.getNome())
        {
            updateQuery += String.format("SET Nome = '%s'", newCliente.getNome());
            counter++;
        }
        if (oldCliente.getCognome() != newCliente.getCognome())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Cognome = '%s'", newCliente.getCognome());
            counter++;
        }
        if (!oldCliente.getDataNascita().equals(newCliente.getDataNascita()))
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            //TODO da verificare
            updateQuery += String.format("SET DataNascita = '%s'", newCliente.getDataNascita());
            counter++;
        }
        if (oldCliente.getLuogoNascita() != newCliente.getLuogoNascita())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET LuogoNascita = '%s'", newCliente.getLuogoNascita());
            counter++;
        }
        if (oldCliente.getGenere() != newCliente.getGenere())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Genere = '%s'", newCliente.getGenere());
            counter++;
        }
        if (oldCliente.getEmail() != oldCliente.getEmail())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Email = '%s'", newCliente.getEmail());
            counter++;
        }



        String sql = "UPDATE CLIENTE " + updateQuery + " WHERE id = " + newCliente.getId();

        if(updateQuery != "") {
            try {
                Connection conn = context.OpenConnection();
                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    public void deleteCliente(Cliente cliente)
    {
        String sql = "DELETE FROM CLIENTE WHERE id = " + cliente.getId() + ";";

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

    public Cliente createCliente(Cliente cliente)
    {
        String sql = "INSERT INTO CLIENTE (Nome, Cognome, DataNascita, LuogoNascita, Genere, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createCliente = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            createCliente.setString(1, cliente.getNome());
            createCliente.setString(2, cliente.getCognome());
       
            createCliente.setDate(3, new java.sql.Date(cliente.getDataNascita().getTime()));
            createCliente.setString(4, cliente.getLuogoNascita());
            createCliente.setObject(5, cliente.getGenere(), Types.OTHER);
            createCliente.setString(6, cliente.getEmail());
            createCliente.executeUpdate();

            try {
                int id;
                ResultSet generatedKeys = createCliente.getGeneratedKeys();
                if (generatedKeys.next())
                {
                    id = Math.toIntExact(generatedKeys.getLong(1));

                    Optional<Cliente> finalCliente = getClienti(new Cliente(id, null, null, null, null, null, null, null, null)).stream().findFirst();
                    if (!finalCliente.isPresent()){
                        throw new SQLException();
                    }
                    return finalCliente.get();
                }
                throw new SQLException();
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            // TODO
            throw new RuntimeException();
        }
    }

}
