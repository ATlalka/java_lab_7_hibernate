package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.Event;
import pl.edu.pwr.classfinancemanager.data.repositories.EventRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public Event createEvent(String args[]) {
        try {
            args[2] = args[2].replace(".","/");
            Event.EventBuilder builder = Event.builder();
            Event event = builder.name(args[0])
                    .place(args[1])
                    .date(CustomDate.parse(args[2]))
                    .build();
            eventRepository.saveAndFlush(event);
            return event;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

}

