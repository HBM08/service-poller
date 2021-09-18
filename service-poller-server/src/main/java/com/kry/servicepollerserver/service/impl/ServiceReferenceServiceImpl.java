package com.kry.servicepollerserver.service.impl;

import com.kry.servicepollerserver.model.ServiceReference;
import com.kry.servicepollerserver.repository.ServiceReferenceRepository;
import com.kry.servicepollerserver.service.ServiceReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceReferenceServiceImpl implements ServiceReferenceService {

    @Autowired
    private ServiceReferenceRepository serviceReferenceRepository;

    @Override
    public Flux<ServiceReference> getAll() {
        return serviceReferenceRepository.findAll();
    }

    @Override
    public Mono<ResponseEntity<ServiceReference>> getById(String id) {
        return serviceReferenceRepository.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ServiceReference> create(ServiceReference serviceReference) {
        return serviceReferenceRepository.save(serviceReference);
    }

    @Override
    public Mono<ResponseEntity<ServiceReference>> update(String id, ServiceReference serviceReference) {
        return serviceReferenceRepository.findById(id)
                .flatMap(existingService -> {
                    existingService.setServiceName(serviceReference.getServiceName());
                    existingService.setServiceURL(serviceReference.getServiceURL());
                    return serviceReferenceRepository.save(existingService);
                })
                .map(updatedService -> ResponseEntity.ok(updatedService))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Void> deleteAll() {
        return serviceReferenceRepository.deleteAll();
    }

    @Override
    public Mono<ResponseEntity<Void>> delete(String id) {
        return serviceReferenceRepository.findById(id)
                .flatMap(existingService ->
                        serviceReferenceRepository.delete(existingService))
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
