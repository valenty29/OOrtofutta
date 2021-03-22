package it.unina.studenti.oortof.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unina.studenti.oortof.models.*;

public class SQLProductDAO implements ProductDAO {

    private DBContext context;

    public SQLProductDAO()
    {
        context = new DBContext();
    }

    //region CREATE
    
    
	
    public void createLotti(ObservedList<Lotto> lotti, long id) {
    	
        String sql = "INSERT INTO LOTTO (CodLotto, IdProdotto, Scadenza, Disponibilita, DataProduzione, CodPaeseOrigine) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createLotto = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            for(Lotto lotto: lotti) {
            	
            	if (lotto.getCodLotto() != null && !lotto.getCodLotto().equals("")) {
            		createLotto.setString(1, lotto.getCodLotto());
                    createLotto.setLong(2, id);
                    createLotto.setDate(3, new java.sql.Date(lotto.getScadenza().getTime()));
                    createLotto.setFloat(4, lotto.getDisponibilita());
                    createLotto.setDate(5, new java.sql.Date(lotto.getDataProduzione().getTime()));
                    createLotto.setString(6, lotto.getCodPaeseOrigine());
                    createLotto.addBatch();
            	}	
            }
            createLotto.executeBatch();
            
            ResultSet rs = createLotto.getGeneratedKeys();
            int counter = 0;
            while(rs.next()) {
            	int idLotto = Math.toIntExact(rs.getLong(1));
            	lotti.get(counter).setId(idLotto);
            	counter++;
            }
            
