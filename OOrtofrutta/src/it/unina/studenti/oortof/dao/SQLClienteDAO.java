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

    public SQLClienteDAO()
    {
        context = new DBContext();
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

            Acquisto acq = clienteTemplate.getScontrini().get(0).getAcquisti().get(0);

            if (acq.getTipoProdotto() != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("Tipo = '%s'", acq.getTipoProdotto());
                filterCount++;
            }

            String quantitaFilters = "";

            if (acq.getString(Acquisto.PREZZO) != null && !acq.getString(Acquisto.PREZZO).equals("")) {

                String puntiFilter = acq.getString(Acquisto.PREZZO);

                quantitaFilters = String.format("GROUP BY (C.id, C.cf, C.genere, R.id, C.nome, C.cognome, C.email, C.datanascita, C.luogonascita, R.puntifruttaverdura, R.puntiprodottocaseario, R.puntifarinaceo, R.puntiuovo, R.punticarnepesce, R.punticonserva, R.puntibibita, R.puntialtro) HAVING SUM(Quantita) %s", DAOHelpers.getFloatQueryField(puntiFilter));
            }



            if (!query.equals("") && !raccoltaPuntiFilters.equals(""))
            {
                query = raccoltaPuntiFilters + " AND " + query;
            } else {
                query = raccoltaPuntiFilters + query;
            }
            String sql = "SELECT DISTINCT C.id AS idC, R.id as idR, C.cf, C.genere, C.nome, C.cognome, C.datanascita, C.luogoNascita, C.email, R.puntifruttaverdura, R.puntiprodottocaseario, R.puntifarinaceo, R.puntiuovo, R.punticarnepesce, R.punticonserva, R.puntibibita, R.puntialtro" + (quantitaFilters.equals("") ? "" : ", SUM(quantita)") + " FROM"
					        +    "((((CLIENTE C INNER JOIN SCONTRINO S ON C.id = S.idCliente)"
					        +   			  "INNER JOIN ACQUISTO A ON A.idScontrino = S.id)"
					        +   		      "INNER JOIN LOTTO L ON A.idLotto = L.id)"
					        +   		      "INNER JOIN PRODOTTO P ON L.idProdotto = P.id)"
					        +                 "INNER JOIN RACCOLTAPUNTI R ON R.idCliente = C.id"
					        +                 (query.equals("") ? ";" : " WHERE " + query)
					        +                 (quantitaFilters.equals("") ? ";" : quantitaFilters);

            //String sql = "SELECT C.id AS idC, R.id as idR, * FROM CLIENTE C INNER JOIN RACCOLTAPUNTI R ON R.idCliente = C.id" + (query.equals("") ? ";" : " WHERE " + query + ";") ;
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
                Cliente cliente = new Cliente(rsIdC, rsCf, rsNome, rsCognome, rsData, rsLuogo, rsGenere, rsEmail);
                cliente.setRaccoltaPunti(raccoltaPunti);
                cliente.setScontrini(getScontrini(cliente));
                list.add(cliente);
            }


            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
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

                ObservedList<Acquisto> acquisti = new ObservedList<Acquisto>("acquisti");

                Scontrino scontrino = new Scontrino(rsId, rsDataOrario, acquisti, cliente, 0f);
                scontrino.setAcquisti(getAcquisti(scontrino));
                Float totale = scontrino.getAcquisti().stream().map(acq -> {
                    return acq.getPrezzo();
                }).reduce(0f,  (subtotal, element) -> subtotal + element);
                scontrino.setTotale(totale);
                list.add(scontrino);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<Acquisto> getAcquisti(Scontrino scontrino){
    	try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM ACQUISTO WHERE IdScontrino = " + scontrino.getId() +";";
            ResultSet rs = stm.executeQuery(sql);
            ObservedList<Acquisto> list = new ObservedList<Acquisto>("acquisti");
            while(rs.next())
            {
            	int rsId = rs.getInt("id");
            	float rsQuantita = rs.getFloat("quantita");
            	float rsPrezzo = rs.getFloat("prezzo");
            	int rsIdLotto = rs.getInt("idLotto");
                Lotto lotto = getLotto(rsIdLotto, conn);
                //TODO assegnare lotto a acquisto

            	list.add(new Acquisto(rsQuantita, rsPrezzo, lotto, "Bibita"));
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    public Lotto getLotto(Integer idLotto, Connection connection) {
        String sql = "SELECT * FROM LOTTO WHERE Id = " + idLotto + ";";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()) {
                int rsId = rs.getInt("id");
                String rsCodLotto = rs.getString("CodLotto");
                java.sql.Date rsScadenza = rs.getDate("Scadenza");
                float rsDisponibilita = rs.getFloat("Disponibilita");
                java.sql.Date rsDataProduzione = rs.getDate("DataProduzione");
                String rsCodPaeseOrigine = rs.getString("CodPaeseOrigine");
                java.sql.Date rsDataMungitura = rs.getDate("DataMungitura");
                Lotto lotto = new Lotto(rsId, rsCodLotto, rsScadenza, rsDisponibilita, rsDataProduzione, rsCodPaeseOrigine, rsDataMungitura);
                return lotto;
            }
            throw new RuntimeException("Couldn't find the lotto");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
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
        if (!oldCliente.getNome().equals(newCliente.getNome()))
        {
            updateQuery += String.format("SET Nome = '%s'", newCliente.getNome());
            counter++;
        }
        if (!oldCliente.getCognome().equals(newCliente.getCognome()))
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
        if (!oldCliente.getLuogoNascita().equals(newCliente.getLuogoNascita()))
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
                throw new RuntimeException(e);
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
            throw new RuntimeException(se); 
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

                    Optional<Cliente> finalCliente = getClienti(new Cliente(id, null, null, null, null, null, null, null)).stream().findFirst();
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

    public void createScontrino(Cliente cliente, ObservedList<Lotto> lotti) {
        int scontrinoId = -1;
        String scontrinoSql = "INSERT INTO SCONTRINO (IdCliente) VALUES (?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createScontrino = conn.prepareStatement(scontrinoSql, Statement.RETURN_GENERATED_KEYS);

            createScontrino.setInt(1, cliente.getId());
            createScontrino.executeUpdate();
            try {
                ResultSet generatedKeys = createScontrino.getGeneratedKeys();
                if(generatedKeys.next()) {
                    scontrinoId = Math.toIntExact(generatedKeys.getLong(1));
                    if (scontrinoId == -1) {
                        throw new RuntimeException("Errore nel recupero dell'id dallo scontrino generato");
                    }

                }

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String acquistiSql = "INSERT INTO ACQUISTO (Quantita, IdLotto, IdScontrino) VALUES (?, ?, ?)";

        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createAcquisto = conn.prepareStatement(acquistiSql);

            for(Lotto lotto: lotti) {

                if (lotto.getCodLotto() != null && !lotto.getCodLotto().equals("")) {
                    createAcquisto.setDouble(1, lotto.getDisponibilita());
                    createAcquisto.setInt(2, lotto.getId());
                    createAcquisto.setInt(3, scontrinoId);
                    createAcquisto.addBatch();
                }
            }
            createAcquisto.executeBatch();

            conn.close();
        } catch (SQLException e) {
            // TODO
            throw new RuntimeException(e);
        }
    }

}
