package pl.edu.pwr.classfinancemanager.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Override
    public String toString() {
        return "[" + id +"] "+
                name +
                " " + surname;
    }
}
