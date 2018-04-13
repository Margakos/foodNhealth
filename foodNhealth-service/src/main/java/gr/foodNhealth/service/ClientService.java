package gr.foodNhealth.service;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.core.event.BeforeSaveEvent;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Client initNewClient (Client client) {
        try {
            publisher.publishEvent(new BeforeSaveEvent(client.getPreference()));
            publisher.publishEvent(new BeforeSaveEvent(client));
            clientRepository.save(client);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DataIntegrityViolationException("Duplicate Violation");
        }
        return client;
    }
}
