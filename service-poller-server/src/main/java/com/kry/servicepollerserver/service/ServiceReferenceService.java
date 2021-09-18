package com.kry.servicepollerserver.service;

import com.kry.servicepollerserver.model.ServiceReference;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceReferenceService {

    Flux<ServiceReference> getAll();

    Mono<ResponseEntity<ServiceReference>> getById(String id);

    Mono<ServiceReference> create(ServiceReference serviceReference);

    Mono<ResponseEntity<ServiceReference>> update(String id, ServiceReference serviceReference);

    Mono<Void> deleteAll();

    Mono<ResponseEntity<Void>> delete(String id);
}
