package models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@Builder
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstname;

    private String lastname;

    public Clients() {

    }

    public Clients(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }



    @Override
    public String toString() {
        return "\n" + id + " Имя = " + firstname + " Фамилия = " + lastname + "\n";
    }

}
