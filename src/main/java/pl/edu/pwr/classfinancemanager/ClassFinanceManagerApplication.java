package pl.edu.pwr.classfinancemanager;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import pl.edu.pwr.classfinancemanager.GUI.MainFrame;
import pl.edu.pwr.classfinancemanager.data.entities.Payment;
import pl.edu.pwr.classfinancemanager.data.services.EventService;
import pl.edu.pwr.classfinancemanager.data.services.InstalmentService;
import pl.edu.pwr.classfinancemanager.data.services.PaymentService;
import pl.edu.pwr.classfinancemanager.data.services.PersonService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;


@SpringBootApplication
@RequiredArgsConstructor
public class ClassFinanceManagerApplication implements CommandLineRunner {

    public LocalDate date;
    private final EventService eventService;
    private final PersonService personService;
    private final InstalmentService instalmentService;
    private final PaymentService paymentService;

    public static void main(String[] args) {
        SpringApplication.run(ClassFinanceManagerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.setProperty("java.awt.headless", "false"); //Disables headless
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    date = LocalDate.now();
                    createData();
                    MainFrame frame = new MainFrame(eventService, instalmentService, paymentService, personService);
                    frame.refreshPanel();
                    frame.setVisible(true);
                    frame.setDate(date);
                    Thread t = new Thread(() -> {
                        try {
                            while (true) {
                                Thread.sleep(2000);
                                date = date.plusDays(1);
                                frame.setDate(date);
                                paymentService.checkShortgages(date);
                            }
                        } catch (InterruptedException ignored) { }
                    });
                    t.start();

                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createData(){
        eventService.createEvent(new String[]{"Balladyna", "Teatr Polski", "18.11.2022"});
        eventService.createEvent(new String[]{"Switeziarnka", "Teatr Polski", "10.05.2022"});
        personService.createPerson(new String[]{"Joachim", "Nowak"});
        personService.createPerson(new String[]{"Maria", "Jopek"});
        instalmentService.createInstalment(new String[]{"1","12.50","30.04.2022","1"});
        instalmentService.createInstalment(new String[]{"2","12.50","15.05.2022","1"});
        instalmentService.createInstalment(new String[]{"1","30","30.04.2022","2"});
        instalmentService.createInstalment(new String[]{"2","10","08.05.2022","2"});
        paymentService.createPayment(new String[]{"12.50", "22.04.2022", "1", "1" });
        personService.createPerson(new String[]{"Kamil", "Skórzak"});
        paymentService.createPayment(new String[]{"10.50", "22.04.2022", "3", "1" });
    }
}
