package service;

import models.Books;
import org.apache.log4j.Logger;
import repository.BooksDao;

import java.util.List;

public class BooksService {
    private static final Logger log = Logger.getLogger(BooksService.class);

    private BooksDao booksDao;

    public BooksService() {
        booksDao  = new BooksDao();
    }

    public List<Books> searchBooks(String name) {
        return booksDao.searchBookForName(name);
    }

    public Books findBooksById(int id) {
        return booksDao.findBooksById(id);
    }

    public void deleteBooks(int id) {
        booksDao.delete(id);
        log.info("Книга удалена");
    }

    public void updateBooks(int id, String name, String category) {
        booksDao.update(id, name, category);
        log.info("Книга обновлена");
    }

    public void updateAvailability(int id){
        booksDao.updateAvailability(id);
    }

    public List<Books> findAllBooks() {
        return booksDao.findAll();
    }

}
