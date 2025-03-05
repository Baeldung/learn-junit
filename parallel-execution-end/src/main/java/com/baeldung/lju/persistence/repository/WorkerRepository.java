package com.baeldung.lju.persistence.repository;

import java.util.Optional;

import com.baeldung.lju.domain.model.Worker;

public interface WorkerRepository {

    Optional<Worker> findById(Long id);

    Worker save(Worker worker);

}
