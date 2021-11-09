package models;

import exceptions.AccountingRecordNotFoundException;
import exceptions.BookNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@Builder
public class AccountingRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="clients_id")
    private Clients client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="books_id")
    private Books book;

    private LocalDate receiptDate;

    private LocalDate returnDate;

    private Status status;

    public AccountingRecords(Clients client){
        this.client = client;
    }

    public AccountingRecords (Clients client, Books book, int days) throws AccountingRecordNotFoundException {
        this.client = client;
        this.book = book;
        receiptDate = LocalDate.now();
        returnDate = receiptDate;
        returnDate = returnDate.plusDays(days);
        if (book != null) {
            book.setStatus(Status.ISSUED);
            status = book.getStatus();
        }
        else throw new AccountingRecordNotFoundException( "The book with id" + id + "was not found");

    }

    public AccountingRecords() {

    }


    @Override
    public String toString() {
        return  "Account Record №" + id + '\n' +
                "client:" + client +
                "book:" + book +
                "Data receipt = " + receiptDate + '\n' +
                "Data return = " + returnDate + '\n' +
                "Status = " + status +
        '}' + '\n' + '\n' ;
    }
}
