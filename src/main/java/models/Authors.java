package models;


import lombok.*;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@Builder
@Log4j
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstname;

    private String lastname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Books> books;


    public Authors() {
        books = new ArrayList<>();
    }

    public Authors(String firstname, String lastname) {
        books = new ArrayList<>();
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public void addBook(Books book) {
        book.setAuthor(this);
        books.add(book);
        log.info("Книга добавлена Автору");
    }

    @Override
    public String toString() {
        return  "(" + id + ")" + " " +
                 firstname + " " +
                 lastname;
    }
}
