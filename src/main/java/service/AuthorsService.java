package service;

import lombok.extern.log4j.Log4j;
import models.Authors;
import org.apache.log4j.Logger;
import repository.AuthorsDao;

import java.util.ArrayList;
import java.util.List;
@Log4j
public class AuthorsService {

    private AuthorsDao authorsDao;

    public AuthorsService() {
        authorsDao = new AuthorsDao();
    }

    public Authors findAuthor(int id) {
        return authorsDao.findAuthorsById(id);
    }

    public void saveAuthor(Authors authors) {
        authorsDao.save(authors);
        log.info("Author save");
    }

    public void updateAuthor(Authors authors) {
        authorsDao.update(authors);
        log.info("Author update");
    }



}