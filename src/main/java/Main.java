
import exceptions.AccountingRecordNotFoundException;
import exceptions.BookNotFoundException;
import exceptions.ClientNotFoundException;
import lombok.extern.log4j.Log4j;
import models.AccountingRecords;
import models.Authors;
import models.Books;
import models.Clients;
import service.AccountingRecordsService;
import service.AuthorsService;
import service.BooksService;
import service.ClientService;
import utils.GenerateInitialLibrary;
import utils.MyNumberFormat;

import java.util.Scanner;

@Log4j
public class Main{
    public static void main(String[] args) {

        AuthorsService authorsService = new AuthorsService();
        BooksService booksService = new BooksService();
        AccountingRecordsService accountingRecordsService = new AccountingRecordsService();
        ClientService clientService = new ClientService();


        new GenerateInitialLibrary().generate();

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        while (true) {
            System.out.println("1 - EDIT \n2 - SHOW \n3 - SEARCH BOOK \n4 - BOOK ISSUED");
            int choice = MyNumberFormat.getChoice(scannerInt.nextLine());
            switch (choice) {
                case (1):
                    System.out.println("1 - EDIT CLIENT \n2 - EDIT BOOK \n3 - EDIT ACCOUNTING RECORD \n4 - BACK");
                    choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                    switch (choice) {
                        case (1):
                            System.out.println("1 - DELETE \n2 - EDIT \n3 - ADD\n4 - BACK ");
                            choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                            switch (choice) {
                                case (1):
                                    System.out.println("ENTER ID");
                                    int id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    try{
                                        clientService.deleteClient(id);
                                    } catch (ClientNotFoundException e){
                                        log.error(e);
                                    }
                                    break;
                                case (2):
                                    System.out.println("ENTER ID, NAME, SURNAME");
                                    id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    String first = scannerStr.nextLine();
                                    String lastname = scannerStr.nextLine();
                                    try {
                                        clientService.updateClient(id, first, lastname);
                                    } catch (ClientNotFoundException e){
                                        log.error(e);
                                    }

                                    break;
                                case (3):
                                    System.out.println("ENTER NAME");
                                    first = scannerStr.nextLine();
                                    System.out.println("ENTER SURNAME");
                                    lastname = scannerStr.nextLine();
                                    clientService.saveClient(Clients.builder().firstname(first).lastname(lastname).build());
                                    break;
                                case (4):
                                    break;
                                default:
                                    System.out.println("Invalid menu item");
                                    break;
                            }
                            break;
                        case (2):
                            System.out.println("1 - DELETE \n2 - EDIT \n3 - ADD\n4 - BACK ");
                            choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                            switch (choice) {
                                case (1):
                                    System.out.println("ENTER ID");
                                    int id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    try{
                                        booksService.deleteBooks(id);
                                    } catch (BookNotFoundException e){
                                        log.error(e);
                                    }

                                    break;
                                case (2):
                                    System.out.println("ENTER ID, NAME, CATEGORY");
                                    id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    String name = scannerStr.nextLine();
                                    String category = scannerStr.nextLine();
                                    try {
                                        booksService.updateBooks(id, name, category);
                                    } catch (BookNotFoundException e){
                                        log.error(e);
                                    }

                                    break;
                                case (3):
                                    System.out.println("ENTER NAME");
                                    String nameadd = scannerStr.nextLine();
                                    System.out.println("ENTER CATEGORY");
                                    String categoryadd = scannerStr.nextLine();
                                    Books book = Books.builder().name(nameadd).categoryId(categoryadd).build();
                                    System.out.println("1 ENTER ID AUTHOR \n2 - ENTER NEW AUTHOR");
                                    int number = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    switch (number) {
                                        case (1):
                                            System.out.println("ENTER id");
                                            int authorId = MyNumberFormat.getChoice(scannerInt.nextLine());
                                            if (authorId != 0) {
                                                Authors author = authorsService.findAuthor(authorId);
                                                book.setAuthor(author);
                                                author.addBook(book);
                                                authorsService.updateAuthor(author);
                                                System.out.println(book);
                                            } else System.out.println("Неверный формат введенных данных");
                                            break;
                                        case (2):
                                            System.out.println("ENTER NAME");
                                            String firstname = scannerStr.nextLine();
                                            System.out.println("ENTER SURNAME");
                                            String lastname = scannerStr.nextLine();
                                            Authors author = Authors.builder().firstname(firstname).lastname(lastname).build();
                                            authorsService.saveAuthor(author);
                                            book.setAuthor(author);
                                            author.addBook(book);
                                            authorsService.updateAuthor(author);
                                            break;
                                        default:
                                            System.out.println("Invalid menu item");
                                            break;
                                    }
                                    break;
                                case (4):
                                    break;
                                default:
                                    System.out.println("Invalid menu item");
                                    break;
                            }
                            break;
                        case (3):
                            System.out.println("1 - DELETE \n2 - EDIT \n3 - ADD\n4 - BACK ");
                            choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                            switch (choice) {
                                case (1):
                                    System.out.println("ENTER ID");
                                    int id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    try {
                                        accountingRecordsService.deleteAccountingRecords(id);
                                    } catch (AccountingRecordNotFoundException e) {
                                        log.error(e);
                                    }
                                    break;
                                case (2):
                                    System.out.println("1 - EDIT CLIENT \n2 - EDIT BOOK \n3 - EDIT DAYS \n4 - BACK ");
                                    choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    switch (choice){
                                        case (1):
                                            System.out.println("ENTER ID ACCOUNT RECORD");
                                            id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                            System.out.println("ENTER ID СLIENT");
                                            int idclient = MyNumberFormat.getChoice(scannerInt.nextLine());
                                            try{
                                                accountingRecordsService.updateClientAccountingRecordsById(id, idclient);
                                            } catch (AccountingRecordNotFoundException e){
                                                log.error(e);
                                            }
                                            break;
                                        case (2):
                                            System.out.println("ENTER ID ACCOUNT RECORD");
                                            id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                            System.out.println("ENTER ID BOOK");
                                            int idbook = MyNumberFormat.getChoice(scannerInt.nextLine());
                                            try{
                                                accountingRecordsService.updateBookAccountingRecordsById(id, idbook);
                                            } catch (AccountingRecordNotFoundException e){
                                                log.error(e);
                                            }
                                            break;
                                        case (3):
                                            System.out.println("ENTER ID ACCOUNT RECORD");
                                            id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                            System.out.println("ENTER ID DAYS");
                                            int days = MyNumberFormat.getChoice(scannerInt.nextLine());
                                            try{
                                                accountingRecordsService.updateDaysAccountingRecordsById(id, days);
                                            } catch (AccountingRecordNotFoundException e){
                                                log.error(e);
                                            }
                                            break;
                                        case (4):
                                            break;
                                        default:
                                            System.out.println("Invalid menu item");
                                            break;
                                    }
                                    break;
                                case (3):
                                    System.out.println("ENTER ID CLIENT");
                                    int idclient = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    if (idclient != 0) {
                                        AccountingRecords accountingRecords = AccountingRecords.builder().client(clientService.findClientById(idclient)).build();
                                        accountingRecordsService.saveAccountingRecords(accountingRecords);
                                    } else System.out.println("Неверный формат введенных данных");

                                case (4):
                                    break;
                                default:
                                    System.out.println("Invalid menu item");
                                    break;
                            }
                            break;

                        case (4):
                            break;
                        default:
                            System.out.println("Invalid menu item");
                            break;
                    }
                    break;

                case (2):
                    System.out.println("1 - CLIENTS \n2 - BOOKS \n3 - ACCOUNTS RECORDS \n4 - BACK");
                    choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                    switch (choice) {
                        case (1):
                            System.out.println(clientService.findAllClients());
                            break;

                        case (2):
                            System.out.println(booksService.findAllBooks());
                            break;

                        case (3):
                            System.out.println(accountingRecordsService.findAllAccountingRecords());
                            break;

                        case (4):
                            break;

                        default:
                            System.out.println("Invalid menu item");
                            break;
                    }

                    break;

                case (3):
                    System.out.println("1 - SEARCH BY AUTHOR  \n2 - SEARCH BY NAME \n3 - BACK");
                    choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                    switch (choice) {
                        case (1):
                            System.out.println("ENTER NAME OR SURNAME");
                            String name = scannerStr.nextLine();
                            System.out.println(booksService.searchBookForNameAuthors(name));
                            break;
                        case (2):
                            System.out.println("ENTER NAME");
                            name = scannerStr.nextLine();
                            System.out.println(booksService.searchBooksForName(name));
                            break;
                        case (3):
                            break;
                        default:
                            System.out.println("Invalid menu item");
                            break;
                    }
                    break;

                case (4):
                    System.out.println("ENTER id CLIENT");
                    int idclient = MyNumberFormat.getChoice(scannerInt.nextLine());
                    System.out.println("ENTER id BOOK");
                    int idbook = MyNumberFormat.getChoice(scannerInt.nextLine());
                    System.out.println("ENTER NUMBER OF DAYS");
                    int days = MyNumberFormat.getChoice(scannerInt.nextLine());
                    try {
                        AccountingRecords accountingRecords = new AccountingRecords(clientService.findClientById(idclient), booksService.findBooksById(idbook), days);
                        accountingRecordsService.saveAccountingRecords(accountingRecords);
                    } catch (AccountingRecordNotFoundException e) {
                     log.error(e);
                    }
                    break;

                default:
                    System.out.println("Invalid menu item");
                    break;
            }
        }

    }
}

