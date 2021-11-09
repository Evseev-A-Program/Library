package service;

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
        return clientDao.findBooksById(id);
    }

    public void deleteClient(int id) {
        clientDao.delete(id);
        log.info("Клиент удален");
    }

    public void updateClient(int id, String firstname, String lastname) {
        clientDao.update(id, firstname, lastname);
        log.info("Клиент обновлен");
    }

    public List<Clients> findAllClient() {
        return clientDao.findAll();
    }
}
