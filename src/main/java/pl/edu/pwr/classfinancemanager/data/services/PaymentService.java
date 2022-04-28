package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.Instalment;
import pl.edu.pwr.classfinancemanager.data.entities.Payment;
import pl.edu.pwr.classfinancemanager.data.repositories.PaymentRepository;
import pl.edu.pwr.classfinancemanager.data.entities.Person;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InstalmentService instalmentService;
    private final PersonService personService;

    public List<Payment> getPayments() {
        return paymentRepository.findAllBy();
    }

    public List<Payment> getPaymentsByPerson(Long personId) {
        return paymentRepository.findAllByPersonId(personId);
    }

    private static final Logger logger = LogManager.getLogger(PaymentService.class);

    public Payment createPayment(String args[]) {
        try {
            args[1] = args[1].replace(".","/");
            Payment.PaymentBuilder builder = Payment.builder();
            Payment payment = builder.amount(Double.parseDouble(args[0]))
                    .payDay(CustomDate.parse(args[1]))
                    .person(Person.builder().id(Long.parseLong(args[2])).build())
                    .instalment(Instalment.builder().id(Long.parseLong(args[3])).build())
                    .build();
            paymentRepository.saveAndFlush(payment);
            savePayment();
            return payment;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

    public void checkShortgages(LocalDate date){
        for(Person p : personService.getPersons()){
            List<Instalment> instalments = instalmentService.getInstallments();
            List<Payment> payments = getPaymentsByPerson(p.getId());
            while(!payments.isEmpty()){
                long id = payments.get(0).getInstalment().getId();
                double paidAmount = payments.get(0).getAmount();
                for(Instalment i : instalments){
                    if(i.getId() == id){
                        if(paidAmount < i.getAmount() && i.getDeadline().isBefore(date)){
                            logger.log(Level.getLevel("Shortgage"), "Person "+p.toString() +" paid too little in "+i.getNumber()+". for "+i.getEvent().getName());
                        }
                        instalments.remove(i);
                        break;
                    }
                }
                payments.remove(0);
            }

            for (Instalment i : instalments){
                if(i.getDeadline().isBefore(date))
                    logger.log(Level.getLevel("Shortgage"), "Person "+p.toString() +" has not paid "+i.getNumber()+". for "+i.getEvent().getName());
            }
        }

    }

    public void savePayment(){
        logger.log(Level.getLevel("Income"), "New income: "+getPayments().get(getPayments().size()-1));
    }

}

