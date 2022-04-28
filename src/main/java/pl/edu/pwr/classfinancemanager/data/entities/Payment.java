package pl.edu.pwr.classfinancemanager.data.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate payDay;

    @Column
    private double amount;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "installment_id")
    private Instalment instalment;

    @Override
    public String toString() {
        return
                "[" + id +"] "+ payDay+" - " +
                amount + "zl - "+person.getName()+" "+person.getSurname()+" - "+instalment.getEvent().getName() + " - "+instalment.getNumber();
    }
}
