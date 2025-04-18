package com.baeldung.lju.service.impl;

import java.util.Optional;

import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.persistence.repository.WorkerRepository;
import com.baeldung.lju.persistence.repository.impl.InMemoryWorkerRepository;
import com.baeldung.lju.service.WorkerService;

public class DefaultWorkerService implements WorkerService {

    private WorkerRepository workerRepository;

    public DefaultWorkerService(WorkerRepository workerRepository) {
        super();
        this.workerRepository = workerRepository;
    }

    public DefaultWorkerService() {
        super();
        this.workerRepository = new InMemoryWorkerRepository();
    }

    @Override
    public Optional<Worker> findById(Long id) {
        return workerRepository.findById(id);
    }

    @Override
    public Worker create(Worker worker) {
        if (worker.getId() != null) {
            throw new IllegalArgumentException("Can't create Worker with assigned 'id'");
        }
        return workerRepository.save(worker);
    }

    @Override
    public Optional<Worker> updateWorker(Long id, Worker worker) {
        return workerRepository.findById(id)
            .map(base -> updateFields(base, worker))
            .map(workerRepository::save);
    }

    private Worker updateFields(Worker base, Worker updatedWorker) {
        base.setFirstName(updatedWorker.getFirstName());
        base.setLastName(updatedWorker.getLastName());
        return base;
    }
}
