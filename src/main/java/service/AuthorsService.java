package service;

import models.Authors;
import org.apache.log4j.Logger;
import repository.AuthorsDao;

import java.util.List;

public class AuthorsService {
    private static final Logger log = Logger.getLogger(AuthorsService.class);

    private AuthorsDao authorsDao;

    public AuthorsService() {
        authorsDao = new AuthorsDao();
    }

    public Authors findAuthor(int id) {
        return authorsDao.findAuthorsById(id);
    }

    public List<Authors> searchBookForNameAuthors(String name) {
        return authorsDao.searchBookForNameAuthors(name);
    }

    public void saveAuthor(Authors authors) {
        authorsDao.save(authors);
        log.info("Автор добавлен");
    }

    public void deleteAuthor(Authors authors) {
        authorsDao.delete(authors);
        log.info("Автор удален");
    }

    public void updateAuthor(Authors authors) {
        authorsDao.update(authors);
        log.info("Автор обновлен");
    }

    public List<Authors> findAllAuthors() {
        return authorsDao.findAll();
    }



}