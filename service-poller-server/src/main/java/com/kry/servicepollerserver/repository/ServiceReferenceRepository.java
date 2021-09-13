package com.kry.servicepollerserver.repository;

import com.kry.servicepollerserver.model.ServiceReference;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ServiceReferenceRepository extends ReactiveMongoRepository<ServiceReference, String> {
}
