package com.scaler.springtaskmgr.controllers;

import com.scaler.springtaskmgr.entities.Task;
import com.scaler.springtaskmgr.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {
    private TasksService tasksService;

    public TasksController(@Autowired TasksService tasksService) {
        this.tasksService = tasksService;
    }

    /**
     * Show all existing tasks
     * GET /tasks
     *
     * @return List of tasks
     */
    @GetMapping("/tasks")
    List<Task> getTasks() {
        return tasksService.getTasks();
    }

    /**
     * Create a new task
     * POST /tasks
     * Body:
     * <pre>
     *      {
     *          "title": "Task 4",
     *          "description": "Description 4",
     *          "dueDate": "2021-01-01"
     *      }
     *  </pre>
     *
     * @param task Task object sent by client
     * @return Task object created
     */
    @PostMapping("/tasks")
    Task createTask(@RequestBody Task task) {
        var newTask = tasksService.createTask(task.getTitle(), task.getDescription(), task.getDueDate().toString());
        return newTask;
    }

    /**
     * Get a task by id
     *
     * @param id
     * @return Task object
     */
    @GetMapping("/tasks/{id}")
    Task getTask(@PathVariable("id") Integer id) {
        return tasksService.getTaskById(id);
        // TODO: BONUS: Return 404 if task not found
    }

    /**
     * Delete a task by given id
     *
     * @param id Task id to delete
     * @return the deleted task
     */
    @DeleteMapping("/tasks/{id}")
    Task deleteTask(@PathVariable("id") Integer id) {
        return tasksService.deleteTask(id);
        // TODO: BONUS: Return 404 if task not found

    }

    /**
     * Update a task by given id
     *
     * @param id   Task id to update
     * @param task Task object sent by client
     * @return the updated task
     */
    @PatchMapping("/tasks/{id}")
    Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        var updatedTask = tasksService.updateTask(
                id,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate().toString()
        );
        return updatedTask;
    }
}
