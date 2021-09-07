package it.unina.studenti.oortof.dao;

import java.util.List;

import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.prodotti.Prodotto;
import it.unina.studenti.oortof.models.exception.DatabaseException;
import it.unina.studenti.oortof.models.exception.ValidationException;

public interface ProdottoDAO {
    void deleteProdotti(List<Prodotto> prodotti) throws DatabaseException;
    void createProdotto(Prodotto prodotto) throws ValidationException, DatabaseException;
    ObservedList<Prodotto> getProdotti(Prodotto prodotto) throws ValidationException, DatabaseException;
    void updateProdotto(Prodotto oldProdotto, Prodotto newProdotto) throws DatabaseException;
}
