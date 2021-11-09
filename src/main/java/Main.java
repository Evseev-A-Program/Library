
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

public class Main{
    public static void main(String[] args) {

        AuthorsService authorsService = new AuthorsService();
        BooksService booksService = new BooksService();
        AccountingRecordsService accountingRecordsService = new AccountingRecordsService();
        ClientService clientService = new ClientService();


        new GenerateInitialLibrary().generate();
        Authors author1 = Authors.builder().firstname("FFFFF").lastname("QQQQQ").build();
        authorsService.saveAuthor(author1);

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Редактирование данных \n2 - Показать \n3 - Поиск книги \n4 - Выдача книг");
            int choice = MyNumberFormat.getChoice(scannerInt.nextLine());
            switch (choice) {
                case (1):
                    System.out.println("1 - редактировать пользователей \n2 - редактировать книги \n3 - редактировать учетные записи \n4 - назад");
                    choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                    switch (choice) {
                        case (1):
                            System.out.println("1 - удалить \n2 - изменить \n3 - добавить\n4 - назад ");
                            choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                            switch (choice) {
                                case (1):
                                    System.out.println("Введите ID");
                                    int id = scannerInt.nextInt();
                                    clientService.deleteClient(id);
                                    break;
                                case (2):
                                    System.out.println("Введите ID, имя, фамилию");
                                    id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    if (id != 0) {
                                    String first = scannerStr.nextLine();
                                    String lastname = scannerStr.nextLine();
                                    booksService.updateBooks(id, first, lastname);
                                    } else System.out.println("Неверный формат введенных данных");
                                    break;
                                case (3):
                                    System.out.println("Введите имя");
                                    String first = scannerStr.nextLine();
                                    System.out.println("Введите фамилию");
                                    String lastname = scannerStr.nextLine();
                                    clientService.saveClient(Clients.builder().firstname(first).lastname(lastname).build());
                                    break;
                                case (4):

                                    break;
                                default:
                                    System.out.println("Неверный пункт меню");
                                    break;
                            }
                            break;
                        case (2):
                            System.out.println("1 - удалить \n2 - изменить \n3 - добавить\n4 - назад ");
                            choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                            switch (choice) {
                                case (1):
                                    System.out.println("Введите ID");
                                    int id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    if (id != 0) {
                                    booksService.deleteBooks(id);
                                    } else System.out.println("Неверный формат введенных данных");
                                    break;
                                case (2):
                                    System.out.println("Введите ID, NAME, CATEGORY");
                                    id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    if (id != 0) {
                                        String name = scannerStr.nextLine();
                                        String category = scannerStr.nextLine();
                                        booksService.updateBooks(id, name, category);
                                    } else System.out.println("Неверный формат введенных данных");
                                    break;
                                case (3):
                                    System.out.println("Введите название");
                                    String nameadd = scannerStr.nextLine();
                                    System.out.println("Введите категорию");
                                    String categoryadd = scannerStr.nextLine();
                                    Books book = Books.builder().name(nameadd).categoryId(categoryadd).build();
                                    System.out.println("1 Выберите id автора \n2 - введите нового");
                                    int number = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    switch (number) {
                                        case (1):
                                            System.out.println("Введите id");
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
                                            System.out.println("Введите имя");
                                            String firstname = scannerStr.nextLine();
                                            System.out.println("Введите фамилию");
                                            String lastname = scannerStr.nextLine();
                                            Authors author = Authors.builder().firstname(firstname).lastname(lastname).build();
                                            authorsService.saveAuthor(author);
                                            book.setAuthor(author);
                                            author.addBook(book);
                                            authorsService.updateAuthor(author);
                                            break;
                                        default:
                                            System.out.println("Неверный пункт меню");
                                            break;
                                    }
                                    break;
                                case (4):
                                    break;
                                default:
                                    System.out.println("Неверный пункт меню");
                                    break;
                            }
                            break;
                        case (3):
                            System.out.println("1 - удалить \n2 - изменить \n3 - добавить\n4 - назад ");
                            choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                            switch (choice) {
                                case (1):
                                    System.out.println("Введите ID");
                                    int id = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    if (id != 0) {
                                        accountingRecordsService.deleteAccountingRecords(id);
                                    } else System.out.println("Неверный формат введенных данных");
                                    break;
                                case (2):
                                    System.out.println("Введите ID");
                                    id = scannerInt.nextInt();
                                    System.out.println("Введите ID клиента");
                                    int idclient = scannerInt.nextInt();
                                    System.out.println("Введите ID книги");
                                    int idbook = scannerInt.nextInt();
                                    System.out.println("Введите колличество дней выдачи");
                                    int days = scannerInt.nextInt();
                                    if (idbook != 0 || idclient != 0 || days != 0) {
                                        booksService.updateAvailability(idbook);
                                        accountingRecordsService.updateAccountingRecords(id, idclient, idbook, days);
                                    } else System.out.println("Неверный формат введенных данных");

                                    break;
                                case (3):
                                    System.out.println("Введите id клиента");
                                    idclient = MyNumberFormat.getChoice(scannerInt.nextLine());
                                    if (idclient != 0) {
                                        AccountingRecords accountingRecords = AccountingRecords.builder().client(clientService.findClientById(idclient)).build();
                                        accountingRecordsService.saveAccountingRecords(accountingRecords);
                                    } else System.out.println("Неверный формат введенных данных");

                                case (4):

                                    break;
                                default:
                                    System.out.println("Неверный пункт меню");
                                    break;
                            }
                            break;

                        case (4):
                            break;
                        default:
                            System.out.println("Неверный пункт меню");
                            break;
                    }
                    break;

                case (2):
                    System.out.println("1 - Клиенты \n2 - Книги \n3 - Авторы \n4 - Учетные записи \n5 - Назад");
                    choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                    switch (choice) {
                        case (1):
                            System.out.println(clientService.findAllClient());
                            break;

                        case (2):
                            System.out.println(booksService.findAllBooks());
                            break;

                        case (3):
                            System.out.println(authorsService.findAllAuthors());
                            break;

                        case (4):
                            System.out.println(accountingRecordsService.findAllAccountingRecords());
                            break;
                        case (5):
                            break;
                        default:
                            System.out.println("Неверный пункт меню");
                            break;
                    }

                    break;

                case (3):
                    System.out.println("1 - Поиск по автору  \n2 - Поиск по названию \n3 - Назад");
                    choice = MyNumberFormat.getChoice(scannerInt.nextLine());
                    switch (choice) {
                        case (1):
                            System.out.println("Введите имя или фамилию автора");
                            String name = scannerStr.nextLine();
                            System.out.println(authorsService.searchBookForNameAuthors(name));
                            break;
                        case (2):
                            System.out.println("Введите название книги");
                            name = scannerStr.nextLine();
                            System.out.println(booksService.searchBooks(name));
                            break;
                        case (3):
                            break;
                        default:
                            System.out.println("Неверный пункт меню");
                            break;
                    }
                    break;

                case (4):
                    System.out.println("Введите id клиента для создания его учетной записи");
                    int idclient = MyNumberFormat.getChoice(scannerInt.nextLine());
                    System.out.println("Введите id книги для создания его учетной записи");
                    int idbook = MyNumberFormat.getChoice(scannerInt.nextLine());
                    System.out.println("Введите время на которое выдается книга (в днях)");
                    int days = MyNumberFormat.getChoice(scannerInt.nextLine());
                    if (idbook != 0 || idclient != 0 || days != 0) {
                        booksService.updateAvailability(idbook);
                        AccountingRecords accountingRecords = new AccountingRecords(clientService.findClientById(idclient), booksService.findBooksById(idbook), days);
                        accountingRecordsService.saveAccountingRecords(accountingRecords);
                    } else System.out.println("Неверный формат введенных данных");
                    break;

                default:
                    System.out.println("Неверный пункт меню");
                    break;
            }
        }

    }
}

