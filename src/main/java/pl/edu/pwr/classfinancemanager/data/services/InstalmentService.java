package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.Event;
import pl.edu.pwr.classfinancemanager.data.entities.Instalment;
import pl.edu.pwr.classfinancemanager.data.entities.Payment;
import pl.edu.pwr.classfinancemanager.data.repositories.InstalmentRepository;
import pl.edu.pwr.classfinancemanager.data.repositories.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstalmentService {
    private final InstalmentRepository instalmentRepository;
    private final PaymentRepository paymentRepository;

    public List<Instalment> getInstallments() {
        return instalmentRepository.findAllBy();
    }

    public List<Instalment> getInstalmentsByPerson(Long personId) {
        List<Instalment> result = new ArrayList<>();
        for (Instalment instalment : instalmentRepository.findAllBy()) {
            for (Payment payment : paymentRepository.findAllByPersonId(personId)) {
                if (payment.getInstalment().getId().equals(instalment.getId())) {
                    result.add(instalment);
                }
            }
        }
        return result;
    }
    public Instalment createInstalment(String args[]) {
        try {
            args[2] = args[2].replace(".","/");
            Instalment.InstalmentBuilder builder = Instalment.builder();
            Instalment instalment = builder.number(Integer.parseInt(args[0]))
                    .amount(Double.parseDouble(args[1]))
                    .deadline(CustomDate.parse(args[2]))
                    .event(Event.builder().id(Long.parseLong(args[3])).build())
                    .build();
            instalmentRepository.saveAndFlush(instalment);
            return instalment;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

}

