package gr.foodNhealth.controller;

import gr.foodNhealth.model.Person;
import gr.foodNhealth.repository.PersonRepository;
import gr.foodNhealth.security.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@BasePathAwareController
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @PostMapping(value = "/login")
    @Transactional
    public ResponseEntity<?> logInPost(@RequestBody Person user, PersistentEntityResourceAssembler persistentEntityResourceAssembler) {
        String clientIP = loginAttemptService.getClientIP();
        if (loginAttemptService.isBlocked(clientIP)) {
            LOGGER.error("User blocked, email/IP: {}/{}", user.getEmail(), clientIP);
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); // 404 is the most 'generic' response to return for login end-points, even GitHub does it this way
        }

        Person person = personRepository.findByEmailAndPasswordAndIsActive(user.getEmail(), user.getPassword(), true);

        if (person != null) {
            LOGGER.info("Login attempt successful for Emai: {}", user.getEmail());
            loginAttemptService.loginSucceeded(clientIP);
            PersistentEntityResource resource = persistentEntityResourceAssembler.toResource(person);
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
        Person person = personRepository.findByEmail(principal.getName());
        if (person != null) {
            PersistentEntityResource resource = persistentEntityResourceAssembler.toResource(person);
            return new ResponseEntity<PersistentEntityResource>(
                    resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }
}
