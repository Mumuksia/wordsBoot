package com.muksia.repository;

import com.muksia.model.Scheduler;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchedulerRepository extends MongoRepository<Scheduler, String> {
}
