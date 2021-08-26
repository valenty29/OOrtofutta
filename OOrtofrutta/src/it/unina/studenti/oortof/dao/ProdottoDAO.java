package it.unina.studenti.oortof.dao;

import java.util.List;

import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.prodotti.Prodotto;
import it.unina.studenti.oortof.models.exception.BatchDatabaseException;
import it.unina.studenti.oortof.models.exception.DatabaseException;
import it.unina.studenti.oortof.models.exception.ValidationException;

public interface ProdottoDAO {
    //List<Prodotto> getProducts(Integer codProdotto, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, CatProdotto tipo);
    //void deleteProducts(List<Prodotto> prodotti);
    //void updateProducts(int id, String nome, Float prezzo, Boolean sfuso);
    void deleteProdotti(List<Prodotto> prodotti) throws DatabaseException;
    void createProdotto(Prodotto prodotto) throws ValidationException, DatabaseException;
    ObservedList<Prodotto> getProdotti(Prodotto prodotto) throws ValidationException, DatabaseException;
    void updateProdotto(Prodotto oldProdotto, Prodotto newProdotto) throws DatabaseException;
}
