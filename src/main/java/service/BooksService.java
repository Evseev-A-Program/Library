package service;

import exceptions.BookNotFoundException;
import models.Authors;
import models.Books;
import org.apache.log4j.Logger;
import repository.BooksDao;

import java.util.ArrayList;
import java.util.List;

public class BooksService {
    private static final Logger log = Logger.getLogger(BooksService.class);

    private BooksDao booksDao;

    public BooksService() {
        booksDao  = new BooksDao();
    }

//    public List<Books> searchBooks(String name) {
//        return booksDao.searchBookForName(name);
//    }

    public Books findBooksById(int id) {
        return booksDao.findBooksById(id);
    }

    public void deleteBooks(int id) throws BookNotFoundException {
        Books books = booksDao.findBooksById(id);
        if (books != null){
            booksDao.delete(id);
            log.info("Book delete");
        } else {
            throw new BookNotFoundException("The book with id" + id + "was not found");
        }

    }

    public List<Books> searchBooksForName(String name){
        int listBooksSize = booksDao.findAll().size();
        List<Books> books;
        books = new ArrayList<>();
        for (int i = 1; i<=listBooksSize; i++){
            Books book = booksDao.findBooksById(i);
            if (book.getName().startsWith(name)) books.add(book);
        }
        return books;
    }

    public List<Books> searchBookForNameAuthors(String name) {
        int listBooksSize = booksDao.findAll().size();
        List<Books> books;
        books = new ArrayList<>();
        for (int i = 1; i<=listBooksSize; i++){
            Books book = booksDao.findBooksById(i);
            if (book.getAuthor().getFirstname().startsWith(name) || book.getAuthor().getLastname().startsWith(name)){
                books.add(book);
            }
        }
        return books;
    }

    public void updateBooks(int id, String name, String category) throws BookNotFoundException {
        Books books = booksDao.findBooksById(id);
        if(books != null){
            books.setName(name);
            books.setCategoryId(category);
            booksDao.update(books);
            log.info("Book delete");
        } else {
            throw new BookNotFoundException("The book with id " + id + " was not found");
        }
        }

    public List<Books> findAllBooks() {
        return booksDao.findAll();
    }

}
