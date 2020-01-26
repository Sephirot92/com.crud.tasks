package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldReturnTasksListTest() {
        //Given
        Task taskOne = new Task(1L, "Test", "test");
        Task taskTwo = new Task(2L, "Testing", "testing");

        List<Task> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);

        dbService.saveTask(taskOne);
        dbService.saveTask(taskTwo);
        //When
        when(taskRepository.findAll()).thenReturn(taskList);

        int numberOfTasks = dbService.getAllTasks().size();

        //Then
        Assert.assertEquals(2, numberOfTasks);
    }

    @Test
    public void shouldReturnEmptyList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        //When
        when(dbService.getAllTasks()).thenReturn(taskList);

        int numberOfTasks = dbService.getAllTasks().size();
        //Then
        Assert.assertEquals(0, numberOfTasks);
    }

    @Test
    public void shouldReturnTaskById() {
        //Given
        List<Optional<Task>> taskList = new ArrayList<>();
        Task taskOne = new Task(1L, "One", "one");
        Task taskTwo = new Task(2L, "Two", "two");
        Task taskThree = new Task(3L, "Three", "three");
        taskList.add(Optional.of(taskOne));
        taskList.add(Optional.of(taskTwo));
        taskList.add(Optional.of(taskThree));

        //When
        when(dbService.getTaskById(1L)).thenReturn(taskList.get(0));
        Task taskTest = dbService.getTaskById(1L).get();
        String title = taskTest.getTitle();
        String content = taskTest.getContent();
        //Then
        Assert.assertEquals("One", title);
        Assert.assertEquals("one", content);
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task taskOne = new Task(1L, "Test", "test");

        List<Task> taskList = new ArrayList<>();
        taskList.add(taskOne);

        //When

        when(dbService.getAllTasks()).thenReturn(taskList);
        int numberOfTasks = dbService.getAllTasks().size();

        //Then
        Assert.assertEquals(1, numberOfTasks);
    }

    @Test
    public void shouldDeleteTask() {
        //Given

        //When
        dbService.deleteTask(1L);

        //Then
        verify(taskRepository, times(1)).deleteById(1L);
    }
}
