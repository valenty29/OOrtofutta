package it.unina.studenti.oortof.dao;


import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unina.studenti.oortof.models.entities.Lotto;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.prodotti.Altro;
import it.unina.studenti.oortof.models.entities.prodotti.Bibita;
import it.unina.studenti.oortof.models.entities.prodotti.BibitaSpecifico;
import it.unina.studenti.oortof.models.entities.prodotti.CarnePesce;
import it.unina.studenti.oortof.models.entities.prodotti.CarnePesceSpecifico;
import it.unina.studenti.oortof.models.entities.prodotti.Conserva;
import it.unina.studenti.oortof.models.entities.prodotti.ConservaSpecifico;
import it.unina.studenti.oortof.models.entities.prodotti.Farinaceo;
import it.unina.studenti.oortof.models.entities.prodotti.FarinaceoSpecifico;
import it.unina.studenti.oortof.models.entities.prodotti.FruttaVerdura;
import it.unina.studenti.oortof.models.entities.prodotti.FruttaVerduraSpecifico;
import it.unina.studenti.oortof.models.entities.prodotti.Prodotto;
import it.unina.studenti.oortof.models.entities.prodotti.ProdottoCaseario;
import it.unina.studenti.oortof.models.entities.prodotti.ProdottoCasearioSpecifico;
import it.unina.studenti.oortof.models.entities.prodotti.ProdottoCommon;
import it.unina.studenti.oortof.models.entities.prodotti.TipoBibita;
import it.unina.studenti.oortof.models.entities.prodotti.Uovo;
import it.unina.studenti.oortof.models.entities.prodotti.UovoSpecifico;
import it.unina.studenti.oortof.models.entities.prodotti.enumeration.CatPeso;
import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoCarnePesce;
import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoConservazione;
import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoFruttaVerdura;

import it.unina.studenti.oortof.models.exception.DatabaseException;
import it.unina.studenti.oortof.models.exception.FieldException;
import it.unina.studenti.oortof.models.exception.ValidationException;

import org.postgresql.util.PSQLException;

public class SQLProdottoDAO implements ProdottoDAO {

    private DBContext context;

    public SQLProdottoDAO()
    {
        context = new DBContext();
    }

    //region CREATE
    
    
	
