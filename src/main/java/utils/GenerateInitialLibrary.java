package utils;

import models.Authors;
import models.Books;
import service.AuthorsService;

public class GenerateInitialLibrary {


    public void generate(){
        AuthorsService authorsService = new AuthorsService();
        Authors author1 = Authors.builder().firstname("J. K.").lastname("Rowling").build();
        authorsService.saveAuthor(author1);
        Books book1 = Books.builder().name("Harry Potter").categoryId("Fantasy").build();
        Books book2 = Books.builder().name("Harry Potter 2").categoryId("Fantasy").build();
        Books book3 = Books.builder().name("Harry Potter 3").categoryId("Fantasy").build();
        Books book4 = Books.builder().name("Harry Potter 4").categoryId("Fantasy").build();
        book1.setAuthor(author1);
        book2.setAuthor(author1);
        book3.setAuthor(author1);
        book4.setAuthor(author1);
        author1.addBook(book1);
        author1.addBook(book2);
        author1.addBook(book3);
        author1.addBook(book4);
        authorsService.updateAuthor(author1);
        Authors author2 = Authors.builder().firstname("Stephen ").lastname("King").build();
        authorsService.saveAuthor(author2);
        Books book5 = Books.builder().name("Radiance").categoryId("horror literature").build();
        Books book6 = Books.builder().name("The Green Mile").categoryId("thriller").build();
        book5.setAuthor(author1);
        book6.setAuthor(author1);
        author2.addBook(book5);
        author2.addBook(book6);
        authorsService.updateAuthor(author2);
        Authors author3 = new Authors("Mikhail", "Bulgakov");
        authorsService.saveAuthor(author3);
        Books book7 = new Books("A dog's heart", "the story");
        Books book8 = new Books("The Master and Margarita", "novel");
        book7.setAuthor(author1);
        book8.setAuthor(author1);
        author3.addBook(book7);
        author3.addBook(book8);
        authorsService.updateAuthor(author3);
    }
}
