package pl.edu.pwr.classfinancemanager.GUI;

import lombok.RequiredArgsConstructor;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.Event;
import pl.edu.pwr.classfinancemanager.data.entities.Instalment;
import pl.edu.pwr.classfinancemanager.data.entities.Payment;
import pl.edu.pwr.classfinancemanager.data.services.EventService;
import pl.edu.pwr.classfinancemanager.data.services.InstalmentService;
import pl.edu.pwr.classfinancemanager.data.services.PaymentService;
import pl.edu.pwr.classfinancemanager.data.services.PersonService;
import pl.edu.pwr.classfinancemanager.data.entities.Person;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private EventService eventService;
    private PersonService personService;
    private InstalmentService instalmentService;
    private PaymentService paymentService;
    private LocalDate actualDate = LocalDate.now();

    private JPanel contentPane;
    private JTextField instalmentPath;
    private JTextField paymentPath;
    private JTextField personPath;
    private JTextField eventPath;
    private JTextField eventName;
    private JTextField txtName;
    private JTextField eventPlace;
    private JTextField txtPlace;
    private JTextField eventDate;
    private JTextField txtDate;
    private JTextField personName;
    private JTextField personSurname;
    private JTextField textField_5;
    private JTextField txtSurname;
    private JTextField paymentAmount;
    private JTextField paymentDate;
    private JTextField txtAmount;
    private JTextField txtDate_1;
    private JTextField txtPerson;
    private JTextField txtInstallment;
    private JComboBox personBox;
    private JComboBox instalmentBox;
    private JList paymentsList;
    private JTextField textField_3;
    private JButton btnAdd_2;
    private JTextField textField_4;
    private JComboBox personBox2;
    private JButton btnImport_2_2;
    private JTextField instalmentNumber;
    private JTextField instalmentAmount;
    private JTextField instalmentDeadline;
    private JButton btnAdd_3;
    private JTextField txtNumber;
    private JTextField textField_11;
    private JTextField txtDeadline;
    private JTextField txtEvent;
    private JComboBox eventBox;
    private JList instalmentsList;
    private JTextField currentDate;
    private JComboBox personBox1;
    private JList eventList;
    private JList peopleList;

    private DefaultListModel<String> paymentsListModel = new DefaultListModel<>();
    private DefaultListModel<String> eventsListModel = new DefaultListModel<>();
    private DefaultListModel<String> instalmentsListModel = new DefaultListModel<>();
    private DefaultListModel<String> peopleListModel = new DefaultListModel<>();

    public MainFrame(EventService e, InstalmentService i, PaymentService p, PersonService pe) {
        eventService = e;
        instalmentService = i;
        paymentService = p;
        personService = pe;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 568, 503);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 550, 429);
        contentPane.add(tabbedPane);

        JPanel eventPanel = new JPanel();
        tabbedPane.addTab("Events", null, eventPanel, null);
        eventPanel.setLayout(null);

        JButton btnNewButton = new JButton("Refresh");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshEvents();
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(425, 10, 110, 45);
        eventPanel.add(btnNewButton);

        eventPath = new JTextField("C:\\Users\\otlal\\Desktop\\events.csv");
        eventPath.setColumns(10);
        eventPath.setBounds(425, 65, 110, 19);
        eventPanel.add(eventPath);

        JButton btnImport = new JButton("Import");
        btnImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(eventPath.getText().isEmpty()){
                    JOptionPane.showMessageDialog(contentPane, "Enter path");
                }

                else {
                    for(String [] tab : getCSV(eventPath.getText())){
                        eventService.createEvent(tab);
                    }
                    refreshEvents();
                }
            }
        });
        btnImport.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImport.setBounds(425, 94, 110, 45);
        eventPanel.add(btnImport);

        eventName = new JTextField();
        eventName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        eventName.setBounds(10, 383, 96, 19);
        eventPanel.add(eventName);
        eventName.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(eventName.getText().isEmpty() || eventPlace.getText().isEmpty() || eventDate.getText().isEmpty()){
                    JOptionPane.showMessageDialog(contentPane, "None of the fields can be empy");
                }

                else{
                    Event event = eventService.createEvent(new String[]{eventName.getText(), eventPlace.getText(), eventDate.getText()});
                    refreshEvents();
                    if(event!=null)
                        JOptionPane.showMessageDialog(contentPane, "Added");
                    else
                        JOptionPane.showMessageDialog(contentPane, "Something went wrong");
                }

            }
        });
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAdd.setBounds(335, 354, 110, 45);
        eventPanel.add(btnAdd);

        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtName.setEditable(false);
        txtName.setText("Name");
        txtName.setColumns(10);
        txtName.setBounds(10, 354, 96, 19);
        eventPanel.add(txtName);

        eventPlace = new JTextField();
        eventPlace.setFont(new Font("Tahoma", Font.PLAIN, 14));
        eventPlace.setColumns(10);
        eventPlace.setBounds(116, 383, 96, 19);
        eventPanel.add(eventPlace);

        txtPlace = new JTextField();
        txtPlace.setEditable(false);
        txtPlace.setText("Place");
        txtPlace.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPlace.setColumns(10);
        txtPlace.setBounds(116, 354, 96, 19);
        eventPanel.add(txtPlace);

        eventDate = new JTextField();
        eventDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        eventDate.setColumns(10);
        eventDate.setBounds(222, 383, 96, 19);
        eventPanel.add(eventDate);

        txtDate = new JTextField();
        txtDate.setEditable(false);
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDate.setText("Date");
        txtDate.setColumns(10);
        txtDate.setBounds(222, 354, 96, 19);
        eventPanel.add(txtDate);

        eventList = new JList();
        eventList.setBounds(10, 10, 401, 330);
        eventPanel.add(eventList);

        JPanel personPanel = new JPanel();
        tabbedPane.addTab("People", null, personPanel, null);
        personPanel.setLayout(null);

        JButton btnNewButton_1 = new JButton("Refresh");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshPeople();
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(425, 10, 110, 45);
        personPanel.add(btnNewButton_1);

        personPath = new JTextField("C:\\Users\\otlal\\Desktop\\people.csv");
        personPath.setColumns(10);
        personPath.setBounds(425, 65, 110, 19);
        personPanel.add(personPath);

        JButton btnImport_1 = new JButton("Import");
        btnImport_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(personPath.getText().isEmpty()){
                    JOptionPane.showMessageDialog(contentPane, "Enter path");
                }

                else {
                    for(String [] tab : getCSV(personPath.getText())){
                        personService.createPerson(tab);
                    }
                    refreshPeople();
                }
            }
        });
        btnImport_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImport_1.setBounds(425, 94, 110, 45);
        personPanel.add(btnImport_1);

        personName = new JTextField();
        personName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        personName.setColumns(10);
        personName.setBounds(10, 383, 96, 19);
        personPanel.add(personName);

        personSurname = new JTextField();
        personSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        personSurname.setColumns(10);
        personSurname.setBounds(116, 383, 96, 19);
        personPanel.add(personSurname);

        textField_5 = new JTextField();
        textField_5.setText("Name");
        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_5.setEditable(false);
        textField_5.setColumns(10);
        textField_5.setBounds(10, 354, 96, 19);
        personPanel.add(textField_5);

        txtSurname = new JTextField();
        txtSurname.setText("Surname");
        txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtSurname.setEditable(false);
        txtSurname.setColumns(10);
        txtSurname.setBounds(116, 354, 96, 19);
        personPanel.add(txtSurname);

        JButton btnAdd_1 = new JButton("Add");
        btnAdd_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(personName.getText().isEmpty() || personSurname.getText().isEmpty()){
                    JOptionPane.showMessageDialog(contentPane, "None of the fields can be empy");
                }

                else {
                    Person p = personService.createPerson(new String[]{personName.getText(), personSurname.getText()});
                    if(p!=null){
                        JOptionPane.showMessageDialog(contentPane, "Added");
                        refreshPeople();
                    }

                    else
                        JOptionPane.showMessageDialog(contentPane, "Something went wrong");
                }

            }
        });
        btnAdd_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAdd_1.setBounds(222, 355, 110, 45);
        personPanel.add(btnAdd_1);

        peopleList = new JList();
        peopleList.setBounds(10, 10, 404, 331);
        personPanel.add(peopleList);

        JPanel paymentPanel = new JPanel();
        tabbedPane.addTab("Payments", null, paymentPanel, null);
        paymentPanel.setLayout(null);

        JButton btnNewButton_2 = new JButton("Refresh");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshPayments();
            }
        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_2.setBounds(425, 10, 110, 45);
        paymentPanel.add(btnNewButton_2);

        paymentPath = new JTextField("C:\\Users\\otlal\\Desktop\\payments.csv");
        paymentPath.setColumns(10);
        paymentPath.setBounds(425, 65, 110, 19);
        paymentPanel.add(paymentPath);

        JButton btnImport_2 = new JButton("Import");
        btnImport_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(paymentPath.getText().isEmpty()){
                    JOptionPane.showMessageDialog(contentPane, "Enter path");
                }

                else {
                    for(String [] tab : getCSV(paymentPath.getText())){
                        paymentService.createPayment(tab);
                    }
                    refreshPayments();
                }
            }
        });
        btnImport_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImport_2.setBounds(425, 100, 110, 45);
        paymentPanel.add(btnImport_2);

        paymentAmount = new JTextField();
        paymentAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        paymentAmount.setColumns(10);
        paymentAmount.setBounds(10, 383, 96, 19);
        paymentPanel.add(paymentAmount);

        paymentDate = new JTextField();
        paymentDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        paymentDate.setColumns(10);
        paymentDate.setBounds(116, 383, 96, 19);
        paymentPanel.add(paymentDate);

        txtAmount = new JTextField();
        txtAmount.setText("Amount");
        txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtAmount.setEditable(false);
        txtAmount.setColumns(10);
        txtAmount.setBounds(10, 354, 96, 19);
        paymentPanel.add(txtAmount);

        txtDate_1 = new JTextField();
        txtDate_1.setText("Date");
        txtDate_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDate_1.setEditable(false);
        txtDate_1.setColumns(10);
        txtDate_1.setBounds(116, 354, 96, 19);
        paymentPanel.add(txtDate_1);

        txtPerson = new JTextField();
        txtPerson.setText("Person");
        txtPerson.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPerson.setEditable(false);
        txtPerson.setColumns(10);
        txtPerson.setBounds(222, 354, 96, 19);
        paymentPanel.add(txtPerson);

        txtInstallment = new JTextField();
        txtInstallment.setText("Instalment");
        txtInstallment.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtInstallment.setEditable(false);
        txtInstallment.setColumns(10);
        txtInstallment.setBounds(328, 354, 96, 19);
        paymentPanel.add(txtInstallment);

        personBox1 = new JComboBox();
        personBox1.setBounds(425, 227, 110, 19);
        paymentPanel.add(personBox1);

        JButton btnImport_2_1 = new JButton("Apply");
        btnImport_2_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(personBox1.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(contentPane, "Enter path");
                }

                else if(personBox1.getSelectedItem().equals("All")){
                    refreshPayments();
                }

                else{
                    paymentsListModel.clear();
                    for(Payment p : paymentService.getPaymentsByPerson(personService.getPersons().get(personBox1.getSelectedIndex()-1).getId())){
                        paymentsListModel.addElement(p.toString());
                    }
                    paymentsList.setModel(paymentsListModel);
                }
            }
        });
        btnImport_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImport_2_1.setBounds(425, 256, 110, 45);
        paymentPanel.add(btnImport_2_1);

        personBox = new JComboBox();
        personBox.setBounds(222, 383, 96, 19);
        paymentPanel.add(personBox);

        instalmentBox = new JComboBox();
        instalmentBox.setBounds(328, 383, 96, 19);
        paymentPanel.add(instalmentBox);

        paymentsList = new JList();
        paymentsList.setBounds(10, 10, 405, 322);
        paymentPanel.add(paymentsList);

        textField_3 = new JTextField();
        textField_3.setText("Person");
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBounds(425, 198, 110, 19);
        paymentPanel.add(textField_3);

        btnAdd_2 = new JButton("Add");
        btnAdd_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(paymentAmount.getText().isEmpty()||paymentDate.getText().isEmpty()||personBox1.getSelectedIndex()==-1 || instalmentBox.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(contentPane, "None of the fields can be empy");
                }

                else{
                    Payment payment = paymentService.createPayment(new String[]{paymentAmount.getText(), paymentDate.getText(), personService.getPersons().get(personBox1.getSelectedIndex()).getId().toString(), instalmentService.getInstallments().get(instalmentBox.getSelectedIndex()).getId().toString()});
                    if(payment != null){
                        JOptionPane.showMessageDialog(contentPane, "Added");
                        refreshPayments();
                    }
                    else{
                        JOptionPane.showMessageDialog(contentPane, "Something went wrong");
                    }
                }
            }
        });
        btnAdd_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAdd_2.setBounds(435, 355, 110, 45);
        paymentPanel.add(btnAdd_2);

        JPanel instalmentPanel = new JPanel();
        tabbedPane.addTab("Instalments", null, instalmentPanel, null);
        instalmentPanel.setLayout(null);

        JButton btnNewButton_3 = new JButton("Refresh");
        btnNewButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshInstalments();
            }
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_3.setBounds(425, 10, 110, 45);
        instalmentPanel.add(btnNewButton_3);

        instalmentPath = new JTextField("C:\\Users\\otlal\\Desktop\\instalments.csv");
        instalmentPath.setBounds(425, 66, 110, 19);
        instalmentPanel.add(instalmentPath);
        instalmentPath.setColumns(10);

        JButton btnImport_3 = new JButton("Import");
        btnImport_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(instalmentPath.getText().isEmpty()){
                    JOptionPane.showMessageDialog(contentPane, "Enter path");
                }

                else {
                    for(String [] tab : getCSV(instalmentPath.getText())){
                        instalmentService.createInstalment(tab);
                    }
                    refreshInstalments();
                }
            }
        });
        btnImport_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImport_3.setBounds(425, 95, 110, 45);
        instalmentPanel.add(btnImport_3);

        textField_4 = new JTextField();
        textField_4.setText("Person");
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_4.setEditable(false);
        textField_4.setColumns(10);
        textField_4.setBounds(425, 213, 110, 19);
        instalmentPanel.add(textField_4);

        personBox2 = new JComboBox();
        personBox2.setBounds(425, 242, 110, 19);
        instalmentPanel.add(personBox2);

        btnImport_2_2 = new JButton("Apply");
        btnImport_2_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(personBox2.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(contentPane, "Enter path");
                }

                else if(personBox2.getSelectedItem().equals("All")){
                    refreshInstalments();
                }

                else{
                    instalmentsListModel.clear();
                    for(Instalment i : instalmentService.getInstalmentsByPerson(personService.getPersons().get(personBox2.getSelectedIndex()-1).getId())){
                        instalmentsListModel.addElement(i.toString());
                    }
                    instalmentsList.setModel(instalmentsListModel);
                }
            }
        });
        btnImport_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImport_2_2.setBounds(425, 271, 110, 45);
        instalmentPanel.add(btnImport_2_2);

        instalmentNumber = new JTextField();
        instalmentNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        instalmentNumber.setColumns(10);
        instalmentNumber.setBounds(10, 383, 96, 19);
        instalmentPanel.add(instalmentNumber);

        instalmentAmount = new JTextField();
        instalmentAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        instalmentAmount.setColumns(10);
        instalmentAmount.setBounds(116, 383, 96, 19);
        instalmentPanel.add(instalmentAmount);

        instalmentDeadline = new JTextField();
        instalmentDeadline.setFont(new Font("Tahoma", Font.PLAIN, 14));
        instalmentDeadline.setColumns(10);
        instalmentDeadline.setBounds(222, 383, 96, 19);
        instalmentPanel.add(instalmentDeadline);

        btnAdd_3 = new JButton("Add");
        btnAdd_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(instalmentAmount.getText().isEmpty() || instalmentDeadline.getText().isEmpty() || instalmentNumber.getText().isEmpty() || eventBox.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(contentPane, "None of the fields can be empy");
                }

                else{
                    Instalment instalment = instalmentService.createInstalment(new String[]{instalmentNumber.getText(), instalmentAmount.getText(), instalmentDeadline.getText(), eventService.getEvents().get(eventBox.getSelectedIndex()).getId().toString()});
                    if(instalment != null){
                        JOptionPane.showMessageDialog(contentPane, "Added");
                        refreshInstalments();
                    }
                    else{
                        JOptionPane.showMessageDialog(contentPane, "Something went wrong");
                    }
                }
            }
        });
        btnAdd_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAdd_3.setBounds(435, 354, 110, 45);
        instalmentPanel.add(btnAdd_3);

        txtNumber = new JTextField();
        txtNumber.setText("Number");
        txtNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNumber.setEditable(false);
        txtNumber.setColumns(10);
        txtNumber.setBounds(10, 354, 96, 19);
        instalmentPanel.add(txtNumber);

        textField_11 = new JTextField();
        textField_11.setText("Amount");
        textField_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_11.setEditable(false);
        textField_11.setColumns(10);
        textField_11.setBounds(116, 354, 96, 19);
        instalmentPanel.add(textField_11);

        txtDeadline = new JTextField();
        txtDeadline.setText("Deadline");
        txtDeadline.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDeadline.setEditable(false);
        txtDeadline.setColumns(10);
        txtDeadline.setBounds(222, 354, 96, 19);
        instalmentPanel.add(txtDeadline);

        txtEvent = new JTextField();
        txtEvent.setText("Event");
        txtEvent.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtEvent.setEditable(false);
        txtEvent.setColumns(10);
        txtEvent.setBounds(328, 354, 96, 19);
        instalmentPanel.add(txtEvent);

        eventBox = new JComboBox();
        eventBox.setBounds(329, 384, 96, 21);
        instalmentPanel.add(eventBox);

        instalmentsList = new JList();
        instalmentsList.setBounds(10, 10, 396, 327);
        instalmentPanel.add(instalmentsList);

        currentDate = new JTextField();
        currentDate.setEditable(false);
        currentDate.setBounds(132, 437, 275, 19);
        contentPane.add(currentDate);
        currentDate.setColumns(10);
    }

    private void refreshPeople(){
        peopleListModel.clear();
        personBox.removeAllItems();
        personBox1.removeAllItems();
        personBox2.removeAllItems();
        personBox1.addItem("All");
        personBox2.addItem("All");
        for(Person p : personService.getPersons()){
            personBox.addItem(p);
            personBox1.addItem(p);
            personBox2.addItem(p);
            peopleListModel.addElement(p.toString());
        }
        peopleList.setModel(peopleListModel);
    }

    private void refreshEvents(){
        eventsListModel.clear();
        eventBox.removeAllItems();
        for(Event e : eventService.getEvents()){
            eventBox.addItem(e);
            eventsListModel.addElement(e.toString());
        }
        eventList.setModel(eventsListModel);
    }

    private void refreshInstalments(){
        instalmentsListModel.clear();
        instalmentBox.removeAllItems();
        for(Instalment i : instalmentService.getInstallments()){
            instalmentBox.addItem(i);
            instalmentsListModel.addElement(i.toString());
        }
        instalmentsList.setModel(instalmentsListModel);
    }

    private void refreshPayments(){
        paymentsListModel.clear();
        for(Payment p : paymentService.getPayments()){
            paymentsListModel.addElement(p.toString());
        }
        paymentsList.setModel(paymentsListModel);
    }

    public void setDate(LocalDate date){
        currentDate.setText(date.toString());
        currentDate.repaint();
    }

    public void refreshPanel(){
        refreshEvents();
        refreshInstalments();
        refreshPayments();
        refreshPeople();
    }

    private List<String[]> getCSV(String path) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(";"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