            conn.close();
        } catch (SQLException e) {
            // TODO
            return;
        }
    }
    
    public void createProduct(Prodotto prodotto) {
    	int id = -1;
    	if (prodotto.getProdottoCommon().isBibita()) {
    		id = createBibita(prodotto);
    	} else if (prodotto.getProdottoCommon().isFruttaVerdura()) {
    		id = createFruttaVerdura(prodotto);
    	} else if (prodotto.getProdottoCommon().isProdottoCaseario()) {
    		id = createProdottoCaseario(prodotto);
    	} else if (prodotto.getProdottoCommon().isUovo()) {
    		id = createUovo(prodotto);
    	} else if (prodotto.getProdottoCommon().isCarnePesce()) {
    		id = createCarnePesce(prodotto);
    	} else if (prodotto.getProdottoCommon().isConserva()) {
    		id = createConserva(prodotto);
    	} else if (prodotto.getProdottoCommon().isFarinaceo()) {
    		id = createFarinaceo(prodotto);
    	} else if (prodotto.getProdottoCommon().isAltro()) {
    		id = createProductCommon(prodotto.getProdottoCommon());
    	}
    	
    	prodotto.getProdottoCommon().setId(id);
    }
    
    private String getTipo(ProdottoCommon prodotto) {
    	String tipo = "";
    	
    	if(prodotto.isAltro()) {
    		tipo = Prodotto.ALTRO;
    	} else if (prodotto.isBibita()) {
    		tipo = Prodotto.BIBITA;
    	} else if (prodotto.isCarnePesce()) {
    		tipo = Prodotto.CARNE_PESCE;
    	} else if (prodotto.isConserva()) {
    		tipo = Prodotto.CONSERVA;
    	} else if (prodotto.isFarinaceo()) {
    		tipo = Prodotto.FARINACEO;
    	} else if (prodotto.isFruttaVerdura()) {
    		tipo = Prodotto.FRUTTA_VERDURA;
    	} else if (prodotto.isProdottoCaseario()) {
    		tipo = Prodotto.PRODOTTO_CASEARIO;
    	} else if (prodotto.isUovo()) {
    		tipo = Prodotto.UOVO;
    	}
    	return tipo;
    }
    
    @SuppressWarnings("unchecked")
	private int createProductCommon(ProdottoCommon prodotto){
        int prodId = -1;
        String tipo = getTipo(prodotto);
        
        
        String sql = "INSERT INTO PRODOTTO (Nome, Prezzo, Sfuso, Tipo) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createProds = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            createProds.setString(1, prodotto.getNome());
            createProds.setFloat(2, prodotto.getPrezzo());
            createProds.setBoolean(3, prodotto.isSfuso());
            createProds.setObject(4, tipo, Types.OTHER);
            createProds.executeUpdate();

            try {
                ResultSet generatedKeys = createProds.getGeneratedKeys();
                if (generatedKeys.next())
                {
                    prodId = Math.toIntExact(generatedKeys.getLong(1));
                 	createLotti(prodotto.getLotti(), prodId);
                    
                }
            } catch (Exception e)
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

    private int createBibita(Prodotto bibita){
        int id = createProductCommon(bibita.getProdottoCommon());
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
            return id;
        } catch (SQLException e) {
            // TODO
            throw new RuntimeException(e);
        }
    }

    private int createUovo(Prodotto uovo){
        int id = createProductCommon(uovo.getProdottoCommon());
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
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }

    private int createCarnePesce(Prodotto carnePesce){
        int id = createProductCommon(carnePesce.getProdottoCommon());
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
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }

    private int createFruttaVerdura(Prodotto fruttaVerdura){
        int id = createProductCommon(fruttaVerdura.getProdottoCommon());
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
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }

    private int createProdottoCaseario(Prodotto prodottoCaseario){
        int id = createProductCommon(prodottoCaseario.getProdottoCommon());
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
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }

    private int createFarinaceo(Prodotto farinaceo){
        int id = createProductCommon(farinaceo.getProdottoCommon());
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
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }

    private int createConserva(Prodotto conserva){
        int id = createProductCommon(conserva.getProdottoCommon());
        String sql = "INSERT INTO CONSERVA (IdProdotto, Glutine, TipoFarina, Fresco, Surgelato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.OpenConnection();
            PreparedStatement createConserva = conn.prepareStatement(sql);
            createConserva.setLong(1, id);
            createConserva.setObject(2, conserva.getConservaSpecifico().getTipoConservazione().name(), Types.OTHER);

            createConserva.executeUpdate();
            conn.close();
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }


    //endregion

    //region READ

    

    @SuppressWarnings("unchecked")
	public ObservedList<Prodotto> getProduct(Prodotto prodotto) {
    	ObservedList<Prodotto> prodotti = new ObservedList<Prodotto>("prodotti");
    	boolean allNull = true;
    	
    	allNull = prodotto.getProdottoCommon().getBibita() == null;
    	allNull &= prodotto.getProdottoCommon().getConserva() == null;
    	allNull &= prodotto.getProdottoCommon().getAltro() == null;
    	allNull &= prodotto.getProdottoCommon().getCarnePesce() == null;
    	allNull &= prodotto.getProdottoCommon().getFarinaceo() == null;
    	allNull &= prodotto.getProdottoCommon().getProdottoCaseario() == null;
    	allNull &= prodotto.getProdottoCommon().getUovo() == null;
    	allNull &= prodotto.getProdottoCommon().getFruttaVerdura() == null;
    	
    	if ( allNull ||  prodotto.getProdottoCommon().isBibita()) {
    		ObservedList<Bibita> prods = getBibita(prodotto);
    		for(Bibita bib: prods) {
    			prodotti.add(bib);
    		}
    	} 
    	if (allNull || prodotto.getProdottoCommon().isConserva()) {
    		ObservedList<Conserva> prods = getConserva(prodotto);
    		for(Conserva cons: prods) {
    			prodotti.add(cons);
    		}
    	}
    	if (allNull || prodotto.getProdottoCommon().isCarnePesce()) {
    		ObservedList<CarnePesce> prods = getCarnePesce(prodotto);
    		for(CarnePesce carnePesce: prods) {
    			prodotti.add(carnePesce);
    		}
    	}
    	if (allNull || prodotto.getProdottoCommon().isFruttaVerdura()) {
    		ObservedList<FruttaVerdura> prods = getFruttaVerdura(prodotto);
    		for(FruttaVerdura fruttaVerdura: prods) {
    			prodotti.add(fruttaVerdura);
    		}
    	}
    	 if (allNull || prodotto.getProdottoCommon().isFarinaceo()) {
    		ObservedList<Farinaceo> prods = getFarinaceo(prodotto);
    		for(Farinaceo farinaceo: prods) {
    			prodotti.add(farinaceo);
    		}
    	 }
    	if (allNull || prodotto.getProdottoCommon().isProdottoCaseario()) {
    		ObservedList<ProdottoCaseario> prods = getProdottoCaseario(prodotto);
    		for(ProdottoCaseario prodottoCaseario: prods) {
    			prodotti.add(prodottoCaseario);
    		}
    	} 
    	if (allNull || prodotto.getProdottoCommon().isUovo()) {
    		ObservedList<Uovo> prods = getUovo(prodotto);
    		for(Uovo uovo: prods) {
    			prodotti.add(uovo);
    		}
    	} 
    	if (allNull || prodotto.getProdottoCommon().isAltro()) {
    		/*ObservedList<Altro> prods = getAltro(prodotto);
    		for (Altro altro: prods) {
    			prodotti.add(altro);
    		}*/
    	}
    	return prodotti;
    }

    private ObservedList<Bibita> getBibita(Prodotto bibita)  {
        BibitaSpecifico bibSpec = bibita.getBibitaSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(bibita.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if(bibSpec.getString (BibitaSpecifico.FRIZZANTE) != null)
            {
                query += "Frizzante = " + bibSpec.isFrizzante();
                filterCount++;
            }
            if (bibSpec.getString(BibitaSpecifico.GRADAZIONE_ALCOLICA) != null && !bibSpec.getString(BibitaSpecifico.GRADAZIONE_ALCOLICA).equals(""))
            {
                if(filterCount != 0)
                    query += " AND ";
                String gradazioneFilter = bibSpec.getString(BibitaSpecifico.GRADAZIONE_ALCOLICA);
                query += String.format("PuntiFruttaVerdura %s", DAOHelpers.getFloatQueryField(gradazioneFilter));
                filterCount++;
            }
            if(bibSpec.getString(BibitaSpecifico.TIPO_BIBITA) != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += String.format("TipoB = '%s'", bibSpec.getTipoBibita().name());
                filterCount++;
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
            ObservedList<Bibita> list = new ObservedList<Bibita>("bibita");
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                float rsGradazione = rs.getFloat("GradazioneAlcolica");
                boolean rsFrizzante = rs.getBoolean("Frizzante");
                TipoBibita rsTipoBibita = TipoBibita.valueOf(rs.getString("TipoB"));
                Bibita bib = new Bibita(rsId, rsNome, rsPrezzo, rsSfuso, rsGradazione, rsFrizzante, rsTipoBibita );
                bib.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(bib);
            }
            conn.close();
            return list;
            }
        catch (SQLException se)
        {
           throw new RuntimeException(se);
        }
    }

    private ObservedList<Uovo> getUovo(Prodotto uovo)
    {

        UovoSpecifico uovoSpecifico = uovo.getUovoSpecifico();

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(uovo.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (uovoSpecifico.getString(UovoSpecifico.TIPO_ALLEVAMENTO) != null) {
                query += "TipoAllevamento = " + uovoSpecifico.getTipoAllevamento();
                filterCount++;
            }
            if(uovoSpecifico.getString(UovoSpecifico.COD_ALLEVAMENTO) != null && !uovoSpecifico.getString(UovoSpecifico.COD_ALLEVAMENTO).equals(""))
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("CodAllevamento = '%s'", uovoSpecifico.getCodAllevamento());
                filterCount++;
            }
            if(uovoSpecifico.getString(UovoSpecifico.CAT_PESO) != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += String.format("CatPeso = '%s'", uovoSpecifico.getCatPeso().name());
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
            ObservedList<Uovo> list = new ObservedList<Uovo>("uovo");
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                int rsTipoAllevamento = rs.getInt("TipoAllevamento");
                String rsCodAllevamento = rs.getString("CodAllevamento");
                CatPeso rsCatPeso = CatPeso.valueOf(rs.getString("CatPeso"));
                Uovo newUovo = new Uovo(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoAllevamento, rsCodAllevamento, rsCatPeso);
                uovo.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newUovo);
            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<CarnePesce> getCarnePesce(Prodotto carnePesce)
    {

            CarnePesceSpecifico carnePesceSpecifico = carnePesce.getCarnePesceSpecifico();

        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(carnePesce.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (carnePesceSpecifico.getString(CarnePesceSpecifico.TIPO_CARNE_PESCE) != null && !carnePesceSpecifico.getString(CarnePesceSpecifico.TIPO_CARNE_PESCE).equals("")) {
                query += String.format("Tipo = '%s'", carnePesceSpecifico.getTipoCarnePesce().name());
                filterCount++;
            }
            if(carnePesceSpecifico.getString(CarnePesceSpecifico.DA_ALLEVAMENTO) != null)
            {
                if (filterCount != 0)
                    query += " AND ";
                query += "DaAllevamento = " + carnePesceSpecifico.isDaAllevamento();
                filterCount++;
            }
            if(carnePesceSpecifico.getString(CarnePesceSpecifico.ANIMALE) != null && !carnePesceSpecifico.getString(CarnePesceSpecifico.ANIMALE).equals(""))
            {
                if(filterCount != 0)
                    query += " AND ";
                query += String.format("Animale = '%s'" + carnePesceSpecifico.getAnimale());
                filterCount++;
            }
            if(carnePesceSpecifico.getString(CarnePesceSpecifico.CONFEZIONATO) != null)
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
            ObservedList<CarnePesce> list = new ObservedList<CarnePesce>("carnePesce");
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
                CarnePesce newCarnePesce = new CarnePesce(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoCP, rsDaAllevamento, rsAnimale, rsConfezionato);
                carnePesce.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newCarnePesce);

            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }


    private ObservedList<Farinaceo> getFarinaceo(Prodotto farinaceo)
    {
        FarinaceoSpecifico farinaceoSpecifico = farinaceo.getFarinaceoSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(farinaceo.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (farinaceoSpecifico.getString(FarinaceoSpecifico.GLUTINE) != null) {
                query += "Glutine = " + farinaceoSpecifico.isGlutine();
                filterCount++;
            }
            if(farinaceoSpecifico.getString(FarinaceoSpecifico.TIPO_FARINA) != null && !farinaceoSpecifico.getString(FarinaceoSpecifico.TIPO_FARINA).equals(""))
            {
                if (filterCount != 0)
                    query += " AND ";
                query += String.format("TipoFarina = '%s'", farinaceoSpecifico.getTipoFarina());
                filterCount++;
            }
            if(farinaceoSpecifico.getString(FarinaceoSpecifico.FRESCO) != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Fresco = " + farinaceoSpecifico.isFresco();
                filterCount++;
            }
            if(farinaceoSpecifico.getString(FarinaceoSpecifico.SURGELATO) != null)
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
            ObservedList<Farinaceo> list = new ObservedList<Farinaceo>("farinaceo");
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
                Farinaceo newFarinaceo = new Farinaceo(rsId, rsNome, rsPrezzo, rsSfuso, rsGlutine, rsTipoFarina, rsFresco, rsSurgelato);
                newFarinaceo.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newFarinaceo);
            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<FruttaVerdura> getFruttaVerdura(Prodotto fruttaVerdura)
    {
        FruttaVerduraSpecifico fruttaVerduraSpecifico = fruttaVerdura.getFruttaVerduraSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(fruttaVerdura.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if(fruttaVerduraSpecifico.getTipoFruttaVerdura() != null && !fruttaVerduraSpecifico.getTipoFruttaVerdura().equals(""))
            {
                query += String.format("Tipo = '%s'", fruttaVerduraSpecifico.getTipoFruttaVerdura().name());
                filterCount++;
            }
            if(fruttaVerduraSpecifico.getString(FruttaVerduraSpecifico.BIO) != null)
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Bio = " + fruttaVerduraSpecifico.isBio();
                filterCount++;
            }
            if(fruttaVerduraSpecifico.getString(FruttaVerduraSpecifico.SURGELATO) != null)
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
            ObservedList<FruttaVerdura> list = new ObservedList<FruttaVerdura>("fruttaVerdura");
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                TipoFruttaVerdura rsTipoFV = TipoFruttaVerdura.valueOf(rs.getString("Tipo"));
                Boolean rsBio = rs.getBoolean("Bio");
                Boolean rsSurgelato = rs.getBoolean("Surgelato");
                FruttaVerdura newFruttaVerdura = new FruttaVerdura(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoFV, rsBio, rsSurgelato);
                fruttaVerdura.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newFruttaVerdura);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<Conserva> getConserva(Prodotto conserva)
    {

        ConservaSpecifico conservaSpecifico = conserva.getConservaSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(conserva.getProdottoCommon());
            String query = "";
            if(conservaSpecifico.getString(ConservaSpecifico.TIPO_CONSERVAZIONE) != null && !conservaSpecifico.getString(ConservaSpecifico.TIPO_CONSERVAZIONE).equals(""))
            {
                query += String.format("TipoConservazione = '%s'", conservaSpecifico.getTipoConservazione());
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
            ObservedList<Conserva> list = new ObservedList<Conserva>("conserva");
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                TipoConservazione rsTipoC= TipoConservazione.valueOf(rs.getString("TipoConservazione"));
                Conserva newConserva = new Conserva(rsId, rsNome, rsPrezzo, rsSfuso,  rsTipoC);
                conserva.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newConserva);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<ProdottoCaseario> getProdottoCaseario(Prodotto prodottoCaseario)
    {

        ProdottoCasearioSpecifico prodCasSpecifico = prodottoCaseario.getProdottoCasearioSpecifico();
        try {
            Connection conn = context.OpenConnection();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(prodottoCaseario.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if(prodCasSpecifico.getString(ProdottoCasearioSpecifico.TIPO_LATTE) != null && !prodCasSpecifico.getString(ProdottoCasearioSpecifico.TIPO_LATTE).equals(""))
            {
                query += String.format("TipoLatte = '%s'", prodCasSpecifico.getTipoLatte());
                filterCount++;
            }
            if(prodCasSpecifico.getString(ProdottoCasearioSpecifico.STABILIMENTO) != null && !prodCasSpecifico.getString(ProdottoCasearioSpecifico.STABILIMENTO).equals(""))
            {
                if(filterCount != 0)
                    query += " AND ";
                query += "Stabilimento = " + prodCasSpecifico.getStabilimento();
                filterCount++;
            }
            if(prodCasSpecifico.getString(ProdottoCasearioSpecifico.STAGIONATURA) != null)
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
            String sql = "SELECT * FROM PRODOTTO INNER JOIN PRODOTTOCASEARIO ON PRODOTTO.id = PRODOTTOCASEARIO.idProdotto" + (query.equals("") ? ";" : " WHERE " + query + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            ObservedList<ProdottoCaseario> list = new ObservedList<ProdottoCaseario>("prodottoCasearioList");
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");
                String rsTipoLatte = rs.getString("TipoLatte");
                String rsStabilimento = rs.getString("Stabilimento");
                int rsStagionatura = rs.getInt("Stagionatura");

                ProdottoCaseario newProdottoCaseario = new ProdottoCaseario(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoLatte, rsStabilimento, rsStagionatura);
                list.add(newProdottoCaseario);

            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }
    
    private ObservedList<Lotto> getLotti(int id, Connection connection)
    {
        ObservedList<Lotto> lotti = new ObservedList<Lotto>("lotto");
        String sql = "SELECT * FROM LOTTO WHERE IdProdotto = " + id + ";";
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
                Date rsDataMungitura = rs.getDate("DataMungitura");
                Lotto lotto = new Lotto(rsId, rsCodLotto, rsScadenza, rsDisponibilita, rsDataProduzione, rsCodPaeseOrigine, rsDataMungitura);
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
            query += "Id = " + prodCom.getId();
            filterCount++;
        }
       
        if (prodCom.getString(ProdottoCommon.NOME) != null && !prodCom.getString(ProdottoCommon.NOME).equals(""))
        {
            if (filterCount != 0)
                query += " AND ";
            query += String.format("Nome = '%s'", prodCom.getNome());
            filterCount++;
        }

        if (prodCom.getString(ProdottoCommon.PREZZO) != null)
        {
            if(filterCount != 0)
                query += " AND ";
            query += String.format("Prezzo %s", DAOHelpers.getFloatQueryField(prodCom.getString(ProdottoCommon.PREZZO)));
        }

        if(prodCom.getString(ProdottoCommon.SFUSO) != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Sfuso = " + prodCom.isSfuso();
            filterCount++;
        }

        return query;
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
    
    public void updateProducts(Prodotto oldProdotto, Prodotto newProdotto) {
    	if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateBibita((Bibita)oldProdotto, (Bibita)newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateFruttaVerdura((FruttaVerdura)oldProdotto, (FruttaVerdura)newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateProdottoCaseario((ProdottoCaseario)oldProdotto, (ProdottoCaseario)newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateUovo((Uovo)oldProdotto, (Uovo)newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateCarnePesce((CarnePesce)oldProdotto, (CarnePesce)newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateConserva((Conserva)oldProdotto, (Conserva)newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateFarinaceo((Farinaceo)oldProdotto, (Farinaceo)newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateProductsCommon(oldProdotto.getProdottoCommon(), newProdotto.getProdottoCommon());
    	}
    }
    
    public void updateLotti(ObservedList<Lotto> oldLotti, ObservedList<Lotto> newLotti, long id) {
    	ObservedList<Lotto> lottiDaCreare = new ObservedList<Lotto>("lottiDaCreare");
    	ObservedList<Lotto> lottiDaAggiornare = new ObservedList<Lotto>("lottiDaAggiornare");
    	
    	for(Lotto lotto: newLotti) {
    		Optional<Lotto> optLotto = oldLotti.stream().filter(oldLotto -> oldLotto.getId() == lotto.getId()).findFirst(); 
    		
    		if (optLotto.isPresent()) {
    			lottiDaAggiornare.add(lotto);
    		} else {
    			lottiDaCreare.add(lotto);
    		}
    	}
    	
    	createLotti(lottiDaCreare, id);
    	
    	if (lottiDaAggiornare.size() > 0) {
    		String sql = "UPDATE LOTTO SET CodLotto = ?, Scadenza = ?, Disponibilita = ?, DataProduzione = ?, CodPaeseOrigine = ?, DataMungitura = ? WHERE id = ?";
        	try {
        		Connection conn = context.OpenConnection();
        		PreparedStatement stm = conn.prepareStatement(sql);
        		
        		for(Lotto lottoDaAggiornare: lottiDaAggiornare) {
            		stm.setString(1, lottoDaAggiornare.getCodLotto());
            		stm.setDate(2, new java.sql.Date(lottoDaAggiornare.getScadenza().getTime()));
            		stm.setFloat(3, lottoDaAggiornare.getDisponibilita());
            		stm.setDate(4, new java.sql.Date(lottoDaAggiornare.getDataProduzione().getTime()));
            		stm.setString(5, lottoDaAggiornare.getCodPaeseOrigine());
            		if (lottoDaAggiornare.getDataMungitura() != null) {
            			stm.setDate(6, new java.sql.Date(lottoDaAggiornare.getDataMungitura().getTime()));
            		} else {
            			stm.setNull(6, java.sql.Types.DATE);
            		}
            		stm.setLong(7, lottoDaAggiornare.getId());
            		
            		stm.addBatch();
        		}
        		stm.executeBatch();
        		conn.close();
        	} catch (Exception e) {
        		throw new RuntimeException(e);
        	}
    	}
    	
    	
    }

    public void updateProductsCommon(ProdottoCommon oldProdotto, ProdottoCommon newProdotto) {

    	
    	
        if (oldProdotto.getId() != newProdotto.getId())
        {
            throw new RuntimeException("Gli id sono diversi");
        }
        
        updateLotti(oldProdotto.getLotti(), newProdotto.getLotti(), newProdotto.getId());

        String updateQuery = "";
        int counter = 0;
        if (oldProdotto.getNome().equals(newProdotto.getNome()))
        {
            updateQuery += String.format("SET Nome = '%s'", newProdotto.getNome());
            counter++;
        }
        if (!oldProdotto.getPrezzo().equals(newProdotto.getPrezzo()))
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET Prezzo = %f", newProdotto.getPrezzo());
            counter++;
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

    private void updateBibita(Bibita oldBibita, Bibita newBibita) {
        updateProductsCommon(oldBibita.getProdottoCommon(), newBibita.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldBibita.getBibitaSpecifico().getGradazioneAlcolica() != newBibita.getBibitaSpecifico().getGradazioneAlcolica())
        {
            updateQuery += String.format("SET GradazioneAlcolica = '%f'", newBibita.getBibitaSpecifico().getGradazioneAlcolica());
            counter++;
        }
        if (oldBibita.getBibitaSpecifico().isFrizzante() != newBibita.getBibitaSpecifico().isFrizzante())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Frizzante = " + newBibita.getBibitaSpecifico().isFrizzante();
            counter++;
        }
        if (!oldBibita.getBibitaSpecifico().getTipoBibita().equals(newBibita.getBibitaSpecifico().getTipoBibita()))
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

    private void updateUovo(Uovo oldUovo, Uovo newUovo) {
        updateProductsCommon(oldUovo.getProdottoCommon(), newUovo.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldUovo.getUovoSpecifico().getTipoAllevamento() != newUovo.getUovoSpecifico().getTipoAllevamento())
        {
            updateQuery += String.format("SET TipoAllevamento = %d", newUovo.getUovoSpecifico().getTipoAllevamento());
            counter++;
        }
        if (oldUovo.getUovoSpecifico().getCodAllevamento().equals(newUovo.getUovoSpecifico().getCodAllevamento()))
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET CodAllevamento = '%s'", newUovo.getUovoSpecifico().getCodAllevamento());
            counter++;
        }
        if (oldUovo.getUovoSpecifico().getCatPeso().equals(newUovo.getUovoSpecifico().getCatPeso()));
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

    private void updateFarinaceo(Farinaceo oldFarinaceo, Farinaceo newFarinaceo) {
        updateProductsCommon(oldFarinaceo.getProdottoCommon(), newFarinaceo.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldFarinaceo.getFarinaceoSpecifico().isGlutine() != newFarinaceo.getFarinaceoSpecifico().isGlutine())
        {
            updateQuery += "SET Glutine = " + newFarinaceo.getFarinaceoSpecifico().isGlutine();
            counter++;
        }
        if (!oldFarinaceo.getFarinaceoSpecifico().getTipoFarina().equals(newFarinaceo.getFarinaceoSpecifico().getTipoFarina()))
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += String.format("SET TipoFarina = '%s'", newFarinaceo.getFarinaceoSpecifico().getTipoFarina());
            counter++;
        }
        if (oldFarinaceo.getFarinaceoSpecifico().isFresco() != newFarinaceo.getFarinaceoSpecifico().isFresco())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            }
            updateQuery += "SET Fresco = " + newFarinaceo.getFarinaceoSpecifico().isFresco();
            counter++;
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

    private void updateCarnePesce(CarnePesce oldCarnePesce, CarnePesce newCarnePesce) {
        updateProductsCommon(oldCarnePesce.getProdottoCommon(), newCarnePesce.getProdottoCommon());

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

    private void updateFruttaVerdura(FruttaVerdura oldFruttaVerdura, FruttaVerdura newFruttaVerdura) {
        updateProductsCommon(oldFruttaVerdura.getProdottoCommon(), newFruttaVerdura.getProdottoCommon());

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

    private void updateConserva(Conserva oldConserva, Conserva newConserva) {
        updateProductsCommon(oldConserva.getProdottoCommon(), newConserva.getProdottoCommon());

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

    private void updateProdottoCaseario(ProdottoCaseario oldProdottoCaseario, ProdottoCaseario newProdottoCaseario) {
        updateProductsCommon(oldProdottoCaseario.getProdottoCommon(), newProdottoCaseario.getProdottoCommon());

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
