package com.baeldung.lju.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.parallel.ResourceLock;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.domain.model.TaskStatus;

class InMemoryTaskRepositoryUnitTest {

    static final InMemoryTaskRepository taskRepository = new InMemoryTaskRepository(new HashSet<>());

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        System.out.printf("[%s] %s%n", Thread.currentThread()
            .getName(), testInfo.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.printf("[%s] FINISHED %s%n", Thread.currentThread()
            .getName(), testInfo.getDisplayName());
    }

    @Test
    @ResourceLock("task-repository")
    void givenExistingTask_whenUpdatingItsName_thenTaskCanBeFoundByTheNewName() {
        // given
        Task originalTask = aTask("task-1");
        taskRepository.save(originalTask);

        // when
        originalTask.setName("updated-task-1");
        taskRepository.save(originalTask);

        // then
        List<Task> tasks = taskRepository.findByNameContainingAndAssigneeId("updated-task-1", null);
        assertEquals(1, tasks.size());
    }


    @Test
    @ResourceLock("task-repository")
    void givenExistingTask_whenUpdatingItsDescription_thenTaskWasUpdated() {
        // given
        Task originalTask = aTask("task-2");
        taskRepository.save(originalTask);

        // when
        originalTask.setDescription("updated description");
        taskRepository.save(originalTask);

        // then
        Task updatedTask = taskRepository.findById(originalTask.getId())
            .get();
        assertEquals("updated description", updatedTask.getDescription());
    }

    static Task aTask(String name) {
        return new Task(name, "description", LocalDate.now(), new Campaign(), TaskStatus.TO_DO, null);
    }

}
