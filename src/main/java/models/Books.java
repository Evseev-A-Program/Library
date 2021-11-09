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
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    private String categoryId;

    private boolean availability = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId")
    private Authors author;

    public Books() {

    }

    public Books(String name, String category) {
        this.name = name;
        this.categoryId = category;
    }

    @Override
    public String toString() {
        return "\n" + id + " Название = " + name + "; Категория = " + categoryId + '\n';
    }
}