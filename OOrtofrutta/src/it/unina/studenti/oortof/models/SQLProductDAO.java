package it.unina.studenti.oortof.models;

import java.util.List;

public class SQLProductDAO implements ProductDAO {

    private DBContext context;

    public SQLProductDAO(DBContext context)
    {
        this.context = context;
    }

    @Override
    public List<Prodotto> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProducts(List<Prodotto> prodotti) {

    }

    @Override
    public void updateProducts(List<Prodotto> prodotti) {

    }
}
