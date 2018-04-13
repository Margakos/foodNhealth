package gr.foodNhealth.controller;

import gr.foodNhealth.model.Client;
import gr.foodNhealth.model.Preference;
import gr.foodNhealth.model.projection.ClientSafeProjection;
import gr.foodNhealth.repository.ClientRepository;
import gr.foodNhealth.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.rest.core.event.BeforeSaveEvent;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PagedResourcesAssembler<ClientSafeProjection> assembler;

    @Autowired
    private ProjectionFactory factory;

    @GetMapping("/search/searchByQuery")
    public ResponseEntity<?> findCountByAssignmentProcesses(@RequestParam("query") String query, Pageable pageable) {
        query = query.replace("+", " ");
        Page<Client> clients = clientRepository.searchByQuery(query, pageable);
        Page<ClientSafeProjection> projected = clients.map(c -> factory.createProjection(ClientSafeProjection.class, c));
        PagedResources<Resource<ClientSafeProjection>> resources = assembler.toResource(projected);
        return ResponseEntity.ok(resources);
    }
}
