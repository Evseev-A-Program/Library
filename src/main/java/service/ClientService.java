package service;

import exceptions.ClientNotFoundException;
import models.Clients;
import org.apache.log4j.Logger;
import repository.ClientDao;

import java.util.List;

public class ClientService {
    private static final Logger log = Logger.getLogger(ClientService.class);

    private ClientDao clientDao;

    public ClientService() {
        clientDao = new ClientDao();
    }

    public void saveClient(Clients clients) {
        clientDao.save(clients);
        log.info("Клиент добавлен");
    }

    public Clients findClientById(int id) {
        return clientDao.findClientById(id);
    }

    public void deleteClient(int id) throws ClientNotFoundException {
        Clients clients = clientDao.findClientById(id);
        if (clients != null){
            clientDao.delete(clients);
            log.info("Клиент удален");
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
            log.info("Клиент обновлен");
        } else {
            throw new ClientNotFoundException("The client with id " + id + " was not found");
        }

    }

    public List<Clients> findAllClients() {
        return clientDao.findAll();
    }
}
