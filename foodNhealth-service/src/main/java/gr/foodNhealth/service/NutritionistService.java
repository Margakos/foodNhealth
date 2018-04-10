package gr.foodNhealth.service;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Nutritionist;
import gr.foodNhealth.repository.NutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class NutritionistService {

    @Autowired
    private NutritionistRepository nutritionistRepository;

    @Transactional
    public boolean addClient (Client client) {
        String activeNutritionistEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Nutritionist nutritionist = nutritionistRepository.findByEmail(activeNutritionistEmail);
        nutritionist.getClients().size();
        Collection<Client> clients = nutritionist.getClients();
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (clients.contains(client)) {
            return false;
        }
        clients.add(client);
        nutritionist.setClients(clients);
        nutritionistRepository.save(nutritionist);
        return true;
    }

    @Transactional
    public boolean removeClient (Client client) {
        String activeNutritionistEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Nutritionist nutritionist = nutritionistRepository.findByEmail(activeNutritionistEmail);
        nutritionist.getClients().size();
        Collection<Client> clients = nutritionist.getClients();
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (!clients.contains(client)) {
            return false;
        }
        clients.remove(client);
        nutritionist.setClients(clients);
        nutritionistRepository.save(nutritionist);
        return true;
    }
}
