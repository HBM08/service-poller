package com.kry.servicepollerserver.controller;

import com.kry.servicepollerserver.model.ServiceReference;
import com.kry.servicepollerserver.model.StatusEvent;
import com.kry.servicepollerserver.service.ServiceReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/services")
public class ServiceReferenceController {

    @Autowired
    private ServiceReferenceService serviceReferenceService;

    @GetMapping
    public Flux<ServiceReference> getAllServices() {
        return serviceReferenceService.getAll();
    };

    @GetMapping("{id}")
    public Mono<ResponseEntity<ServiceReference>> getService(@PathVariable String id) {
        return serviceReferenceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ServiceReference> saveService(@RequestBody ServiceReference serviceReference) {
        return serviceReferenceService.create(serviceReference);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ServiceReference>> updateService(@PathVariable(value = "id") String id,
            @RequestBody ServiceReference serviceReference) {
        return serviceReferenceService.update(id, serviceReference);
    }

    @DeleteMapping
    public Mono<Void> deleteAllServices() {
        return serviceReferenceService.deleteAll();
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteService(@PathVariable(value = "id") String id) {
        return serviceReferenceService.delete(id);
    }

    @GetMapping(value = "/sse/status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public Flux<StatusEvent> getServiceStatuses() {
        // TODO: here you should replace with a service that will use WebClient to fetch status from real services
        return getAllServices().flatMap(serviceReference -> Flux.just(new StatusEvent(serviceReference.getId(), "OK")));
    }
}
