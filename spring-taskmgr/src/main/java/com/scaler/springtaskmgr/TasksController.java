package com.scaler.springtaskmgr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TasksController {
    private final List<Task> taskList;
    private AtomicInteger taskId = new AtomicInteger(0);

    public TasksController() {
        taskList = new ArrayList<>();
        taskList.add(new Task(taskId.incrementAndGet(), "Task 1", "Description 1", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 2", "Description 2", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 3", "Description 3", new Date()));
    }

    @GetMapping("/tasks")
    List<Task> getTasks() {
        return taskList;
    }

    @PostMapping("/tasks")
    Task createTask(@RequestBody Task task) {
        var newTask = new Task(taskId.incrementAndGet(), task.getTitle(), task.getDescription(), task.getDueDate());
        taskList.add(newTask);
        return newTask;
    }
}
