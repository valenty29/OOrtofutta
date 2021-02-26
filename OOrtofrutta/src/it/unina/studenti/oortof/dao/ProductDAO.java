package it.unina.studenti.oortof.models;

import java.util.List;

public interface ProductDAO {
    List<Prodotto> getProducts(Integer codProdotto, String nome, Float prezzoFloor, Float prezzoCeil, Boolean sfuso, CatProdotto tipo);
    void deleteProducts(List<Prodotto> prodotti);
    void updateProducts(int id, String nome, Float prezzo, Boolean sfuso);

}
