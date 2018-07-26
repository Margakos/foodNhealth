package gr.foodNhealth.listener;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Client.class)
public class ClientEventHandler {

    @Autowired
    private ClientService clientService;

    @HandleBeforeCreate
    public void handleBeforeCreate (Client client) {
        client = clientService.initNewClient(client);
    }
}
