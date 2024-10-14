package com.baeldung.lju.persistence.repository.impl;

import static java.util.List.copyOf;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.persistence.repository.TaskRepository;

public class InMemoryTaskRepository implements TaskRepository {

    private Set<Task> tasks;

    public InMemoryTaskRepository() {
        super();
        this.tasks = new HashSet<>();
    }

    public InMemoryTaskRepository(Set<Task> tasks) {
        super();
        this.tasks = tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream()
            .filter(p -> p.getId()
                .equals(id))
            .findFirst();
    }

    @Override
    public Task save(Task task) {
        Long taskId = task.getId();
        if (taskId == null) {
            task.setId(new Random().nextLong(Long.MAX_VALUE));
        } else {
            findById(taskId).ifPresent(tasks::remove);
        }
        tasks.add(task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return copyOf(tasks);
    }

    @Override
    public List<Task> findByNameContainingAndAssigneeId(String name, Long assigneeId) {// @formatter:off
        return tasks.stream()
            .filter(p -> 
                (name == null || p.getName().contains(name))
                && (assigneeId == null || Optional.ofNullable(p.getAssignee()).map(Worker::getId).map(assigneeId::equals).orElse(false)))
            .toList();
    }// @formatter:on
}
