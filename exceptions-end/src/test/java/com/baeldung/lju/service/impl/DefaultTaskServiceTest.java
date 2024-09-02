package com.baeldung.lju.service.impl;

import com.baeldung.lju.domain.model.Task;
import com.baeldung.lju.persistence.repository.impl.InMemoryTaskRepository;
import com.baeldung.lju.service.TaskService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultTaskServiceTest {

    TaskService taskService = new DefaultTaskService(new InMemoryTaskRepository());

    @Test
    void givenId_whenCreateTask_shouldThrowAnException() {
        // given
        Task newTask = new Task();
        newTask.setId(1L);
        newTask.setName("Important task");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> taskService.create(newTask));
    }

    @Test
    void givenId_whenCreateTask_shouldThrowAnExceptionWithMessage() {
        // given
        Task newTask = new Task();
        newTask.setId(1L);
        newTask.setName("Important task");

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> taskService.create(newTask));

        // then
        assertEquals(exception.getMessage(), "Can't create Task with assigned 'id'");
    }
}