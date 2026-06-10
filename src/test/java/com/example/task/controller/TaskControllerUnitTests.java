package com.example.task.controller;

import com.example.task.service.Task;
import com.example.task.service.TaskService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerUnitTests {

    @Autowired
    private MockMvc mockMvc; // Object used to make HTTP request on our API

    @MockitoBean
    private TaskService taskService; // Mock object TaskService automatically injected in TaskController instance

    @Test
    void hello_should_return_message() throws Exception {
        mockMvc.perform(get("/tasks/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to the Task Manager API!"));
    }

    @Test
    void getTasks_should_return_tasks_list() throws Exception {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");

        when(taskService.getTasks()).thenReturn(List.of(task1, task2));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Task 1"))
                .andExpect(jsonPath("$[0].status").value("EN_COURS"))
                .andExpect(jsonPath("$[1].description").value("Task 2"))
                .andExpect(jsonPath("$[1].status").value("EN_COURS"));
    }
}