package it.unina.studenti.oortof.dao;

import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.Lotto;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.exception.DatabaseException;
import it.unina.studenti.oortof.models.exception.ValidationException;

public interface ClienteDAO {
    ObservedList<Cliente> getClienti(Cliente cliente) throws ValidationException, DatabaseException;
    Cliente updateCliente(Cliente oldCliente, Cliente newCliente) throws ValidationException, DatabaseException;
    void deleteCliente(Cliente clienti);
    Cliente createCliente(Cliente cliente) throws DatabaseException;
    int createScontrino(Cliente cliente, ObservedList<Lotto> lotti) throws DatabaseException;




}
