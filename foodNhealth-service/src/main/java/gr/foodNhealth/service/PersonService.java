package gr.foodNhealth.service;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Person;
import gr.foodNhealth.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void addClient (Client client) {
        String activePersonEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = personRepository.findByEmail(activePersonEmail);
        person.getClients().size();
        Collection<Client> clients = person.getClients();
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (clients.contains(client)) {
            return;
        }
        clients.add(client);
        person.setClients(clients);
        personRepository.save(person);
    }

    @Transactional
    public void removeClient (Client client) {
        String activePersonEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = personRepository.findByEmail(activePersonEmail);
        person.getClients().size();
        Collection<Client> clients = person.getClients();
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (!clients.contains(client)) {
            return;
        }
        clients.remove(client);
        person.setClients(clients);
        personRepository.save(person);
    }
}
