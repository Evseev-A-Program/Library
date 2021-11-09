package utils;

import models.Authors;
import models.Books;
import models.Clients;
import models.Status;
import service.AuthorsService;
import service.ClientService;

public class GenerateInitialLibrary {


    public void generate(){
        AuthorsService authorsService = new AuthorsService();
        Authors author1 = new Authors("J. K.", "Rowling");
        authorsService.saveAuthor(author1);
        Books book1 = new Books("Harry Potter", "Fantasy");
        Books book2 = new Books("Harry Potter 2", "Fantasy");
        Books book3 = new Books("Harry Potter 3", "Fantasy");
        Books book4 = new Books("Harry Potter 4", "Fantasy");
        book1.setAuthor(author1);
        book2.setAuthor(author1);
        book3.setAuthor(author1);
        book4.setAuthor(author1);
        author1.addBook(book1);
        author1.addBook(book2);
        author1.addBook(book3);
        author1.addBook(book4);
        authorsService.updateAuthor(author1);
        Authors author2 = new Authors("Stephen", "King");
        authorsService.saveAuthor(author2);
        Books book5 = new Books("Radiance", "horror literature");
        Books book6 = new Books("The Green Mile", "thriller");
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
        ClientService clientService = new ClientService();
        Clients clients = new Clients("FFFFFFF", "QQQQQQQ");
        clientService.saveClient(clients);
    }
}
