package service;

import exceptions.ClientNotFoundException;
import lombok.extern.log4j.Log4j;
import models.Clients;
import org.apache.log4j.Logger;
import repository.ClientDao;

import java.util.List;
@Log4j
public class ClientService {

    private ClientDao clientDao;

    public ClientService() {
        clientDao = new ClientDao();
    }

    public void saveClient(Clients clients) {
        clientDao.save(clients);
        log.info("Client save");
    }

    public Clients findClientById(int id) {
        return clientDao.findClientById(id);
    }

    public void deleteClient(int id) throws ClientNotFoundException {
        Clients clients = clientDao.findClientById(id);
        if (clients != null){
            clientDao.delete(clients);
            log.info("Client delete");
        } else {
            throw new ClientNotFoundException("The client with id" + id + "was not found");
        }

    }

    public void updateClient(int id, String firstname, String lastname) throws ClientNotFoundException {
        Clients clients = clientDao.findClientById(id);
        if (clients != null){
            clients.setLastname(lastname);
            clients.setFirstname(firstname);
            clientDao.update(clients);
            log.info("Client update");
        } else {
            throw new ClientNotFoundException("The client with id " + id + " was not found");
        }

    }

    public List<Clients> findAllClients() {
        return clientDao.findAll();
    }
}
