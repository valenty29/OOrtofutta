package Models;

import java.util.List;

public interface ProductDAO {
    List<Prodotto> getAllProducts();
    void deleteProducts(List<Prodotto> prodotti);
    void updateProducts(List<Prodotto> prodotti);

}
