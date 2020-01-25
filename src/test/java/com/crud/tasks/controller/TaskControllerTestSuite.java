package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {

    @InjectMocks
    TaskController taskController;

    @Mock
    DbService dbService;

    @Mock
    TaskMapper taskMapper;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getTasksTest() {
        //Given
        List<TaskDto> tasksDtosList = new ArrayList<>();
        Task task = new Task(1L, "One", "one");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        tasksDtosList.add(taskDto);
        when(taskController.getTasks()).thenReturn(tasksDtosList);
        //Then
        Assert.assertNotNull(taskController.getTasks().size());
    }

    @Test
    public void deleteTaskIsInvokedTest() {
        //Given

        //When
        taskController.deleteTask(1L);
        //Then
        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    public void updateTaskIsInvokedTest() {
        //Given

        //When
        taskController.updateTask(null);
        //Then
        verify(dbService, times(1)).saveTask(null);
    }

    @Test
    public void createTaskIsInvoked() {
        //Given

        //When
        taskController.createTask(null);
        //Then
        verify(dbService, times(1)).saveTask(null);
    }
}
