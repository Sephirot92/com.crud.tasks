package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;
    @Test
    public void shouldReturnTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = taskMapper.mapToTask(taskDto);

        //When
        Long id = task.getId();
        String title = task.getTitle();
        String content = task.getContent();

        //Then
        assertEquals((Long) 1L, (Long) id);
        assertEquals("Title", title);
        assertNotEquals("Stupid_content", content);
    }

    @Test
    public void shouldReturnTaskDto(){
        //Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //When
        Long id = taskDto.getId();
        String title = taskDto.getTitle();
        String content = taskDto.getContent();

        //Then
        assertEquals((Long) 1L, (Long) id);
        assertEquals("Title", title);
        assertEquals("Content", content);
    }

    @Test
    public void shouldReturnTaskDtoList(){
        //Given
        Task task0 = new Task(1L, "Title", "Content1");
        Task task1 = new Task(2L, "Title", "Content2");
        Task task2 = new Task(3L, "Title", "Content3");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task0);
        taskList.add(task1);
        taskList.add(task2);

        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //When
        Long firstTaskId = taskDtos.get(0).getId();
        Long secondTaskId = taskDtos.get(1).getId();
        Long thirdTaskId = taskDtos.get(2).getId();

        //Then
        assertEquals((Long)1L, firstTaskId);
        assertEquals((Long)2L, secondTaskId);
        assertEquals((Long)3L, thirdTaskId);
        assertEquals(3, taskDtos.size());
    }
}
