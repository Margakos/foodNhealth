package gr.foodNhealth.service;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Preference;
import gr.foodNhealth.repository.ClientRepository;
import gr.foodNhealth.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Client initNewClient (Client client) {
        Preference preference = new Preference();
        preference.setClient(client);
        publisher.publishEvent(new BeforeCreateEvent(preference));
        preferenceRepository.save(preference);

        return client;
    }
}
