package models;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@Builder
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstname;

    private String lastname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Books> books = new ArrayList<>();

   // private static final Logger log = Logger.getLogger(Authors.class);

    public Authors() {
    }

    public Authors(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public void addBook(Books book) {
        book.setAuthor(this);
        books.add(book);
     //   log.info("Книга добавлена Автору");
    }

    @Override
    public String toString() {
        return "\n" + "\n" +
                "(" + id + ")" + " " +
                 firstname + " " +
                 lastname +
                " Книги: " + books;
    }
}
