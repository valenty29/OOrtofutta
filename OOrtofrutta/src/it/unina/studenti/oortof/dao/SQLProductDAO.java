package it.unina.studenti.oortof.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unina.studenti.oortof.models.*;

public class SQLProductDAO implements ProductDAO {

    private DBContext context;

    public SQLProductDAO(DBContext context)
    {
        this.context = context;
    }

    //region CREATE
    //lasciare come parametro la classe o inserire gli attributi di prodotto come parametro?
    public long createProductCommon(ProdottoCommon prodotto){
        long prodId = -1;
        String sql = "INSERT INTO PRODOTTO (Nome, Prezzo, Sfuso, Tipo) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createProds = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            createProds.setString(1, prodotto.getNome());
            createProds.setFloat(2, prodotto.getPrezzo());
            createProds.setBoolean(3, prodotto.isSfuso());
            createProds.setObject(4, prodotto.getCatProdotto().name(), Types.OTHER);

            createProds.executeUpdate();


            try {
                ResultSet generatedKeys = createProds.getGeneratedKeys();
                if (generatedKeys.next())
                {
                    prodId = generatedKeys.getLong(1);
                }
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }

            conn.close();

            return prodId;
        } catch (SQLException e) {
            // TODO
            throw new RuntimeException(e);
        }
    }

    public void createBibita(Bibita bibita){
        long id = createProductCommon(bibita.getProdottoCommon());
        String sql = "INSERT INTO BIBITA (IdProdotto, GradazioneAlcolica, Frizzante, TipoB) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createBibs = conn.prepareStatement(sql);
            createBibs.setLong(1, id);
            createBibs.setFloat(2, bibita.getBibitaSpecifico().getGradazioneAlcolica());
            createBibs.setBoolean(3, bibita.getBibitaSpecifico().isFrizzante());
            createBibs.setObject(4, bibita.getBibitaSpecifico().getTipoBibita().name(), Types.OTHER);

            createBibs.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }

    public void createUovo(Uovo uovo){
        long id = createProductCommon(uovo.getProdottoCommon());
        String sql = "INSERT INTO UOVO (IdProdotto, TipoAllevamento, CodAllevamento, CatPeso) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createUovo = conn.prepareStatement(sql);
            createUovo.setLong(1, id);
            createUovo.setInt(2, uovo.getUovoSpecifico().getTipoAllevamento());
            createUovo.setString(3, uovo.getUovoSpecifico().getCodAllevamento());
            createUovo.setObject(4, uovo.getUovoSpecifico().getCatPeso().name(), Types.OTHER);

            createUovo.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }

    public void createCarnePesce(CarnePesce carnePesce){
        long id = createProductCommon(carnePesce.getProdottoCommon());
        String sql = "INSERT INTO CARNEPESCE (IdProdotto, TipoCP, DaAllevamento, Animale, Confezionato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createCarnePesce = conn.prepareStatement(sql);
            createCarnePesce.setLong(1, id);
            createCarnePesce.setObject(2, carnePesce.getCarnePesceSpecifico().getTipoCarnePesce().name(), Types.OTHER);
            createCarnePesce.setBoolean(3, carnePesce.getCarnePesceSpecifico().isDaAllevamento());
            createCarnePesce.setString(4, carnePesce.getCarnePesceSpecifico().getAnimale());
            createCarnePesce.setBoolean(5, carnePesce.getCarnePesceSpecifico().isConfezionato());

            createCarnePesce.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }

    public void createFruttaVerdura(FruttaVerdura fruttaVerdura){
        long id = createProductCommon(fruttaVerdura.getProdottoCommon());
        String sql = "INSERT INTO FRUTTAVERDURA (IdProdotto, TipoFV, Bio, Surgelato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createFruttaVerdura = conn.prepareStatement(sql);
            createFruttaVerdura.setLong(1, id);
            createFruttaVerdura.setObject(2, fruttaVerdura.getFruttaVerduraSpecifico().getTipoFruttaVerdura().name(), Types.OTHER);
            createFruttaVerdura.setBoolean(3, fruttaVerdura.getFruttaVerduraSpecifico().isBio());
            createFruttaVerdura.setBoolean(4, fruttaVerdura.getFruttaVerduraSpecifico().isSurgelato());

            createFruttaVerdura.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }

    public void createProdottoCaseario(ProdottoCaseario prodottoCaseario){
        long id = createProductCommon(prodottoCaseario.getProdottoCommon());
        String sql = "INSERT INTO PRODOTTOCASEARIO (IdProdotto, TipoLatte, Stabilimento, Stagionatura) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createProdottoCaseario = conn.prepareStatement(sql);
            createProdottoCaseario.setLong(1, id);
            createProdottoCaseario.setString(2, prodottoCaseario.getProdottoCasearioSpecifico().getTipoLatte());
            createProdottoCaseario.setString(3, prodottoCaseario.getProdottoCasearioSpecifico().getStabilimento());
            createProdottoCaseario.setInt(4, prodottoCaseario.getProdottoCasearioSpecifico().getStagionatura());

            createProdottoCaseario.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }

    public void createFarinaceo(Farinaceo farinaceo){
        long id = createProductCommon(farinaceo.getProdottoCommon());
        String sql = "INSERT INTO FARINACEO (IdProdotto, Glutine, TipoFarina, Fresco, Surgelato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createFarinaceo = conn.prepareStatement(sql);
            createFarinaceo.setLong(1, id);
            createFarinaceo.setBoolean(2, farinaceo.getFarinaceoSpecifico().isGlutine());
            createFarinaceo.setString(3, farinaceo.getFarinaceoSpecifico().getTipoFarina());
            createFarinaceo.setBoolean(4, farinaceo.getFarinaceoSpecifico().isFresco());
            createFarinaceo.setBoolean(5, farinaceo.getFarinaceoSpecifico().isSurgelato());


            createFarinaceo.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }

    public void createConserva(Conserva conserva){
        long id = createProductCommon(conserva.getProdottoCommon());
        String sql = "INSERT INTO CONSERVA (IdProdotto, Glutine, TipoFarina, Fresco, Surgelato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createConserva = conn.prepareStatement(sql);
            createConserva.setLong(1, id);
            createConserva.setObject(2, conserva.getConservaSpecifico().getTipoConservazione().name(), Types.OTHER);

            createConserva.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }


    //endregion

    //region READ
    /*public List<Prodotto> getProducts(Prodotto prod)
    {
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String query = getProdottoFilters(Prodotto prod);

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
                prod.getProdottoCommon().setLotti(lotti);
                list.add(prod);
            }


            conn.close();
            return list;
        } catch (SQLException se)
        {
            //TODO
            return null;
        }
    }*/

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
            throw new RuntimeException(e);
        }

    }

    private String getProdottoFilters(ProdottoCommon prodCom) {

        String query = "";
        int filterCount = 0;
        if (prodCom.getId() != null) {
            query += "CodProdotto = " + prodCom.getId();
            filterCount++;
        }
        if (prodCom.getNome() != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Nome = '" + prodCom.getNome() + "'";
            filterCount++;
        }
        /*if (prezzoFloor != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Prezzo > " + prodCom.getPrezzo()prezzoFloor;
            filterCount++;
        }
        if(prezzoCeil != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Prezzo < " + prezzoCeil;
            filterCount++;
        }*/
        if(prodCom.isSfuso() != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Sfuso = " + prodCom.isSfuso();
            filterCount++;
        }
        if (prodCom.getCatProdotto() != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Tipo = '" + prodCom.getCatProdotto().name() + "'";
            filterCount++;
        }
        return query;
    }

    public List<Bibita> getBibita(Bibita bibita)  {
        BibitaSpecifico bibSpec = bibita.getBibitaSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(bibita.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (bibSpec.getGradazioneAlcolica() != null) {
                query += "GradazioneAlcolica > " + bibSpec.getGradazioneAlcolica();
                filterCount++;
            }
            /*if(gradazioneAlcolicaCeil != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "GradazioneAlcolica < " + gradazioneAlcolicaCeil;
                filterCount++;
            }*/
            if(bibSpec.isFrizzante() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Frizzante = " + bibSpec.isFrizzante();
            }
            if(bibSpec.getTipoBibita() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "TipoB = '" + bibSpec.getTipoBibita().name() + "'";
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
                bib.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(bib);
            }
            conn.close();
            return list;
            }
        catch (SQLException se)
        {
            return null;
        }
    }

    public List<Uovo> getUovo(Uovo uovo)
    {

        UovoSpecifico uovoSpecifico = uovo.getUovoSpecifico();

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(uovo.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (uovoSpecifico.getTipoAllevamento() != null) {
                query += "TipoAllevamento = " + uovoSpecifico.getTipoAllevamento();
                filterCount++;
            }
            if(uovoSpecifico.getCodAllevamento() != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "CodAllevamento = '" + uovoSpecifico.getCodAllevamento() + "'";
                filterCount++;
            }
            if(uovoSpecifico.getCatPeso() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "CatPeso = '" + uovoSpecifico.getCatPeso().name() + "'";
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
                Uovo newUovo = new Uovo(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Uovo, rsTipoAllevamento, rsCodAllevamento, rsCatPeso);
                uovo.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newUovo);
            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<CarnePesce> getCarnepesce(CarnePesce carnePesce)
    {

            CarnePesceSpecifico carnePesceSpecifico = carnePesce.getCarnePesceSpecifico();

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(carnePesce.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (carnePesceSpecifico.getTipoCarnePesce() != null) {
                query += "Tipo = '" + carnePesceSpecifico.getTipoCarnePesce().name() + "'";
                filterCount++;
            }
            if(carnePesceSpecifico.isDaAllevamento() != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "DaAllevamento = " + carnePesceSpecifico.isDaAllevamento();
                filterCount++;
            }
            if(carnePesceSpecifico.getAnimale() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Animale = '" + carnePesceSpecifico.getAnimale() + "'";
            }
            if(carnePesceSpecifico.isConfezionato() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Confezionato = " + carnePesceSpecifico.isConfezionato();
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
                CarnePesce newCarnePesce = new CarnePesce(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Carnepesce, rsTipoCP, rsDaAllevamento, rsAnimale, rsConfezionato);
                carnePesce.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newCarnePesce);

            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }


    public List<Farinaceo> getFarinaceo(Farinaceo farinaceo)
    {
        FarinaceoSpecifico farinaceoSpecifico = farinaceo.getFarinaceoSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(farinaceo.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (farinaceoSpecifico.isGlutine() != null) {
                query += "Glutine = " + farinaceoSpecifico.isGlutine();
                filterCount++;
            }
            if(farinaceoSpecifico.getTipoFarina() != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "TipoFarina = '" + farinaceoSpecifico.getTipoFarina() + "'";
                filterCount++;
            }
            if(farinaceoSpecifico.isFresco() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Fresco = " + farinaceoSpecifico.isFresco();
            }
            if(farinaceoSpecifico.isSurgelato() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Surgelato = " + farinaceoSpecifico.isSurgelato();
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
                Farinaceo newFarinaceo = new Farinaceo(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Farinaceo, rsGlutine, rsTipoFarina, rsFresco, rsSurgelato);
                farinaceo.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newFarinaceo);
            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<FruttaVerdura> getFruttaVerdura(FruttaVerdura fruttaVerdura)
    {
        FruttaVerduraSpecifico fruttaVerduraSpecifico = fruttaVerdura.getFruttaVerduraSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(fruttaVerdura.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if(fruttaVerduraSpecifico.getTipoFruttaVerdura() != null)
            {
                query += "Tipo = '" + fruttaVerduraSpecifico.getTipoFruttaVerdura().name() + "'";
                filterCount++;
            }
            if(fruttaVerduraSpecifico.isBio() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Bio = " + fruttaVerduraSpecifico.isBio();
            }
            if(fruttaVerduraSpecifico.isSurgelato() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Surgelato = " + fruttaVerduraSpecifico.isSurgelato();
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
                FruttaVerdura newFruttaVerdura = new FruttaVerdura(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Fruttaverdura, rsTipoFV, rsBio, rsSurgelato);
                fruttaVerdura.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newFruttaVerdura);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<Conserva> getConserva(Conserva conserva)
    {

        ConservaSpecifico conservaSpecifico = conserva.getConservaSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(conserva.getProdottoCommon());
            String query = "";
            if(conservaSpecifico.getTipoConservazione() != null)
            {
                query += "TipoConservazione = '" +conservaSpecifico.getTipoConservazione() + "'";
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
                Conserva newConserva = new Conserva(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Conserva, rsTipoC);
                conserva.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newConserva);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            return null;
        }
    }

    public List<ProdottoCaseario> getProdottoCaseario(ProdottoCaseario prodottoCaseario)
    {

        ProdottoCasearioSpecifico prodCasSpecifico = prodottoCaseario.getProdottoCasearioSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(prodottoCaseario.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if(prodCasSpecifico.getTipoLatte() != null)
            {
                query += "TipoLatte = " + prodCasSpecifico.getTipoLatte();
                filterCount++;
            }
            if(prodCasSpecifico.getStabilimento() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Stabilimento = " + prodCasSpecifico.getStabilimento();
            }
            if(prodCasSpecifico.getStagionatura() != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Stagionatura = " + prodCasSpecifico.getStagionatura();
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
            List<ProdottoCaseario> list = new ArrayList();
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                String rsTipoLatte = rs.getString("TipoLatte");
                String rsStabilimento = rs.getString("Stabilimento");
                int rsStagionatura = rs.getInt("Stagionatura");

                ProdottoCaseario newProdottoCaseario = new ProdottoCaseario(rsId, rsNome, rsPrezzo, rsSfuso, CatProdotto.Prodottocaseario, rsTipoLatte, rsStabilimento, rsStagionatura);
                //List<LottoCaseareo> lottCas = getLottiCaseari()  TODO
                list.add(newProdottoCaseario);

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
    //@Override
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

    public void updateProducts(ProdottoCommon oldProdotto, ProdottoCommon newProdotto) {

        if (oldProdotto.getId() != newProdotto.getId())
        {
            throw new RuntimeException("Gli id sono diversi");
        }

        String updateQuery = "";
        int counter = 0;
        if (oldProdotto.getNome() != newProdotto.getNome())
        {
            updateQuery += String.format("SET Nome = '%s'", newProdotto.getNome());
        }
        if (oldProdotto.getPrezzo() != newProdotto.getPrezzo())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Prezzo = %f", newProdotto.getPrezzo());
        }
        if (oldProdotto.isSfuso() != newProdotto.isSfuso())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Sfuso = " + newProdotto.isSfuso();
        }

        String sql = "UPDATE PRODOTTO " + updateQuery + " WHERE id = " + newProdotto.getId();

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

    public void updateBibita(Bibita oldBibita, Bibita newBibita) {
        updateProducts(oldBibita.getProdottoCommon(), newBibita.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldBibita.getBibitaSpecifico().getGradazioneAlcolica() != newBibita.getBibitaSpecifico().getGradazioneAlcolica())
        {
            updateQuery += String.format("SET GradazioneAlcolica = '%f'", newBibita.getBibitaSpecifico().getGradazioneAlcolica());
        }
        if (oldBibita.getBibitaSpecifico().isFrizzante() != newBibita.getBibitaSpecifico().isFrizzante())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Frizzante = " + newBibita.getBibitaSpecifico().isFrizzante();
        }
        if (oldBibita.getBibitaSpecifico().getTipoBibita() != newBibita.getBibitaSpecifico().getTipoBibita())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET TipoB = '%s'", newBibita.getBibitaSpecifico().getTipoBibita().name());
        }

        String sql = "UPDATE BIBITA " + updateQuery + " WHERE IdProdotto = " + newBibita.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.OpenConnection();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    public void updateUovo(Uovo oldUovo, Uovo newUovo) {
        updateProducts(oldUovo.getProdottoCommon(), newUovo.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldUovo.getUovoSpecifico().getTipoAllevamento() != newUovo.getUovoSpecifico().getTipoAllevamento())
        {
            updateQuery += String.format("SET TipoAllevamento = %d", newUovo.getUovoSpecifico().getTipoAllevamento());
        }
        if (oldUovo.getUovoSpecifico().getCodAllevamento() != newUovo.getUovoSpecifico().getCodAllevamento())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET CodAllevamento = '%s'", newUovo.getUovoSpecifico().getCodAllevamento());
        }
        if (oldUovo.getUovoSpecifico().getCatPeso() != newUovo.getUovoSpecifico().getCatPeso())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET CatPeso = '%s'", newUovo.getUovoSpecifico().getCatPeso().name());
        }

        String sql = "UPDATE UOVO " + updateQuery + " WHERE IdProdotto = " + newUovo.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.OpenConnection();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    public void updateFarinaceo(Farinaceo oldFarinaceo, Farinaceo newFarinaceo) {
        updateProducts(oldFarinaceo.getProdottoCommon(), newFarinaceo.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldFarinaceo.getFarinaceoSpecifico().isGlutine() != newFarinaceo.getFarinaceoSpecifico().isGlutine())
        {
            updateQuery += "SET Glutine = " + newFarinaceo.getFarinaceoSpecifico().isGlutine();
        }
        if (oldFarinaceo.getFarinaceoSpecifico().getTipoFarina() != newFarinaceo.getFarinaceoSpecifico().getTipoFarina())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET TipoFarina = '%s'", newFarinaceo.getFarinaceoSpecifico().getTipoFarina());
        }
        if (oldFarinaceo.getFarinaceoSpecifico().isFresco() != newFarinaceo.getFarinaceoSpecifico().isFresco())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Fresco = " + newFarinaceo.getFarinaceoSpecifico().isFresco();
        }
        if (oldFarinaceo.getFarinaceoSpecifico().isSurgelato() != newFarinaceo.getFarinaceoSpecifico().isSurgelato())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Surgelato = " + newFarinaceo.getFarinaceoSpecifico().isSurgelato();
        }

        String sql = "UPDATE FARINACEO " + updateQuery + " WHERE IdProdotto = " + newFarinaceo.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.OpenConnection();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    public void updateCarnePesce(CarnePesce oldCarnePesce, CarnePesce newCarnePesce) {
        updateProducts(oldCarnePesce.getProdottoCommon(), newCarnePesce.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldCarnePesce.getCarnePesceSpecifico().getTipoCarnePesce() != newCarnePesce.getCarnePesceSpecifico().getTipoCarnePesce())
        {
            updateQuery += String.format("SET TipoCP = '%s'", newCarnePesce.getCarnePesceSpecifico().getTipoCarnePesce().name());
        }
        if (oldCarnePesce.getCarnePesceSpecifico().isDaAllevamento() != newCarnePesce.getCarnePesceSpecifico().isDaAllevamento())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET DaAllevamento = " + newCarnePesce.getCarnePesceSpecifico().isDaAllevamento();
        }
        if (oldCarnePesce.getCarnePesceSpecifico().getAnimale() != newCarnePesce.getCarnePesceSpecifico().getAnimale())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Animale = '%s'", newCarnePesce.getCarnePesceSpecifico().getAnimale());
        }
        if (oldCarnePesce.getCarnePesceSpecifico().isConfezionato() != newCarnePesce.getCarnePesceSpecifico().isConfezionato())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Confezionato = " + newCarnePesce.getCarnePesceSpecifico().isConfezionato();
        }

        String sql = "UPDATE CARNEPESCE " + updateQuery + " WHERE IdProdotto = " + newCarnePesce.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.OpenConnection();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    public void updateFruttaVerdura(FruttaVerdura oldFruttaVerdura, FruttaVerdura newFruttaVerdura) {
        updateProducts(oldFruttaVerdura.getProdottoCommon(), newFruttaVerdura.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldFruttaVerdura.getFruttaVerduraSpecifico().getTipoFruttaVerdura() != newFruttaVerdura.getFruttaVerduraSpecifico().getTipoFruttaVerdura())
        {
            updateQuery += String.format("SET TipoFV = '%s'", newFruttaVerdura.getFruttaVerduraSpecifico().getTipoFruttaVerdura().name());
        }
        if (oldFruttaVerdura.getFruttaVerduraSpecifico().isBio() != newFruttaVerdura.getFruttaVerduraSpecifico().isBio())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Bio = " + newFruttaVerdura.getFruttaVerduraSpecifico().isBio();
        }
        if (oldFruttaVerdura.getFruttaVerduraSpecifico().isSurgelato() != newFruttaVerdura.getFruttaVerduraSpecifico().isSurgelato())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Surgelato = '%s'" + newFruttaVerdura.getFruttaVerduraSpecifico().isSurgelato();
        }

        String sql = "UPDATE FRUTTAVERDURA " + updateQuery + " WHERE IdProdotto = " + newFruttaVerdura.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.OpenConnection();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    public void updateConserva(Conserva oldConserva, Conserva newConserva) {
        updateProducts(oldConserva.getProdottoCommon(), newConserva.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldConserva.getConservaSpecifico().getTipoConservazione() != newConserva.getConservaSpecifico().getTipoConservazione())
        {
            updateQuery += String.format("SET TipoConservazione = '%s'", newConserva.getConservaSpecifico().getTipoConservazione().name());
        }


        String sql = "UPDATE CONSERVA " + updateQuery + " WHERE IdProdotto = " + newConserva.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.OpenConnection();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    public void updateProdottoCaseario(ProdottoCaseario oldProdottoCaseario, ProdottoCaseario newProdottoCaseario) {
        updateProducts(oldProdottoCaseario.getProdottoCommon(), newProdottoCaseario.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldProdottoCaseario.getProdottoCasearioSpecifico().getTipoLatte() != newProdottoCaseario.getProdottoCasearioSpecifico().getTipoLatte())
        {
            updateQuery += String.format("SET TipoLatte = '%s'", newProdottoCaseario.getProdottoCasearioSpecifico().getTipoLatte());
        }
        if (oldProdottoCaseario.getProdottoCasearioSpecifico().getStabilimento() != newProdottoCaseario.getProdottoCasearioSpecifico().getStabilimento())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Stabilimento = '%s'", newProdottoCaseario.getProdottoCasearioSpecifico().getStabilimento());
        }
        if (oldProdottoCaseario.getProdottoCasearioSpecifico().getStagionatura() != newProdottoCaseario.getProdottoCasearioSpecifico().getStagionatura())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Stagionatura = %d" + newProdottoCaseario.getProdottoCasearioSpecifico().getStagionatura());
        }


        String sql = "UPDATE PRODOTTOCASEARIO " + updateQuery + " WHERE IdProdotto = " + newProdottoCaseario.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.OpenConnection();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    //endregion

    private String arrayToString(List<Prodotto> prodotti)
    {
        int[] ids = new int[prodotti.size()];
        int count = 0;
        for(Prodotto prod: prodotti)
        {
            ids[count] = prod.getProdottoCommon().getId();
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
