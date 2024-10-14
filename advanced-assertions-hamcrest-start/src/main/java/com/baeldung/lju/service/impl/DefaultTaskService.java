package com.baeldung.lju.service.impl;

import java.util.List;
import java.util.Optional;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.persistence.repository.TaskRepository;
import com.baeldung.lju.persistence.repository.impl.InMemoryTaskRepository;
import com.baeldung.lju.service.TaskService;

public class DefaultTaskService implements TaskService {

    private TaskRepository taskRepository;

    public DefaultTaskService(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    public DefaultTaskService() {
        super();
        this.taskRepository = new InMemoryTaskRepository();
    }

    @Override
    public List<Task> searchTasks(String nameSubstring, Long assigneeId) {
        return taskRepository.findByNameContainingAndAssigneeId(nameSubstring, assigneeId);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task create(Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Can't create Task with assigned 'id'");
        }
        task.setStatus(TaskStatus.TO_DO);
        task.setAssignee(null);
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> updateTask(Long id, Task task) {
        return taskRepository.findById(id)
            .map(base -> updateFields(base, task))
            .map(taskRepository::save);
    }

    private Task updateFields(Task base, Task updatedTask) {
        base.setName(updatedTask.getName());
        base.setDescription(updatedTask.getDescription());
        base.setDueDate(updatedTask.getDueDate());
        base.setStatus(updatedTask.getStatus());
        base.setAssignee(updatedTask.getAssignee());
        base.setCampaign(updatedTask.getCampaign());
        return base;
    }

    @Override
    public Optional<Task> updateStatus(Long id, TaskStatus status) {
        return taskRepository.findById(id)
            .map(base -> {
                base.setStatus(status);
                return taskRepository.save(base);
            });
    }

    @Override
    public Optional<Task> updateAssignee(Long id, Worker assignee) {
        return taskRepository.findById(id)
            .map(base -> {
                base.setAssignee(assignee);
                return taskRepository.save(base);
            });
    }
}
