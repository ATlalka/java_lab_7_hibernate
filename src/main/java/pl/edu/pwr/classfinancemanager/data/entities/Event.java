package pl.edu.pwr.classfinancemanager.data.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String place;

    @Column
    private LocalDate date;

    @Override
    public String toString() {
        return
                "[" + id +"] "+
                name +
                " : " + place  + " ("
                +  date + ")";

    }
}
