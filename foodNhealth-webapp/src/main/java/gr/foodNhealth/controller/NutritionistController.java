package gr.foodNhealth.controller;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Nutritionist;
import gr.foodNhealth.repository.ClientRepository;
import gr.foodNhealth.repository.NutritionistRepository;
import gr.foodNhealth.security.LoginAttemptService;
import gr.foodNhealth.service.NutritionistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@BasePathAwareController
public class NutritionistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NutritionistController.class);

    @Autowired
    private NutritionistRepository nutritionistRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @PostMapping(value = "/login")
    @Transactional
    public ResponseEntity<?> logInPost(@RequestBody Nutritionist user, PersistentEntityResourceAssembler persistentEntityResourceAssembler) {
        String clientIP = loginAttemptService.getClientIP();
        if (loginAttemptService.isBlocked(clientIP)) {
            LOGGER.error("User blocked, email/IP: {}/{}", user.getEmail(), clientIP);
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); // 404 is the most 'generic' response to return for login end-points, even GitHub does it this way
        }

        Nutritionist nutritionist = nutritionistRepository.findByEmailAndPasswordAndIsActive(user.getEmail(), user.getPassword(), true);

        if (nutritionist != null) {
            LOGGER.info("Login attempt successful for Email: {}", user.getEmail());
            loginAttemptService.loginSucceeded(clientIP);
            PersistentEntityResource resource = persistentEntityResourceAssembler.toResource(nutritionist);
            return new ResponseEntity<PersistentEntityResource>(
                    resource, HttpStatus.OK);
        } else {
            LOGGER.error("Login attempt failed for Email: {}", user.getEmail());
            loginAttemptService.loginFailed(clientIP);
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/refreshUser")
    @Transactional(readOnly = true)
    public ResponseEntity<?> refreshLoggedInUser(Principal principal,
                                                 PersistentEntityResourceAssembler persistentEntityResourceAssembler) {
        Nutritionist nutritionist = nutritionistRepository.findByEmail(principal.getName());
        if (nutritionist != null) {
            PersistentEntityResource resource = persistentEntityResourceAssembler.toResource(nutritionist);
            return new ResponseEntity<PersistentEntityResource>(
                    resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "nutritionists/addClient")
    public ResponseEntity<?> addClient (@RequestBody Client client) {
        if (client == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        boolean added = nutritionistService.addClient(client);
        return added ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "nutritionists/removeClient")
    public ResponseEntity<?> removeClient (@RequestParam(value = "email") String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        boolean removed = nutritionistService.removeClient(client);
        return removed ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
    }
}
