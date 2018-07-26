package gr.foodNhealth.controller;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Nutritionist;
import gr.foodNhealth.model.Preference;
import gr.foodNhealth.model.projection.ClientSafeProjection;
import gr.foodNhealth.repository.ClientRepository;
import gr.foodNhealth.repository.NutritionistRepository;
import gr.foodNhealth.security.LoginAttemptService;
import gr.foodNhealth.service.ClientService;
import gr.foodNhealth.service.NutritionistService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@BasePathAwareController
public class NutritionistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NutritionistController.class);

    @Autowired
    private NutritionistRepository nutritionistRepository;

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
            return new ResponseEntity<PersistentEntityResource>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }
}
