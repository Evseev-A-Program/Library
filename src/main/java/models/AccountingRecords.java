package models;

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

    private String statusId;

    public AccountingRecords(Clients client){
        this.client = client;
    }
    public AccountingRecords (Clients client, Books book, int days) {
        this.client = client;
        this.book = book;
        receiptDate = LocalDate.now();
        returnDate = receiptDate;
        returnDate = returnDate.plusDays(days);
        statusId = "ISSUED";
        book.setStatus(Status.ISSUED);
    }

    public AccountingRecords() {

    }


    @Override
    public String toString() {
        return "Учетная запись №" + id + '\n' +
                "клиент:" + client + '\n' +
                "книга:" + book + '\n' +
                "Дата выдачи = " + receiptDate + '\n' +
                "Дата возврата = " + returnDate + '\n' +
                "Статус = " + statusId +
        '}' + '\n' + '\n' ;
    }
}
