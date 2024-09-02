package com.baeldung.lju.persistence.repository.impl;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.domain.model.Worker;
import com.baeldung.lju.persistence.repository.TaskRepository;

class InMemoryTaskRepositoryTest {

    @Test
    void givenSomeTasks_whenFindByNameContainingAndAssigneeId_thenReturnFilteredTasks() {
        // given
        Worker assignee = new Worker("jd@gmail.com", "John", "Doe");
        assignee.setId(100L);

        var task1 = new Task("BACKEND-1001", "description 1", now(), new Campaign(), TaskStatus.TO_DO, assignee);
        var task2 = new Task("BACKEND-1002", "description 2", now(), new Campaign(), TaskStatus.TO_DO, assignee);
        var task3 = new Task("FRONTEND-1003", "description 3", now(), new Campaign(), TaskStatus.TO_DO, assignee);

        // and
        TaskRepository repo = new InMemoryTaskRepository();
        repo.save(task1);
        repo.save(task2);
        repo.save(task3);

        // when
        List<Task> tasks = repo.findByNameContainingAndAssigneeId("BACKEND", 100L);

        // then
        assertThat(tasks).hasSize(2)
            .allMatch(task -> task.getAssignee().getId() == 100L)
            .noneMatch(task -> task.getName().contains("FRONTEND"))
            .containsExactlyInAnyOrder(task1, task2);
    }

}