    private void createLotti(ObservedList<Lotto> lotti, long id) throws DatabaseException {
    	ArrayList<DatabaseException> exceptionList = new ArrayList();
        String sql = "INSERT INTO LOTTO (CodLotto, IdProdotto, Scadenza, Disponibilita, DataProduzione, CodPaeseOrigine) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
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
        } catch (BatchUpdateException e) {

            PSQLException psqlExc = (PSQLException)e.getNextException();

            if (psqlExc.getSQLState().equals("T1GR0")) {
                throw new DatabaseException(psqlExc.getMessage());
            } else if (psqlExc.getSQLState().equals("23514")) {
                String constrDesc = "Un constraint non e' stato rispettato";

                switch (((PSQLException) e.getNextException()).getServerErrorMessage().getConstraint()) {
                    case "data_prod":
                        constrDesc = "La data di produzione non puo' essere successiva alla data odierna";
                        break;
                    case "data_mungitura":
                        constrDesc = "La data di mungitura non puo' essere successiva alla data odierna";
                        break;
                }

                throw new DatabaseException(constrDesc);
            }
        } catch (SQLException se) {
            throw new DatabaseException("Si e' verificato un errore nell'operazione sulla base dati");
        }
    }
    
    public void createProdotto(Prodotto prodotto) throws ValidationException, DatabaseException {
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
	private int createProductCommon(ProdottoCommon prodotto) throws ValidationException {
        ArrayList<FieldException> exceptions = new ArrayList();
        int prodId = -1;
        String tipo = getTipo(prodotto);
        
        
        String sql = "INSERT INTO PRODOTTO (Nome, Prezzo, Sfuso, Tipo) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
            PreparedStatement createProds = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (prodotto.getNome().isBlank() || prodotto.getNome().isEmpty()) {
                exceptions.add(new FieldException(prodotto.getNome(), "Il nome e' un campo richiesto", java.util.Optional.empty()));
            } else {
                createProds.setString(1, prodotto.getNome());
            }
            if (prodotto.getPrezzo() == null) {
                exceptions.add(new FieldException("null", "Il prezzo e' un campo richiesto", java.util.Optional.empty()));
            } else {
                createProds.setFloat(2, prodotto.getPrezzo());
            }

            createProds.setBoolean(3, prodotto.isSfuso());

            createProds.setObject(4, tipo, Types.OTHER);

            if (exceptions.size() > 0) {
                throw new ValidationException(exceptions);
            }

            createProds.executeUpdate();

            try {
                ResultSet generatedKeys = createProds.getGeneratedKeys( );
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

    private int createBibita(Prodotto bibita) throws ValidationException, DatabaseException {
        ArrayList<FieldException> exceptions = new ArrayList();
        int id = -1;
        try {
            id = createProductCommon(bibita.getProdottoCommon());
        } catch (ValidationException ve) {
            exceptions.addAll(ve.getExceptionList());
        }

        String sql = "INSERT INTO BIBITA (IdProdotto, GradazioneAlcolica, Frizzante, TipoB) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
            PreparedStatement createBibs = conn.prepareStatement(sql);
            createBibs.setLong(1, id);
            if (bibita.getBibitaSpecifico().getGradazioneAlcolica() == null) {
                exceptions.add(new FieldException("null", "Gradazione alcolica e' un campo richiesto", java.util.Optional.empty()));
            }  else {
                createBibs.setFloat(2, bibita.getBibitaSpecifico().getGradazioneAlcolica());
            }
            createBibs.setBoolean(3, bibita.getBibitaSpecifico().isFrizzante());
            if (bibita.getBibitaSpecifico().getTipoBibita() == null) {
                exceptions.add(new FieldException("null", "Tipo bibita e' un campo richiesto", java.util.Optional.empty()));
            } else {
                createBibs.setObject(4, bibita.getBibitaSpecifico().getTipoBibita().name(), Types.OTHER);
            }


            if (exceptions.size() > 0) {
                conn.close();
                throw new ValidationException(exceptions);
            }
            createBibs.executeUpdate();
            conn.close();
            return id;
        } catch (SQLException e) {
            // TODO
            throw new RuntimeException(e);
        }
    }

    private int createUovo(Prodotto uovo) throws ValidationException {
        ArrayList<FieldException> exceptions = new ArrayList<FieldException>();
        int id = -1;
        try {
            id = createProductCommon(uovo.getProdottoCommon());
        } catch (ValidationException ve) {
            exceptions.addAll(ve.getExceptionList());
            throw new ValidationException(exceptions);
        }

        String sql = "INSERT INTO UOVO (IdProdotto, TipoAllevamento, CatPeso) VALUES (?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
            PreparedStatement createUovo = conn.prepareStatement(sql);
            createUovo.setLong(1, id);
            createUovo.setInt(2, uovo.getUovoSpecifico().getTipoAllevamento());
            createUovo.setObject(3, uovo.getUovoSpecifico().getCatPeso().name(), Types.OTHER);

            createUovo.executeUpdate();
            conn.close();
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }

    private int createCarnePesce(Prodotto carnePesce) throws ValidationException {
        int id = createProductCommon(carnePesce.getProdottoCommon());
        String sql = "INSERT INTO CARNEPESCE (IdProdotto, TipoCP, DaAllevamento, Animale, Confezionato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
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

    private int createFruttaVerdura(Prodotto fruttaVerdura) throws ValidationException {
        int id = createProductCommon(fruttaVerdura.getProdottoCommon());
        String sql = "INSERT INTO FRUTTAVERDURA (IdProdotto, TipoFV, Bio, Surgelato) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
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

    private int createProdottoCaseario(Prodotto prodottoCaseario) throws ValidationException {
        int id = createProductCommon(prodottoCaseario.getProdottoCommon());
        String sql = "INSERT INTO PRODOTTOCASEARIO (IdProdotto, TipoLatte, Stabilimento, Stagionatura) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
            PreparedStatement createProdottoCaseario = conn.prepareStatement(sql);
            createProdottoCaseario.setLong(1, id);
            createProdottoCaseario.setString(2, prodottoCaseario.getProdottoCasearioSpecifico().getTipoLatte());
            createProdottoCaseario.setInt(3, prodottoCaseario.getProdottoCasearioSpecifico().getStagionatura());

            createProdottoCaseario.executeUpdate();
            conn.close();
            return id;
        } catch (SQLException e) {
            // TODO
        	throw new RuntimeException(e);
        }
    }

    private int createFarinaceo(Prodotto farinaceo) throws ValidationException {
        int id = createProductCommon(farinaceo.getProdottoCommon());
        String sql = "INSERT INTO FARINACEO (IdProdotto, Glutine, TipoFarina, Fresco, Surgelato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
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

    private int createConserva(Prodotto conserva) throws ValidationException {
        int id = createProductCommon(conserva.getProdottoCommon());
        String sql = "INSERT INTO CONSERVA (IdProdotto, Glutine, TipoFarina, Fresco, Surgelato) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = context.openConnessione();
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

    


	public ObservedList<Prodotto> getProdotti(Prodotto prodotto) throws ValidationException, DatabaseException{
    	ObservedList<Prodotto> prodotti = new ObservedList<Prodotto>("prodotti");
    	boolean allNull = true;
    	
        	allNull = !prodotto.getProdottoCommon().isBibita();
    	allNull &= !prodotto.getProdottoCommon().isConserva();
    	allNull &= !prodotto.getProdottoCommon().isConserva();
    	allNull &= !prodotto.getProdottoCommon().isCarnePesce();
    	allNull &= !prodotto.getProdottoCommon().isFarinaceo();
    	allNull &= !prodotto.getProdottoCommon().isProdottoCaseario();
    	allNull &= !prodotto.getProdottoCommon().isUovo();
    	allNull &= !prodotto.getProdottoCommon().isFruttaVerdura();
    	
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
    		ObservedList<Altro> prods = getAltro(prodotto);
    		for (Altro altro: prods) {
    			prodotti.add(altro);
    		}
    	}
    	return prodotti;
    }

    private ObservedList<Altro> getAltro(Prodotto prod) throws ValidationException, DatabaseException {

        try {
            Connection conn = context.openConnessione();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(prod.getProdottoCommon());

            int filterCount = 0;



            String sql = "SELECT * FROM PRODOTTO WHERE Tipo = 'Altro'" + (productFilters.equals("") ? ";" : " AND " + productFilters + ";");
            System.out.println(sql);
            ResultSet rs = stm.executeQuery(sql);
            ObservedList<Altro> list = new ObservedList<Altro>("altro");
            while(rs.next())
            {
                int rsId = rs.getInt("Id");
                String rsNome = rs.getString("Nome");
                boolean rsSfuso = rs.getBoolean("Sfuso");
                float rsPrezzo = rs.getFloat("Prezzo");

                Altro altro = new Altro(rsId, rsNome, rsPrezzo, rsSfuso);


                altro.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(altro);
            }
            conn.close();
            return list;
        }
        catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<Bibita> getBibita(Prodotto bibita) throws ValidationException, DatabaseException {
        BibitaSpecifico bibSpec = bibita.getBibitaSpecifico();
        ArrayList<FieldException> exceptionList = new ArrayList<>();
        try {
            Connection conn = context.openConnessione();
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
                try {
                    query += String.format("PuntiFruttaVerdura %s", DAOHelpers.getFloatQueryField(gradazioneFilter));
                } catch (FieldException fe){
                    exceptionList.add(fe);
                }

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

            if (exceptionList.size() > 0) {
                throw new ValidationException(exceptionList);
            }
            return list;
            }
        catch (SQLException se)
        {
           throw new RuntimeException(se);
        }
    }

    private ObservedList<Uovo> getUovo(Prodotto uovo) throws ValidationException, DatabaseException {

        UovoSpecifico uovoSpecifico = uovo.getUovoSpecifico();

        try {
            Connection conn = context.openConnessione();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(uovo.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if (uovoSpecifico.getString(UovoSpecifico.TIPO_ALLEVAMENTO) != null) {
                query += "TipoAllevamento = " + uovoSpecifico.getTipoAllevamento();
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
                CatPeso rsCatPeso = CatPeso.valueOf(rs.getString("CatPeso"));
                Uovo newUovo = new Uovo(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoAllevamento,rsCatPeso);
                newUovo.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newUovo);
            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<CarnePesce> getCarnePesce(Prodotto carnePesce) throws ValidationException, DatabaseException {

            CarnePesceSpecifico carnePesceSpecifico = carnePesce.getCarnePesceSpecifico();

        try {
            Connection conn = context.openConnessione();
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

                TipoCarnePesce rsTipoCP = TipoCarnePesce.valueOf(rs.getString("TipoCP"));
                Boolean rsDaAllevamento = rs.getBoolean("DaAllevamento");
                String rsAnimale = rs.getString("Animale");
                Boolean rsConfezionato = rs.getBoolean("Confezionato");
                CarnePesce newCarnePesce = new CarnePesce(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoCP, rsDaAllevamento, rsAnimale, rsConfezionato);
                newCarnePesce.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newCarnePesce);

            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }


    private ObservedList<Farinaceo> getFarinaceo(Prodotto farinaceo) throws ValidationException, DatabaseException {
        FarinaceoSpecifico farinaceoSpecifico = farinaceo.getFarinaceoSpecifico();
        try {
            Connection conn = context.openConnessione();
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

    private ObservedList<FruttaVerdura> getFruttaVerdura(Prodotto fruttaVerdura) throws ValidationException, DatabaseException {
        FruttaVerduraSpecifico fruttaVerduraSpecifico = fruttaVerdura.getFruttaVerduraSpecifico();
        try {
            Connection conn = context.openConnessione();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(fruttaVerdura.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if(fruttaVerduraSpecifico.getTipoFruttaVerdura() != null && !fruttaVerduraSpecifico.getTipoFruttaVerdura().equals(""))
            {
                query += String.format("TipoFV = '%s'", fruttaVerduraSpecifico.getTipoFruttaVerdura().name());
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
                TipoFruttaVerdura rsTipoFV = TipoFruttaVerdura.valueOf(rs.getString("TipoFV"));
                Boolean rsBio = rs.getBoolean("Bio");
                Boolean rsSurgelato = rs.getBoolean("Surgelato");
                FruttaVerdura newFruttaVerdura = new FruttaVerdura(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoFV, rsBio, rsSurgelato);
                newFruttaVerdura.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newFruttaVerdura);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<Conserva> getConserva(Prodotto conserva) throws ValidationException, DatabaseException {

        ConservaSpecifico conservaSpecifico = conserva.getConservaSpecifico();
        try {
            Connection conn = context.openConnessione();
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
                newConserva.getProdottoCommon().setLotti(getLotti(rsId, conn));
                list.add(newConserva);
            }
            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<ProdottoCaseario> getProdottoCaseario(Prodotto prodottoCaseario) throws ValidationException, DatabaseException {

        ProdottoCasearioSpecifico prodCasSpecifico = prodottoCaseario.getProdottoCasearioSpecifico();
        try {
            Connection conn = context.openConnessione();
            Statement stm = conn.createStatement();
            String productFilters = getProdottoFilters(prodottoCaseario.getProdottoCommon());
            String query = "";
            int filterCount = 0;
            if(prodCasSpecifico.getString(ProdottoCasearioSpecifico.TIPO_LATTE) != null && !prodCasSpecifico.getString(ProdottoCasearioSpecifico.TIPO_LATTE).equals(""))
            {
                query += String.format("TipoLatte = '%s'", prodCasSpecifico.getTipoLatte());
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
                int rsStagionatura = rs.getInt("Stagionatura");
                ProdottoCaseario newProdottoCaseario = new ProdottoCaseario(rsId, rsNome, rsPrezzo, rsSfuso, rsTipoLatte, rsStagionatura);
                //TODO LOtti caseari
                list.add(newProdottoCaseario);

            }

            conn.close();
            return list;
        } catch (SQLException se)
        {
            throw new RuntimeException(se);
        }
    }

    private ObservedList<Lotto> getLotti(int id, Connection connessione) throws ValidationException, DatabaseException
    {
        ObservedList<Lotto> lotti = new ObservedList<Lotto>("lotto");
        String sql = "SELECT * FROM LOTTO WHERE IdProdotto = " + id + ";";
        try {
            Statement stm = connessione.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next())
            {
                int rsId = rs.getInt("id");
                int rsIdProdotto = rs.getInt("idProdotto");
                String rsCodLotto = rs.getString("CodLotto");
                Date rsScadenza = rs.getDate("Scadenza");
                float rsDisponibilita = rs.getFloat("Disponibilita");
                Date rsDataProduzione = rs.getDate("DataProduzione");
                String rsCodPaeseOrigine = rs.getString("CodPaeseOrigine");
                Date rsDataMungitura = rs.getDate("DataMungitura");

                String prodRicerca = "SELECT * FROM PRODOTTO WHERE Id = " + rsIdProdotto;
                Statement stmProd = connessione.createStatement();
                ResultSet rsProd = stmProd.executeQuery(prodRicerca);

                if (rsProd.next()) {
                    int rsIdProd = rsProd.getInt("Id");
                    String rsNomeProd = rsProd.getString("Nome");
                    boolean rsSfusoProd = rsProd.getBoolean("Sfuso");
                    float rsPrezzoProd = rsProd.getFloat("Prezzo");
                    ProdottoCommon prodCommon = new ProdottoCommon(rsIdProd, rsNomeProd, rsPrezzoProd, rsSfusoProd);
                    Lotto lotto = new Lotto(rsId, prodCommon, rsCodLotto, rsScadenza, rsDisponibilita, rsDataProduzione, rsCodPaeseOrigine, rsDataMungitura);
                    lotti.add(lotto);
                } else {
                    throw new DatabaseException("Il lotto non ha un prodotto di base");
                }
            }
            return lotti;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
    

    private String getProdottoFilters(ProdottoCommon prodCom) throws ValidationException {

        ArrayList<FieldException> exceptions = new ArrayList();
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

        if (prodCom.getString(ProdottoCommon.PREZZO) != null && !prodCom.getString(ProdottoCommon.PREZZO).equals("")) 
        {
            if(filterCount != 0)
                query += " AND ";
            try {
                query += String.format("Prezzo %s", DAOHelpers.getFloatQueryField(prodCom.getString(ProdottoCommon.PREZZO)));
            } catch (FieldException fe) {
                exceptions.add(fe);
            }

        }

        if(prodCom.getString(ProdottoCommon.SFUSO) != null)
        {
            if (filterCount != 0)
                query += " AND ";
            query += "Sfuso = " + prodCom.isSfuso();
            filterCount++;
        }

        if (exceptions.size() > 0) {
            throw new ValidationException(exceptions);
        }

        return query;
    }


    //endregion

    //region DELETE
    //la presenza di lotti impedisce la cancellazione di un prodotto
    //@Override
    public void deleteProdotti(List<Prodotto> prodotti) {
        String inSql = arrayToString(prodotti);
        String sql = "DELETE FROM PRODOTTO WHERE id IN (" +inSql + ");";

        try {
            Connection conn = context.openConnessione();
            Statement stm = conn.createStatement();
            stm.execute(sql);
            conn.close();
        } catch (SQLException se)
        {
            System.out.println(se);
            return;
        }
    }

    private void deleteLotti(ObservedList<Lotto> lotti) {
        String inSql = arrayToString(lotti);
        String sql = "DELETE FROM LOTTO WHERE id IN (" +inSql + ");";

        try {
            Connection conn = context.openConnessione();
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
    
    public void updateProdotto(Prodotto oldProdotto, Prodotto newProdotto) throws DatabaseException {
    	if (oldProdotto.getProdottoCommon().isBibita() && newProdotto.getProdottoCommon().isBibita()) {
    		updateBibita(oldProdotto, newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isFruttaVerdura() && newProdotto.getProdottoCommon().isFruttaVerdura()) {
    		updateFruttaVerdura(oldProdotto, newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isProdottoCaseario() && newProdotto.getProdottoCommon().isProdottoCaseario()) {
    		updateProdottoCaseario(oldProdotto, newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isUovo() && newProdotto.getProdottoCommon().isUovo()) {
    		updateUovo(oldProdotto, newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isCarnePesce() && newProdotto.getProdottoCommon().isCarnePesce()) {
    		updateCarnePesce(oldProdotto, newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isConserva() && newProdotto.getProdottoCommon().isConserva()) {
    		updateConserva(oldProdotto, newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isFarinaceo() && newProdotto.getProdottoCommon().isFarinaceo()) {
    		updateFarinaceo(oldProdotto, newProdotto);
    	} else if (oldProdotto.getProdottoCommon().isAltro() && newProdotto.getProdottoCommon().isAltro()) {
    		updateProductsCommon(oldProdotto.getProdottoCommon(), newProdotto.getProdottoCommon());
    	}
    }
    
    private void updateLotti(ObservedList<Lotto> oldLotti, ObservedList<Lotto> newLotti, long id) throws DatabaseException {

    	ObservedList<Lotto> lottiDaCreare = new ObservedList<Lotto>("lottiDaCreare");
    	ObservedList<Lotto> lottiDaAggiornare = new ObservedList<Lotto>("lottiDaAggiornare");
    	ObservedList<Lotto> lottiDaEliminare = new ObservedList<>("lottiDaEliminare");
    	newLotti.removeIf(lotto -> lotto.getCodLotto() == null);

    	for(Lotto lotto: newLotti) {

    		Optional<Lotto> optLotto = oldLotti.stream().filter(oldLotto -> oldLotto.getId().equals(lotto.getId())).findFirst();
    		
    		if (optLotto.isPresent()) {
    			lottiDaAggiornare.add(lotto);
    		} else {
    			lottiDaCreare.add(lotto);
    		}
    	}

        for(Lotto lotto: oldLotti) {

            Optional<Lotto> optLotto = newLotti.stream().filter(newLotto -> newLotto.getId().equals(lotto.getId())).findFirst();

            if (!optLotto.isPresent()) {
                lottiDaEliminare.add(lotto);
            }

        }

        if (lottiDaEliminare.size() > 0) {
            deleteLotti(lottiDaEliminare);
        }

        if (lottiDaCreare.size() > 0) {
            createLotti(lottiDaCreare, id);
        }




    	
    	if (lottiDaAggiornare.size() > 0) {
    		String sql = "UPDATE LOTTO SET CodLotto = ?, Scadenza = ?, Disponibilita = ?, DataProduzione = ?, CodPaeseOrigine = ?, DataMungitura = ? WHERE id = ?";
        	try {
        		Connection conn = context.openConnessione();
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

    private void updateProductsCommon(ProdottoCommon oldProdotto, ProdottoCommon newProdotto) throws DatabaseException {

    	
    	
        if (oldProdotto.getId() != newProdotto.getId())
        {
            throw new RuntimeException("Gli id sono diversi");
        }
        
        updateLotti(oldProdotto.getLotti(), newProdotto.getLotti(), newProdotto.getId());

        String updateQuery = "";
        int counter = 0;
        if (!oldProdotto.getNome().equals(newProdotto.getNome()))
        {
            updateQuery += String.format("SET Nome = '%s'", newProdotto.getNome());
            counter++;
        }
        if (!oldProdotto.getPrezzo().equals(newProdotto.getPrezzo()))
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += String.format(" Prezzo = %f", newProdotto.getPrezzo());
            counter++;
        }
        if (oldProdotto.isSfuso() != newProdotto.isSfuso())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += " Sfuso = " + newProdotto.isSfuso();
        }

        String sql = "UPDATE PRODOTTO " + updateQuery + " WHERE id = " + newProdotto.getId();

        if(updateQuery != "") {
            try {
                Connection conn = context.openConnessione();
                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    private void updateBibita(Prodotto oldBibita, Prodotto newBibita) throws DatabaseException{
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
            } else {
                updateQuery += "SET";
            }
            updateQuery += " Frizzante = " + newBibita.getBibitaSpecifico().isFrizzante();
            counter++;
        }
        if (!oldBibita.getBibitaSpecifico().getTipoBibita().equals(newBibita.getBibitaSpecifico().getTipoBibita()))
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += String.format(" TipoB = '%s'", newBibita.getBibitaSpecifico().getTipoBibita().name());
            
        }

        String sql = "UPDATE BIBITA " + updateQuery + " WHERE IdProdotto = " + newBibita.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.openConnessione();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    private void updateUovo(Prodotto oldUovo, Prodotto newUovo) throws DatabaseException {
        updateProductsCommon(oldUovo.getProdottoCommon(), newUovo.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldUovo.getUovoSpecifico().getTipoAllevamento() != newUovo.getUovoSpecifico().getTipoAllevamento())
        {
            updateQuery += String.format("SET TipoAllevamento = %d", newUovo.getUovoSpecifico().getTipoAllevamento());
            counter++;
        }
        if (oldUovo.getUovoSpecifico().getCatPeso().equals(newUovo.getUovoSpecifico().getCatPeso()));
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += String.format(" CatPeso = '%s'", newUovo.getUovoSpecifico().getCatPeso().name());
        }

        String sql = "UPDATE UOVO " + updateQuery + " WHERE IdProdotto = " + newUovo.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.openConnessione();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    private void updateFarinaceo(Prodotto oldFarinaceo, Prodotto newFarinaceo) throws DatabaseException {
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
            } else {
                updateQuery += "SET";
            }
            updateQuery += String.format(" TipoFarina = '%s'", newFarinaceo.getFarinaceoSpecifico().getTipoFarina());
            counter++;
        }
        if (oldFarinaceo.getFarinaceoSpecifico().isFresco() != newFarinaceo.getFarinaceoSpecifico().isFresco())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += " Fresco = " + newFarinaceo.getFarinaceoSpecifico().isFresco();
            counter++;
        }
        if (oldFarinaceo.getFarinaceoSpecifico().isSurgelato() != newFarinaceo.getFarinaceoSpecifico().isSurgelato())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += " Surgelato = " + newFarinaceo.getFarinaceoSpecifico().isSurgelato();
        }

        String sql = "UPDATE FARINACEO " + updateQuery + " WHERE IdProdotto = " + newFarinaceo.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.openConnessione();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    private void updateCarnePesce(Prodotto oldCarnePesce, Prodotto newCarnePesce) throws DatabaseException {
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
            } else {
                updateQuery += "SET";
            }
            updateQuery += " DaAllevamento = " + newCarnePesce.getCarnePesceSpecifico().isDaAllevamento();
            counter++;
        }
        if (oldCarnePesce.getCarnePesceSpecifico().getAnimale() != newCarnePesce.getCarnePesceSpecifico().getAnimale())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += String.format(" Animale = '%s'", newCarnePesce.getCarnePesceSpecifico().getAnimale());
            counter++;
        }
        if (oldCarnePesce.getCarnePesceSpecifico().isConfezionato() != newCarnePesce.getCarnePesceSpecifico().isConfezionato())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += " Confezionato = " + newCarnePesce.getCarnePesceSpecifico().isConfezionato();
        }

        String sql = "UPDATE CARNEPESCE " + updateQuery + " WHERE IdProdotto = " + newCarnePesce.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.openConnessione();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    private void updateFruttaVerdura(Prodotto oldFruttaVerdura, Prodotto newFruttaVerdura) throws DatabaseException {
        updateProductsCommon(oldFruttaVerdura.getProdottoCommon(), newFruttaVerdura.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldFruttaVerdura.getFruttaVerduraSpecifico().getTipoFruttaVerdura() != newFruttaVerdura.getFruttaVerduraSpecifico().getTipoFruttaVerdura())
        {
            updateQuery += String.format("SET TipoFV = '%s'", newFruttaVerdura.getFruttaVerduraSpecifico().getTipoFruttaVerdura().name());
            counter++;
        }
        if (oldFruttaVerdura.getFruttaVerduraSpecifico().isBio() != newFruttaVerdura.getFruttaVerduraSpecifico().isBio())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += " Bio = " + newFruttaVerdura.getFruttaVerduraSpecifico().isBio();
            counter++;
        }
        if (oldFruttaVerdura.getFruttaVerduraSpecifico().isSurgelato() != newFruttaVerdura.getFruttaVerduraSpecifico().isSurgelato())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += " Surgelato = " + newFruttaVerdura.getFruttaVerduraSpecifico().isSurgelato();
        }

        String sql = "UPDATE FRUTTAVERDURA " + updateQuery + " WHERE IdProdotto = " + newFruttaVerdura.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.openConnessione();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    private void updateConserva(Prodotto oldConserva, Prodotto newConserva) throws DatabaseException  {
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
                Connection conn = context.openConnessione();

                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);
            } catch (SQLException e) {
                return;
            }
        }
    }

    private void updateProdottoCaseario(Prodotto oldProdottoCaseario, Prodotto newProdottoCaseario) throws DatabaseException {
        updateProductsCommon(oldProdottoCaseario.getProdottoCommon(), newProdottoCaseario.getProdottoCommon());

        String updateQuery = "";
        int counter = 0;
        if (oldProdottoCaseario.getProdottoCasearioSpecifico().getTipoLatte() != newProdottoCaseario.getProdottoCasearioSpecifico().getTipoLatte())
        {
            updateQuery += String.format("SET TipoLatte = '%s'", newProdottoCaseario.getProdottoCasearioSpecifico().getTipoLatte());
            counter++;
        }
        if (oldProdottoCaseario.getProdottoCasearioSpecifico().getStagionatura() != newProdottoCaseario.getProdottoCasearioSpecifico().getStagionatura())
        {
            if (counter != 0)
            {
                updateQuery += ",";
            } else {
                updateQuery += "SET";
            }
            updateQuery += String.format(" Stagionatura = %d", newProdottoCaseario.getProdottoCasearioSpecifico().getStagionatura());
        }


        String sql = "UPDATE PRODOTTOCASEARIO " + updateQuery + " WHERE IdProdotto = " + newProdottoCaseario.getProdottoCommon().getId();
        if (updateQuery != ""){
            try {
                Connection conn = context.openConnessione();

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

    private String arrayToString(ObservedList<Lotto> lotti)
    {
        int[] ids = new int[lotti.size()];
        int count = 0;
        for(Lotto lotto: lotti)
        {
            ids[count] = lotto.getId();
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
